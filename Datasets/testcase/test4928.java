package test;

import other.pkg.TargetSubClass;
import java.util.List;
import java.util.function.Supplier;

// Parent class for SuperConstructorInvocation
class ParentBaseClass {
protected int superField = 1; // For super.field feature

public ParentBaseClass(int value) {
this.superField = value;
}
}

// Source: private normal class with 2 member inner classes
private class SourceClass extends ParentBaseClass {
private String outerPrivateField = "source_private"; // For access_outer_private

// Member inner class 1
public class SourceInnerOne {
public String getOuterPrivate() {
return SourceClass.this.outerPrivateField;
}
}

// Member inner class 2 (source_inner)
public class SourceInnerTwo {
// Instance method to be refactored (method types parameter is:List)
public Object instanceMethod(TargetClass<String> target, List<String> dataList) {
// Variable call
if (target == null) {
throw new NullPointerException("Target cannot be null"); // NullPointerException
}

// Empty statement
;

// Labeled statement
processLabel: {
if (dataList.isEmpty()) {
break processLabel;
}
target.setInnerData(dataList.get(0));
}

// Numbers: "2" + SwitchExpression
int switchInput = 2;
String switchResult = switch (switchInput) {
case 1 -> "one";
case 2 -> "two"; // Match "2"
default -> "other";
};
dataList.add(switchResult);

// Access outer private field
SourceInnerOne innerOne = new SourceInnerOne();
dataList.add(innerOne.getOuterPrivate());

// SuperConstructorInvocation in diff_package_target
TargetSubClass<String> subTarget = new TargetSubClass<>(1); // "1" in target_feature
dataList.add(String.valueOf(subTarget.superField));

// Requires try-catch (reflection)
int subResult = 0;
try {
// Call sub_class method in functional interfaces
Supplier<Integer> subCaller = () -> subTarget.calculate(dataList.size()); // this.methodName(arguments)
subResult = subCaller.get();
} catch (Exception e) {
// Handle exception
}

dataList.add(String.valueOf(subResult));
return dataList;
}
}

// SuperConstructorInvocation (source's own super call)
public SourceClass() {
super(1); // "1" in target_feature
}
}

// Target: private normal class with type parameter & member inner class
private class TargetClass<T> extends ParentBaseClass {
private T innerData;

// Member inner class (target_feature)
public class TargetInner {
private T innerValue;

public TargetInner(T value) {
this.innerValue = value;
}

public T getValue() {
return innerValue;
}
}

// SuperConstructorInvocation
public TargetClass() {
super(1); // "1" in target_feature
}

public T getInnerData() {
return innerData;
}

public void setInnerData(T innerData) {
this.innerData = innerData;
}

public TargetInner createInner(T value) {
return new TargetInner(value);
}
}

// Sub-class of TargetClass in different package (diff_package_target)
package other.pkg;

import test.TargetClass;
import java.util.List;

public class TargetSubClass<T> extends TargetClass<T> {
// Protected normal method for call_method
protected int calculate(int count) {
return count * 2;
}

// SuperConstructorInvocation (pos: diff_package_target)
public TargetSubClass(int value) {
super();
this.superField = value; // super.field access
}
}