package test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

// Source: sealed generic class with 2 static nested classes
sealed class SourceClass<T> permits SourceClass.ConcreteSource1, SourceClass.ConcreteSource2 {
// Source contains target's field (per_condition)
protected TargetClass<T> targetField;

// Static nested class 1
static class SourceStaticNested1 {
public static void processTarget(TargetClass target) {
// Empty implementation for target usage
}
}

// Static nested class 2
static class SourceStaticNested2 {
// Inner class containing instance method (source_inner)
public class SourceInner {
private int innerField = 1; // "1" for TryStatement target_feature

// Instance method to be refactored (method types parameter is:Target class)
protected TargetClass<T> instanceMethod(TargetClass<T> targetParam) {
// Variable call
targetField = targetParam;
TargetClass<T> result = targetField;

// Assert statement
assert targetParam != null : "TargetClass parameter cannot be null";

// Used by reflection
try {
Method method = TargetClass.class.getMethod("getValue");
method.invoke(targetParam);
} catch (Exception e) {
// No new exception
}

// Public TryStatement (pos: diff_package_others simulation)
try {
OtherPackageClass.process(targetParam);
this.innerField = 1; // this.field + "1"
} catch (RuntimeException e) {
// No new exception
}

// Public varargs method in while loop
int count = 0;
while (count < 2) {
int varargsResult = handleVarargs(targetParam, "arg1", "arg2");
count++;
}

// Call method in exception handling statements
try {
Object callResult = new OtherPackageClass()
.m1(targetParam)
.m2()
.m3(); // obj.m1().m2().m3()
} catch (Exception e) {
// No new exception
}

return result;
}

// Public varargs method (method_feature: 1, target, varargs, obj.m1().m2().m3())
public int handleVarargs(TargetClass<T> target, String... args) {
return args.length + 1; // "1" in method_feature
}
}
}
}

// Concrete subclasses for sealed SourceClass
final class SourceClass.ConcreteSource1<T> extends SourceClass<T> {}
final class SourceClass.ConcreteSource2<T> extends SourceClass<T> {}

// Target: public generic class with no target features
public class TargetClass<T> {
private T value;

public TargetClass(T value) {
this.value = value;
}

public T getValue() {
return value;
}

public void setValue(T value) {
this.value = value;
}
}

// Other package class (simulate diff_package_others)
package test.other;

import test.TargetClass;

public class OtherPackageClass {
public static <T> void process(TargetClass<T> target) {
// Empty implementation for TryStatement pos
}

// For call_method: constructor + obj.m1().m2().m3()
public OtherPackageClass m1(TargetClass<?> target) {
return this;
}

public OtherPackageClass m2() {
return this;
}

public Object m3() {
return new Object();
}
}