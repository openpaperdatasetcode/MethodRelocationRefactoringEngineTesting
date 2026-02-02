package test;
/**
Javadoc for TargetClass enum*/final enum TargetClass {INSTANCE;
/**
Javadoc for TargetNested1 static nested class
*/
static class TargetNested1 {
void nestedMethod() {}
}
/**
Javadoc for TargetNested2 static nested class*/static class TargetNested2 {final void recursiveInnerMethod(int depth) {if (depth <= 0) {return;}int num = 1;nestedMethodCall(num);recursiveInnerMethod(depth - 1);}
private void nestedMethodCall(int param) {}}}
strictfp enum SourceClass {INSTANCE;
static class SourceNested1 {}
static class SourceNested2 {}
final void recursiveMethod(TargetClass.TargetNested2 targetParam, int depth) {if (depth <= 0) {return;}int value = 1;targetParam.recursiveInnerMethod(value);targetParam.nestedMethodCall(depth);recursiveMethod(targetParam, depth - 1);}}