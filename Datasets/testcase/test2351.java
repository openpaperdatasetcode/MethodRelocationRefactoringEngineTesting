package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnot {}
private class SourceClass {static class SourceStaticNested {}
class SourceInner {class SourceInnerRec {@MyAnnotprotected int methodToMove(TargetClass.TargetInner targetParam) {// SuperConstructorInvocationclass Sub extends ParentClass {Sub() {super(targetParam.field + 1);}}
// Instance method in whileint count = 0;while (super.parentMethod(1) > 0) {count++;if (count > 5) break;}
// Constructor invocationTargetClass.TargetInner newInner = new TargetClass().new TargetInner();
// Super constructor invocationnew Sub();
// ArrayAccess expressions (2)int[] arr = {1, 2};int val1 = arr[0];targetParam.array[1] = 5;int val2 = targetParam.array[1];
// Variable callint var = targetParam.field;
return var;}}}}
/**
Javadoc for TargetClass
Contains member inner class
*/
class TargetClass {
class TargetInner {
int field;
int[] array = new int[2];
}
}
class ParentClass {ParentClass(int i) {}protected int parentMethod(int num) {return num;}}