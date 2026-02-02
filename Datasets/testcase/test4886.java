com.source.privatepkg;
import com.target.TargetClass;
import java.lang.reflect.Method;

// Source private normal class (different package from target, with inner classes)
private class SourceOverridingClass extends ParentForOverriding {
// Private outer field (for access_outer_private)
private String outerPrivateField = "source-private-data";
// Instance field (for access_instance_field)
private int outerInstanceInt = 100;

// Member inner class (source_class feature)
private class SourceMemberInner {
public String formatInput(String input) {
return input + "_formatted";
}
}

// Overriding method (final access, return TargetClas Type)
// Per_condition: contains TargetClass parameter
@Override
public final TargetClass process(TargetClass targetParam) {
// Variable call: use member inner class
SourceMemberInner inner = new SourceMemberInner();
String formatted = inner.formatInput(targetParam.targetData);

// Access_outer_private: access source's private field
if (outerPrivateField == null) {
// Throw statement
throw new IllegalArgumentException("Outer private field cannot be null");
}

// Access_instance_field: access source's instance field
int computed = outerInstanceInt + targetParam.targetValue;

// Depends_on_static_field: use target's static field
String staticVal = TargetClass.TARGET_STATIC_FIELD;
targetParam.targetData = staticVal + formatted;

// Used_by_reflection: access method via reflection
try {
Method reflectMethod = TargetClass.class.getMethod("getFormattedData");
Object reflectedResult = reflectMethod.invoke(targetParam);
} catch (Exception e) {
// No_new_exception: no new checked/unchecked exceptions thrown
e.printStackTrace();
}

// Override_violation: parent method is public, this override is final (narrower behavior)
return targetParam;
}

// Method with local inner class (source_class feature)
public void useLocalInner() {
class LocalInner {
void log(String msg) {
System.out.println("Log: " + msg);
}
}
new LocalInner().log(outerPrivateField);
}
}

// Parent class for overriding (Scenario 1)
class ParentForOverriding {
public abstract TargetClass process(TargetClass targetParam);
}

package com.target;

/**

Target normal class with javadoc and member inner class (Scenario 1: ID 71342)
*/
class TargetClass {
// Static field (for depends_on_static_field)
public static final String TARGET_STATIC_FIELD = "target-static-";
// Instance fields
String targetData;
int targetValue;
// Member inner class (target_feature)
public class TargetMemberInner {
public String getDetail() {
return targetData + "-detail";
}
}
public TargetClass(String data, int value) {
this.targetData = data;
this.targetValue = value;
}
public String getFormattedData() {
return targetData.toUpperCase();
}
}

// ------------------------------ Scenario 2: ID 86022 ------------------------------
package com.samepkg;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Parent class for target (extends feature, Scenario 2)
class TargetParentClass {
protected String parentData = "parent-base-data";

public TargetClass processTargetData(String input) {
return new TargetClass(input + "_parent-processed");
}
}

// Target private normal class (same package as source, extends parent + anonymous inner)
private class TargetClass extends TargetParentClass {
String innerData;

public TargetClass(String data) {
this.innerData = data;
// Anonymous inner class (target_feature)
Runnable targetAnonymous = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous: " + innerData);
}
};
targetAnonymous.run();
}

// Target inner class (target_inner: target class for method move)
public class TargetInner {
public void collectData(List<Object> dataList) {
dataList.add(innerData);
}
}
}

// Source private normal class (same package as target, with inner classes)
private class SourceVarargsClass {
// Member inner class (source_class feature)
private class SourceMemberInner {
public Object wrapData(Object data) {
return new Object[] { data };
}
}

// Anonymous inner class (source_class feature)
private Runnable sourceAnonymous = new Runnable() {
@Override
public void run() {
System.out.println("Source anonymous executed");
}
};

// Varargs method (synchronized access, return Object)
// Per_condition: contains TargetClass.TargetInner parameter
public synchronized Object processVarargs(TargetClass.TargetInner targetInner, Object... args) {
// Variable call: use source anonymous inner class
sourceAnonymous.run();

// Collection operations: use parent_class method reference (instanceReference::methodName)
List<Object> dataList = new ArrayList<>();
TargetClass targetInstance = new TargetClass("base-data");
// 1 parent_class instance method reference
Function<String, TargetClass> parentMethodRef = targetInstance::processTargetData;

// Variable call: process varargs via parent method reference
for (Object arg : args) {
TargetClass processed = parentMethodRef.apply(arg.toString());
dataList.add(processed.innerData);
}

// Variable call: use target inner class
targetInner.collectData(dataList);

// No_new_exception: no new exceptions thrown
return dataList;
}
}