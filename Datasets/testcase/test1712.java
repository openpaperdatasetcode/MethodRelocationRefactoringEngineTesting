package test;
import java.util.function.Runnable;
final class SourceClass {protected String outerProtectedField;
{new Runnable() {};}
class MemberInner {protected Object instanceMethod(TargetClass.InnerClass targetInner) {super();class LocalInner {}
// Access target fieldint fieldVal = targetInner.targetField;
this.helperMethod();variableCall();
// Access outer protected memberString outerVal = SourceClass.this.outerProtectedField;
// Overloaded methods existoverloadedMethod();overloadedMethod(1);
// Call strictfp method in functional interfaceRunnable runnable = () -> this.strictfpMethod();runnable.run();
return new Object();}
private void variableCall() {}
private void helperMethod() {}
private void overloadedMethod() {}private void overloadedMethod(int num) {}
strictfp void strictfpMethod() {}}}
protected class TargetClass {class InnerClass {int targetField;}}