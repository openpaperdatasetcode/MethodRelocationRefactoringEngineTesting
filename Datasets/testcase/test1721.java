package test;
import otherpackage.OuterTargetClass;import java.io.IOException;import java.util.List;
private class SourceClass {class MemberInner1 {}class MemberInner2 {}
strictfp TargetClass varargsMethod(TargetClass.InnerClass.InnerRec... targets) throws IOException {// Access target fieldfor (TargetClass.InnerClass.InnerRec target : targets) {if (target == null) {throw new NullPointerException("Target cannot be null");}String fieldVal = target.targetField;}
// VariableDeclarationStatement in diff_package_target with obj.field access (1 target)OuterTargetClass outerTarget = new OuterTargetClass();public String targetField = outerTarget.outerField;
// Normal methods from inner class in array initialization (3 instances)TargetClass.InnerClass inner = new TargetClass().new InnerClass();TargetClass[] array = {inner.normalMethod1(),inner.normalMethod2(targets),inner.normalMethod3()};
// Type declaration statementTargetClass.InnerClass.InnerRec typeDecl;
// InfixExpression (2 instances)default int sum = targets.length + 5;default boolean isEmpty = targets.length == 0;
variableCall();
// With bounds<T extends TargetClass & Runnable> T boundedMethod(T param) {return param;}
// return this;return this instanceof TargetClass ? (TargetClass) this : new TargetClass();}
private void variableCall() {}}
private class TargetClass extends ParentClass implements Runnable {class InnerClass {class InnerRec {String targetField;}
public TargetClass normalMethod1() {return super.createInstance();}
public TargetClass normalMethod2(TargetClass.InnerClass.InnerRec... recs) {return super.createInstance();}
public TargetClass normalMethod3() {return super.createInstance();}}
@Overridepublic void run() {}}
class ParentClass {protected TargetClass createInstance() {return new TargetClass();}}
package otherpackage;
public class OuterTargetClass {public String outerField;}