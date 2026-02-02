import java.lang.reflect.Method; import java.util.function.IntSupplier;
// Target strictfp normal class (with type parameter & local inner class)
strictfp class TargetClass<T> {
private T targetField;

public TargetClass(T field) {
this.targetField = field;

// Local inner class (target_feature)
class TargetLocalInner {
int processLocal(T data) {
return data.toString().length();
}
}
new TargetLocalInner().processLocal(targetField);
}

public T getTargetField() {
return targetField;
}
}

// Source private normal class (same package, with static & member inner classes)
private class SourceClass {
// Static nested class (source_class feature)
public static class SourceStaticNested {
public static int staticCalculate(int a, int b) {
return a * b;
}
}

// Member inner class (source_class feature)
public class SourceMemberInner {
private int innerValue;

public SourceMemberInner(int value) {
this.innerValue = value;
}

public int getInnerValue() {
return innerValue;
}
}

// Overloading method 1: base (protected access, return base type int)
/**

Overloading method to process TargetClass parameter
@param targetParam TargetClass instance (parameter of target)
@return computed base type (int) result
*/
protected int compute(TargetClass<String> targetParam) {
// Private TryStatement (pos: same_package_others, this.field access 1 time)
SourceMemberInner inner = new SourceMemberInner(5);
int result = 0;
try {
// this.field access (1 time: inner's instance field via getter)
result = inner.getInnerValue() + SourceStaticNested.staticCalculate(2, 3);
// Variable call: use target parameter
String targetData = targetParam.getTargetField();
result += targetData.length();
} catch (NullPointerException e) {
// No_new_exception: no new checked/unchecked exceptions thrown
result = 0;
}
// Empty statement
;
// 1 protected InstanceofExpression
if (targetParam instanceof TargetClass<?>) {
result += 10;
}
// Used_by_reflection: access method via reflection
try {
Method reflectMethod = SourceClass.class.getDeclaredMethod("compute", TargetClass.class);
reflectMethod.setAccessible(true);
Object reflectedResult = reflectMethod.invoke(this, targetParam);
result += (int) reflectedResult;
} catch (Exception e) {
result += 5;
}
// Call overloading method (call_method feature: source, protected, overloading)
IntSupplier supplier = () -> this.compute(targetParam, 10); // instanceReference.methodName(arguments)
result += supplier.getAsInt();
return result;
}

// Overloading method 2: with additional int parameter (for call_method)
/**

Overloading method with additional int parameter
@param targetParam TargetClass instance
@param extra extra int value
@return computed int result
*/
protected int compute(TargetClass<String> targetParam, int extra) {
// Variable call: use target parameter and extra value
return targetParam.getTargetField().length() + extra;
}

// Method to trigger overloading method
public int startCompute(TargetClass<String> targetParam) {
return compute(targetParam);
}
}