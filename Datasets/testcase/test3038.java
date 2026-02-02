package test;
public class SourceClass extends ParentClass {static class StaticNested {public TargetClass instanceMethod(TargetClass targetParam) {class LocalInner {}LocalInner inner = new LocalInner();
// Instance method from others_class in ternaryOtherClass other = new OtherClass();Object result = targetParam != null? super.process(other, "arg"): super.process(other, "default");
// Constructor invocation + super constructor invocationTargetClass.MemberInner targetInner = targetParam.new MemberInner();super();
// Synchronized statementsynchronized (targetInner) {targetInner.doAction();}
// Expression statementint count = 5;count++;
// Variable calltargetParam.doSomething();return targetParam;}}}
class TargetClass {class MemberInner {void doAction () {}}
void doSomething() {}}
class ParentClass {protected Object process(OtherClass other, String arg) {return arg + other.getValue();}}
class OtherClass {public final String getValue() {return "otherValue";}}