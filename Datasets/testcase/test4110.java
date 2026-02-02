package test;
private class SourceClass {private TargetClass targetField = new TargetClass();
class MemberInner {void useOuterMethod(TargetClass targetParam) {SourceClass.this.recursiveMethod(targetParam, 3);}}
static class StaticNested {static void helperMethod() {}}
public final void recursiveMethod(TargetClass targetParam, int depth) {if (depth <= 0) {return;}
volatile int localVar = 1;if (localVar == TargetClass.StaticNested.STATIC_CONST) {StaticNested.helperMethod();}
int var = targetParam.targetField;System.out.println("Target field value: " + var);
recursiveMethod(targetParam, depth - 1);}}
protected class TargetClass {int targetField = 5;
static class StaticNested {public static final int STATIC_CONST = 1;}}