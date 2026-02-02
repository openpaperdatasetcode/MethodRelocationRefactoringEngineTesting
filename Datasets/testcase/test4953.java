package source.pkg;

import java.lang.annotation.*;
import target.pkg.TargetAnnotation;
import others.pkg.OthersClass;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MethodMarker {
String value() default "";
}

@interface SourceAnnotation implements java.io.Serializable {
// Member inner class
class SourceMemberInner {
private TargetAnnotation.TargetMemberInner targetInnerInstance;

public SourceMemberInner(TargetAnnotation.TargetMemberInner inner) {
this.targetInnerInstance = inner;
}
}

// Varargs method to be refactored
@MethodMarker("RefactorTargetVarargs")
synchronized Object process(TargetAnnotation target, Object... args);

// Static initializer with local inner class
static {
class SourceLocalInner {
void useTarget(TargetAnnotation target) {
// Variable call
Object targetData = target.data();

// For statement with break
for (Object arg : new Object[]{"a", "b"}) {
if (arg.equals("b")) {
// Break statement
break;
}
}
}
}
new SourceLocalInner().useTarget(TargetAnnotation.DEFAULT);
}

// Default method implementation (for @interface abstract method)
default Object process(TargetAnnotation target, Object... args) {
// Type declaration statement
OthersClass othersInstance = new OthersClass();

// Exception handling statements with call_method
try {
// Call others_class instance method: this.methodName(arguments)
othersInstance.processArgs(this, args);
} catch (RuntimeException e) {
// No new exception
}

// Variable call (use target parameter)
return target.process(target.memberInner(), args);
}

// Abstract method for member inner class reference
TargetAnnotation.TargetMemberInner getTargetInner();
}

package target.pkg;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
protected @interface TargetAnnotation {
// Member inner class (target_feature)
class TargetMemberInner {
private Object value;

public TargetMemberInner(Object value) {
this.value = value;
}

public Object getValue() {
return value;
}
}

// Abstract method for variable call
String data() default "target_default";

// Abstract method for member inner class access
TargetMemberInner memberInner() default @TargetMemberInner("default_inner");

// Abstract method for varargs processing
Object process(TargetMemberInner inner, Object... args);

// Default instance for reference
TargetAnnotation DEFAULT = new TargetAnnotation() {
@Override
public String data() {
return "default_data";
}

@Override
public TargetMemberInner memberInner() {
return new TargetMemberInner("default_inner_value");
}

@Override
public Object process(TargetMemberInner inner, Object... args) {
return inner.getValue();
}

@Override
public Class<? extends Annotation> annotationType() {
return TargetAnnotation.class;
}
};
}

package others.pkg;

import source.pkg.SourceAnnotation;

class OthersClass {
// Instance method (call_method type: others_class, modifier: default)
void processArgs(SourceAnnotation sourceAnn, Object... args) {
// this.methodName(arguments) feature (call internal method)
this.validateArgs(args);
System.out.println("Processed " + args.length + " args via source annotation");
}

private void validateArgs(Object... args) {
if (args == null) {
throw new IllegalArgumentException("Args cannot be null");
}
}
}