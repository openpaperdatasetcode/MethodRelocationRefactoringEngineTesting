package test;
interface MyInterface {}
class ParentClass {static ParentClass m1() { return new ParentClass(); }ParentClass m2() { return this; }void m3() {}}
class SourceClass {static class StaticNested1 {}static class StaticNested2 {}
/**
Overloaded method using TargetClass inner
@param inner target inner class instance
@return base type result
*/
final int overloadedMethod(TargetClass.TargetInner inner) {
if (inner == null) {
throw new IllegalArgumentException("Inner cannot be null");
}
int val = inner.getValue();
ParentClass.m1().m2().m3();
return val;
}
final int overloadedMethod(int num) {return new TargetClass.TargetInner(num).getValue() * 2;}}
public class TargetClass implements MyInterface {public class TargetInner {private int value;
public TargetInner(int value) {this.value = value;}
public int getValue() {return value;}}}