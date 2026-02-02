package test;

import java.lang.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MethodCallAnnotation {
String invokeMethod() default "OtherClass.processTarget(target, 3)"; // "3" in method_feature
}

// Other class (others_class for method_feature)
class OtherClass {
public static TargetClass.TargetStaticNested processTarget(TargetClass target, int count) {
TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested(count);
return nested;
}
}

// Parent class for super keywords
class ParentSourceClass {
protected String parentField = "parent_data";

public String getParentField() {
return parentField;
}
}

// Source: private generic normal class with type parameter, anonymous inner, member inner class
private class SourceClass<T> extends ParentSourceClass {
// Member inner class
public class SourceInner {
public String processInner(T data) {
return data.toString();
}
}

// Private overloading method 1
@MethodCallAnnotation
private List<String> overloadedMethod(TargetClass target, T param) {
List<String> result = new ArrayList<>();

// Variable call (use target parameter)
result.add(target.getTargetData());
result.add(param.toString());

// Super keywords (call parent class method/field)
result.add(super.getParentField());
result.add(super.parentField);

// Raw type usage
Collection rawList = new ArrayList();
rawList.add(target);
result.add(rawList.toString());

// Call normal method (method_feature: 3, others_class, normal, this.methodName(arguments))
TargetClass.TargetStaticNested nested = OtherClass.processTarget(target, 3);
result.add(nested.getStaticData());
result.add(this.overloadedMethod(target).toString()); // This method call

// Anonymous inner class
Runnable anonTask = new Runnable() {
@Override
public void run() {
System.out.println("Processed target: " + target.getTargetData());
}
};
anonTask.run();

return result;
}

// Private overloading method 2 (overload_exist)
private List<String> overloadedMethod(TargetClass target) {
List<String> result = new ArrayList<>();
result.add("default_param");
result.add(target.getTargetData());
return result;
}
}

// Target: protected normal class with static nested class (target_feature)
protected class TargetClass {
private String targetData = "default_target";

// Static nested class (target_feature)
public static class TargetStaticNested {
private int staticData;

public TargetStaticNested(int data) {
this.staticData = data;
}

public int getStaticData() {
return staticData;
}
}

public String getTargetData() {
return targetData;
}

public void setTargetData(String targetData) {
this.targetData = targetData;
}
}