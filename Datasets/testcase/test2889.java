
protected record TargetRecord(String targetField) {

class TargetInner {
void processField () {
System.out.println (targetField);
}
}
}
protected record SourceRecord (String sourceField) {static class SourceStaticNested {}
class SourceInner {}
protected int methodToMove (TargetRecord target) {if (target == null) {throw new NullPointerException ("null");}
TargetRecord.TargetInner targetInner = target.new TargetInner ();targetInner.processField ();
return target.targetField ().length ();}}