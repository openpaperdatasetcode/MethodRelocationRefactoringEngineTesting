package source.package;
import target.package.TargetClass;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Annotation for "has_annotation" feature
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RecursiveMethodAnnot {}

// SourceClass: protected, different package, 2 static nested classes
protected class SourceClass {
private int sourceInstanceField = 10;

// 1st static nested class (source_class feature)
public static class SourceStaticNested1 {
public static String processData(String input) {
return "static_nested1_" + input;
}
}

// 2nd static nested class (source_class feature)
public static class SourceStaticNested2 {
public static int calculate(int num) {
return num * 2;
}
}

// Inner class (method_position: source_inner)
public class SourceInner {
private int innerCount = 0;

@RecursiveMethodAnnot // "has_annotation" feature
public synchronized Object recursiveMethod(int depth, TargetClass targetParam) {
if (targetParam == null) {
throw new NullPointerException("TargetClass parameter cannot be null");
}
if (depth < 0) {
return "Recursion end";
}

// Constructor invocation (method feature)
TargetClass.TargetMemberInner targetInner = targetParam.new TargetMemberInner("inner_init_" + depth);

// Lambda: () -> System.out.println(this.value) (method feature)
Runnable lambdaTask = () -> System.out.println("Lambda: Inner count = " + this.innerCount);
lambdaTask.run();

innerCount++;
if (innerCount % 2 == 0) {
variableCall(targetParam, "Skipping even count: " + innerCount);
continue; // "continue statement" (used in implicit loop context of recursion steps)
}

// Access instance method (source's instance method + target's instance method)
String sourceProcessed = SourceStaticNested1.processData(targetParam.getTargetData());
int targetCalculated = targetInner.callTargetInstanceMethod(SourceStaticNested2.calculate(depth));

// Variable call (method feature)
variableCall(targetParam, "Depth: " + depth + ", Processed: " + sourceProcessed + ", Calculated: " + targetCalculated);

// Recursion call
return recursiveMethod(depth - 1, targetParam);
}

private void variableCall(TargetClass target, String message) {
System.out.printf("[SourceInner] %s | Target data: %s%n",
message, target.getTargetData());
}

// Instance method for "access_instance_method"
public String getInnerInfo() {
return "SourceInner[count=" + innerCount + ", sourceField=" + SourceClass.this.sourceInstanceField + "]";
}
}

// Instance method for "access_instance_method"
public int updateSourceField(int delta) {
sourceInstanceField += delta;
return sourceInstanceField;
}
}
</doubaocanvas-part>

plaintext
&#x3C;doubaocanvas-part path=&#x22;target/package/TargetClass.java&#x22; language=&#x22;java&#x22; description=&#x22;TargetClass in different package from SourceClass&#x22;&#x3E;

package target.package;

// TargetClass: public, with member inner class (target_class feature)
public class TargetClass {
private String targetData;

// Member inner class (target_class feature)
public class TargetMemberInner {
private String innerData;

public TargetMemberInner(String innerData) {
this.innerData = innerData;
}

// Instance method for "access_instance_method"
public int callTargetInstanceMethod(int input) {
return input + innerData.length();
}

public String getInnerData() {
return innerData;
}
}

public TargetClass(String targetData) {
this.targetData = targetData;
}

// Instance method for "access_instance_method"
public String getTargetData() {
return targetData;
}

// Instance method for "access_instance_method"
public void setTargetData(String targetData) {
this.targetData = targetData;
}
}
</doubaocanvas-part>

plaintext
&#x3C;doubaocanvas-part path=&#x22;test/package/TestEntry.java&#x22; language=&#x22;java&#x22; description=&#x22;Test entry to validate structure&#x22;&#x3E;

package test.package;

import source.package.SourceClass;
import target.package.TargetClass;

public class TestEntry {
public static void main(String[] args) {
// Initialize source and target (different packages)
SourceClass source = new SourceClass();
SourceClass.SourceInner sourceInner = source.new SourceInner();
TargetClass target = new TargetClass("target_init_data");

// Call recursive method (method contains target parameter: per_condition)
Object recursionResult = sourceInner.recursiveMethod(3, target);
System.out.println("Recursion final result: " + recursionResult);

// Verify instance method access
System.out.println("Source inner info: " + sourceInner.getInnerInfo());
System.out.println("Updated source field: " + source.updateSourceField(5));
}
}