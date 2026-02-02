package test.refactoring;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

class SourceClass {
private static TargetClass targetField;

protected static TargetClass staticMethod(TargetClass targetParam) throws NoSuchMethodException, IllegalAccessException {
if (targetParam == null) {
throw new NullPointerException("TargetClass parameter cannot be null");
}
targetField = targetParam;

class LocalType {
void processTarget(TargetClass.TargetInner inner) {
SubSourceClass.staticMethod1(inner);
SubSourceClass.staticMethod2(inner);
SubSourceClass.staticMethod3(inner);
}
}

LocalType localType = new LocalType();
TargetClass.TargetInner targetInner = new TargetClass.TargetInner("inner_data");
volatile int switchVar = 1;

String switchResult = switch (switchVar) {
case 1 -> "case_1_processed";
case 2 -> "case_2_processed";
default -> "default_processed";
};

do {
localType.processTarget(targetInner);
variableCall(targetInner, "Processing round: " + switchResult);

Collection rawCollection = new ArrayList();
rawCollection.add(targetParam);
rawCollection.add(targetInner);

Method reflectMethod = TargetClass.TargetInner.class.getMethod("getInnerData");
String reflectResult = (String) reflectMethod.invoke(targetInner);
switchVar = reflectResult.length() > 5 ? 2 : 1;

} while (switchVar != 2);

return targetField;
}

private static void variableCall(TargetClass.TargetInner targetInner, String message) {
System.out.printf("%s | Target inner data: %s%n", message, targetInner.getInnerData());
}
}

class SubSourceClass extends SourceClass {
protected static void staticMethod1(TargetClass.TargetInner inner) {
inner.setInnerData(inner.getInnerData() + "_method1");
}

protected static void staticMethod2(TargetClass.TargetInner inner) {
inner.setInnerData(inner.getInnerData() + "_method2");
}

protected static void staticMethod3(TargetClass.TargetInner inner) {
inner.setInnerData(inner.getInnerData() + "_method3");
}
}

class TargetClass {
private String targetData;

class TargetInner {
private String innerData;

public TargetInner(String innerData) {
this.innerData = innerData;
}

public String getInnerData() {
return innerData;
}

public void setInnerData(String innerData) {
this.innerData = innerData;
}
}

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