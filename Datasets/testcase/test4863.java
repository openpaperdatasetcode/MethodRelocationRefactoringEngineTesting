import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

// Custom annotation for method (has_annotation feature)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface InterfaceMethodAnnotation {
String value() default "Protected instance method in source interface";
}

// Target public interface (with static nested class)
public interface TargetInterface {
// Interface field (for source to contain: per_condition)
String TARGET_FIELD = "target-interface-field";

// Static nested class (target_feature)
static class TargetStaticNested {
private String nestedData;

public TargetStaticNested(String data) {
this.nestedData = data;
}

// Method for variable call
public String getNestedInfo() {
return "TargetNested: " + nestedData;
}

// Static method for call_method feature
public static String staticFormat(String input) {
return input + "_formatted";
}
}

// Method to return TargetClass Type (interface method)
TargetInterface createNewInstance(String data);
}

// Permitted class for source interface (source_class feature: permits)
class SourcePermitted implements SourceInterface {
@Override
public TargetInterface createTarget(String data) {
return new TargetInterface() {
@Override
public TargetInterface createNewInstance(String newData) {
return new TargetInterface.TargetStaticNested(newData);
}
};
}
}

// Source interface (no modifier, permits clause + anonymous inner + member inner classes)
interface SourceInterface permits SourcePermitted {
// Member inner class (source_class feature)
class SourceInner {
// Final static method for call_method feature (OuterClass.InnerClass.methodName())
public final static String processSource(String input) {
return "SourceInner: " + input;
}
}

// 1st Anonymous inner class (source_class feature)
Runnable sourceAnon1 = new Runnable() {
@Override
public void run() {
System.out.println("Source anon1: Processing target data");
}
};

// Method to create target instances (for variable call)
TargetInterface createTarget(String data);

// Protected instance method to be moved (returns TargetClass Type)
// Per_condition: source contains target field (TargetInterface.TARGET_FIELD)
@InterfaceMethodAnnotation // has_annotation feature
protected default TargetInterface processTarget(String... data) {
// Type declaration: initialize target nested class
TargetInterface.TargetStaticNested targetNested = new TargetInterface.TargetStaticNested(TargetInterface.TARGET_FIELD);
List<String> processed = new ArrayList<>();

// 1. EnhancedForStatement (private modifier, this.field access 2 times)
// Note: Interface methods use 'this' to reference default method context
for (String item : data) {
// this.field access 1: reference to interface's implicit context via method call
processed.add(this.createTarget(item).toString());
// this.field access 2: another reference to interface's context
processed.add("Length: " + this.createTarget(item).toString().length());
}

// 2. 2 default modifier SwitchExpressions
int dataSize = data.length;
String sizeCategory = switch (dataSize) {
case 0 -> "empty";
case 1 -> "single";
default -> "multiple";
};
processed.add("Size category: " + sizeCategory);

String firstItem = switch (dataSize > 0 ? data[0] : "") {
case "test" -> "special-test";
case "" -> "no-data";
default -> data[0];
};
processed.add("First item: " + firstItem);

// 3. Call_method: source final static method (OuterClass.InnerClass.methodName())
// pos: array initialization
String[] sourceProcessed = {
SourceInner.processSource(firstItem),
SourceInner.processSource(sizeCategory)
};
for (String s : sourceProcessed) {
processed.add(s);
}

// 4. Variable call: use target static nested class method
processed.add(targetNested.getNestedInfo());
processed.add(TargetInterface.TargetStaticNested.staticFormat(firstItem));

// Trigger anonymous inner class
sourceAnon1.run();

// Return TargetClass Type (new instance)
return new TargetInterface() {
@Override
public TargetInterface createNewInstance(String newData) {
return new TargetInterface.TargetStaticNested(newData);
}

@Override
public String toString() {
return String.join("; ", processed);
}
};
}

// Default method to trigger protected method
default TargetInterface triggerProcessing(String... data) {
return processTarget(data); // Variable call: invoke protected method
}
}

// Test entry point
class InterfaceMethodTest {
public static void main(String[] args) {
// Initialize source via permitted class
SourceInterface source = new SourcePermitted();

// Trigger method to be moved
TargetInterface result = source.triggerProcessing("apple", "banana", "cherry");
System.out.println("Processed result: " + result);

// Verify source contains target field
System.out.println("Target field from source: " + TargetInterface.TARGET_FIELD);
}
}