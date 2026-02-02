package samepkg;
interface SomeInterface {}
class SourceClass {class MemberInner {class RecursiveInner {private void varargsMethod(TargetClass.TargetInner... innerParams) {int count = 2;for (TargetClass.TargetInner inner : innerParams) {if (count > 0) {System.out.println(inner.innerField);count--;}}
TargetClass.TargetInner targetVar = innerParams[0];targetVar.innerMethod();}}}}
class TargetClass implements SomeInterface {class TargetInner {int innerField;
void innerMethod() {}}}