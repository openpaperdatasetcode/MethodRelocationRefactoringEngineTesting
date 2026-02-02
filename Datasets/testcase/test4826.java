package test.refactoring;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class OthersClass {
public final void varargsMethod(Object... args) {
if (args == null || args.length < 3) {
throw new IllegalArgumentException("At least 3 args required");
}
System.out.printf("Varargs processed: %s, %s, %s%n", args[0], args[1], args[2]);
}
}

strictfp class SourceClass {
class SourceMemberInner {
private String innerField1 = "inner_field1";
private String innerField2 = "inner_field2";
private String innerField3 = "inner_field3";

public void setProperty(Consumer<String> consumer) {
consumer.accept(innerField1);
}

public String getField1() { return innerField1; }
public String getField2() { return innerField2; }
public String getField3() { return innerField3; }
}

Runnable sourceAnonInner = new Runnable() {
@Override
public void run() {
System.out.println("Source anonymous inner class executed");
}
};

/**

Recursive method to process TargetClass instances and collect results
@param target Param of TargetClass to process
@param depth Recursion depth (must be non-negative)
@return List of processed strings from recursion
*/
default List<String> recursiveMethod(TargetClass target, int depth) {
if (target == null) {
throw new NullPointerException("TargetClass parameter cannot be null");
}
if (depth < 0) {
throw new IllegalArgumentException("Recursion depth cannot be negative");
}
class LocalType {
String processTargetField(TargetClass t) {
return t.targetField + "_local_processed";
}
}
LocalType localType = new LocalType();
List<String> result = new ArrayList<>();
SourceMemberInner memberInner = new SourceMemberInner();
OthersClass others = new OthersClass();
private TargetClass tc1 = new TargetClass(memberInner.getField1());
private TargetClass tc2 = new TargetClass(memberInner.getField2());
private TargetClass tc3 = new TargetClass(memberInner.getField3());
try {
others.varargsMethod(tc1.targetField, tc2.targetField, tc3.targetField)
.toString()
.toLowerCase()
.trim();
result.add(localType.processTargetField(target));
result.add("Depth: " + depth);
variableCall(target, "Processed at depth: " + depth);
memberInner.setProperty(target::setTargetField);
if (depth > 0) {
result.addAll(recursiveMethod(target, depth - 1));
}
} catch (Exception e) {
result.add("Exception occurred: " + e.getMessage());
}
return result;
}

private void variableCall(TargetClass target, String message) {
System.out.printf("Variable call: %s | Target field: %s%n", message, target.targetField);
}
}

class TargetClass {
String targetField;

Runnable targetAnonInner = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous inner class executed | Field: " + targetField);
}
};

public TargetClass(String targetField) {
this.targetField = targetField;
}

public void setTargetField(String targetField) {
this.targetField = targetField;
}
}