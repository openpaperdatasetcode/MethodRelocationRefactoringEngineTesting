package test;
record SourceRecord<T>(T data) {static class StaticNestedSource<T> {T nestedData;}
public class SourceInner {@Overrideprotected void methodToMove(TargetRecord<T> targetParam) {// Local inner classclass LocalInner {void processTarget() {// Enhanced for statement (corrected typo)for (T item : targetParam.items()) {// Access target's instance fieldTargetRecord.MemberInner<T> innerRec = targetParam.new MemberInner<>();innerRec.innerField = item;
// Variable callSystem.out.println(innerRec.innerField);variableCall(innerRec);}}}
new LocalInner().processTarget();
// Target's anonymous inner classtargetParam.new Object() {{System.out.println("Target anonymous: " + targetParam.data());}};}
private void variableCall(TargetRecord.MemberInner<T> inner) {inner.printField();}}}
/**
Javadoc for TargetRecord: Generic record with inner recursive structure*/record TargetRecord<T>(T data, T[] items) {public class MemberInner {
U innerField;
public void printField() {System.out.println(innerField);}
public class InnerRec {void useOuterField() {System.out.println(MemberInner.this.innerField);}}}}
// Subclass for overriding featureclass SubSourceRecord<T> extends SourceRecord<T> {public SubSourceRecord(T data) {super(data);}
@Overridepublic class SourceInner {@Overrideprotected void methodToMove(TargetRecord<T> targetParam) {super.methodToMove(targetParam);TargetRecord.MemberInner<T> inner = targetParam.new MemberInner<>();inner.innerField = targetParam.data();inner.new InnerRec().useOuterField();}}}