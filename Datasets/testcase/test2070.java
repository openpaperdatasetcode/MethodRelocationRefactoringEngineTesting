package test;
import otherpackage.OtherClass;import java.lang.reflect.Constructor;import java.lang.reflect.Method;
private class SourceClass {private int outerPrivateField = 42;
class FirstMemberInner {class InnerRecursive {/**
Recursive method with strictfp modifier*/strictfp void methodToMove() {try {OtherClass other = new OtherClass(super::toString);
if (other.check(this.field)) {targetField.variableCall();}
if (outerPrivateField > 0) {new FirstMemberInner().new InnerRecursive().methodToMove();}
String expr = "test";
Method refMethod = InnerRecursive.class.getMethod("methodToMove");refMethod.invoke(this);
Constructor<?> refConstructor = TargetClass.MemberInner.class.getConstructor(TargetClass.class);refConstructor.newInstance(targetField);} catch (Exception e) {}}
private int field;}}
class SecondMemberInner {}
private TargetClass targetField = new TargetClass();}
public class TargetClass {class MemberInner {MemberInner() {}}
void variableCall() {}}
package otherpackage;
public class OtherClass {private String value;
public OtherClass(Supplier<String> supplier) {this.value = supplier.get();}
public boolean check(int field) {return field > 0;}}