package test;
import java.util.List;import java.util.ArrayList;
interface MyInterface {}
interface TargetInterface {}
@MyAnnotationclass SourceClass<T> implements MyInterface {private T sourceField;static int staticField = 2;
class MemberInner {}
{new MyInterface() {}; // Anonymous inner class}
public void methodToMove(TargetClass targetParam) {// VariableDeclarationStatement with target featuresint val1 = TargetClass.staticField;int val2 = targetParam.instanceField + 2;
// Normal method in do-whileSourceClass<T> outer = this;do {Object obj = outer.new MemberInner().innerMethod(2);} while (false);
// SuperFieldAccess expression (1)int superVal = super.hashCode();
// Variable callT var = sourceField;TargetClass.TargetInner inner = targetParam.new TargetInner();inner.doSomething();}
class MemberInner {Object innerMethod(int num) {return num;}}}
private class TargetClass implements TargetInterface {static int staticField = 1;int instanceField = 3;
class TargetInner {}}
class SubClass extends SourceClass<String> {@Overridepublic List<String> callMethod() {List<String> list = new ArrayList<>();TargetClass target = new TargetClass();list.add(String.valueOf(new SourceClass<String>().methodToMove(target).toString().length()));return list;}}
@interface MyAnnotation {}