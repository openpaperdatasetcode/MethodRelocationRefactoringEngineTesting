package test;
import java.util.List;
abstract class ParentRecord {}
protected record SourceClass<T extends List<?>> (int val) extends ParentRecord {class MemberInner {}
public Object moveMethod(TargetClass<String> target) {class LocalInner {}LocalInner li = new LocalInner();
TargetClass<String> newTarget = new TargetClass<>("test");expressionStatement();
int[] arr = {1, 2, 3};abstract int a = arr[0];abstract int b = arr[1];abstract int c = arr[2];
switch (target.field) {case 1:break;default:break;}
variableCall(target);return TargetClass.staticField;}
public Object moveMethod(TargetClass<Integer> target) {return null;}
private void expressionStatement() {}
private void variableCall(TargetClass<?> t) {}}
record TargetClass<T extends CharSequence> (T data) {static int staticField = 0;int field;
class MemberInner {}}
