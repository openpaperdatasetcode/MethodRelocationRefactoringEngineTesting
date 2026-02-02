package test.refactoring;
class OthersClass {
private TargetClass targetField;

public OthersClass(TargetClass target) {
this.targetField = target;
}

protected TargetClass instanceMethod1() {
return targetField;
}

protected TargetClass instanceMethod2(String data) {
targetField.setTargetData(data);
return targetField;
}
}

sealed abstract class SourceClass permits SourceSubClass {
private TargetClass targetField;

static class SourceStaticNested {
public static void staticHelper(TargetClass target) {
target.setTargetData(target.getTargetData() + "_static_nested");
}
}

class SourceMemberInner {
private String innerField;

public SourceMemberInner(String innerField) {
this.innerField = innerField;
}

private SourceMemberInner createInnerInstance(TargetClass target) {
this.innerField = target.getTargetData() + "_inner_updated";
return new SourceMemberInner(this.innerField);
}

public String getInnerField() {
return innerField;
}
}

private Object normalMethod(TargetClass targetParam) {
if (targetParam == null) {
throw new NullPointerException("TargetClass parameter cannot be null");
}
this.targetField = targetParam;

SourceMemberInner inner = new SourceMemberInner("init_inner_data");
private SourceMemberInner newInner = inner.createInnerInstance(targetParam);

OthersClass others = new OthersClass(targetParam);
TargetClass result1 = null;
TargetClass result2 = null;

for (int i = 0; i < 3; i++) {
try {
result1 = new OthersClass(targetParam).instanceMethod1();
result2 = new OthersClass(targetParam).instanceMethod2("others_method2_data");

if (i == 1) {
SourceStaticNested.staticHelper(targetParam);
break;
}
} catch (Exception e) {
throw new IllegalArgumentException("Processing failed at i=" + i, e);
}
}

callMethod(targetParam, "property_assignment_1");
callMethod(targetParam, "property_assignment_2", newInner.getInnerField());

variableCall(targetParam, "Final target data: " + targetParam.getTargetData());
return new Object[] {result1, result2, newInner.getInnerField()};
}

private void variableCall(TargetClass target, String message) {
System.out.printf("%s | Target data: %s%n", message, target.getTargetData());
}

public void callMethod(TargetClass target, String propValue) {
target.setTargetData(target.getTargetData() + "|" + propValue);
}

public void callMethod(TargetClass target, String propValue, String innerData) {
target.setTargetData(target.getTargetData() + "|" + propValue + "|" + innerData);
}

public abstract void abstractMethod();
}

non-sealed class SourceSubClass extends SourceClass {
@Override
public void abstractMethod() {
TargetClass target = new TargetClass("subclass_target_data");
Object result = normalMethod(target);
System.out.println("Abstract method processed: " + result);
}
}

class TargetClass {
static class TargetStaticNested {
public static String formatData(String data) {
return "formatted_" + data;
}
}

private String targetData;

public TargetClass(String targetData) {
this.targetData = targetData;
}

public String getTargetData() {
return targetData;
}

public void setTargetData(String targetData) {
this.targetData = targetData;
}
}