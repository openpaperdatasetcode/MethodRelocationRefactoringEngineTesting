package test;
private class SourceClass<T> {// Static nested class (source_class feature)static class SourceStaticNested {}
// Member inner class (source_class feature)class SourceMemberInner {SourceMemberInner() {// Super constructor invocation (implicit for inner class)super();}}
// Recursive method to be refactored@Deprecated // has_annotation featureprotected Object recursiveProcess(TargetClass targetParam, int depth) {if (depth <= 0) {return targetParam.new TargetInner().new TargetRecursiveInner();}
// Variable call: access target instance field + inner class methodint targetVal = targetParam.targetField;targetParam.new TargetInner().processVal(targetVal);
// Recursionreturn recursiveProcess(targetParam, depth - 1);}}
private class TargetClass implements Runnable { // target_class implements featureint targetField = 10;
// Static nested class (target_class feature)static class TargetStaticNested {}
// Target inner class (for target_inner_rec)class TargetInner {void processVal(int val) {TargetClass.this.targetField = val * 2;}
// Target recursive inner class (for target_inner_rec)class TargetRecursiveInner {}}
@Overridepublic void run() {}}