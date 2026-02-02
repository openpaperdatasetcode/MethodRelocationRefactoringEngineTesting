package test;
class ParentOfTarget {int superField1;String superField2;Object superField3;}
class OtherClass {OtherClass m1() { return this; }OtherClass m2() { return this; }OtherClass m3() { return this; }}
protected class SourceClass<T extends Number> {static class StaticNested {}
Object methodToMove(TargetClass target) {new Object() {{System.out.println(SourceClass.this.toString());}};
int field1 = target.superField1;String field2 = target.superField2;Object field3 = target.superField3;
OtherClass other = new OtherClass();target.property = other.m1().m2().m3();
if (field1 > 0) {return field2;} else {return field3;}}}
abstract class TargetClass extends ParentOfTarget {Object property;
class MemberInner {}}