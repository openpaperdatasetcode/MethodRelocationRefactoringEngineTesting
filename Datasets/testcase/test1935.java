package test;
class SourceClass {protected int outerProtected = 1;
static class StaticNested1 {}static class StaticNested2 {static void staticMethod(TargetClass target) {target.memberInner.updateField(outerProtected);}}
class Inner {class InnerRec {/**
Processes target using do-while loop and static method calls
@param target the target class instance to process*/final void method(TargetClass target) {int i = 0;do {TargetClass.staticMethod(target);i++;} while (i < 1);
target.field = outerProtected;target.memberInner.printField();}}}}
strictfp class TargetClass extends ParentClass {int field;
class MemberInner {void updateField(int value) {field = value;}
void printField() {System.out.println(field);}}MemberInner memberInner = new MemberInner();
static void staticMethod(TargetClass target) {super.staticParentMethod(target);target.field += 1;}}
class ParentClass {static void staticParentMethod(TargetClass target) {target.field *= 2;}}