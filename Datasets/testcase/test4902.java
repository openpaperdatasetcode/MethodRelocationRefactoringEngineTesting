package test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Parent generic class for super constructor invocation
abstract class ParentGenericClass<T> {
protected T parentData;

public ParentGenericClass(T data) {
this.parentData = data;
}

public abstract List<String> parentAbstractMethod(int param);
}

// Source: abstract generic class with permits, local inner class, member inner class
sealed abstract class SourceClass<T> extends ParentGenericClass<T> permits SourceSubClass1, SourceSubClass2 {
// Source contains target's field (per_condition)
private TargetClass<T> targetField;

// Member inner class (source_feature)
public class SourceInner {
private String innerData;

public SourceInner(String data) {
this.innerData = data;
}

public String getInnerData() {
return innerData;
}
}

// Super constructor invocation (feature)
public SourceClass(T data) {
super(data);
}

// Overloading method 1 (return List<String>)
List<String> overloadingMethod(TargetClass<T> target, int depth) {
List<String> result = new ArrayList<>();
targetField = target; // Store target in source field (per_condition)

// Variable call: use target and its inner recursive class
TargetClass<T>.TargetInnerRec targetInner = target.createInnerRec(depth);
result.add("Target inner data: " + targetInner.getInnerData());

// Expression statement: process data with target's static nested class
String exprResult = TargetClass.TargetStatic.formatData(targetInner.getInnerData().toString());
result.add("Expression result: " + exprResult);

// Synchronized statement
synchronized (targetInner) {
targetInner.addToHistory("synchronized_access");
result.add("Synchronized history size: " + targetInner.getHistorySize());
}

// Used by reflection: access target inner class method
try {
Method addMethod = TargetClass.TargetInnerRec.class.getMethod("addToHistory", String.class);
addMethod.invoke(targetInner, "reflection_add");
result.add("Reflection: history updated");
} catch (Exception e) {
result.add("Reflection error: " + e.getMessage());
}

// Local inner class (source_feature)
class SourceLocalInner {
public List<String> processLocal(TargetClass<T>.TargetInnerRec inner) {
List<String> localResult = new ArrayList<>();
for (String item : inner.getHistory()) {
localResult.add("local_processed: " + item);
}
return localResult;
}
}
SourceLocalInner localInner = new SourceLocalInner();
result.addAll(localInner.processLocal(targetInner));

// Overriding method in switch (method_feature)
switch (depth % 3) {
case 0:
result.addAll(this.overriddenMethod(3)); // "3" + this.methodName(arguments)
break;
case 1:
result.addAll(this.overriddenMethod(depth));
break;
default:
result.add("Switch default: depth=" + depth);
}

// Call target's strictfp method in if/else conditions (call_method)
if (depth > 0) {
String targetResult = target.processWithStrictfp(
targetInner.getInnerData(),
TargetClass.TargetStatic::formatData // ClassName::methodName
);
result.add("Target strictfp result: " + targetResult);
} else {
result.add("If/else: base case reached");
}

// Requires_try_catch
try {
if (depth < 0) {
throw new IllegalArgumentException("Depth cannot be negative");
}
// Recursive call for inner recursion
if (depth > 0) {
result.addAll(overloadingMethod(target, depth - 1));
}
} catch (IllegalArgumentException e) {
result.add("Caught exception: " + e.getMessage());
}

return result;
}

// Overloading method 2 (different parameter type)
List<String> overloadingMethod(TargetClass<T> target, String depthStr) {
List<String> result = new ArrayList<>();
result.add("Overloaded with String: " + depthStr);
try {
int depth = Integer.parseInt(depthStr);
result.addAll(overloadingMethod(target, depth));
} catch (NumberFormatException e) {
result.add("Invalid depth string: " + depthStr);
}
return result;
}

// Overridden method (method_feature: overriding)
@Override
public List<String> parentAbstractMethod(int param) {
List<String> result = new ArrayList<>();
result.add("Overridden parent method: param=" + param);
return result;
}

// Helper method for overriding feature
public List<String> overriddenMethod(int param) {
return parentAbstractMethod(param);
}
}

// Permitted subclasses of SourceClass
final class SourceSubClass1 extends SourceClass<String> {
public SourceSubClass1(String data) {
super(data);
}
}

final class SourceSubClass2 extends SourceClass<Integer> {
public SourceSubClass2(Integer data) {
super(data);
}
}

// Target: final generic class with static nested class (target_feature)
final class TargetClass<T> {
// Static nested class (target_feature)
public static class TargetStatic {
public static String formatData(String input) {
return "formatted_" + input;
}
}

// Recursive inner class (target_inner_rec)
public class TargetInnerRec {
private T innerData;
private int depth;
private List<String> history;
private TargetInnerRec next;

public TargetInnerRec(T data, int depth) {
this.innerData = data;
this.depth = depth;
this.history = new ArrayList<>();
this.history.add("init:depth=" + depth);

// Recursive initialization
if (depth > 0) {
this.next = new TargetInnerRec(data, depth - 1);
}
}

public T getInnerData() {
return innerData;
}

public void addToHistory(String entry) {
history.add(entry);
}

public List<String> getHistory() {
return new ArrayList<>(history);
}

public int getHistorySize() {
return history.size();
}

public TargetInnerRec getNext() {
return next;
}
}

// strictfp method with overriding (call_method)
public strictfp String processWithStrictfp(T data, Function<String, String> processor) {
return processor.apply(data.toString() + "_strictfp");
}

public TargetInnerRec createInnerRec(int depth) {
return new TargetInnerRec((T) ("target_data_" + depth), depth);
}
}