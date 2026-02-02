package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {String value() default SourceRecord.StaticNested.callMethod();}
public record SourceRecord(TargetRecord targetField) {// Member inner classpublic class MemberInner {private TargetRecord innerTarget;
public MemberInner(TargetRecord target) {this.innerTarget = target; // this.var = var}}
// Static nested classpublic static class StaticNested {public static String callMethod() {return "called";}}
// Static field for depends_on_static_fieldprivate static TargetRecord staticTargetField = new TargetRecord("static");
private List<String> instanceMethod() {// Super constructor invocation (implicit in record)List<String> result = new ArrayList<>();
// Uses outer thisMemberInner inner = SourceRecord.this.new MemberInner(targetField);
// Enhanced for statementfor (TargetRecord tr : List.of(targetField, staticTargetField)) {// Variable callvariableCall(tr);
// Empty statement;
// NullPointerException riskif (tr.value() == null) {tr.value().toString();}
// Depends on static fieldresult.add(staticTargetField.value());}
// Annotation attribute with call_method (pos)@CallAnnotationObject annotatedObj = new SubClass().instanceMethod();
return result; // No new exception}
private void variableCall(TargetRecord target) {TargetRecord local = target;local.createLocalInner().process();}}
private record TargetRecord(String value) {// Local inner classTargetRecord createLocalInner() {class LocalInner {TargetRecord process() {return TargetRecord.this;}}return new LocalInner().process();}}
// Sub class for call_methodstrictfp class SubClass {// Instance method (OuterClass.InnerClass.methodName() feature)public TargetRecord instanceMethod() {return SourceRecord.StaticNested.callMethod().isEmpty()? new TargetRecord("sub"): new TargetRecord("default");}}