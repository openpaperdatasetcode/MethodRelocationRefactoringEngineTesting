package test;

import java.lang.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface VarargsCallAnnotation {
String invokeVarargs() default "SourceAnnotation.SourceInner.synchronizedVarargs(target, "arg1", "arg2")";
}

// Source: @interface with member inner & static nested class
@interface SourceAnnotation {
// Source contains target's field (per_condition)
TargetAnnotation.TargetInner targetField() default @TargetAnnotation.TargetInner(value = "default_target");

// Static nested class
static class SourceStaticNested {
public static <T> void process(T obj) {
System.out.println("Static nested process: " + obj);
}
}

// Member inner class (source_inner)
class SourceInner {
private TargetAnnotation target;

public SourceInner(TargetAnnotation target) {
this.target = target;
}

/**

Overloaded method 1: process target with single param
@param data Input string data
*/
@VarargsCallAnnotation
default void processTarget(String data) {
// Type declaration statement
RawTypeHolder rawHolder = new RawTypeHolder();
List<String> result = new ArrayList<>();
// Variable call
result.add(target.targetData());
result.add(data);
// Raw type usage
rawHolder.rawCollection.add(target);
// otherObject.process(this)
SourceStaticNested.process(this);
// Access instance method (target's inner class method)
String innerResult = target.new TargetInner().getInnerValue();
result.add(innerResult);
// Requires try-catch (reflection)
try {
Method method = TargetAnnotation.class.getMethod("targetData");
Object reflected = method.invoke(target);
result.add(reflected.toString());
} catch (Exception e) {
// No new exception
}
// Call parent_class method in instance code blocks
ParentClass parent = new ParentClass();
int parentResult = parent.getParentValue(this.target.new TargetInner());
result.add(String.valueOf(parentResult));
return; // Return statement
}

/**

Overloaded method 2: process target with varargs (overload_exist)
@param args Varargs of string
*/
default void processTarget(String... args) {
if (args.length == 0) return;
processTarget(args[0]); // Call overloaded method
}

/**

Synchronized varargs method (method_feature)
@param target TargetAnnotation instance
@param args Varargs params
@return List of processed strings
*/
public synchronized List<String> synchronizedVarargs(TargetAnnotation target, String... args) {
List<String> result = new ArrayList<>();
result.add(target.targetData());
for (int i = 0; i < args.length && i < 2; i++) { // "2" in method_feature
result.add(args[i]);
}
// OuterClass.InnerClass.methodName()
result.add(target.new TargetInner().getInnerValue());
return result;
}

// Raw type helper class
private class RawTypeHolder {
Collection rawCollection = new ArrayList();
}
}

// Abstract method for inner class access
SourceInner getSourceInner() default @SourceInner(target = @TargetAnnotation);
}

// Target: public @interface with type parameter, implements, member inner class
public @interface TargetAnnotation implements java.io.Serializable {
// Type parameter: generic method
<T> T processGeneric(T data);

// Abstract method for variable call
String targetData() default "target_default_data";

// Member inner class (target_feature)
@interface TargetInner {
String value() default "";

default String getInnerValue() {
return this.value();
}
}

// Abstract method for inner class instantiation
TargetInner targetInner() default @TargetInner;

// Default method (implements feature)
@Override
default Class<? extends Annotation> annotationType() {
return TargetAnnotation.class;
}
}

// Parent class for call_method
class ParentClass {
/**

Instance method: OuterClass.InnerClass.methodName() feature
@param inner TargetAnnotation's inner class instance
@return Parent value
*/
public int getParentValue(TargetAnnotation.TargetInner inner) {
return inner.getInnerValue().length();
}
}