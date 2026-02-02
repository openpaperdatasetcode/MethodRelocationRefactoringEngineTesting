package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.Objects;
/**
Generic record source class with required features
@param <T> type parameter (source feature)*/public record SourceRecord<T>(T data, protected String outerProtectedField) { // access_outer_protected feature// Source feature: local inner classpublic void sourceWithLocalInner() {class SourceLocalInnerClass {void localMethod() {}}new SourceLocalInnerClass().localMethod();}
// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous inner class");}};anonymous.run();}
/**
Varargs method to be refactored (public, return base type int)
@param targetParam target record parameter (per_condition)
@param varargs varargs parameters
@return int base type result*/public int refactorTargetMethod(TargetRecord<String> targetParam, String... varargs) {// NullPointerException (explicit check)Objects.requireNonNull(targetParam, "Target parameter cannot be null");
// Constructor invocation (target record constructor)TargetRecord<String> newTarget = new TargetRecord<>("constructor_arg", "protected_field");
// Variable callTargetRecord<String> tempTarget = targetParam;String targetData = tempTarget.data();
// Access outer protected field (access_outer_protected)String protectedData = this.outerProtectedField();
// Used by reflectiontry {Method reflectMethod = SourceRecord.class.getDeclaredMethod("refactorTargetMethod", TargetRecord.class, String[].class);reflectMethod.invoke(this, targetParam, (Object) varargs);} catch (Exception e) {// No new exception thrown}
// Return base type (int)return targetData.length() + protectedData.length() + varargs.length;}
/**
Call method: source type, default modifier, pos in while loop
@param subSource subclass instance for overriding
@param target target record parameter
@return TargetRecord instance
*/
default TargetRecord<String> callSourceOverriddenMethod(
SubSourceRecord<String> subSource, TargetRecord<String> target
) {
TargetRecord<String> result = null;
int count = 0;
while (count < 2) { // while loop position
// Overriding feature + super.methodName(arguments)
subSource.overriddenMethod(target, "loop_arg_" + count);
result = target;
count++;
}
return result;
}
}
/**
Subclass of SourceRecord for call_method overriding feature
@param type parameter
*/
class SubSourceRecord extends SourceRecord {
public SubSourceRecord(U data, String outerProtectedField) {
super(data, outerProtectedField);
}
// Overriding parent class method (target_feature: overriding)@Overridepublic int refactorTargetMethod(TargetRecord<String> targetParam, String... varargs) {super.refactorTargetMethod(targetParam, varargs); // super.methodName(arguments)return varargs.length * 2;}}
/**
Generic target record: protected modifier, type parameter + anonymous inner class (target_feature)
@param <V> type parameter (target_feature)
*/
protected record TargetRecord<V>(V data, protected String protectedField) {
// Target feature: anonymous inner class (target_inner_rec)
public void invokeAnonymousInner() {
Runnable anonymous = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous inner class");
}
};
anonymous.run();
}
}