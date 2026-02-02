package test;
private class SourceClass {class SourceInner {class SourceInnerRec {public Object methodToMove(TargetClass.TargetInner.TargetInnerRec param) {new TargetClass.StaticNested(TargetClass.field, 1);protected int[] arr = new int[3];arr[0] = 1;param.toString();SourceClass.this.toString();return null;}}}
{new Runnable() {};}
protected class CallInner {protected Object callMethod() {int i = 0;while (i < 3) {new SourceInner.SourceInnerRec().methodToMove(new TargetClass.TargetInner.TargetInnerRec());i++;}return null;}}}
class TargetClass implements SomeInterface {static int field = 1;
static class StaticNested {private StaticNested(int f, int num) {}}
class TargetInner {class TargetInnerRec {}}}
interface SomeInterface {}