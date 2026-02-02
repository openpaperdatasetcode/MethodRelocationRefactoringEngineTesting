package test.refactoring;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

// Annotation for "has_annotation" feature
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface OverloadedMethodAnnot {}

// Interface for SourceClass to "implements" (source_class feature)
interface DataProcessor {
String process(Object data);
}

// TargetClass: normal class, abstract modifier, has type parameter + member inner class (target_feature)
abstract class TargetClass<T> {
// Instance field for "access_instance_field"
protected T targetInstanceField;

// Member inner class (target_feature)
public class TargetInner {
private String innerData;

public TargetInner(String innerData) {
this.innerData = innerData;
}

public String getCombinedData() {
return innerData + "|TargetOuterField: " + targetInstanceField;
}
}

public TargetClass(T targetInstanceField) {
this.targetInstanceField = targetInstanceField;
}

// Abstract method (required for abstract class)
public abstract String getTargetTypeInfo();
}

// Concrete subclass of TargetClass (to create instances)
class ConcreteTarget<T> extends TargetClass<T> {
public ConcreteTarget(T targetInstanceField) {
super(targetInstanceField);
}

@Override
public String getTargetTypeInfo() {
return "ConcreteTarget[Type=" + targetInstanceField.getClass().getSimpleName() + ", Value=" + targetInstanceField + "]";
}
}

// SourceClass: normal class, default modifier, same package, has implements/member inner/static nested (source_feature)
class SourceClass implements DataProcessor { // "implements" feature
// Instance field for "access_instance_field"
private String sourceInstanceField = "source_default_field_value";

// Static nested class (source_class feature)
public static class SourceStaticNested {
public static String staticProcess(String input) {
return "StaticNested_Processed: " + input;
}
}

// Member inner class (source_class feature)
public class SourceInner {
public String innerProcess(TargetClass<?> target) {
return "InnerProcessed|TargetInfo: " + target.getTargetTypeInfo();
}
}

// Overloaded method 1 (method type: overloading), return base type (String)
@OverloadedMethodAnnot
public String process(TargetClass<String> targetParam) {
// Per_condition: method contains target parameter
if (targetParam == null) {
return "Error: Target parameter cannot be null";
}

// Try statement (method feature)
try {
// Access instance field: source's field + target's field
String fieldAccessResult = "SourceField: " + sourceInstanceField + "|TargetField: " + targetParam.targetInstanceField;

// If statement (method feature)
String innerResult = "";
if (targetParam.targetInstanceField.length() > 5) {
// Create target's member inner class instance
TargetClass.TargetInner targetInner = targetParam.new TargetInner("long_data_inner");
innerResult = targetInner.getCombinedData();
} else {
TargetClass.TargetInner targetInner = targetParam.new TargetInner("short_data_inner");
innerResult = targetInner.getCombinedData();
}

// Raw type (method feature): use List without generic type
List rawList = new ArrayList();
rawList.add(fieldAccessResult);
rawList.add(innerResult);
rawList.add(SourceStaticNested.staticProcess(targetParam.getTargetTypeInfo()));

// Variable call (method feature)
variableCall(targetParam, "Overloaded method 1 processed");

// Return base type (String)
return String.join("|", rawList.toArray(new String[0]));
} catch (Exception e) {
// no_new_exception: handle exception without rethrowing
return "Process failed: " + e.getMessage();
}
}

// Overloaded method 2 (method type: overloading), return base type (String)
@OverloadedMethodAnnot
public String process(TargetClass<Integer> targetParam) {
// Per_condition: method contains target parameter
if (targetParam == null) {
return "Error: Target parameter cannot be null";
}

try {
// Access instance field
String fieldAccessResult = "SourceField: " + sourceInstanceField + "|TargetField: " + targetParam.targetInstanceField;

// If statement
String innerResult = "";
if (targetParam.targetInstanceField > 100) {
TargetClass.TargetInner targetInner = targetParam.new TargetInner("large_num_inner");
innerResult = targetInner.getCombinedData();
} else {
TargetClass.TargetInner targetInner = targetParam.new TargetInner("small_num_inner");
innerResult = targetInner.getCombinedData();
}

// Raw type
List rawList = new ArrayList();
rawList.add(fieldAccessResult);
rawList.add(innerResult);
rawList.add(SourceStaticNested.staticProcess(targetParam.getTargetTypeInfo()));

// Variable call
variableCall(targetParam, "Overloaded method 2 processed");

// Return base type
return String.join("|", rawList.toArray(new String[0]));
} catch (Exception e) {
return "Process failed: " + e.getMessage();
}
}

// Implement DataProcessor interface method (overloading context)
@Override
public String process(Object data) {
if (data instanceof TargetClass) { return "Interface process: " + ((TargetClass) data).getTargetTypeInfo();
}
return "Interface process: Unsupported data type";
}

// Variable call (method feature)
private void variableCall(TargetClass<?> target, String message) {
System.out.printf("[SourceClass] %s | Target Instance Field: %s%n",
message, target.targetInstanceField);
}

// Setter for source instance field (for test flexibility)
public void setSourceInstanceField(String sourceInstanceField) {
this.sourceInstanceField = sourceInstanceField;
}
}

// Test entry
public class TestEntry {
public static void main(String[] args) {
// Create Source instance
SourceClass source = new SourceClass();

// Test 1: Call overloaded method 1 (TargetClass<String>)
TargetClass<String> targetStr = new ConcreteTarget<>("test_string");
String result1 = source.process(targetStr);
System.out.println("\nOverloaded Method 1 Result (String Target):\n" + result1);

// Test 2: Call overloaded method 2 (TargetClass<Integer>)
TargetClass<Integer> targetInt = new ConcreteTarget<>(123);
source.setSourceInstanceField("updated_source_field");
String result2 = source.process(targetInt);
System.out.println("\nOverloaded Method 2 Result (Integer Target):\n" + result2);

// Test 3: Call interface method (overloading context)
String result3 = source.process(targetStr);
System.out.println("\nInterface Method Result:\n" + result3);
}
}