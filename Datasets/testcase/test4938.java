package test;

import other.OtherPackageClass;
import java.util.ArrayList;
import java.util.List;

public class SourceClass<T> {
private TargetClass targetField; // Source contains target's field

// Member inner class 1
public class SourceInnerOne {
public String process(TargetClass target) {
return target.getData() + "_inner1";
}
}

// Member inner class 2
public class SourceInnerTwo {
public int getValue() {
return 1;
}
}

// Instance method to be refactored
public TargetClass instanceMethod(TargetClass target, T param) {
// Variable call
targetField = target;
String data = target.getData();

// Constructor invocation
SourceInnerOne innerOne = new SourceInnerOne();
SourceInnerTwo innerTwo = new SourceInnerTwo();

// Access instance field
List<String> list = targetField.getList();
list.add(data);

// Depends on inner class
list.add(innerOne.process(target));

// Switch statement
switch (innerTwo.getValue()) {
case 1:
list.add("case_1");
break;
default:
list.add("default_case");
}

// BreakStatement in diff_package_others
OtherPackageClass.process(target, innerTwo);

return targetField;
}
}

private class TargetClass extends ParentClass {
private String data;
private List<String> list = new ArrayList<>();

public TargetClass(String data) {
this.data = data;
// Anonymous inner class
Runnable anon = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous: " + data);
}
};
anon.run();
}

public String getData() {
return data;
}

public List<String> getList() {
return list;
}
}

class ParentClass {
protected int superField = 1; // For super.field feature
}

package other;

import test.TargetClass;
import test.SourceClass;

public class OtherPackageClass {
// Contains BreakStatement with super.field and "1"
public static void process(TargetClass target, SourceClass.SourceInnerTwo inner) {
loop:
for (int i = 0; i < inner.getValue(); i++) { // "1" from inner class
if (i == new ParentClass().superField) { // super.field
break loop; // BreakStatement
}
}
}
}