package test.refactoring;
import java.lang.reflect.Method;
import java.util.function.Function;

// Parent class for source to inherit (supports parent_class in method_feature)
class ParentSource<T> {
protected T parentField;

public ParentSource(T parentField) {
this.parentField = parentField;
}

protected T getParentField() {
return parentField;
}
}

// SourceClass: private, generic, implements interface, has anonymous inner & static nested
private class SourceClass<T extends TargetClass> extends ParentSource<T> implements Function<T, Object> {
private T targetField; // Source contains target field (per_condition)

// Static nested class (source_class feature)
static class SourceStaticNested {
public static String formatData(U data) {
return "static_nested_" + data.toString();
}
}

public SourceClass(T targetField) {
super(targetField);
this.targetField = targetField;
}

// Overloaded method 1 (method type: overloading)
@Override
protected Object apply(T target) {
if (target == null) {
throw new NullPointerException("TargetClass parameter cannot be null");
}

Object result = null;
try {
// Call varargs method (pos: if/else conditions)
if (target.getTargetData().length() > 5) {
result = varargsMethod(target, "extra_data_1", "extra_data_2");
} else {
result = varargsMethod(target, "short_data");
}

// Used by reflection (first occurrence)
Method reflectMethod = TargetClass.TargetStaticNested.class.getMethod("staticProcess", String.class);
String reflectResult = (String) reflectMethod.invoke(null, target.getTargetData());
variableCall(target, "Reflection 1 result: " + reflectResult);

// Anonymous inner class (source_class feature)
Runnable anonInner = new Runnable() {
@Override
public void run() {
System.out.println("Anonymous inner: Target data = " + target.getTargetData());
}
};
anonInner.run();

// Used by reflection (second occurrence)
Method targetMethod = T.class.getMethod("setTargetData", String.class);
targetMethod.invoke(target, SourceStaticNested.formatData(reflectResult));

} catch (Exception e) {
e.printStackTrace();
}
return result;
}

// Overloaded method 2 (method type: overloading)
protected Object apply(T target, String extraParam) {
Object baseResult = apply(target);
return baseResult + "|" + extraParam;
}

// Varargs method (method_feature: varargs, parent_class)
private T varargsMethod(T target, String... extraArgs) {
String combined = target.getTargetData();
for (String arg : extraArgs) {
combined += "|" + arg;
}
target.setTargetData(combined);
return target; // Return TargetClass Type (method_feature return_type)
}

// Variable call (method feature)
private void variableCall(T target, String message) {
System.out.printf("%s | Target static nested data: %s%n",
message, TargetClass.TargetStaticNested.staticProcess(target.getTargetData()));
}
}

// TargetClass: private, has static nested class (target_class feature)
private class TargetClass {
private String targetData;

// Static nested class (target_class feature)
static class TargetStaticNested {
public static String staticProcess(String data) {
return "target_static_" + data;
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

// Test entry (to validate structure)
class TestEntry {
public static void main(String[] args) {
TargetClass target = new TargetClass("init_data");
SourceClass<TargetClass> source = new SourceClass<>(target);

// Call overloaded methods
Object result1 = source.apply(target);
Object result2 = source.apply(target, "test_extra");

System.out.println("Overload 1 result: " + result1);
System.out.println("Overload 2 result: " + result2);
}
}