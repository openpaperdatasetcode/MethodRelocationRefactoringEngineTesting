package test;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MethodAnnotation {}

abstract class ParentClass<T extends CharSequence> {
protected T parentField;

public ParentClass(T field) {
this.parentField = field;
}

public abstract TargetClass newClassInstance();
}

// Source: public normal class with member inner & static nested class
public class SourceClass extends ParentClass<String> {
// Source contains target's field (per_condition)
private TargetClass targetField;

public SourceClass(String field) {
super(field);
}

// Static nested class
public static class SourceStaticNested<T extends CharSequence> {
public T processBoundType(T data) {
return data;
}
}

// Member inner class (source_inner)
public class SourceInner {
private List<Object> dataList = new ArrayList<>();

// Default abstract method (method_feature)
@MethodAnnotation
public default TargetClass abstractMethod() {
return new TargetClass();
}

// Public varargs method to be refactored
@MethodAnnotation
public Object varargsMethod(TargetClass target, Object... args) {
targetField = target; // Access instance field (source's targetField)
dataList.add(targetField);

// Variable call
String targetData = target.getTargetData();
dataList.add(targetData);

// With bounds (T extends CharSequence)
SourceStaticNested<String> staticNested = new SourceStaticNested<>();
String boundResult = staticNested.processBoundType(targetData);
dataList.add(boundResult);

// Expression statement
int exprResult = targetData.length() * 2;
dataList.add(exprResult);

// Enhanced for with continue statement
for (Object arg : args) {
if (arg == null) {
continue; // Continue statement
}
dataList.add(arg);
}

// Throw statement
if (dataList.size() < 1) { // "1" in method_feature
throw new IllegalArgumentException("Data list cannot be empty");
}

// Depends on inner class (call SourceInner's abstract method)
TargetClass newTarget = this.abstractMethod();
dataList.add(newTarget);

// Call parent_class method in instance code blocks
TargetClass parentNewClass = SourceClass.this.newClassInstance();
dataList.add(parentNewClass.getTargetData());

// Call "new ClassName().method()"
String newInstanceResult = new SourceStaticNested<>().processBoundType("test");
dataList.add(newInstanceResult);

return dataList;
}
}

@Override
public TargetClass newClassInstance() {
return new TargetClass();
}
}

// Target: protected normal class with anonymous inner class
protected class TargetClass {
private String targetData = "default_target_data";

public TargetClass() {
// Anonymous inner class (target_feature)
Runnable anonTask = new Runnable() {
@Override
public void run() {
System.out.println("TargetClass anonymous class: " + targetData);
}
};
anonTask.run();
}

public String getTargetData() {
return targetData;
}

public void setTargetData(String targetData) {
this.targetData = targetData;
}
}