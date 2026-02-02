package test.refactoring.movemethod;
import test.refactoring.other.DiffPackageClass;import java.util.function.Function;
/**
Private source class: same package with target, contains member inner and static nested class/
private class SourceClass {
// Source feature: member inner class
class SourceMemberInnerClass {
// Source inner class (method_position: source_inner_rec)
public class NestedInnerClass {
/*
Varargs method to be refactored (default access, return base type int)
@param targetParam target class parameter (per_condition)
@param varargs varargs parameters
@return int base type result*/int refactorTargetMethod(TargetClass targetParam, Integer... varargs) {// Variable callTargetClass tempTarget = targetParam;int targetField = tempTarget.field; // Access target's field
// Expression statementtempTarget.invokeLocalInner();
// While statementint whileCount = 0;while (whileCount < varargs.length) {whileCount++;}
// Do-while position for accessor nested methodint doCount = 0;Object accessorResult;do {accessorResult = accessorNestedMethod(tempTarget, doCount);doCount++;} while (doCount < 2);
// BreakStatement in diff_package_others (type: BreakStatement, modifier: static)static void staticBreakMethod(DiffPackageClass diffPackageObj) {int count = 3; // "3" in target_featurefor (int i = 0; i < 5; i++) {if (diffPackageObj.getField() == tempTarget.field) { // this.field (target's field)break; // BreakStatement}}}staticBreakMethod(new DiffPackageClass());
// No new exception thrownreturn targetField + varargs.length;}
/**
Accessor nested method (type: accessor, modifier: public, return_type: Object)
@param target target class instance
@param num "1" in method_feature
@return Object result
*/
public Object accessorNestedMethod(TargetClass target, int num) {
int value = 1; // "1" in method_feature
// (parameters) -> methodBody lambda + parent_class accessor
Function<TargetClass, Integer> accessorLambda = TargetClass::getParentField; // parent_class accessor
return accessorLambda.apply(target); // "accessor" feature
}
}
}
// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Container method to access private inner classpublic NestedInnerClass getNestedInnerClass() {return new SourceMemberInnerClass().new NestedInnerClass();}}
/**
Target class: default modifier, extends parent + local inner class (target_feature)*/class TargetClass extends TargetParentClass {// Target field for this.field featurepublic int field = 10;
// Target feature: local inner classpublic void invokeLocalInner() {class TargetLocalInnerClass {void innerMethod() {}}new TargetLocalInnerClass().innerMethod();}
// Call method: target type, public modifier, pos in exception throwing statementspublic int callTargetStaticMethod(TargetClass target) {try {// Exception throwing statements position + static + instanceReference::methodNameFunction<TargetClass, Integer> methodRef = TargetClass::staticMethod; // static featurereturn methodRef.apply(target);} catch (Exception e) {throw new RuntimeException("Call failed", e); // exception throwing statements}}
// Static method for call_method target_featurepublic static int staticMethod(TargetClass target) {return target.field + target.getParentField();}}
/**
Parent class for target class extends feature*/class TargetParentClass {// Parent field for parent_class accessor featureprotected int parentField = 5;
// Accessor method (getter) for accessor featurepublic int getParentField() {return parentField;}}
// Diff package class for BreakStatement pos: diff_package_otherspackage test.refactoring.other;
public class DiffPackageClass {private int field = 10;
public int getField() {return field;}}
// Container class to access private SourceClassclass SourceClassContainer {public SourceClass createSourceClass() {return new SourceClass();}}
