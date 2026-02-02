package test;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Source: @interface with 2 local inner classes
@interface SourceAnnotation {
// Public varargs method to be refactored (contains target parameter per per_condition)
public void varargsMethod(TargetAnnotation target, String... args);

// Default implementation of varargsMethod (since Java 8, interfaces/annotations support default methods)
default void varargsMethod(TargetAnnotation target, String... args) {
// 1. Constructor invocation: create target's member inner class instance
TargetAnnotation.TargetInner targetInner = target.new TargetInner("init_data");
System.out.println("TargetInner initialized with: " + targetInner.getInnerData());

// 2. Variable call: use target and its inner class methods/fields
String targetBase = target.targetBase(); // Call target annotation's abstract method
String innerData = targetInner.getInnerData();
System.out.println("Target base value: " + targetBase + ", Inner data: " + innerData);

// 3. numbers: "1" + modifier: protected + exp: TypeMethodReference
// Protected TypeMethodReference (via target's generic method with bound)
Function<String, String> stringProcessor = target::processWithBound; // TypeMethodReference
String processedArg = stringProcessor.apply(args.length > 0 ? args[0] : "default_1"); // "1" in logic (default for args[0])
System.out.println("Processed string (TypeMethodReference): " + processedArg);

// 4. with_bounds: use target's generic method with CharSequence bound
List<CharSequence> boundList = new ArrayList<>();
boundList.add(targetBase);
boundList.add(innerData);
for (CharSequence seq : boundList) {
String boundedResult = target.processWithBound(seq); // Bound: CharSequence
System.out.println("Bounded processing result: " + boundedResult);
}

// 5. depends_on_inner_class: rely on target's inner class to process varargs
targetInner.processVarargs(args);
System.out.println("Varargs processed by TargetInner: " + String.join(",", targetInner.getProcessedList()));

// 6. used_by_reflection 1: access target's member inner class method
try {
Method innerGetMethod = TargetAnnotation.TargetInner.class.getMethod("getProcessedList");
@SuppressWarnings("unchecked")
List<String> reflectedList = (List<String>) innerGetMethod.invoke(targetInner);
System.out.println("Reflected TargetInner processed list: " + reflectedList);
} catch (Exception e) {
// No new exception thrown
System.out.println("Reflection 1 handled exception: " + e.getMessage());
}

// 7. used_by_reflection 2: access target's generic method
try {
Method targetGenericMethod = TargetAnnotation.class.getMethod("processWithBound", CharSequence.class);
String reflectedGenericResult = (String) targetGenericMethod.invoke(target, "reflection_test_1"); // "1" in value
System.out.println("Reflected target generic method result: " + reflectedGenericResult);
} catch (Exception e) {
// No new exception thrown
System.out.println("Reflection 2 handled exception: " + e.getMessage());
}

// 8. 1st Local inner class (source_feature): process varargs metadata
class SourceLocalOne {
public void logVarargsMetadata(String[] args) {
System.out.println("LocalOne: Varargs length = " + args.length);
for (int i = 0; i < args.length; i++) {
System.out.println("LocalOne: Arg[" + i + "] = " + args[i]);
}
}
}
new SourceLocalOne().logVarargsMetadata(args);

// 9. 2nd Local inner class (source_feature): validate target data
class SourceLocalTwo {
public boolean validateTarget(TargetAnnotation target) {
return target.targetBase() != null && !target.targetBase().isEmpty();
}
}
boolean isValid = new SourceLocalTwo().validateTarget(target);
System.out.println("LocalTwo: Target validation result = " + isValid);
}

// Abstract method (required for @interface, can be used as metadata)
String sourceMetadata() default "source_default_metadata";
}

// Target: protected @interface with type parameter and member inner class (target_feature)
protected @interface TargetAnnotation {
// 1. Type parameter: generic method with CharSequence bound (target_feature: type parameter)
<T extends CharSequence> String processWithBound(T data);

// 2. Member inner class (target_feature: member inner class)
class TargetInner {
private String innerData;
private List<String> processedList = new ArrayList<>();

// Constructor for inner class (used in source's constructor invocation)
public TargetInner(String data) {
this.innerData = data;
}

// Getter for variable call
public String getInnerData() {
return innerData;
}

// Method to process varargs (for depends_on_inner_class)
public void processVarargs(String... args) {
processedList.clear();
for (String arg : args) {
processedList.add("inner_processed:" + arg);
}
}

// Getter for processed varargs (used in reflection)
public List<String> getProcessedList() {
return new ArrayList<>(processedList);
}
}

// Abstract method (required for @interface, used in source's variable call)
String targetBase() default "target_default_base";

// Required method for @interface (overrides Annotation interface)
@Override
Class<? extends Annotation> annotationType() default TargetAnnotation.class;

// Default implementation of generic method (type parameter)
default <T extends CharSequence> String processWithBound(T data) {
return "target_bounded:" + data + "_length=" + data.length();
}
}