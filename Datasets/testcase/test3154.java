package source;
import target.TargetClass;import java.lang.reflect.Constructor;import java.lang.reflect.Method;import java.io.IOException;import java.util.List;
interface TargetInterface {}
class ParentSourceClass {}
abstract class SourceClass extends ParentSourceClass {static class SourceStaticNested {}class MemberInner {}
protected int methodToMove(TargetClass target) throws IOException { // requires_throws// Variable callint var = target.targetField;TargetClass.TargetInner targetInner = target.new TargetInner();
// Depends on inner classMemberInner inner = new MemberInner();
// Enhanced for statementList<Integer> nums = List.of(1, 2, 3);for (int num : nums) {var += num;}
// Synchronized statementsynchronized (this) {var *= 2;}
// Used by reflectiontry {Constructor<TargetClass> constructor = TargetClass.class.getDeclaredConstructor();constructor.setAccessible(true);TargetClass reflected = constructor.newInstance();var += reflected.targetField;
Method targetMethod = TargetClass.class.getDeclaredMethod("innerMethod");targetMethod.setAccessible(true);targetMethod.invoke(target);} catch (Exception e) {throw new IOException("Reflection failed", e); // requires_throws}
// Exception throwing statements with call_methodtry {if (var < 0) {OtherClass.staticMethod(target::innerMethod); // instanceReference::methodNamethrow new IOException("Invalid value");}} catch (IllegalArgumentException e) {throw new IOException("Call failed", e);}
// Constructor parameter list with method_featureSourceStaticNested nested = new SourceStaticNested(OtherClass.subClassMethod(target));
return var;}
// Static nested class constructor (parameter list uses method_feature)static class SourceStaticNested {public SourceStaticNested(int param) {}}}
package target;
private class TargetClass implements TargetInterface { // target_feature: implementsint targetField;
class TargetInner {} // target_feature: member inner class
private void innerMethod() {}}
package source;
import target.TargetClass;import java.util.function.Consumer;
class OtherClass {// Sub_class constructor-related methodpublic static int subClassMethod(TargetClass target) {return target.targetField + 1;}
// Call_method: static methodpublic static void staticMethod(Consumer<TargetClass> consumer) {TargetClass target = new TargetClass();consumer.accept(target);}
// Sub_class (logical sub-class of TargetClass)static class TargetSubClass extends TargetClass {}}