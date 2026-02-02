package test;
public record SourceRecord<T>(T data) {class SourceInner {}
public void createLocalInner() {class LocalInnerSource {}}
strictfp public Object methodToMove(TargetRecord<T>.TargetInner... targets) {// ConstructorInvocation with this.field and count 1 (diff_package_others)for (TargetRecord<T>.TargetInner target : targets) {TargetRecord.TargetStatic nested = new TargetRecord.TargetStatic(target.this.field);}
// Constructor invocationTargetRecord<T>.TargetInner newInner = new TargetRecord<>(data).new TargetInner();
// Try statementtry {for (TargetRecord<T>.TargetInner target : targets) {// Variable call + access instance methodtarget.toString();target.innerMethod();// Contains target parameter (per_condition)T targetField = target.getFieldValue();}} finally {}
return targets;}}
record TargetRecord(U value) extends ParentTargetClass {
class TargetInner {
public U field = value; // this.field reference
{// Anonymous inner class (target_feature)new Runnable() {};}
public U getFieldValue() {return field;}
public void innerMethod() {}}
// Static nested classpublic static class TargetStatic {public TargetStatic(U field) {}}}
class ParentTargetClass {default <V> TargetRecord<TargetRecord<V>.TargetInner> callMethod(TargetRecord<V> target) {// Constructor + this.methodName(arguments) in expressionreturn new TargetRecord<>(target.new TargetInner().this.innerMethod()).new TargetRecord.TargetInner();}}