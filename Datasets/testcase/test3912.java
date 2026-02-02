package test;
import java.lang.reflect.Method;
// Diff-package class for SuperConstructorInvocation pos: "diff_package_others"package test.other;import test.SourceClass;import test.TargetClass;
public class DiffPackageClass {public void triggerRecursion(SourceClass source, TargetClass target) {// SuperConstructorInvocation with target_feature (obj.field & 1)SourceClass.SourceStaticNested.SubNested subNested = new SourceClass.SourceStaticNested.SubNested();if (subNested.getField() == 1) {source.recursiveMethod(target, 3);}}}
// Back to test packagepackage test;
// Source class: protected modifier, with member inner & static nested classesprotected class SourceClass {// Static nested class (source feature)static class SourceStaticNested {// Nested class for SuperConstructorInvocationstatic class SubNested {private int field;
// Private constructor (matches method_feature modifier)private SubNested() {this.field = 1; // Matches target_feature "1"}
public int getField() {return field;}}
public void staticNestedMethod() {}}
// Member inner class (source feature) - depends_on_inner_classclass SourceInner {public void processTarget(TargetClass target) {if (target != null) {variableCall();}}}
// Recursive method (method type: recursion) - refactored methodprotected void recursiveMethod(TargetClass targetParam, int depth) {// Base case for recursion (target_class: target_inner_rec)if (depth <= 0) {return;}
// Constructor invocation (method feature)SourceInner inner = new SourceInner();SourceStaticNested staticNested = new SourceStaticNested();
// Synchronized statement (method feature)synchronized (this) {// Depends_on_inner_class (method feature) - use member inner classinner.processTarget(targetParam);staticNested.staticNestedMethod();}
// used_by_reflection (method feature)try {Method targetMethod = TargetClass.class.getMethod("toString");targetMethod.invoke(targetParam);} catch (Exception e) {}
// Variable call (method feature)variableCall();
// Recursive call (target_inner_rec)recursiveMethod(targetParam, depth - 1);}
// Variable call target methodprivate void variableCall() {}}
// Target class: default modifier, no target_featuresclass TargetClass {// Field for target_feature "obj.field"private int targetField = 1;
public int getTargetField() {return targetField;}}