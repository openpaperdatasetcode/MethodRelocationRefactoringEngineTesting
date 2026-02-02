package test.refactoring;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Annotation for "has_annotation" feature
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorMethodAnnot {}

// Parent interface for call_method overriding
interface CallMethodInterface {
int processInDoWhile(TargetClass target, int count);
}

// SourceClass: sealed (permits), public, with anonymous inner & static nested
sealed public class SourceClass implements CallMethodInterface permits SourceSubClass {
private TargetClass targetField; // Source contains target field (per_condition)

// Static nested class (source_class feature)
public static class SourceStaticNested<T extends TargetClass> {
private T boundedTarget;

public SourceStaticNested(T target) {
this.boundedTarget = target;
}

public String getBoundedData() {
return boundedTarget.getTargetData();
}
}

// Inner class (method_position: source_inner_rec)
public class SourceInnerRec {
@RefactorMethodAnnot // "has_annotation" feature
private void instanceMethod(TargetClass target) {
if (target == null) {
target = new TargetClass("default_target_data"); // Constructor invocation
}
SourceClass.this.targetField = target;

// "with_bounds" feature (use generic static nested with TargetClass bound)
SourceStaticNested<TargetClass> boundedNested = new SourceStaticNested<>(target);
String boundedData = boundedNested.getBoundedData();

// Switch case (method feature)
switch (boundedData.length() % 3) {
case 0:
target.setTargetData(boundedData + "_case0");
break;
case 1:
target.setTargetData(boundedData + "_case1");
break;
case 2:
target.setTargetData(boundedData + "_case2");
break;
}

// Variable call (method feature)
variableCall(target, "Processed bounded data: " + boundedData);

// Anonymous inner class (source_class feature)
Runnable anonInner = new Runnable() {
@Override
public void run() {
System.out.println("Anonymous inner: Target final data = " + target.getTargetData());
}
};
anonInner.run();
}

private void variableCall(TargetClass target, String message) {
System.out.printf("%s | Target current data: %s%n", message, target.getTargetData());
}

// Trigger instanceMethod and call_method
public void startProcessing(TargetClass target) {
instanceMethod(target);
int doWhileResult = SourceClass.this.processInDoWhile(target, 2);
System.out.println("Do-while call_method result: " + doWhileResult);
}
}

// call_method: overriding (implements interface), lambda in pos: do-while
@Override
protected int processInDoWhile(TargetClass target, int count) {
int result = 0;
do {
// "(parameters) -> methodBody" (call_method target_feature)
java.util.function.IntUnaryOperator countProcessor = (c) -> c * 2 + target.getTargetData().length();
result = countProcessor.applyAsInt(count);
count--;
} while (count >= 0);
return result;
}
}

// Subclass of SourceClass (sealed permits)
non-sealed class SourceSubClass extends SourceClass {
@Override
protected int processInDoWhile(TargetClass target, int count) {
// Override call_method (overriding feature)
int result = 0;
do {
java.util.function.IntUnaryOperator countProcessor = (c) -> c * 3 + target.getTargetData().length();
result = countProcessor.applyAsInt(count);
count--;
} while (count >= 0);
return result;
}
}

// TargetClass: protected, with local inner class (target_class feature)
protected class TargetClass {
private String targetData;

public TargetClass(String targetData) {
this.targetData = targetData;

// Local inner class (target_class feature)
class TargetLocalInner {
public String formatData(String data) {
return "local_inner_formatted_" + data;
}
}
TargetLocalInner localInner = new TargetLocalInner();
this.targetData = localInner.formatData(targetData);
}

public String getTargetData() {
return targetData;
}

public void setTargetData(String targetData) {
this.targetData = targetData;
}
}

// Test entry
public class TestRefactor {
public static void main(String[] args) {
TargetClass target = new TargetClass("init_data");

// Test SourceClass
SourceClass source = new SourceClass();
SourceClass.SourceInnerRec sourceInner = source.new SourceInnerRec();
sourceInner.startProcessing(target);

// Test SourceSubClass (sealed permit)
TargetClass targetSub = new TargetClass("sub_init_data");
SourceSubClass sourceSub = new SourceSubClass();
SourceSubClass.SourceInnerRec sourceSubInner = sourceSub.new SourceInnerRec();
sourceSubInner.startProcessing(targetSub);
}
}