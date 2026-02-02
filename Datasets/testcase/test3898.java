package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RecurseAnnotation {}
// Source generic class: sealed (for permits), with static nested & member inner classesprivate sealed class SourceClass<T extends Number> permits SubSourceClass {// Target class field (satisfies "source contains the field of the target")TargetClass<String> targetField = new TargetClass<>();
// Static nested class (source feature)static class SourceStaticNested<S> {}
// Member inner class (source feature)class SourceInnerClass {}
@RecurseAnnotation // has_annotation (method feature)public void recursiveMethod(int depth) {// Base case for recursionif (depth <= 0) {return;}
// TypeDeclarationStatement (method feature)private class LocalType {void checkTargetField() {// Match target_feature: ClassName.field (TargetClass.STATIC_FIELD) & "1"if (TargetClass.STATIC_FIELD == 1) {variableCall(); // Variable call (method feature)}}}new LocalType().checkTargetField();
// Recursive call (method type)recursiveMethod(depth - 1);}
// Variable call target method (method feature)private void variableCall() {}}
// Permitted subclass for sealed SourceClass (matches "permits" feature)final class SubSourceClass extends SourceClass<Integer> {}
// Target generic class: non-sealed, with type parameter & member inner classnon-sealed class TargetClass {
// Type parameter (target feature)
private U targetData;
// Static field for "ClassName.field" (target_feature)
public static int STATIC_FIELD = 1;
// Member inner class (target feature)class TargetInnerClass {U getInnerData() {return targetData;}}
// Instance of inner class for "target_inner"TargetInnerClass inner = new TargetInnerClass();
public void setTargetData(U targetData) {this.targetData = targetData;}}