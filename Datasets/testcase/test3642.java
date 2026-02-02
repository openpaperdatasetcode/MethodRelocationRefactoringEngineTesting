package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
class ParentClass {protected String superField1 = "super1";protected String superField2 = "super2";}
private class SourceClass<T> {static String staticField = "staticData";
class MemberInner {@MethodAnnotpublic int varargsMethod(ProtectedTargetClass<T> target, Integer... args) {new Runnable() {public void run() {SourceClass.this.toString();}}.run();
class InnerConstructor extends ParentClass {private InnerConstructor(ProtectedTargetClass<T> target) {super();this.superField1 = target.superField1;this.superField2 = target.superField2;}}new InnerConstructor(target);
System.out.println(super.toString());variableCall(target.memberInner);int count = SourceClass.staticField.length();
return args.length;}
private void variableCall(ProtectedTargetClass<T>.TargetInner inner) {inner.innerMethod();}}}
protected class ProtectedTargetClass<V> extends ParentClass {class TargetInner {void innerMethod() {}}
TargetInner memberInner = new TargetInner();}
