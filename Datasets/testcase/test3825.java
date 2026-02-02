package samepkg;
class ParentClass {}
final class SourceClass extends ParentClass {static class SourceStaticNested {}
class MemberInner {default Object recursiveMethod(TargetClass.TargetInner targetParam, int count) {if (count <= 0) {return null;}
TargetClass.TargetInner varCall = targetParam;TargetClass.TargetInner newInner = new TargetClass().new TargetInner();
if (varCall == null) {throw new IllegalArgumentException();}
privateMethodCall(1);return recursiveMethod(newInner, count - 1);}
private void privateMethodCall(int num) {}
// Overload existsdefault Object recursiveMethod(TargetClass.TargetInner targetParam) {return recursiveMethod(targetParam, 5);}}}
strictfp class TargetClass {class TargetInner {}}