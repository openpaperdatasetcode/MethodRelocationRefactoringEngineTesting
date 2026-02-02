package test;
private class TargetClass {String targetField;static class TargetStaticNested {} // target_feature: static nested class
class TargetInner {} // target_inner}
public class SourceClass {class MemberInner {}
public SourceClass() {// Anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}
class SourceInner {record SourceInnerRec() {} // source_inner_rec
protected Object methodToMove(TargetClass.TargetInner inner, Integer... args) {// Type declaration statementclass TypeDecl {}TypeDecl type = new TypeDecl();
// Variable callTargetClass target = new TargetClass();String var = target.targetField;
// Expression statementtarget.targetField = "updated";
// Raw typeTargetClass.TargetStaticNested rawNested = new TargetClass.TargetStaticNested();
// Empty statement;
return var;}}}