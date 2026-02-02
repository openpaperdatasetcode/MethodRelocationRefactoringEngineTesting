package test;
class OthersClass {int method() {return 0;}}
protected class SourceClass {class MemberInner {}static class StaticNested {}
protected void moveMethod(TargetClass target, int... args) {switch (target.field) {case 1:break;default:break;}
for (int arg : args) {new OthersClass().method();}
variableCall(target);target.overloadMethod(1);target.overloadMethod("str");}
private void variableCall(TargetClass t) {}}
public class TargetClass {int field;
void overloadMethod(int i) {}void overloadMethod(String s) {}}