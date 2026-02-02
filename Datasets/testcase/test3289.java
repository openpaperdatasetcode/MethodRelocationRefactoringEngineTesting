package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Supplier;
abstract class ParentTargetClass {}
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
protected record SourceRecord<T extends Comparable<T>>(T data) {@MethodAnnoprotected Object instanceMethod(TargetRecord<T> targetParam) {class SourceLocalInner {}new SourceLocalInner();
new Runnable() {@Overridepublic void run() {targetParam.new TargetStaticNested().doAction();}}.run();
// CreationReference (3 variables)Supplier<TargetRecord<T>> creator1 = TargetRecord::new;Supplier<TargetRecord<T>.TargetStaticNested> creator2 = targetParam::new TargetStaticNested;Supplier<SourceLocalInner> creator3 = SourceLocalInner::new;
privateThrowStatement(targetParam);
switch (targetParam.data().compareTo(data)) {case 0 -> targetParam.new TargetStaticNested().field = "equal"; // Property assignmentcase 1 -> targetParam.new TargetStaticNested().doAction(); // Variable calldefault -> this.publicNormalMethod(targetParam);}
return creator1.get();}
private void privateThrowStatement(TargetRecord<T> target) {if (target == null || target.new TargetStaticNested().field == null || target.data() == null) {throw new IllegalArgumentException("Three obj.fields must not be null");}}
public void publicNormalMethod(TargetRecord<T> target) {target.new TargetStaticNested().doAction();this.instanceMethod(target); // This.methodName(arguments)}}
public record TargetRecord<U extends Comparable>(U data) extends ParentTargetClass {
static class TargetStaticNested {
String field = "staticInnerField";
void doAction() {}
}
}
// Override violation (method signature mismatch)class ParentSourceClass {public Object instanceMethod(TargetRecord<String> target) {return new Object();}}
