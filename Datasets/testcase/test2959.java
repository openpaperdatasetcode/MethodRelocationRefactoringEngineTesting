package test;
private class SourceClass<T> {private int field = 0;
private class LocalInnerSource {void foo() {}}
private int methodToMove(TargetClass targetParam) {switch (targetParam.field) {case 1:;TargetClass.LocalInnerTarget lit = new TargetClass.LocalInnerTarget();lit.value = 5;targetParam.foo();new Object() {{super();}};break;default:break;}return 0;}
final Object callMethod() {Object[] arr = {this::methodToMove};return arr;}}
class TargetClass extends ParentClass {protected int field;
class LocalInnerTarget {int value;}
public int methodToMove() {return 0;}
void foo() {}}
class ParentClass {}