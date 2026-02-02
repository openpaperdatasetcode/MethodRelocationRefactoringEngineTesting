package test;

import java.util.List;
import java.util.ArrayList;
import other.pkg.OtherPackageClass;

// Source: interface with 2 member inner classes
interface SourceInterface {
// Source contains target's field (per_condition)
TargetInterface.TargetStaticNested targetField = TargetInterface.newTargetStaticNested(3);

// Member inner class 1
class SourceInnerOne {
public int getInnerValue() {
return 5;
}
}

// Member inner class 2
class SourceInnerTwo<T extends CharSequence> {
// With bounds: T extends CharSequence
public String processBoundType(T type) {
return type.toString();
}
}

// Normal method to be refactored
default int normalMethod(TargetInterface target) {
// Type declaration statement
SourceInnerOne innerOne = new SourceInnerOne();
SourceInnerTwo<String> innerTwo = new SourceInnerTwo<>();
int result = 0;

// Labeled statement
outerLabel:
for (int i = 0; i < 5; i++) {
for (int j = 0; j < 3; j++) {
if (j == 2) {
break outerLabel;
}
result += i + j;
}
}

// Variable call (use target parameter)
result += target.getTargetValue();
// Access instance method (call target's instance method)
result += target.processTargetData("test_data");

// Depends on static field (use source's targetField static field)
result += targetField.getStaticFieldValue();

// ReturnStatement in diff_package_others
result += OtherPackageClass.processReturn(targetField);

// With bounds usage
result += innerTwo.processBoundType("bounded_string").length();

return result;
}
}

public interface TargetInterface extends java.io.Serializable {
// Static nested class (target_feature)
class TargetStaticNested {
private int staticField;
public TargetStaticNested(int value) {
this.staticField = value;
}
public int getStaticFieldValue() {
return staticField;
}
public void setStaticFieldValue(int value) {
this.staticField = value;
}
}
static TargetStaticNested newTargetStaticNested (int value) {
return new TargetStaticNested (value);
}
int getTargetValue ();
int processTargetData (String data);
}

// Other package class (simulate diff_package_others)
package other.pkg;

import test.TargetInterface;

public class OtherPackageClass {
public static int processReturn (TargetInterface.TargetStaticNested targetStatic) {
// ReturnStatement: target_feature (obj.field: targetStatic.getStaticFieldValue (), "3")
if (targetStatic.getStaticFieldValue () == 3) {
return targetStatic.getStaticFieldValue ();
}
return 0;
}
}