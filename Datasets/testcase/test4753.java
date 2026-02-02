package test;
import java.util.ArrayList;import java.util.List;
class ParentClass<T> {}
private class SourceClass<T> extends ParentClass<T> {class MemberInner {}
final TargetClass<T>.Inner moveMethod(TargetClass<T> targetParam) {class LocalInner {}LocalInner li = new LocalInner();
TargetClass<T>.Inner targetInner = targetParam.new Inner();targetInner.field = 1;
expressionStatement();
protected char c1 = 'a';protected char c2 = 'b';protected char c3 = 'c';
List rawList = new ArrayList();rawList.add(targetInner);
variableCall(targetInner);
return targetInner;}
private void expressionStatement() {}
private void variableCall(TargetClass<T>.Inner inner) {}}
protected class TargetClass<T> {class Inner {int field;}
Object anonymous = new Object() {};}