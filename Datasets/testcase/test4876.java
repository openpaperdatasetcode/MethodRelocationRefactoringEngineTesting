public class MethodMoveTestContext { // Target protected normal class (no target_features) protected static class TargetClass { private int targetNum; private String targetStr;
public TargetClass(int num, String str) {
this.targetNum = num;
this.targetStr = str;
}

public int getTargetNum() {
return targetNum;
}

public String getTargetStr() {
return targetStr;
}
}

// Source protected normal class (same package, with local/member inner classes)
protected static class SourceClass {
// Instance field (for access_instance_field feature)
private int sourceInstanceField = 20;

// Member inner class (source_class feature)
protected class SourceMemberInner {
// Method for variable call feature
public int multiply(int a, int b) {
return a * b;
}
}

// ------------------------------
// Overloaded methods (overload_exist feature)
// ------------------------------
// Overload 1: TargetClass parameter (method to be moved)
public final int calculate(TargetClass targetParam) { // per_condition: contains target parameter
// Access_instance_field: access source's instance field
int base = sourceInstanceField;

// Variable call: use target parameter methods
int targetNum = targetParam.getTargetNum();
String targetStr = targetParam.getTargetStr();

// Variable call: use member inner class
SourceMemberInner inner = new SourceMemberInner();
int multiplied = inner.multiply(base, targetNum);

// 1 default modifier SwitchExpression
int strLen = targetStr.length();
int switchResult = switch (strLen % 3) {
case 0 -> 10;
case 1 -> 20;
default -> 30; // Default branch
};

// No_new_exception: no new checked/unchecked exceptions thrown
return multiplied + switchResult;
}

// Overload 2: int + String parameters (proves overload_exist)
public final int calculate(int num, String str) {
TargetClass tempTarget = new TargetClass(num, str);
return calculate(tempTarget); // Variable call: invoke overloaded method
}

// Method with local inner class (source_class feature)
public void useLocalInner() {
// Local inner class (source_class feature)
class SourceLocalInner {
public void logResult(TargetClass target) {
int result = calculate(target); // Variable call: invoke method to be moved
System.out.println("Local inner log: " + result);
}
}

TargetClass testTarget = new TargetClass(5, "test-data");
new SourceLocalInner().logResult(testTarget);
}
}

// Test entry point
public static void main(String[] args) {
SourceClass source = new SourceClass();
TargetClass target = new TargetClass(3, "sample");

// Trigger method to be moved
int result1 = source.calculate(target);
System.out.println("Direct call result: " + result1);

// Trigger overloaded method
int result2 = source.calculate(4, "overload-test");
System.out.println("Overload call result: " + result2);

// Trigger local inner class usage
source.useLocalInner();
}
}