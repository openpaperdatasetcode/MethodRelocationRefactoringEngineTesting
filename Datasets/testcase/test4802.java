package test.refactoring;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

// Parent class for enum-related static method calls (call_method: parent_class type)
class EnumParentClass {
// Synchronized method for call_method (modifier: synchronized)
public synchronized Object parentCallMethod(TargetEnum targetEnum) {
// target_feature: instanceReference.methodName(arguments)
return "EnumParentClass[Processed=" + targetEnum.getTargetData() +
", InnerData=" + targetEnum.new TargetInner().getInnerInfo() + "]";
}
}

// TargetClass: enum class, strictfp modifier, has member inner class (target_feature)
strictfp enum TargetEnum {
TARGET1("target1_data"),
TARGET2("target2_data");

private final String targetData;

// Member inner class (target_feature)
public class TargetInner {
public String getInnerInfo() {
// uses_outer_this: access outer enum instance via this
return "TargetInner[OuterEnum=" + TargetEnum.this.name() +
", OuterData=" + TargetEnum.this.targetData + "]";
}
}

TargetEnum(String targetData) {
this.targetData = targetData;
}

public String getTargetData() {
return targetData;
}

// Static method for source's static method feature
public static synchronized TargetEnum getTargetByData(String data) {
for (TargetEnum target : values()) {
if (target.targetData.equals(data)) {
return target;
}
}
throw new IllegalArgumentException("No TargetEnum found for data: " + data);
}
}

// SourceClass: enum class, default modifier, same package, has member inner/static nested (source_feature)
enum SourceEnum {
SOURCE1("source1_config"),
SOURCE2("source2_config");

private final String sourceConfig;
private final EnumParentClass parentInstance;

// Static nested class (source_class feature)
public static class SourceStaticNested {
// Static method for ClassName.methodName(arguments)
public static String staticProcess(String input) {
return "SourceStaticNested[Processed=" + input + "]";
}
}

// Member inner class (source_class feature)
public class SourceInner {
public String innerProcess(TargetEnum target) {
// uses_outer_this: access outer enum instance via this
return "SourceInner[OuterSource=" + SourceEnum.this.name() +
", TargetData=" + target.getTargetData() + "]";
}
}

// Enum constructor: this(arguments) (implicit, explicit via instance initialization)
SourceEnum(String sourceConfig) {
this.sourceConfig = sourceConfig;
this.parentInstance = new EnumParentClass(); // this(arguments) context for parent instance
}

// Method: normal type, return TargetClass Type, public access, position source
public TargetEnum processTarget(TargetEnum targetParam) {
// Per_condition: method contains target parameter
if (targetParam == null) {
throw new IllegalArgumentException("TargetEnum parameter cannot be null");
}

// Labeled statement (method feature)
processLabel: {
try {
// Static method feature: synchronized, parent_class, ClassName.methodName(arguments)
// pos: exception throwing statements (wrapped in try-catch)
TargetEnum staticTarget = TargetEnum.getTargetByData(targetParam.getTargetData());

// Expression statement: call static nested class method
String staticResult = SourceStaticNested.staticProcess(staticTarget.getTargetData());
// Expression statement: call member inner class method
String innerResult = new SourceInner().innerProcess(staticTarget);

// Variable call (method feature)
variableCall(staticTarget, "Processed in labeled block: " + staticResult);

// used_by_reflection (method feature): call TargetEnum's method via reflection
Method reflectMethod = TargetEnum.class.getMethod("getTargetData");
String reflectResult = (String) reflectMethod.invoke(staticTarget);
System.out.println("Reflection result: " + reflectResult);

// call_method: parent_class type, pos: functional interfaces
Function<TargetEnum, Object> parentFunc = this.parentInstance::parentCallMethod;
Object parentResult = parentFunc.apply(staticTarget);
System.out.println("Parent call method result: " + parentResult);

return staticTarget;
} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
// no_new_exception: handle exception without rethrowing
System.err.println("Reflection error: " + e.getMessage());
break processLabel; // Exit labeled block on error
}
}

return targetParam; // Fallback return
}

// Variable call (method feature)
private void variableCall(TargetEnum target, String message) {
System.out.printf("[SourceEnum:%s] %s | TargetName=%s, TargetData=%s%n",
this.name(), message, target.name(), target.getTargetData());
}

// uses_outer_this: outer enum instance accessor for inner use
public SourceEnum getOuterThis() {
return this;
}
}

// Test entry
public class TestEntry {
public static void main(String[] args) {
// Test 1: Source1 process Target1
TargetEnum target1 = TargetEnum.TARGET1;
TargetEnum result1 = SourceEnum.SOURCE1.processTarget(target1);
System.out.println("\nTest 1 Result: Source1 -> " + result1.name());

// Test 2: Source2 process Target2
TargetEnum target2 = TargetEnum.TARGET2;
TargetEnum result2 = SourceEnum.SOURCE2.processTarget(target2);
System.out.println("\nTest 2 Result: Source2 -> " + result2.name());

// Test 3: Process null target (exception case)
try {
SourceEnum.SOURCE1.processTarget(null);
} catch (IllegalArgumentException e) {
System.out.println("\nTest 3 Result: " + e.getMessage());
}
}
}