package test;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RecursionAnnotation {}

// Parent class for super constructor invocation and method_feature
class ParentAbstractClass {
protected int parentField = 1; // "1" in method_feature

public void parentInstanceMethod(int param) {
System.out.println("Parent instance method: param=" + param);
}
}

// Source: default abstract class with type parameter, permits, 2 static nested classes
sealed abstract class SourceClass<T> extends ParentAbstractClass permits SourceSubClass1, SourceSubClass2 {
// Source instance field (for access_instance_field and this.field)
private TargetClass targetField;
private int sourceCount = 0;

// Static nested class 1
public static class SourceStaticOne {
public static void staticProcess() {
System.out.println("SourceStaticOne processing");
}
}

// Static nested class 2
public static class SourceStaticTwo {
public static void genericStaticProcess(U data) {
System.out.println("SourceStaticTwo generic data: " + data);
}
}

// Recursive inner class (source_inner_rec)
public class SourceInnerRec {
// Final recursive method (method types parameter is:none)
@RecursionAnnotation // has_annotation
public final void recursiveMethod(TargetClass target, int depth) {
// Super constructor invocation (implicit via SourceClass extending ParentAbstractClass)
super.parentField = depth;

// Variable call: assign target to source field & use target
targetField = target;
target.executeTargetLogic();

// Access instance field (source's instance field)
sourceCount++;
System.out.println("Source count: " + sourceCount);

// DoStatement (modifier: static, pos: source, target_feature: this.field, "2")
int doCount = 0;
do {
SourceStaticOne.staticProcess(); // static modifier
doCount++;
} while (doCount < 2 && this.sourceCount < 5); // this.field (sourceCount) + "2"

// Instance method in object initialization (method_feature)
ParentAbstractClass parentInstance = new ParentAbstractClass() {
@Override
public void parentInstanceMethod(int param) {
SourceInnerRec.this.recursiveHelper(param); // this.methodName(arguments)
}
};
parentInstance.parentInstanceMethod(1); // "1" + parent_class + instance

// Recursion base case
if (depth <= 0) {
return;
}

// Recursive invocation (source_inner_rec)
new SourceInnerRec().recursiveMethod(targetField, depth - 1);
}

// Helper method for this.methodName(arguments)
private void recursiveHelper(int param) {
System.out.println("Recursive helper param: " + param);
SourceStaticTwo.genericStaticProcess(param);
}
}

// Abstract method (required for abstract class)
public abstract T abstractSourceMethod();

// Helper method to create inner recursive class instance
public SourceInnerRec createInnerRec() {
return new SourceInnerRec();
}
}

// Permitted subclasses of SourceClass (to satisfy "permits" feature)
final class SourceSubClass1 extends SourceClass<String> {
@Override
public String abstractSourceMethod() {
return "SourceSubClass1 result";
}
}

final class SourceSubClass2 extends SourceClass<Integer> {
@Override
public Integer abstractSourceMethod() {
return 100;
}
}

// Target: final abstract class with anonymous inner class (target_feature)
final abstract class TargetClass {
private String targetData = "default_target_data";

public TargetClass() {
// Anonymous inner class (target_feature)
Runnable anonTask = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous class: " + targetData);
}
};
anonTask.run();
}

// Method for variable call
public void executeTargetLogic() {
System.out.println("Target logic executed: " + targetData);
}

// Abstract method (required for abstract class)
public abstract void abstractTargetMethod();
}