// Package for source class (different from target package)
package test.source;

import test.target.TargetClass;
import java.lang.reflect.Method;
import java.util.List;

public class SourceClass {
// Source contains target's field (per_condition)
private TargetClass targetField;

// Overloading method 1 (return TargetClass Type)
TargetClass overloadingMethod(TargetClass target, String data) {
this.targetField = target; // Assign target to source field
TargetClass result = null;

// Constructor invocation: create target's static nested class instance
TargetClass.TargetStatic nestedInstance = new TargetClass.TargetStatic(data);

// Variable call: use target instance and its static nested class
String targetData = target.getTargetData();
String nestedData = nestedInstance.getStaticData();

// Try statement
try {
// Variable call: invoke target's method via reflection (no_new_exception)
Method getMethod = TargetClass.class.getMethod("getTargetData");
String reflectedData = (String) getMethod.invoke(target);
result = new TargetClass(reflectedData + "_processed");
} catch (Exception e) {
// No new exception thrown
result = new TargetClass(targetData);
}

// ReturnStatement (same_package_target, obj.field, "1")
return result.getTargetCount() > 1 ? result : target;
}

// Overloading method 2 (overload_exist feature)
TargetClass overloadingMethod(TargetClass target, int count) {
// Local inner class (source_feature)
class SourceLocalInner {
String formatCount(int num) {
return "count_" + num;
}
}
SourceLocalInner localInner = new SourceLocalInner();

// Anonymous inner class (source_feature)
Runnable anonTask = new Runnable() {
@Override
public void run() {
System.out.println("Processed count: " + count);
}
};
anonTask.run();

// Delegate to overloading method 1
return overloadingMethod(target, localInner.formatCount(count));
}

// Getter for target field (for verification)
public TargetClass getTargetField() {
return targetField;
}
}

// Package for target class (different from source package)
package test.target;

import java.util.ArrayList;
import java.util.List;

// Target: private normal class (implements interface + static nested class)
class TargetClass implements DataHandler {
private String targetData;
private int targetCount = 1; // obj.field with value "1" for ReturnStatement

// Static nested class (target_feature)
public static class TargetStatic {
private String staticData;

public TargetStatic(String data) {
this.staticData = data;
}

public String getStaticData() {
return staticData;
}
}

// Constructor invocation (used in source)
public TargetClass(String data) {
this.targetData = data;
}

// Variable call (used in source)
public String getTargetData() {
return targetData;
}

public int getTargetCount() {
return targetCount;
}

public void setTargetCount(int count) {
this.targetCount = count;
}

// Implement interface method (target_feature: implements)
@Override
public List<String> handleData(String data) {
List<String> handled = new ArrayList<>();
handled.add("handled_" + data);
return handled;
}
}

// Interface for target to implement (target_feature: implements)
package test.target;

import java.util.List;

interface DataHandler {
List<String> handleData(String data);
}
