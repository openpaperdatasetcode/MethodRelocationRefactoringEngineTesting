package test;
public record SourceRecord(String data) {class SourceMemberInner {}
private TargetRecord targetField = new TargetRecord("targetData");
public final void varargsMethod(TargetRecord... targetParams) {new Runnable() {@Overridepublic void run() {for (TargetRecord target : targetParams) {target.new TargetMemberInner().doAction();}}}.run();
super();labeled: {privateForStatement(targetParams[0]);
TargetRecord rawTarget = new TargetRecord("raw"); // Raw typerawTarget.new TargetMemberInner().field = "updated"; // Property assignment
OtherClass.publicInstanceMethod(rawTarget);break labeled;}
targetField.new TargetMemberInner().doAction(); // Variable callString fieldValue = targetField.new TargetMemberInner().field; // Access instance field}
private void privateForStatement(TargetRecord target) {for (int i = 0; i < 1; i++) {assert target.new TargetMemberInner().field != null : "Obj.field must not be null";}}}
public record TargetRecord(String data) {class TargetMemberInner {String field = "innerField";void doAction() {}}}
class OtherClass extends ParentOtherClass {public static Object publicInstanceMethod(TargetRecord target) {target.new TargetMemberInner().doAction();return super.parentMethod();}}
class ParentOtherClass {protected Object parentMethod() {return new Object();}}