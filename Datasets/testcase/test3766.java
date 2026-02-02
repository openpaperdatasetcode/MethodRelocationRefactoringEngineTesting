package test;
interface SourceInterface {// Static nested class (source_class feature)static class SourceStaticNested {}
// Member inner class (source_class feature)class SourceMemberInner {}
// Normal method (method type: normal)public Object processTarget(TargetInterface target) {// TypeDeclarationStatement (static modifier, pos: same_package_target)static class TypeDecl {void useTargetField() {String targetField = TargetInterface.TargetMemberInner.targetStaticField;}}new TypeDecl().useTargetField();
// Variable call (uses target's field, meets per_condition)String targetInnerField = target.inner.targetField;Object result = targetInnerField;
// Assert statement (method feature)assert targetInnerField != null : "Target field cannot be null";
return result;}}
// Public target interface (target_class modifier: public)public interface TargetInterface {// Member inner class (target_class feature)class TargetMemberInner {// Target field (for source to contain, meets per_condition)public static String targetStaticField = "targetStaticData";public String targetField = "targetInnerData";}
// Instance of member inner class (exposed for source access)TargetMemberInner inner = new TargetMemberInner();}