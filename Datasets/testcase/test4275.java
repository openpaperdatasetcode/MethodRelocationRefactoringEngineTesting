package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
// Source annotation: extends parent, has member inner & anonymous inner classes@interface SourceAnnotation extends ParentAnnotation {// Member inner class (source_class feature)class SourceMemberInner {// Super constructor invocation (implicit for inner class)SourceMemberInner() { super(); }
int getValue() { return 10; }}
// Anonymous inner class (source_class feature)Runnable sourceAnon = new Runnable() {@Overridepublic void run() {new SourceMemberInner().getValue();}};
// Varargs method to be refactoreddefault List<String> processVarargs(TargetAnnotation.TargetInner... targetParams) {List<String> result = new ArrayList<>();SourceMemberInner innerInst = new SourceMemberInner(); // Type declaration statement
// 2 ExpressionMethodReference (matches "numbers": "2")Function<TargetAnnotation.TargetInner, String> ref1 = TargetAnnotation.TargetInner::getField1;Function<TargetAnnotation.TargetInner, String> ref2 = TargetAnnotation.TargetInner::getField2;
// While statementint idx = 0;while (idx < targetParams.length) {TargetAnnotation.TargetInner param = targetParams[idx];
// For statementfor (int i = 0; i < 2; i++) {// SwitchStatement with 2 "obj.field" (matches target_feature)switch (i) {case 0:result.add(ref1.apply(param)); // Variable call (method reference)break;case 1:result.add(ref2.apply(param)); // Variable call (method reference)break;}}idx++;}return result;}}
// Parent annotation for source "extends" feature@interface ParentAnnotation {}
// Target annotation: has type parameter & member inner class@interface TargetAnnotation {// Type parameter (target_class feature)<T> T getParam();
// Member inner class (target_inner_rec, matches "target class": "target_inner_rec")class TargetInner {String field1;String field2;
public String getField1() { return field1; }public String getField2() { return field2; }}}