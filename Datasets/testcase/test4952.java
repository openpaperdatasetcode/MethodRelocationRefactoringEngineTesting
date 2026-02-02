package test;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

// Source: private normal class with type parameter, anonymous inner, member inner
private class SourceClass<T> {
private String sourceField = "source_private_field"; // this.field for ContinueStatement

// Member inner class
class SourceMemberInner {
// Private ContinueStatement with this.field and 3 (pos: inner class)
private void processLoop() {
for (int i = 0; i < 5; i++) {
if (i % 3 == 0) {
SourceClass.this.sourceField = "updated_at_" + i; // OuterClass.this.x
continue; // ContinueStatement
}
}
}

// Abstract method feature (modifier: default, pos: ternary operators)
default int abstractMethodProxy(Supplier<Integer> supplier) {
return supplier.get();
}
}

// Normal method to be refactored
List<String> normalMethod(TargetClass.TargetInnerRec targetInnerRec) {
// Type declaration statement
List<String> result = new ArrayList<>();
SourceMemberInner memberInner = new SourceMemberInner();
T typeParamInstance = (T) "type_param_value";

// Anonymous inner class
Runnable anonRunnable = new Runnable() {
@Override
public void run() {
result.add("anonymous_inner_executed");
memberInner.processLoop(); // Call inner class ContinueStatement method
}
};
anonRunnable.run();

// Empty statement
;

// Variable call (use target parameter)
result.add(targetInnerRec.getInnerData());
// OuterClass.this.x reference
result.add(SourceClass.this.sourceField);

// Assert statement
assert targetInnerRec != null : "TargetInnerRec cannot be null";

// Ternary operators: 1. abstract method feature; 2. call_method
int ternaryResult = (targetInnerRec.getCount() > 0)
? memberInner.abstractMethodProxy(() -> 1) // abstract method + "1" in method_feature
: (int) TargetClass.callTargetMethod(targetInnerRec, "ternary_arg"); // call_method (target class, ClassName.methodName(arguments))

result.add(String.valueOf(ternaryResult));
return result;
}
}

// Target: public normal class with local inner class
public class TargetClass {
// Target inner recursive class (target_inner_rec)
public static class TargetInnerRec {
private String innerData;
private int count;

public TargetInnerRec(String data, int count) {
this.innerData = data;
this.count = count;
// Local inner class (target_feature)
class TargetLocalInner {
void init() {
TargetInnerRec.this.innerData = "init_" + data; // Access outer inner class field
}
}
new TargetLocalInner().init();
}

// Getter for variable call
public String getInnerData() {
return innerData;
}

public int getCount() {
return count;
}
}

// Static method for call_method (ClassName.methodName(arguments))
public static Object callTargetMethod(TargetInnerRec innerRec, String arg) {
return innerRec.getInnerData() + "_" + arg;
}
}