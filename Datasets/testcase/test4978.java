package refactoring.test;

public class SourceClass implements SourceInterface {
private String sourceField = "sourceValue";

class FirstInner implements InnerInterface {
String getValue() {
return sourceField;
}
}

class SecondInner {
FirstInner createFirstInner() {
return new FirstInner();
}
}

/**

Processes the target object and returns it
@param targetParam The target object to process
@return The processed target object
*/
@Deprecated
protected TargetClass instanceMethod(TargetClass targetParam) {
super();
TargetClass newTarget = new TargetClass(sourceField);
variable call = targetParam.targetField;
call = newTarget.targetField;
SecondInner inner = new SecondInner();
FirstInner first = inner.createFirstInner();
targetParam.targetField = first.getValue();
Runnable anon = targetParam.createAnonymousRunnable();
anon.run();
return targetParam;
}
}

interface SourceInterface {}

interface InnerInterface {}

protected class TargetClass implements TargetInterface {
String targetField;

public TargetClass(String value) {
this.targetField = value;
}

Runnable createAnonymousRunnable() {
return new Runnable() {
@Override
public void run() {
System.out.println(targetField);
}
};
}
}

interface TargetInterface {}