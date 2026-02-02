package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
sealed record SourceRecord(String data) extends ParentRecord permits SourceSubRecord {class SourceInner {@MyAnnotationprotected Object methodToMove(TargetRecord.InnerRec innerRec) throws Exception {TargetRecord target = new TargetRecord(1);super.toString();
Object raw = new ArrayList(); // Raw typeint field = innerRec.obj.field;
if (field == 1) {return new Object();}
overloadMethod(1);overloadMethod("str");
ParentRecord parent = new SourceRecord("data");parent.privateMethod(target);
return null;}
private void overloadMethod(int num) {}private void overloadMethod(String str) {}}}
final record SourceSubRecord(String data) extends SourceRecord {public SourceSubRecord(String data) {super(data);}}
abstract record ParentRecord() {private void privateMethod(TargetRecord target) {super.toString();}}
record TargetRecord(int value) {int field = value;
record InnerRec() {TargetRecord obj = new TargetRecord(1);
void createLocalInner() {class LocalInner {}}}}