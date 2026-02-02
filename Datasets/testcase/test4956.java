package test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface RefactoringTest {
String value();
}

sealed enum SourceEnum extends ParentEnum implements TestInterface permits SourceEnum.Value1, SourceEnum.Value2 {
Value1, Value2;

// Static nested class 1
static class StaticNestedOne {
protected static TargetEnum getTargetInstance() {
return TargetEnum.TargetValue1;
}
}

// Static nested class 2
static class StaticNestedTwo {
public static Object processTarget(TargetEnum target) {
return target;
}
}

// Inner class containing recursive method
class SourceInner {
// Synchronized recursive method with annotation
@RefactoringTest("RecursiveMethodTest")
public synchronized Object recursiveMethod(TargetEnum target, int depth) throws IOException {
// Variable call
if (target == null) {
// Throw statement
throw new IOException("Target cannot be null");
}

// Used by reflection
try {
Method method = TargetEnum.class.getMethod("getValue");
method.invoke(target);
} catch (Exception e) {
// No new exception
}

// Ternary operator with protected static method (OuterClass.InnerClass.methodName())
TargetEnum currentTarget = (depth > 2) ? StaticNestedOne.getTargetInstance() : target;

// For statement with continue
for (int i = 0; i < 5; i++) {
if (i % 2 == 0) {
// Continue statement
continue;
}
StaticNestedTwo.processTarget(currentTarget);
}

// Recursive base case
if (depth <= 0) {
return new Object();
}

// Instance code blocks with inner class call & super.methodName(arguments)
{
InnerCaller caller = new InnerCaller();
caller.callSuperMethod(currentTarget);
}

// Recursive invocation
return recursiveMethod(currentTarget, depth - 1);
}

// Inner class for call_method
class InnerCaller extends ParentCaller {
public Object callSuperMethod(TargetEnum target) {
// super.methodName(arguments) & instance method call
return super.processTarget(target);
}
}
}
}

abstract class ParentEnum {
protected Object processTarget(TargetEnum target) {
return target;
}
}

interface TestInterface {
default void testMethod() {}
}

class ParentCaller {
public Object processTarget(TargetEnum target) {
return target;
}
}

enum TargetEnum {
TargetValue1, TargetValue2;

public String getValue() {
return this.name();
}
}