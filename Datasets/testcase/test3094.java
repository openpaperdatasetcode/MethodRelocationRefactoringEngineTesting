package test;
private record TargetRecord(String targetField) {public TargetRecord {Runnable r = new Runnable() {@Overridepublic void run() {}};}}
public record SourceRecord(String sourceField) {class InnerClass {record InnerRec(String recField) {}
void methodToMove(TargetRecord target, String... varargs) {// Access outer private (record's implicit private component)String outerPrivate = sourceField;
// Constructor invocationInnerRec innerRec = new InnerRec(outerPrivate);TargetRecord newTarget = new TargetRecord(target.targetField());
// Variable call + Access instance fieldString var = target.targetField();String recVar = innerRec.recField();
// NullPointerExceptionif (var == null) {throw new NullPointerException("Target field is null");}
// Varargs processingfor (String arg : varargs) {System.out.println(arg);}}}}