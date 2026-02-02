package test;
sealed class TargetClass permits SourceClass {class TargetInner {int value = 5;}TargetInner inner = new TargetInner();}
class SourceClass extends TargetClass {TargetClass targetField = new TargetClass();
final Object sourceMethod() throws Exception {int var = targetField.inner.value;switch (var) {case 5:var = targetField.inner.value * 2;break;default:var = 0;}if (var < 0) {throw new Exception();}return var;}}