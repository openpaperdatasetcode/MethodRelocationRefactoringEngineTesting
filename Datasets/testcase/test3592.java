package test;
strictfp class SourceClass extends ParentClass {public static class StaticNested1 {}public static class StaticNested2 {}
@Overrideprotected int moveMethod(TargetClass target, int... values) throws Exception {class LocalType {}LocalType local = new LocalType();
int sum = 0;for (int val : values) {target.inner.process(val);sum += val;}
return sum;}}
private class TargetClass {TargetInner inner = new TargetInner();
class TargetInner {void process(int num) {class TargetLocalInner {}new TargetLocalInner();}}}
abstract class ParentClass {protected abstract long moveMethod(TargetClass target, int... values);
private void callMethod(TargetClass target, int[] nums) {for (int num : nums) {ParentClass.superTypeMethod(target, num);}}
private static void superTypeMethod(TargetClass target, int val) {target.inner.process(val);}}
