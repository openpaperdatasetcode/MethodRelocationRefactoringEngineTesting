package test;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface VarargsMethodAnnotation {}

// Parent class for call_method (overriding + super.methodName())
abstract class ParentBaseClass {
public String parentMethod(String data) {
return "parent_processed:" + data;
}
}

// Source: protected abstract class with member inner class & static nested class
protected abstract class SourceClass extends ParentBaseClass {
// Static nested class (source_feature)
public static class SourceStaticNested {
public static String staticField = "static_default"; // ClassName.field for target_feature
}

// Member inner class (source_feature)
public class SourceInner {
public String innerProcess(String data) {
return "inner_" + data;
}
}

// Default varargs method to be refactored (return List<String>)
@VarargsMethodAnnotation // has_annotation
public List<String> varargsMethod(TargetClass target, String... args) {
List<String> result = new ArrayList<>();

// Variable call: use target parameter
String targetBaseData = target.getTargetData();
result.add(targetBaseData);

// ConstructorInvocation (modifier: static, pos: source, target_feature: ClassName.field, "2")
SourceStaticNested.staticField = "updated_static_" + 2; // "2" + ClassName.field (SourceStaticNested.staticField)
result.add(SourceStaticNested.staticField);

// Array initialization with private constructor (method_feature)
TargetClass.TargetInner[] innerArray = {
target.new TargetInner("arr1", 1), // "1" + target + constructor
target.new TargetInner("arr2", 2)
};
for (TargetClass.TargetInner inner : innerArray) {
result.add(inner.getInnerData());
}

// If statement
if (args.length > 0) {
result.add("args_count:" + args.length);
for (String arg : args) {
result.add("arg:" + arg);
}
} else {
result.add("no_args_provided");
}

// Switch case
switch (innerArray.length) {
case 1:
result.add("switch_case_1: innerArray length 1");
break;
case 2:
result.add("switch_case_2: innerArray length 2");
break;
default:
result.add("switch_default: innerArray length " + innerArray.length);
}

// Call parent_class method in functional interfaces (call_method)
Supplier<String> parentCaller = () -> {
// Overriding (SourceClass overrides ParentBaseClass.parentMethod) + OuterClass.InnerClass.methodName()
String parentResult = super.parentMethod(target.getTargetData()); // super.methodName()
return new SourceInner().innerProcess(parentResult); // OuterClass.InnerClass.methodName()
};
result.add(parentCaller.get());

return result;
}

// Abstract method (required for abstract class)
public abstract String getSourceAbstractData();
}

// Target: protected abstract class with local inner class (target_feature)
protected abstract class TargetClass {
private String targetData = "default_target_data";

// Member inner class (used in array initialization)
public class TargetInner {
private String innerData;
private int innerNum;

// Private constructor (method_feature: modifier private)
private TargetInner(String data, int num) {
this.innerData = data;
this.innerNum = num;
}

public String getInnerData() {
// Local inner class (target_feature)
class TargetLocalInner {
public String formatLocal() {
return "local_inner_" + innerData + "_num" + innerNum;
}
}
return new TargetLocalInner().formatLocal();
}
}

public String getTargetData() {
return targetData;
}

public void setTargetData(String targetData) {
this.targetData = targetData;
}

// Abstract method (required for abstract class)
public abstract void targetAbstractMethod();
}