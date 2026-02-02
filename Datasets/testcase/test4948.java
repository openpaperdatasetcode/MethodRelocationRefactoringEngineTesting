package test;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface AccessorAnnotation {
String invokeAccessor() default "subClassInstance.callSuperAccessor(targetInnerRec)";
}

// Source: public normal class with member inner and local inner classes
public class SourceClass {
// Member inner class
protected class SourceMemberInner {
private int innerValue;

public SourceMemberInner(int value) {
this.innerValue = value;
}
}

// Instance method to be refactored
protected int instanceMethod(TargetClass.TargetInnerRec targetInnerRec) throws IOException {
// Variable call
int result = targetInnerRec.getRecursiveValue();

// Local inner class
class SourceLocalInner {
int process(TargetClass.TargetInnerRec inner) {
return inner.getRecursiveValue() * 2;
}
}
result += new SourceLocalInner().process(targetInnerRec);

// Private ConstructorInvocation with obj.field and 1
SourceMemberInner member = new SourceMemberInner(1);
result += member.innerValue;
targetInnerRec.counter = 1; // obj.field access

// Switch statement
switch (targetInnerRec.getRecursiveValue() % 3) {
case 0:
result *= 2;
break;
case 1:
result += 5;
break;
default:
result -= 3;
}

// IOException handling
if (targetInnerRec.getRecursiveValue() < 0) {
throw new IOException("Negative value not allowed");
}

// Call accessor in annotation attribute values
invokeAccessorMethod(targetInnerRec);

return result;
}

// Sub-class for accessor feature
protected class SubClass extends SourceClass {
// Protected accessor method with super.methodName()
protected void callSuperAccessor(TargetClass.TargetInnerRec inner) {
super.instanceMethod(inner); // super.methodName()
inner.setRecursiveValue(1); // "1" in method_feature
}
}

@AccessorAnnotation
private void invokeAccessorMethod(TargetClass.TargetInnerRec targetInnerRec) throws IOException {
SubClass subClassInstance = new SubClass();
subClassInstance.callSuperAccessor(targetInnerRec); // sub_class accessor call
}
}

// Target: abstract normal class with no target features
abstract class TargetClass {
// Recursive inner class (target_inner_rec)
public class TargetInnerRec {
protected int recursiveValue;
protected int counter;

public TargetInnerRec(int value) {
this.recursiveValue = value;
}

public int getRecursiveValue() {
return recursiveValue;
}

public void setRecursiveValue(int value) {
this.recursiveValue = value;
}

public abstract int computeNext();
}
}