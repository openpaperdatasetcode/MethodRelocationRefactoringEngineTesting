package test;
sealed class ParentRecord permits SourceRecord, TargetRecord {protected String superField = "parentField";}
sealed record SourceRecord(String sourceField) extends ParentRecord permits SourceSubRecord {static class SourceStaticNested {}
public TargetRecord instanceMethod(TargetRecord target) {class SourceLocalInner {}volatile int count = 0;
synchronized (this) {while (count < 5) {if (count == 3) {String superFieldVal = target.superField;break;}count++;}}
String varCall = target.targetField();int methodCall = target.new TargetInner().innerMethod();
try {if (varCall.isEmpty()) {throw new Exception();}} catch (Exception e) {}
return target;}}
non-sealed record SourceSubRecord(String sourceField) extends SourceRecord {SourceSubRecord(String sourceField) {super(sourceField);}}
sealed record TargetRecord(String targetField) extends ParentRecord permits TargetSubRecord {class TargetInner {int innerMethod() {return targetField.length();}}
void callInFunctionalInterface() {Runnable runnable = () -> {int result = TargetRecord.InnerHelper.callInnerMethod(this);};runnable.run();}
static class InnerHelper {static int callInnerMethod(TargetRecord target) {return target.new TargetInner().innerMethod();}}
void methodWithLocalClass() {class TargetLocalInner {}}}
non-sealed record TargetSubRecord(String targetField) extends TargetRecord {TargetSubRecord(String targetField) {super(targetField);}}
