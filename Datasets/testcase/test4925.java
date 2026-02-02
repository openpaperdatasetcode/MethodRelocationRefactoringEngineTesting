package test;

import java.util.List;
import java.util.ArrayList;

// Source: private generic class with 2 static nested classes
private class SourceClass<T> {
// Source private field for access_outer_private
private String outerPrivateField = "source_private_data";
// This.field for SynchronizedStatement target_feature
private List<Object> syncList = new ArrayList<>();

// Static nested class 1
public static class SourceStaticOne {
public U process(U data) {
return data;
}
}

// Static nested class 2 (contains inner class for SynchronizedStatement pos)
public static class SourceStaticTwo<V> {
// Inner class (pos: inner class)
public class InnerSyncClass {
// Public SynchronizedStatement
public void syncProcess(SourceClass source, TargetClass target) {
// SynchronizedStatement with this.field (source.syncList) and "2"
synchronized (source.syncList) {
source.syncList.add(target.getTargetField());
if (source.syncList.size() >= 2) { // "2" in target_feature
source.syncList.clear();
}
}
}
}
}

// Private recursive method to be refactored (method types parameter is:List)
private Object recursiveMethod(TargetClass<T> target, List<T> dataList, int depth) {
// Variable call (use target parameter)
T targetData = target.getTargetField();
dataList.add(targetData);

// Expression statement
String exprResult = SourceStaticOne.<String>process("expr_" + depth);
dataList.add((T) exprResult);

// Access outer private field (access_outer_private)
dataList.add((T) this.outerPrivateField);

// Access instance field (this.syncList)
this.syncList.add(targetData);

// OuterClass.this.x (uses_outer_this) - here SourceClass.this refers to current instance
SourceStaticTwo<T>.InnerSyncClass syncClass = new SourceStaticTwo<T>().new InnerSyncClass();
syncClass.syncProcess(SourceClass.this, target);

// Recursion base case
if (depth <= 0) {
return dataList;
}

// Recursive invocation
return recursiveMethod(target, dataList, depth - 1);
}

// Helper method to start recursion
public Object startRecursion(TargetClass<T> target) {
return recursiveMethod(target, new ArrayList<>(), 3);
}
}

// Target: strictfp generic class with static nested class
strictfp class TargetClass<T> {
private T targetField;

// Static nested class (target_feature)
public static class TargetStatic {
public static U staticProcess(U data) {
return data;
}
}

public TargetClass(T field) {
this.targetField = field;
}

public T getTargetField() {
return targetField;
}

public void setTargetField(T targetField) {
this.targetField = targetField;
}
}