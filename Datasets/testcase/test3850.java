package test.refactoring;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
protected abstract class SourceClass {private TargetClass targetField = new TargetClass() {@Overridevoid abstractMethod() {}};
static class StaticNested {class InnerClass {Object process() {TargetClass.StaticNested targetInner = targetField.new StaticNested();List rawList = new ArrayList();rawList.add(targetInner.getValue());
switch (targetInner.getType()) {case "TYPE_A":variableCall(targetInner, "A");break;case "TYPE_B":variableCall(targetInner, "B");break;default:rawList.add("DEFAULT");}
try {Method method = TargetClass.StaticNested.class.getMethod("setValue", Object.class);method.invoke(targetInner, "reflectionValue");} catch (Exception e) {}
return rawList;}
private void variableCall(TargetClass.StaticNested inner, String type) {inner.setValue(type);}}}
void useAnonymous() {Runnable anon = new Runnable() {@Overridepublic void run() {new StaticNested.InnerClass().process();}};anon.run();}}
protected abstract class TargetClass {abstract void abstractMethod();
class StaticNested {private String type;private Object value;
String getType() {return type;}
Object getValue() {return value;}
void setValue(Object val) {this.value = val;}}}