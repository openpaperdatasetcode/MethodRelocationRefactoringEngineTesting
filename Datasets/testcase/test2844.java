package test;
interface FunctionInterface {Object apply(TargetClass target);}
class SourceClass {private int outerPrivateField = 99;private TargetClass instanceField = new TargetClass();
class MemberInner extends SubClass {public TargetClass instanceMethod(TargetClass target) {// Access outer private fieldint privateVal = outerPrivateField;// Access instance fieldTargetClass fieldRef = instanceField;
// If statementif (target != null) {// Functional interfaces (pos for overloading feature)FunctionInterface func = this::overloadMethod;Object result = func.apply(target);}
// Variable callvariableCall(target);
// Local inner class (source_class feature)class LocalInner {TargetClass getTarget() {return target.createLocalInner().getValue();}}
// Anonymous inner class (source_class feature)Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(target.hashCode());}};runnable.run();
return new LocalInner().getTarget();}
private void variableCall(TargetClass target) {TargetClass localTarget = target;localTarget.createLocalInner().getValue();}
// Overloading method (sub_class type, super.methodName() feature)@Overridepublic Object overloadMethod(TargetClass target) {return super.overloadMethod(target);}}}
abstract class SubClass {// Overloading method (base for subclass overriding)public Object overloadMethod(TargetClass target) {return target;}}
protected class TargetClass implements TestInterface {// Local inner class (target_class feature)TargetClass createLocalInner() {class LocalInner {TargetClass getValue() {return TargetClass.this;}}return new LocalInner().getValue();}
@Overridepublic void implementMethod() {}}
interface TestInterface {void implementMethod();}