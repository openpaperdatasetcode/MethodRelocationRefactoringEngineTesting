package test;
private class SourceClass<T> {static int staticField = 10;
class MemberInner {strictfp int moveMethod(TargetClass<T> param) {class TypeDeclaration {}new TypeDeclaration();
TargetClass<T> newTarget = new TargetClass<>();variableCall(param);return param.getBaseValue() + staticField;}
strictfp int moveMethod(TargetClass<T> param, int num) {return param.getBaseValue() + num + staticField;}
private void variableCall(TargetClass<T> target) {target.innerClass.doTask();}}
static class StaticNested {}}
class TargetClass<T> {class TargetInner {strictfp int moveMethod(TargetClass<T> param) {return param.getBaseValue() + SourceClass.staticField;}
strictfp int moveMethod(TargetClass<T> param, int num) {return param.getBaseValue() + num + SourceClass.staticField;}
void doTask() {}}
private TargetInner innerClass = new TargetInner();private int baseValue = 5;
public int getBaseValue() {return baseValue;}}
class OthersClass {public static final <T> int callMethod(SourceClass<T> source, TargetClass<T> target) {SourceClass<T>.MemberInner inner = source.new MemberInner();int flag = target.getBaseValue();
switch (flag) {case 5:return inner.moveMethod(target);default:return inner.moveMethod(target, flag);}}}