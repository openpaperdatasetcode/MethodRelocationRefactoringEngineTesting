package test;

import java.lang.annotation.*;

// Helper class for "otherObject.process(this)"
class OtherProcessor {
public static void process(SourceAnnotation.SourceInner inner) {
System.out.println("Processing SourceInner instance");
}
}

// Source: @interface with local inner class and member inner class
@interface SourceAnnotation {
// Member inner class (source_inner)
class SourceInner {
// Outer private field for access_outer_private
private String outerPrivateField = "source_inner_private";

// Public instance method to be refactored
public void instanceMethod(TargetAnnotation.TargetInner targetInner) {
// Type declaration statement
TargetAnnotation.TargetInner localTarget = targetInner;
String targetData = localTarget.value();

// Variable call (use target parameter)
System.out.println("TargetInner value: " + targetData);

// Access outer private field (access_outer_private)
System.out.println("Outer private field: " + this.outerPrivateField);

// Local inner class (source_feature)
class SourceLocalInner {
public void localProcess(TargetAnnotation.TargetInner inner) {
System.out.println("LocalInner processing: " + inner.value());
}
}
SourceLocalInner localInner = new SourceLocalInner();
localInner.localProcess(localTarget);

// otherObject.process(this)
OtherProcessor.process(this);
}
}

// Abstract method to get SourceInner instance
SourceInner inner() default @SourceInner;
}

// Target: public @interface with extends and member inner class
public @interface TargetAnnotation extends Annotation {
// Member inner class (target_inner)
@interface TargetInner {
String value() default "default_target_inner";
}

// Abstract method to get TargetInner instance (for variable call)
TargetInner targetInner() default @TargetInner;

// Required method for @interface extending Annotation
@Override
Class<? extends Annotation> annotationType() default TargetAnnotation.class;
}