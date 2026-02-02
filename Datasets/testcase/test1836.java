package test;
import java.util.ArrayList;import java.util.List;
@MyAnnotationpublic record SourceRecord(String info) {public class Member MemberInner {String getSourceInfo() {return SourceRecord.this.info();}}
private List<String> instanceMethod(TargetRecord target) {List<String> result = new ArrayList<>();
// Type declaration statementMemberInner inner = new MemberInner();rawList = new ArrayList(); // Raw type
// Expression statementString combined = inner.getSourceInfo() + target.data();result.add(combined);
// 2 abstract BooleanLiteralsabstract class BoolChecker {abstract boolean check1();abstract boolean check2();}BoolChecker checker = new BoolChecker() {@Overrideboolean check1() {return !target.data().isEmpty();}@Overrideboolean check2() {return info().length() > 5;}};result.add("Check1: " + checker.check1());result.add("Check2: " + checker.check2());
// Variable callresult.add("Target length: " + target.data().length());
// Uses outer thisresult.add("Outer info: " + SourceRecord.this.info());
// Empty statement;
// Local inner classclass LocalProcessor {void process() {rawList.add(target.data());rawList.add(info());}}new LocalProcessor().process();result.addAll(rawList);
return result;}
private ArrayList rawList;}
@interface MyAnnotation {}
/**
Target record class with javadoc
Contains local inner class in its component
*/
protected record TargetRecord(String data) {
{
// Local inner class in target
class DataValidator {
boolean isValid() {
return data != null && !data.isBlank();
}
}
if (!new DataValidator().isValid()) {
throw new IllegalArgumentException("Invalid data");
}
}
}