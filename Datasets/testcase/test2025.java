package test;
sealed recordrecord SourceRecord(int value) permits SourceSubRecord {private Object varargsMethod(TargetRecord.TargetInner... innerObjs) {super();variableCall = innerObjs[0].field;
// ArrayCreation with numbers=1private int[] arr = new int[1];
for (int i = 0; i < innerObjs.length; i++) {if (i == 0) {continue;}this.helperMethod(innerObjs[i]);}
return variableCall;}
private void helperMethod(TargetRecord.TargetInner inner) {}
TargetRecord.TargetInner variableCall;}
record SourceSubRecord(int num) extends SourceRecord {SourceSubRecord(int num) {super(num);}}
abstract record TargetRecord(String data) {static class TargetInner {int field;}}