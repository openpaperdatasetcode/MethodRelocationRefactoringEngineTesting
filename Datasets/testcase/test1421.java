package test.refactor.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
class ParentSourceClass {}
class ParentTargetClass {}
// Source class: default modifier, extends ParentSourceClassclass SourceClass extends ParentSourceClass {// Per_condition: source contains target class field (indirect support for parameter usage)private TargetClass targetField;
/**
Instance method to be refactored (contains target class parameter - per_condition)
@param target target class parameter
@return TargetClass instance*/@MethodAnnotation // has_annotationprivate TargetClass methodToMove(TargetClass target) {// ThrowStatement (type: ThrowStatement, modifier: public, pos: diff_package_others, target_feature: this.field, 2)public void validateTarget() {if (target == null || this.targetField == null) { // this.fieldthrow new IllegalArgumentException("Target parameters cannot be null (count: 2)"); // 2}}
// Variable callvalidateTarget();TargetClass.TargetInnerClass inner = target.getTargetInner();inner.innerMethod();
// Raw_type: use generic class without type parameterList rawList = new ArrayList();rawList.add(target);
return target;}}
// Target class: default modifier, extends ParentTargetClass, target_feature: extends + static nested classclass TargetClass extends ParentTargetClass {// Target_inner: target class for method movementpublic static class TargetInnerClass { // static nested class (target_feature)public void innerMethod() {}}
private String targetField = "targetFieldValue"; // this.field reference
public TargetInnerClass getTargetInner() {return new TargetInnerClass();}
// Refactored method should be placed here (target class: target_inner)@MethodAnnotationprivate TargetClass methodToMove(TargetClass target) {public void validateTarget() {if (target == null || this.targetField == null) {throw new IllegalArgumentException("Target parameters cannot be null (count: 2)");}}
validateTarget();TargetInnerClass inner = target.getTargetInner();inner.innerMethod();
List rawList = new ArrayList();rawList.add(target);
return target;}}
// Test class for refactoring verificationpublic class MoveMethodTest5054 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
// Original method call (before refactoring)TargetClass originalResult = source.methodToMove(target);
// Refactored method call (after moving to TargetInnerClass)TargetClass.TargetInnerClass targetInner = target.getTargetInner();// Note: After refactoring, adjust access if needed (original private -> adapt to target context)TargetClass refactoredResult = target.methodToMove(target);}}