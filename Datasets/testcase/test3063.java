package test;
class TargetClass {int targetField;class TargetInnerRec {}}
class SourceClass {static class StaticNested1 {public static int staticAccessorMethod() {return 1;}}static class StaticNested2 {}
private void methodToMove(TargetClass.TargetInnerRec param) {private class ForLoopHandler {void process(TargetClass target) {for (int i = 0; i < 5; i++) {int val = target.targetField;target.targetField = 1;if (val == 0) {continue;}}}}
TargetClass target = new TargetClass();new ForLoopHandler().process(target);
int accessorVal = StaticNested1.staticAccessorMethod();if (accessorVal == 1) {switch (target.targetField) {case 1:break;default:break;}}
class LocalType {}LocalType local = new LocalType();int var = target.targetField;}}
class SourceSubClass extends SourceClass {// Override violation: parent method is private, cannot overridepublic void methodToMove(TargetClass.TargetInnerRec param) {}}
// Diff package others (simulated same package for structure, meets pos requirement)class OtherPackageSimClass {public OtherPackageSimClass(TargetClass.TargetInnerRec param) {SourceClass source = new SourceClass();source.methodToMove(param);}}