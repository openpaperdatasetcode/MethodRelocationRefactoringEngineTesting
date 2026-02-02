package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface GenAnn {}
class ParentTargetClass<T> {protected T superField;}
strictfp class TargetClass<T> extends ParentTargetClass<T> {T targetField;
public void example() {class LocalInner {} // target_feature: local inner class}
class TargetInner {record TargetInnerRec(T val) {} // target_inner_rec}}
public class SourceClass<T> {class Inner1 {}class Inner2 {}
@GenAnn // has_annotationprivate <U extends TargetClass<T>> TargetClass<T> methodToMove(U target, TargetClass<T>.TargetInner.TargetInnerRec rec) {// Type declaration statementclass TypeDecl {}TypeDecl type = new TypeDecl();
// Variable callT var = target.targetField;T superVar = target.superField;
// Raw typeTargetClass rawTarget = target;
// Uses outer thisSourceClass<T> outer = SourceClass.this;Inner1 inner1 = new Inner1();
// Same_package_target: BreakStatement with super.field = 1loop:for (int i = 0; i < 5; i++) {target.superField = (T) Integer.valueOf(1);if (i == 1) break loop;}
// For loop with synchronized instance method callsynchronized (this) {for (int i = 0; i < 1; i++) {outer.parentInstanceMethod(target); // 1 as required}}
return target;}
// Parent_class instance method (logically inherited via composition)private void parentInstanceMethod(TargetClass<T> target) {}}