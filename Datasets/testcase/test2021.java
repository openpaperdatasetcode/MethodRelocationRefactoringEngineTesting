package test;
public recordrecord SourceRecord<T>(T value) {static class StaticNested {protected int outerProtected;}
class MemberInnerClass {private void recursiveMethod(int n) {if (n <= 0) return;this.m1().m2().m3();recursiveMethod(n - 1);}
InnerClass m1() { return this; }InnerClass m2() { return this; }void m3() {}}
private Object varargsMethod(TargetRecord... targets) {if (targets == null) {throw new NullPointerException();}
private void throwStatement() {if (targets[0].superField == 3) {throw new IllegalArgumentException();}}throwStatement();
TargetRecord target = new TargetRecord(0);variableCall = target.field;StaticNested.outerProtected = variableCall;
int[] arr = {1};Runnable[] runners = {() -> innerMethod(arr[0])};
int i = 0;switch (i) {case 1:continue;default:i++;}
try {new InnerClass().recursiveMethod(1);} catch (Exception e) {// no new exception}
return target;}
private void innerMethod(int num) {}
int variableCall;}
public record TargetRecord(int data) {int field;int superField;
void methodWithLocal() {class LocalInner {}}}