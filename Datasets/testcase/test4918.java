package test;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface SyncMethodAnnotation {}

// Target: sealed @interface with type parameter and static nested class (target_feature)
sealed @interface TargetAnnotation permits TargetAnnotation.TargetStaticNested {
// Type parameter: generic method (target_feature: type parameter)
<T> T processGeneric(T data);

// Static nested class (target_feature: static nested class)
@interface TargetStaticNested {}

// Abstract method for variable call
String targetValue() default "default_target";

// Instance method for access_instance_method
default TargetStaticNested createStaticNested() {
return new TargetStaticNested() {};
}

// Required method for sealed @interface
@Override
Class<? extends Annotation> annotationType() default TargetAnnotation.class;
}

// Source: @interface with 2 anonymous inner classes and recursive inner structure
@interface SourceAnnotation {
// Source contains target's field (per_condition)
TargetAnnotation targetField() default @TargetAnnotation;

// Recursive inner class (source_inner_rec)
class SourceInnerRec {
// Synchronized instance method 1 (method types parameter is:base type - int)
@SyncMethodAnnotation // has_annotation
public synchronized TargetAnnotation.TargetStaticNested instanceMethod(TargetAnnotation target, int baseParam) {
// Variable call: use target parameter and its method
String targetData = target.targetValue();
TargetAnnotation.TargetStaticNested nested = target.createStaticNested();

// Access instance method (target's instance method)
target.processGeneric(targetData);

// 1st Anonymous inner class (source_feature)
Runnable anon1 = new Runnable() {
@Override
public void run() {
System.out.println("Source Anonymous 1: " + targetData);
}
};
anon1.run();

// Recursive invocation (source_inner_rec)
return new SourceInnerRec().instanceMethod(target, baseParam + 1);
}

// Synchronized instance method 2 (overload_exist, method types parameter is:base type - String)
@SyncMethodAnnotation // has_annotation
public synchronized TargetAnnotation.TargetStaticNested instanceMethod(TargetAnnotation target, String baseParam) {
// Variable call: overload-specific parameter usage
String overloadedData = target.processGeneric(baseParam).toString();
TargetAnnotation.TargetStaticNested nested = target.createStaticNested();

// 2nd Anonymous inner class (source_feature)
Runnable anon2 = new Runnable() {
@Override
public void run() {
System.out.println("Source Anonymous 2: " + overloadedData);
}
};
anon2.run();

// Recursive invocation (source_inner_rec)
return new SourceInnerRec().instanceMethod(target, overloadedData + "_next");
}
}

// Abstract method to get recursive inner class instance
SourceInnerRec innerRec() default @SourceInnerRec;
}
