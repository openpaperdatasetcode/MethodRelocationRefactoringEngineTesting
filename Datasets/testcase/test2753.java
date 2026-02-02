package com.source;
import com.target.AbstractTargetClass;import java.lang.reflect.Method;import java.util.function.Supplier;
interface TestInterface {}
public class SourceClass implements TestInterface {// Anonymous inner class (source_feature)private Runnable anonymous = new Runnable() {@Overridepublic void run() {}};
// Member inner class (source_feature)public class SourceMemberInner {}
// Overload existspublic int methodToMove(AbstractTargetClass target) {return methodToMove(target, 0);}
// Overload existspublic int methodToMove(AbstractTargetClass target, int offset) {super(); // Super constructor invocation// Variable call + contains target parameter (per_condition)target.toString();
// Type declaration statement (raw_type)AbstractTargetClass.RawStaticNested rawNested = new AbstractTargetClass.RawStaticNested();
// Constructor invocationAbstractTargetClass.StaticNested staticNested = new AbstractTargetClass.StaticNested();
// Access instance methodint baseValue = target.calculateBaseValue();int result = baseValue + offset;
// Lambda expressions with overriding method callSupplier<AbstractTargetClass> targetSupplier = () -> new ConcreteTargetClass().overrideMethod();AbstractTargetClass supplierResult = targetSupplier.get();result += supplierResult.calculateBaseValue();
// Used by reflectiontry {Method method = AbstractTargetClass.class.getMethod("calculateBaseValue");result += (int) method.invoke(target);} catch (Exception e) {// No new exception}
return result;}
// Overriding method (source_feature)@Overridepublic synchronized AbstractTargetClass overrideMethod() {return new ConcreteTargetClass();}}
package com.target;
public abstract class AbstractTargetClass {// Static nested class (target_feature)public static class StaticNested {}
// Raw type static nested classpublic static class RawStaticNested {}
public abstract int calculateBaseValue();
// Overriding method templatepublic AbstractTargetClass overrideMethod() {return null;}}
package com.target;
public class ConcreteTargetClass extends AbstractTargetClass {@Overridepublic int calculateBaseValue() {return 10;}
@Overridepublic AbstractTargetClass overrideMethod() {return new ConcreteTargetClass();}}