package test;
interface TestInterface {}
public record SourceRecord<T>(T data) implements TestInterface {// Static nested class (source_feature)public static class StaticNested {}
// Member inner class (source_feature)public class MemberInner {}
// Overloading method 1default TargetRecord<T>.TargetInner.TargetRec methodToMove(TargetRecord<T> target) {return processTarget(target);}
// Overloading method 2default TargetRecord<T>.TargetInner.TargetRec methodToMove(TargetRecord<T> target, String arg) {TargetRecord<T>.TargetInner.TargetRec rec = processTarget(target);rec.arg = arg;return rec;}
private TargetRecord<T>.TargetInner.TargetRec processTarget(TargetRecord<T> target) {// Type declaration statementTargetRecord<T>.TargetInner inner = target.new TargetInner();TargetRecord<T>.TargetInner.TargetRec rec = inner.new TargetRec();
// Do statementdo {// TypeDeclarationStatement with obj.field (count 2, pos: diff_package_others)private T field1 = target.data();private T field2 = inner.innerField;
// Variable call + contains target parameter (per_condition)target.toString();inner.toString();rec.field = field1;} while (rec.field == null);
return rec;}}
record TargetRecord(U data) {
public class TargetInner {
public U innerField = data;
public class TargetRec {public U field;public String arg;}}}