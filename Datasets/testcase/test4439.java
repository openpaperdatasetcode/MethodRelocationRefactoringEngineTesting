package test;
public enum SourceEnum {ENUM1, ENUM2;
class InnerRec {private TargetEnum.Inner process(TargetEnum.Inner targetInner, int depth) {class LocalClass {synchronized abstract Object abstractMethod();}
try {if (depth <= 0) {return new TargetEnum.Inner();}
int val = targetInner.field;Object result = val > 0 ? super.toString() : new LocalClass() {@OverrideObject abstractMethod() {return val;}};
Runnable runnable = () -> System.out.println(this.val);runnable.run();
int switchResult = switch (val) {case 1 -> 1;default -> 0;};
do {OtherClass.callMethod(new TargetEnum.Inner());OtherClass.callMethod(val);if (switchResult == 1) continue;depth--;} while (depth > 0);
return process(targetInner, depth - 1);} catch (Exception e) {return null;}}
private int val;}}
abstract enum TargetEnum {TARGET1, TARGET2;
static class Nested {}
class Inner {int field;}}
class OtherClass {public static void callMethod(TargetEnum.Inner inner) {}public static void callMethod(int value) {}}