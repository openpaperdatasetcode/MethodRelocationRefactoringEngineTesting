package test;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation {
String callRecursive() default "OthersClass.recursiveCall(superTypeRef, 1)";
}

interface TestInterface {
default List<String> getDefaultList() {
return new ArrayList<>();
}
}

abstract class ParentClass {
protected String parentField = "parent_data";

protected abstract List<String> parentAbstractMethod();
}

abstract class SourceClass extends ParentClass implements TestInterface {
private TargetClass targetField; // Source contains target's field (per_condition)

// Member inner class
class SourceInner {
private List<String> innerList;

// Private constructor (method to be refactored)
@MethodAnnotation
private SourceInner(TargetClass target) {
this.targetField = target; // Access target field (source contains target's field)

// With bounds
this.innerList = processWithBounds(new ArrayList<>());

// Abstract method feature in property assignment
this.innerList = (target != null) ?
parentAbstractMethod() :
getDefaultList();

// Synchronized statement
synchronized (this) {
// Variable call
innerList.add(target.getTargetData());
// Access instance field
innerList.add(parentField);
}

// Call method in annotation's attribute values
invokeAnnotatedCall();
}

// With bounds implementation
private <T extends List<String>> T processWithBounds(T list) {
list.add("bounded_value");
return list;
}

// Helper method to trigger annotation-based call
private void invokeAnnotatedCall() {
// Call others_class recursive method with superTypeReference
List<String> result = OthersClass.recursiveCall((TestInterface) this, 1);
innerList.addAll(result);
}
}

@Override
protected List<String> parentAbstractMethod() {
return List.of("parent_abstract_result");
}
}

private abstract class TargetClass {
protected String targetData;

public TargetClass(String data) {
this.targetData = data;
}

public String getTargetData() {
return targetData;
}

public abstract void abstractTargetMethod();
}

class OthersClass {
// Public recursive method (call_method features)
public static List<String> recursiveCall(TestInterface superTypeRef, int depth) {
List<String> result = new ArrayList<>();
result.add("recursive_call_" + depth);

// Recursion base case
if (depth >= 3) {
return result;
}

// Recursive invocation (superTypeReference.methodName(arguments))
List<String> subResult = recursiveCall(superTypeRef, depth + 1);
result.addAll(subResult);
return result;
}
}