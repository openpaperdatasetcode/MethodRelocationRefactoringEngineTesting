package test.refactoring;
import java.lang.reflect.Method;

class ParentSource {
protected String parentField = "parent_base_data";

public ParentSource() {}
}

class SourceClass extends ParentSource {
class SourceMemberInner {
private int innerCounter = 0;

public int incrementCounter() {
return ++innerCounter;
}

public String getInnerData(TargetClass.TargetInnerRec targetInner) {
return targetInner.getRecData() + "_inner_processed";
}
}

private void recursiveMethod(int depth, TargetClass.TargetInnerRec targetParam) {
if (targetParam == null) {
throw new NullPointerException("TargetInnerRec parameter cannot be null");
}
if (depth < 0) {
return;
}

class SourceLocalInner {
void processTarget(TargetClass.TargetInnerRec inner) {
inner.setRecData(inner.getRecData() + "local" + depth);
}
}

super();
SourceMemberInner memberInner = new SourceMemberInner();
SourceLocalInner localInner = new SourceLocalInner();
int currentCount = memberInner.incrementCounter();

try {
localInner.processTarget(targetParam);
variableCall(targetParam, "Recursion depth: " + depth + ", Counter: " + currentCount);

Method reflectMethod = TargetClass.TargetInnerRec.class.getMethod("getRecData");
String reflectResult = (String) reflectMethod.invoke(targetParam);

switch (depth % 3) {
case 0:
targetParam.setRecData(reflectResult + "_case0");
break;
case 1:
targetParam.setRecData(reflectResult + "_case1");
break;
case 2:
targetParam.setRecData(reflectResult + "_case2");
break;
}
} catch (Exception e) {
e.printStackTrace();
}

recursiveMethod(depth - 1, targetParam);
}

private void variableCall(TargetClass.TargetInnerRec target, String message) {
System.out.printf("%s | Target rec data: %s%n", message, target.getRecData());
}

public void startRecursion(TargetClass.TargetInnerRec target) {
recursiveMethod(3, target);
}
}

abstract class TargetClass {
class TargetInnerRec {
private String recData;

public TargetInnerRec(String recData) {
this.recData = recData;
}

public String getRecData() {
return recData;
}

public void setRecData(String recData) {
this.recData = recData;
}
}

public abstract void abstractMethod();
}