package test;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

// Source: public enum class with 2 static nested classes
public enum SourceEnum {
VALUE1, VALUE2;

// Source contains target's field (per_condition)
private TargetEnum<String> targetField;

// Static nested class 1
static class NestedOne {
private String data;

NestedOne(String data) {
this.data = data;
}

String getData() {
return data;
}
}

// Static nested class 2
static class NestedTwo {
static int getCount() {
return 3;
}
}

/**

Overloaded method to process TargetEnum
@param target Target enum instance
@return List of processed strings
*/
List<String> process(TargetEnum<String> target) {
List<String> result = new ArrayList<>();
this.targetField = target; // Store target in source field
// Variable call
result.add(target.getValue());
// Constructor invocation
NestedOne nested = new NestedOne(target.getValue());
result.add(nested.getData());
// For statement
for (int i = 0; i < NestedTwo.getCount(); i++) {
result.add("item_" + i);
}
// Switch statement
switch (target.ordinal()) {
case 0:
result.add("first_target");
break;
case 1:
result.add("second_target");
break;
default:
result.add("unknown_target");
}
// Raw type usage
Collection rawList = new ArrayList();
rawList.add(target);
result.add(rawList.toString());
// Call target's private method in expression
String callResult = target.new TargetInner("call_arg").processData();
result.add(callResult);
return result;
}

/**

Overloaded method with additional parameter
@param target Target enum instance
@param prefix Prefix string to add
@return List of processed strings with prefix
*/
List<String> process(TargetEnum<String> target, String prefix) {
List<String> baseResult = process(target); // Call overloaded method
List<String> prefixedResult = new ArrayList<>();
for (String item : baseResult) {
prefixedResult.add(prefix + "_" + item);
}
return prefixedResult;
}
}

// Target: protected enum class with type parameter
protected enum TargetEnum<T> {
TARGET1, TARGET2;

private T value;

// Super constructor invocation
TargetEnum() {
super();
}

public T getValue() {
return value;
}

public void setValue(T value) {
this.value = value;
}

// Member inner class using type parameter (type parameter feature)
class TargetInner {
private String innerData;

// Constructor (call_method feature)
TargetInner(String data) {
this.innerData = data;
}

// Private method with this.methodName(arguments) (call_method features)
private String processData() {
return this.formatData(innerData);
}

private String formatData(String data) {
return "processed_" + data + "_" + TargetEnum.this.ordinal();
}
}
}