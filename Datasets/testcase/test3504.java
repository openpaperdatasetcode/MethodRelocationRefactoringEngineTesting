package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
class ParentSourceClass {public List<String> moveMethod() {return new ArrayList<>();}}
protected class SourceClass extends ParentSourceClass {private int outerPrivateField = 1;protected TargetClass targetField = new TargetClass();
public void outerMethod1() {class LocalInner1 {private void innerMethod() {; // EmptyStatementif (TargetClass.staticField == 1) {}}}new LocalInner1().innerMethod();}
public void outerMethod2() {class LocalInner2 {}new LocalInner2();}
@Overridepublic List<String> moveMethod() {synchronized (TargetClass.staticField) {TargetClass newTarget = new TargetClass();super();
List rawList = new ArrayList();variableCall();System.out.println(SourceClass.this.outerPrivateField);expressionStatementExecution();
try {Method method = TargetClass.StaticNested.TargetInnerRec.class.getMethod("doTask");method.invoke(new TargetClass.StaticNested.TargetInnerRec());} catch (Exception e) {}}return new ArrayList<>();}
public List<String> moveMethod(String param) {return new ArrayList<>();}
private void variableCall() {targetField.staticNested.innerRec.doTask();}
private void expressionStatementExecution() {int x = 10;x++;}}
protected class TargetClass {public static int staticField = 1;
public static class StaticNested {public static class TargetInnerRec {public List<String> moveMethod() {return new ArrayList<>();}
public List<String> moveMethod(int param) {return new ArrayList<>();}
public void doTask() {}}}
public StaticNested staticNested = new StaticNested();}