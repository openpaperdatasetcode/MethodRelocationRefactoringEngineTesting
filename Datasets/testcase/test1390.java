package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnno {}
public record SourceRecord(String data) {// Anonymous inner class 1Runnable anon1 = new Runnable() {@Overridepublic void run() {}};
// Anonymous inner class 2Runnable anon2 = new Runnable() {@Overridepublic void run() {}};
class SourceInner {class SourceInnerRec { // source_inner_rec@TestAnno // has_annotationpublic final int methodToMove(TargetRecord.TargetParam param) { // contains target parameter (per_condition)// super keywordssuper.toString();
// constructor invocationSourceInner inner = new SourceInner();TargetRecord target = new TargetRecord("target");
// variable callinner.doSomething();target.anon.run();
// expression statementString expr = data + param.value();
// throw statementif (param == null) {throw new IllegalArgumentException();}
// depends_on_inner_classInnerDependency dep = new InnerDependency();dep.use();
return 0; // base type}
private void doSomething() {}}}
// Inner class for depends_on_inner_classprivate class InnerDependency {void use() {}}}
public record TargetRecord(String value) {static class TargetParam {String value() { return "param"; }}
// anonymous inner class (target_feature)Runnable anon = new Runnable() {@Overridepublic void run() {}};}