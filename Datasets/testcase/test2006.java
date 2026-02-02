package test;
interface MyInterface<T> {}
strictfp class SourceClass<T> implements MyInterface<T> {permits SourceSubClass1, SourceSubClass2;
class MemberInner {}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
private void methodToMove(TargetClass<T> targetParam) {TargetClass.Inner inner1 = new TargetClass.Inner();TargetClass.Inner inner2 = new TargetClass.Inner(5);SuperClass superObj = new SuperClass();targetParam.field = 10;useOverload();useOverload(5);}
private void useOverload() {}private void useOverload(int i) {}}
class SourceSubClass1<T> extends SourceClass<T> {}class SourceSubClass2<T> extends SourceClass<T> {}
class SuperClass {public SuperClass() {}}
class TargetClass<T> {int field;
class Inner {Inner() {}Inner(int i) {}}}