import java.util.ArrayList; import java.util.List;
// External class for diff_package_others position (simulates different package context)
package com.external;
public class ExternalHelper {
public static <T> void processList(List<T> list) {}
}
package com.samepkg; // Same package for source & target

// Parent class for source (source_class feature: extends)
class SourceParent {
protected int parentValue = 5;

protected synchronized int parentMethod(int input) {
return input * parentValue;
}
}

// Target normal class (default modifier, with member inner class)
class TargetClass {
// Target inner record (target_inner_rec: target class for method move)
public record TargetInnerRec(String recId, int recValue) {}

// Member inner class (target_feature)
public class TargetMemberInner {
public TargetInnerRec createRec(String id, int val) {
return new TargetInnerRec(id, val);
}
}

public String targetField = "target-data";
}

// Source protected normal class (extends parent, with member/anonymous inner classes)
protected class SourceClass extends SourceParent {
// Member inner class (source_inner: method_position)
public class SourceInner {
private int innerValue = 10;

// Strictfp instance method (return void, no type parameters)
// Per_condition: contains TargetClass.TargetInnerRec parameter
public strictfp void process(TargetClass.TargetInnerRec targetRecParam) {
// Type declaration statement
TargetClass target = new TargetClass();
TargetClass.TargetMemberInner targetInner = target.new TargetMemberInner();

// Constructor invocation (target inner record & member inner class)
TargetClass.TargetInnerRec newRec = targetInner.createRec("new-rec", 20);

// EnhancedForStatement (protected modifier, obj.field access 1 time)
// pos: diff_package_others (uses external package class)
List<String> rawList = new ArrayList(); // Raw_type
rawList.add(target.targetField); // obj.field access (1 time)
com.external.ExternalHelper.processList(rawList);
for (String item : rawList) {
System.out.println("Enhanced for: " + item);
}

// For statement + Continue statement
int sum = 0;
for (int i = 0; i < 5; i++) {
if (i % 2 == 0) {
continue; // Skip even indices
}
// 3 source instance method calls (super.methodName(arguments))
// pos: parameter list of constructors (via lambda in constructor param)
Runnable r1 = new Runnable() {
@Override
public void run() {
sum += SourceParent.super.parentMethod(i); // 1st super call
}
};
Runnable r2 = new Runnable() {
@Override
public void run() {
sum += SourceParent.super.parentMethod(i * 2); // 2nd super call
}
};
Runnable r3 = new Runnable() {
@Override
public void run() {
sum += SourceParent.super.parentMethod(i * 3); // 3rd super call
}
};
r1.run(); r2.run(); r3.run();
}

// 1 private ThisExpression
if (this.innerValue > targetRecParam.recValue()) { // this: SourceInner instance
// Throw statement
throw new IllegalArgumentException("Inner value exceeds target rec value");
}

// Lambda: () -> System.out.println(this.value)
Runnable printLambda = () -> System.out.println(this.innerValue);
printLambda.run();

// Variable call: use target parameter & newRec
System.out.println("Target param recId: " + targetRecParam.recId());
System.out.println("New rec value: " + newRec.recValue());

// No_new_exception: no new checked/unchecked exceptions thrown
}
}

// Anonymous inner class (source_class feature)
private Runnable sourceAnonymous = new Runnable() {
@Override
public void run() {
SourceInner inner = new SourceInner();
TargetClass.TargetInnerRec initRec = new TargetClass.TargetInnerRec("init-rec", 5);
inner.process(initRec); // Variable call: invoke source inner method
}
};

public void triggerProcessing() {
sourceAnonymous.run();
}
}