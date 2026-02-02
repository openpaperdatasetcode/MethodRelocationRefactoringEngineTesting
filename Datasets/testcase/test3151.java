package test;
class ParentSourceClass {}
protected class TargetClass {String targetField;static class TargetStaticNested {} // target_feature: static nested class}
private class SourceClass extends ParentSourceClass { // source_feature: extendsclass MemberInner {} // source_feature: member inner class
public void createLocalInner() {class LocalInner {} // source_feature: local inner classnew LocalInner();}
class SourceInner {record SourceInnerRec<T>(T val) {} // source_inner_rec
// method types parameter is:genericpublic <T> TargetClass methodToMove(TargetClass target, SourceInnerRec<T> rec) {// Variable callString var = target.targetField;T recVal = rec.val();
// Super keywords (call parent class method/field)super.toString();
// Depends on inner classMemberInner inner = new MemberInner();createLocalInner();
// Return target class instancereturn target;}}}