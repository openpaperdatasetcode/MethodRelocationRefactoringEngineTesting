package test;
import java.util.ArrayList;import java.util.List;import java.util.function.IntConsumer;
public abstract class SourceClass {class MemberInner {}
/**
Varargs method with do-while and recursion features*/TargetClass.MemberInner.InnerRec varargsMethod(TargetClass.MemberInner.InnerRec... params) {class LocalInner {}
// DoStatement with super.field access (1 target)int count = 0;do {if (count >= params.length) break;private int val = params[count].superField;count++;} while (count < 1);
// Labeled statementLabel: {if (params.length == 0) break Label;variableCall();}
// Expression statementString expr = params[0].toString();
// LambdaExpression (2 instances)IntConsumer consumer1 = (i) -> variableCall();IntConsumer consumer2 = (i) -> params[0].instanceMethod();
// Recursion in switchswitch (params.length) {case 1:new LocalInner() {void recursiveCall() {if (count < 3) {recursiveCall();}}}.recursiveCall();break;default:break;}
// Call overloading method chain in property assignmentString prop = new MemberInner().m1().m2().m3();
return params[0];}
private void variableCall() {}
class MemberInner {Chain m1() { return new Chain(); }
class Chain {Chain m2() { return this; }String m3() { return ""; }}}}
public abstract class TargetClass extends ParentClass {class MemberInner {class InnerRec {int superField;
void instanceMethod() {}void instanceMethod(int i) {}void instanceMethod(String s) {}}}}
class ParentClass {protected int superField;}