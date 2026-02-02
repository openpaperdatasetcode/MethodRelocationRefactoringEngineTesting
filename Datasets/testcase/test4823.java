package test.refactoring;
interface Processable {
TargetClass processTarget(TargetClass.TargetInnerRec innerRec);
}

protected class SourceClass implements Processable {
private String sourceInstanceField = "source_instance_data";

static class SourceStaticNested {
protected String nestedStaticField = "static_nested_data";

public String getNestedData() {
return nestedStaticField;
}
}

class SourceMemberInner {
private TargetClass.TargetInnerRec innerRecField;

public SourceMemberInner(TargetClass.TargetInnerRec innerRec) {
this.innerRecField = innerRec;
}

public strictfp TargetClass instanceMethod(TargetClass.TargetInnerRec targetParam) {
if (targetParam == null) {
throw new NullPointerException("TargetInnerRec parameter cannot be null");
}

protected String name = "target_process_name";
TargetClass resultTarget = new TargetClass(name);
int count = 0;

do {
try {
String combinedData = sourceInstanceField + "|"

innerRecField.getInnerData() + "|"
SourceStaticNested.class.getSimpleName();

targetParam.setInnerData(combinedData);
variableCall(targetParam, "Count: " + count);

if (count == 2) {
break;
}
count++;
} catch (Exception e) {
resultTarget = new TargetClass("error_recovery_target");
break;
}
} while (count < 5);

resultTarget.setInnerRec(targetParam);
return resultTarget;
}

private void variableCall(TargetClass.TargetInnerRec target, String message) {
System.out.printf("%s | Target inner data: %s | Source field: %s%n",
message, target.getInnerData(), sourceInstanceField);
}
}

@Override
public TargetClass processTarget(TargetClass.TargetInnerRec innerRec) {
SourceMemberInner memberInner = new SourceMemberInner(innerRec);
return memberInner.instanceMethod(innerRec);
}
}

protected class TargetClass {
private String targetInstanceField;
private TargetInnerRec innerRec;

public TargetClass(String fieldValue) {
this.targetInstanceField = fieldValue;

Runnable targetAnonInner = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous inner: " + targetInstanceField);
}
};
targetAnonInner.run();
}

public static class TargetInnerRec {
private String innerRecData;

public TargetInnerRec(String data) {
this.innerRecData = data;
}

public String getInnerData() {
return innerRecData;
}

public void setInnerData(String data) {
this.innerRecData = data;
}
}

public String getTargetField() {
return targetInstanceField;
}

public void setInnerRec(TargetInnerRec innerRec) {
this.innerRec = innerRec;
}

public TargetInnerRec getInnerRec() {
return innerRec;
}
}