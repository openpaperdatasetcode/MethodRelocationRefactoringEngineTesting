package test;
non-sealed enum TargetEnum {TARGET_VAL1("val1"), TARGET_VAL2("val2");
String targetField;static class TargetStaticNested {void updateTargetField(TargetEnum target) {target.targetField += "_nestedUpdated";}}
TargetEnum(String field) {this.targetField = field;}}
class SamePackageOthers {public void validateTarget(TargetEnum target) {assert target.targetField != null : "Target field cannot be null";}}
enum SourceEnum {SOURCE_VAL1, SOURCE_VAL2;
class SourceMemberInner {class SourceRecursiveInner {void instanceMethod(TargetEnum target) {labeledBlock: {class SourceLocalInner {void helper(TargetEnum tc) {variableCall(tc);}}
SamePackageOthers others = new SamePackageOthers();TargetEnum.TargetStaticNested staticNested = new TargetEnum.TargetStaticNested();SourceLocalInner localInner = new SourceLocalInner();
try {others.validateTarget(target);staticNested.updateTargetField(target);localInner.helper(target);} catch (AssertionError e) {break labeledBlock;}}}
private void variableCall(TargetEnum target) {target.targetField += "_varUpdated";}}}}