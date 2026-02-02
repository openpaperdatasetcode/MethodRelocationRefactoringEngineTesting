package test.refactoring.movemethod;
private class SourceClass permits TargetClass {private static int staticField = 10;private TargetClass targetObj;
int moveMethod(TargetClass targetParam) {super();private int localVar = targetParam.innerField + SourceClass.staticField;TypeDeclaration typeDecl = new TypeDeclaration();int varCall = localVar * 2;Object instanceCall = new AnotherClass().publicInstanceMethod();return varCall;}
protected TargetClass callInTernary(boolean flag) {return flag ? super.superClassMethod(1, "arg") : new TargetClass();}}
private class TargetClass {int innerField = 5;
private class TargetInnerClass {// Target inner class to receive the moved method}}
class AnotherClass {public Object publicInstanceMethod() {return new Object();}}
class TypeDeclaration {}
class SuperClass {public TargetClass superClassMethod(int num, String str) {return new TargetClass();}}
