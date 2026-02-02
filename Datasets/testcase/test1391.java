package test;
private record SourceRecord(String sourceField) {class SourceInner {class SourceInnerRec { // source_inner_recvoid methodToMove(TargetRecord.TargetParam... params) { // contains target parameter (per_condition)// access_instance_fieldString fieldVal = SourceRecord.this.sourceField;
// variable callSourceInner inner = new SourceInner();inner.doAction();
// throw statementif (params == null || params.length == 0) {throw new IllegalArgumentException("Params required");}
// depends_on_inner_classSourceInnerDependency dep = new SourceInnerDependency();dep.execute();
// target parameter usagefor (TargetRecord.TargetParam param : params) {param.process();}}}
private void doAction() {}
// Inner class for depends_on_inner_classprivate class SourceInnerDependency {void execute() {}}}}
private record TargetRecord(String targetField) extends SuperClass { // target_feature: extendsstatic class TargetParam {void process() {}}
class MemberInner { // target_feature: member inner classvoid useTargetField() {System.out.println(TargetRecord.this.targetField);}}}
abstract class SuperClass {}