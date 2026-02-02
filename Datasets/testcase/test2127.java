package test;
interface TestInterface {}
class SuperTarget {int superField1;String superField2;double superField3;}
private class SourceClass {class FirstInner {}class SecondInner {}
public int methodToMove(TargetClass target) {if (target == null) {throw new NullPointerException();}
TargetClass.StaticNested.InnerRecursive innerRec = new TargetClass.StaticNested.InnerRecursive();
Label: {System.out.println(innerRec.super.superField1);System.out.println(innerRec.super.superField2);System.out.println(innerRec.super.superField3);break Label;}
switch (innerRec.state) {case 0:target.variableCall();break;case 1:innerRec.variableCall();break;default:break;}
return innerRec.super.superField1;}}
protected class TargetClass extends SuperTarget implements TestInterface {static class StaticNested extends SuperTarget {static class InnerRecursive extends SuperTarget {int state;
void variableCall() {}}}
void variableCall() {}}