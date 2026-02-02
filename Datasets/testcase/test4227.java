package test;
import java.util.ArrayList;import java.util.List;
// Source class: generic, protected modifier, extends ParentClass, has local inner + static nested classesprotected class SourceClass<T extends Number> extends ParentClass {// Protected field for access_outer_protectedprotected T outerProtectedField;
// Static nested class (matches source_class.feature)static class SourceStaticNested {
U nestedField;
}
// Member inner class (for method_position: source_inner_rec)class SourceInner {// Recursive method (matches "rec" in source_inner_rec)private int recursiveSum(int num) {if (num <= 0) return 0;return num + recursiveSum(num - 1);}
// Refactored method: instance, return Object, protected accessprotected Object moveMethod(TargetClass<T> targetParam) throws ClassCastException {// Super keywords (matches method.features)super.parentMethod();
// access_outer_protected: access source's outer protected fieldT outerVal = SourceClass.this.outerProtectedField;
// Variable call: access target's field (matches per_condition)T targetFieldVal = targetParam.targetField;
// raw_type: use generic class without type parameterList rawList = new ArrayList();rawList.add(outerVal);rawList.add(targetFieldVal);
// Use target's static nested class (matches target class: target)TargetClass.TargetStaticNested<T> targetStatic = new TargetClass.TargetStaticNested<>();targetStatic.nestedField = (T) Double.valueOf(recursiveSum(5));
// requires_throws: explicit cast with potential ClassCastExceptionreturn (Number) targetStatic.nestedField;}}
// Method with local inner class (matches source_class.feature)public void methodWithLocalInner() {class SourceLocalInner {void printData(T data) {System.out.println(data);}}new SourceLocalInner().printData(outerProtectedField);}}
// Parent class for SourceClass (extends feature)class ParentClass {protected void parentMethod() {}}
/**
Javadoc for TargetClass (matches target_class.target_feature)
Generic target class with static nested class and generic field
@param <V> Type of the target field, extends Number*/class TargetClass<V extends Number> {// Target field (accessed by source method, matches per_condition)V targetField;
// Static nested class (matches target_class.target_feature)static class TargetStaticNested<W> {W nestedField;}}