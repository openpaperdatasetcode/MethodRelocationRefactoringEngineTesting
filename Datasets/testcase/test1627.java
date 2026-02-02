package test;
import java.lang.annotation.*;import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)@interface Processed {}
class ParentClass {public int overloadMethod(TargetClass.Inner inner) {return inner.counter;}}
public class SourceClass {public class MemberInner {public class InnerRec extends ParentClass {@Processedpublic static int process(TargetClass.Inner targetInner) {// Constructor invocationTargetClass target = new TargetClass();TargetClass.Inner newInner = target.new Inner();
// Overloading method from parent class in object initialization (1 occurrence)Object wrapper = new Object() {int getValue() {return instanceReference.overloadMethod(targetInner);}};
// Variable call - access target inner's fieldint total = targetInner.counter;
// Access instance fieldtargetInner.data = "processed";
// Used by reflectiontry {Method method = wrapper.getClass().getMethod("getValue");total += (int) method.invoke(wrapper);} catch (Exception e) {// no new exception}
return total;}
private ParentClass instanceReference = new ParentClass();}}}
non-sealed class TargetClass {public class Inner {public int counter;public String data;}}