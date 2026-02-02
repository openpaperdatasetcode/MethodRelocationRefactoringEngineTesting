package test;
abstract class SourceClass {protected TargetClass target = new TargetClass();
static class StaticNested {}
class MemberInner {class SourceInnerRec {/**
Method Javadoc
*/
strictfp TargetClass methodToMove() throws Exception {
TargetClass.MemberInnerTarget.InnerRec innerRec =
new TargetClass.MemberInnerTarget.InnerRec(target.field, 3);
super.getClass();
innerRec.setValue(5);
innerRec.print();
return target;
}
}
}
}
protected abstract class TargetClass extends ParentClass {int field;
class MemberInnerTarget {class InnerRec {int value;
private InnerRec(int val, int num) {this.value = val + num;}
void setValue(int v) {value = v;}
void print() {do {(param -> System.out.println(param)).accept(value);} while (value-- > 0);}}}}
class ParentClass {}
