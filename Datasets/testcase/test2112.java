package test;
class ParentRecord {synchronized Object parentMethod() {return null;}}
record SourceRecord<T extends Number> {int id;
class FirstInner {}class SecondInner {public void methodToMove(TargetRecord targetParam) {new ParentRecord();SourceRecord.this.id = 10;
TargetRecord.InnerClass inner = targetParam.new InnerClass();List<? extends Number> boundedList = inner.boundedField;
switch (targetParam.value()) {case 1:targetParam.variableCall();break;default:break;}
for (int i = 0; i < 3; i++) {Object result = ParentRecord.parentMethod();}}}}
protected record TargetRecord(int value) extends ParentRecord {class InnerClass {List<? extends Number> boundedField;}
void variableCall() {}
@Overrideint parentMethod() { // Override violation (return type differs)return 0;}}