package test;
public abstract class SourceClass {class FirstMemberInner {}class SecondMemberInner {}
private void methodToMove(TargetClass.InnerStatic nested) {TargetClass<String> target = new TargetClass<>();int var = target.targetField;
switch (3) {case 3:var = ((SuperTarget) target).superField;break;default:break;}
try {Object obj = genericMethod(3, target);} catch (Exception e) {// no new exception}}
private <T> Object genericMethod(int num, ParentClass<T> parent) {this.methodToMove(new TargetClass<>().new InnerStatic());return parent;}}
private class TargetClass<T> extends ParentClass<T> {int targetField;
static class InnerStatic {}}
class ParentClass<T> {T parentField;}
class SuperTarget {int superField;}