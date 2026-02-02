package test;

import java.lang.reflect.Method;

// Source: public normal class with member inner & anonymous inner class
public class SourceClass {
// Source contains target's field (per_condition)
private TargetClass targetField;

// Member inner class
protected class SourceMemberInner {
private int innerField;

public SourceMemberInner(int value) {
this.innerField = value;
}

public int getInnerField() {
return innerField;
}
}

// Protected normal method to be refactored (method types parameter is:keywords)
protected Object normalMethod(TargetClass target, int ifParam, String classParam) {
this.targetField = target; // Property assignment: source contains target's field

// Variable call
Object targetData = target.getTargetField();

// Private ConstructorInvocation (pos: same_package_target)
SourceMemberInner privateInner = new SourceMemberInner(3); // "3" in target_feature
target.targetDataField = privateInner.getInnerField(); // obj.field: access target's field

// Numbers: "2" + PrefixExpression (e.g., ++count)
int count = 2;
target.setTargetField(++count); // PrefixExpression (++count) with "2"

// Throw statement
if (target.getTargetField() == null) {
throw new IllegalArgumentException("Target field cannot be null");
}

// Used by reflection
try {
Method reflectMethod = TargetClass.class.getMethod("getTargetField");
Object reflectedResult = reflectMethod.invoke(target);
} catch (Exception e) {
// No new exception
}

// Call source method (synchronized, constructor, new ClassName().method()) in property assignment
targetField = new TargetClass();
Object callResult = new SourceClass().synchronizedSourceMethod(target);

// Anonymous inner class
Runnable anonTask = new Runnable() {
@Override
public void run() {
System.out.println("Anonymous inner: " + target.getTargetField());
}
};
anonTask.run();

return targetData;
}

// Synchronized source method for call_method
public synchronized Object synchronizedSourceMethod(TargetClass target) {
return target.getTargetField();
}
}

public class TargetClass {

public Integer targetDataField;
private Object targetField;
public TargetClass() {}
public Object getTargetField() {
return targetField;
}
public void setTargetField(Object targetField) {
this.targetField = targetField;
}

public void processData () {
// Local inner class (target_feature)
class TargetLocalInner {
private Object localData;
public TargetLocalInner(Object data) {
this.localData = data;
}
public Object getLocalData() {
return localData;
}
}
TargetLocalInner localInner = new TargetLocalInner(targetField);
System.out.println("Local inner data: " + localInner.getLocalData());
}
}