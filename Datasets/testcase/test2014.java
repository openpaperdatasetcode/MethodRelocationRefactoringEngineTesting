package test;
abstract class SourceClass {class MemberInner1 {}class MemberInner2 {}
protected Object normalMethod(TargetClass.TargetInner inner) {class InnerClass {private int localVar = inner.superField + 1;}new InnerClass();
variableCall = inner.field;return variableCall;}
Object variableCall;}
/**
Javadoc for TargetClass*/abstract class TargetClass extends ParentClass {class TargetInner {int field;}
{new Runnable() {}; // Anonymous inner class}}
class ParentClass {int superField;}