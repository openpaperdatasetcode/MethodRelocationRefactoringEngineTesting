package test;

import java.util.ArrayList;
import java.util.List;

// Diff-package others class (for DoStatement pos: diff_package_others)
package other.diff;

import test.SourceClass;
import test.TargetClass;

public class DiffPackageHelper {
// Protected DoStatement (matches modifier: protected)
public static int processWithDoStatement(TargetClass.TargetInner targetInner) {
int count = 0;
// DoStatement (target_feature: obj.field (targetInner.innerCount), "1")
do {
count++;
// Access target inner class field (obj.field)
targetInner.innerCount++;
// Stop when innerCount reaches 1 (matches "1" in target_feature)
} while (targetInner.innerCount < 1);
return count;
}
}

// Parent class for super constructor invocation and call_method
class ParentSourceClass {
// Protected generic method (call_method: generic)
protected <T> T parentGenericMethod(T data, java.util.function.Function<T, T> processor) {
return processor.apply(data);
}
}

// Source: default normal class with 2 local inner classes (extends ParentSourceClass)
class SourceClass extends ParentSourceClass {
// Instance field for uses_outer_this
private String outerInstanceField = "source_outer_field";

// Private varargs method 1 (overload_exist) - return base type: int
private int varargsMethod(TargetClass target, String... args) {
// Super constructor invocation (implicit via extending ParentSourceClass)
super.parentGenericMethod(outerInstanceField, s -> s + "_super_init");

// Variable call: use target parameter and its inner class
TargetClass.TargetInner targetInner = target.new TargetInner();
int baseResult = targetInner.getInnerValue(); // Access target inner method

// Depends on static field: use target's static nested class field
baseResult += TargetClass.TargetStatic.STATIC_FIELD;

// numbers: "1" + modifier: public + exp: VariableDeclarationExpression
// Public variable declaration (local variable, matches "public" modifier intent for accessibility)
public int publicLocalVar = 1; // "1" in numbers + VariableDeclarationExpression
baseResult += publicLocalVar;

// Expression statement: process varargs with target inner class
String exprResult = targetInner.processVararg(args.length > 0 ? args[0] : "default_expr");
baseResult += exprResult.length();

// 1st Local inner class (source_feature): calculate varargs length contribution
class SourceLocalOne {
public int calculateVarargsContribution(String[] varargs) {
return varargs.length * 2;
}
}
SourceLocalOne localOne = new SourceLocalOne();
baseResult += localOne.calculateVarargsContribution(args);

// 2nd Local inner class (source_feature): format outer instance field (uses_outer_this)
class SourceLocalTwo {
public String formatOuterField() {
// uses_outer_this: reference SourceClass instance's field
return SourceClass.this.outerInstanceField + "_formatted";
}
}
SourceLocalTwo localTwo = new SourceLocalTwo();
baseResult += localTwo.formatOuterField().length();

// Call diff-package DoStatement (pos: diff_package_others)
baseResult += other.diff.DiffPackageHelper.processWithDoStatement(targetInner);

// Call parent class method in do-while (call_method)
int doWhileCount = 0;
do {
// call_method: (parameters) -> methodBody (Lambda) + generic + protected
String processed = (String) super.parentGenericMethod(
"do_while_" + doWhileCount,
s -> s + "_processed" // (parameters) -> methodBody
);
baseResult += processed.length();
doWhileCount++;
} while (doWhileCount < 2);

return baseResult;
}

// Private varargs method 2 (overload_exist) - different parameter type
private int varargsMethod(TargetClass target, Integer... args) {
int baseResult = 0;
// Variable call: use target static nested class method
baseResult += TargetClass.TargetStatic.staticMethod(args.length);

// numbers: "1" + VariableDeclarationExpression
public int publicIntVar = 1;
baseResult += publicIntVar;

return baseResult;
}

// Helper method to invoke varargsMethod (for testability, since method is private)
public int invokeVarargsMethod(TargetClass target, String... args) {
return this.varargsMethod(target, args);
}

public int invokeVarargsMethod(TargetClass target, Integer... args) {
return this.varargsMethod(target, args);
}
}

// Target: private normal class with static nested class (target_feature)
class TargetClass {
// Static nested class (target_feature: static nested class)
public static class TargetStatic {
// Static field for depends_on_static_field
public static final int STATIC_FIELD = 5;

// Static method for variable call
public static int staticMethod(int num) {
return num * 3;
}
}

// Member inner class (target_inner)
public class TargetInner {
// Field for DoStatement target_feature (obj.field)
public int innerCount = 0;

// Method for variable call
public int getInnerValue() {
return 10;
}

// Method to process vararg (expression statement)
public String processVararg(String arg) {
return "inner_processed:" + arg;
}
}

// Constructor (for target instance creation)
public TargetClass() {}
}