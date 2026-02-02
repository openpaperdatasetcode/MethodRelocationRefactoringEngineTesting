package test;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface CallMethodAnnotation {
String invokeSubClassMethod() default "subClassInstance.process(target, args)";
}

// Source: protected normal class with type parameter, static nested, member inner
protected class SourceClass<T> {
// Static field for depends_on_static_field
protected static String sourceStaticField = "static_field_value";

// Static nested class
static class SourceStaticNested {
public static String getStaticData() {
return sourceStaticField;
}
}

// Member inner class (recursive: source_inner_rec)
class SourceMemberInner {
// Normal method to be refactored (protected access)
protected List<String> normalMethod(TargetClass target, T param) {
List<String> result = new ArrayList<>();

// Variable call (use target parameter)
result.add(target.getTargetData());

// Overload_exist: overloaded method invocation
result.addAll(overloadedHelper(target));
result.addAll(overloadedHelper(target, param));

// Depends_on_static_field: use source static field via static nested class
result.add(SourceStaticNested.getStaticData());

// Call sub-class method in annotation attribute values
callAnnotatedMethod(target, param);

return result;
}

// Overloaded helper method (for overload_exist)
private List<String> overloadedHelper(TargetClass target) {
return List.of(target.getTargetData() + "_overload1");
}

private List<String> overloadedHelper(TargetClass target, T param) {
return List.of(target.getTargetData() + "_overload2:" + param);
}

// Method with annotation (call_method pos: attribute values)
@CallMethodAnnotation
private void callAnnotatedMethod(TargetClass target, T param) {
SubClass subClassInstance = new SubClass();
// Call sub-class strictfp instance method: this.methodName(arguments)
List<String> subResult = subClassInstance.process(target, param);
System.out.println("SubClass method result: " + subResult);
}
}
}

// Target: private normal class with local inner class
private class TargetClass {
private String targetData;

public TargetClass(String data) {
this.targetData = data;
// Local inner class (target_feature)
class TargetLocalInner {
void enhanceData() {
TargetClass.this.targetData = "enhanced_" + data;
}
}
new TargetLocalInner().enhanceData();
}

// Getter for variable call
public String getTargetData() {
return targetData;
}
}

// Sub-class for call_method (type: sub_class)
class SubClass extends SourceClass<String> {
// strictfp instance method (call_method modifier: strictfp)
strictfp List<String> process(TargetClass target, Object param) {
List<String> result = new ArrayList<>();
// this.methodName(arguments): call own instance method
result.add(this.formatResult(target, param));
return result;
}

private String formatResult(TargetClass target, Object param) {
return "sub_processed: " + target.getTargetData() + ", param: " + param;
}
}
