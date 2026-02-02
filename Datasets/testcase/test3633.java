package test;
import java.util.List;import java.util.ArrayList;
interface GenericInterface<T> {Object interfaceMethod();}
strictfp class OtherGenericClass<T> {public List<String> genericMethod(T obj) {return new ArrayList<>(List.of("otherGenericData"));}
public OtherGenericClass<T> m1() { return this; }public OtherGenericClass<T> m2() { return this; }public List<String> m3() { return new ArrayList<>(); }}
protected class SourceClass<T> implements GenericInterface<T> {protected String outerProtectedField = "sourceProtectedData";
class SourceMemberInner {void innerMethod() {}}
@Overridepublic Object interfaceMethod() {new Runnable() {public void run() {SourceClass.this.outerProtectedField = "updated";}}.run();
PrivateTargetClass<T> target = new PrivateTargetClass<>();SourceMemberInner sourceInner = new SourceMemberInner();
Object parenthesized = (target.instanceField);super.toString();
try {List<String> callResult = new OtherGenericClass<T>().m1().m2().m3();} catch (Exception e) {}
variableCall(target.memberInner);return outerProtectedField;}
private void variableCall(PrivateTargetClass<T>.TargetMemberInner inner) {inner.targetInnerMethod();}}
private class PrivateTargetClass<V> {V instanceField;
class TargetMemberInner {void targetInnerMethod() {}}
TargetMemberInner memberInner = new TargetMemberInner();}