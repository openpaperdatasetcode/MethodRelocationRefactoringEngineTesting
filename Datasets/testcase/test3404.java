package test;
import java.util.function.Function;
// Source record class (public, generic type parameter)public record SourceRecord<T>(T data) {// Instance code blocks (for call_method pos){OtherClass other = new OtherClass();Function<OtherClass, Object> func = other::instanceMethod;func.apply(other);}
// Varargs method (final access modifier, returns TargetClas Type)public final TargetRecord<T> varargsMethod(TargetRecord<T>... targetParams) {// Assert statementassert targetParams != null : "Target parameters cannot be null";
// Super keywords (record implicitly extends Record)super.toString();
// Access outer private (record component is implicitly private)T privateVal = this.data;
TargetRecord<T> result = null;for (TargetRecord<T> target : targetParams) {// Variable call + depends on inner classTargetRecord<T>.LocalInner targetInner = target.createLocalInner();targetInner.variableCall(privateVal);
result = target;}
// Instance feature (1, others_class, instance, instanceReference::methodName)OtherClass otherInst = new OtherClass();Object instanceResult = otherInst.instanceMethod();
return result;}}
// Target record class (abstract, with local inner class)abstract record TargetRecord(U value) {
// Local inner class (target_feature)
public LocalInner createLocalInner() {
class LocalInner {
public void variableCall(U data) {}
}
return new LocalInner();
}
}
// Others class for method feature dependencyclass OtherClass {public Object instanceMethod() {return new Object();}}