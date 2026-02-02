package test;
import other.DiffPackageClass;
abstract class SourceClass {// Static nested class (matches source_class.feature)static class SourceStaticNested {}
// Member inner class (for method_position: source_inner)class SourceInner {// Refactored method: instance, return void, public accesspublic void moveMethod(TargetClass targetParam) {// Variable call: access target's fieldint val1 = targetParam.targetField;int val2 = targetParam.anotherField;
// Break statement (matches method.features)loop: for (int i = 0; i < 5; i++) {if (i == val1) break loop;}
// requires_try_catch (matches method.features)try {// SuperConstructorInvocation in diff_package_others (matches nested feature)DiffPackageClass.create(targetParam.targetField, targetParam.anotherField);} catch (Exception e) {e.printStackTrace();}}}
// Another member inner class (matches source_class.feature)class AnotherSourceInner {}}
/**
Javadoc for TargetClass (matches target_class.target_feature)
Abstract target class with member inner class*/protected abstract class TargetClass {// Target fields (accessed by source method)int targetField;int anotherField;
// Member inner class (matches target_class.target_feature)class TargetInner {void innerMethod() {}}}
package other;
import test.TargetClass;
public class DiffPackageClass {// Class with constructor using SuperConstructorInvocation (from nested feature)public DiffPackageClass(int a, int b) {super();}
// Factory method for SuperConstructorInvocation in sourcestatic DiffPackageClass create(int a, int b) {return new DiffPackageClass(a, b);}}