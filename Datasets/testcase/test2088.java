package test;
import java.util.List;import java.util.ArrayList;
class SuperTarget {}
protected abstract class SourceClass {class MemberInner {public List<String> firstMethod() {return super.firstMethod();}
public List<String> secondMethod() {return super.secondMethod();}}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
protected abstract int methodToMove();
{TargetClass target = new TargetClass();int i = 0;do {new MemberInner().firstMethod();new MemberInner().secondMethod();i++;} while (i < 5);
switch (target.field) {case 1:if (target.field > 0) {break;}target.variableCall();break;default:break;}
int fieldVal = target.instanceField;}}
private class TargetClass extends SuperTarget {int field;int instanceField;
class MemberInner {}
void variableCall() {}}