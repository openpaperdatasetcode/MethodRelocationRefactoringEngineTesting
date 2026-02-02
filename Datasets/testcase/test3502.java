package test;
import java.lang.reflect.Method;
interface ActionInterface {Object execute();}
private class SourceClass implements ActionInterface {private TargetClass targetField = new TargetClass();private int outerPrivateField = 42;
static class StaticNested {void invokeLocalInner(SourceClass source) {class LocalInner {Object callSourceMethod() {return source.moveMethod();}}new LocalInner().callSourceMethod();}}
@Overridepublic Object moveMethod() {super.toString();variableCall();
try {Method method = TargetClass.class.getMethod("instanceMethod");method.invoke(targetField);} catch (Exception e) {}
System.out.println(SourceClass.this.outerPrivateField);return new Object();}
public Object moveMethod(String param) {return param;}
private void variableCall() {targetField.memberInner.doAction();}}
class TargetClass {class TargetMemberInner {void doAction() {}}
private TargetMemberInner memberInner = new TargetMemberInner();
public void instanceMethod() {}
public Object moveMethod() {return new Object();}
public Object moveMethod(int param) {return param;}}