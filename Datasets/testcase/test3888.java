package test;
import java.util.function.Function;import java.lang.reflect.Method;
protected class SourceClass {// Target class field (satisfies "source contains the field of the target")TargetClass targetField = new TargetClass();
// Anonymous inner class (source feature){new Runnable() {@Overridepublic void run() {}};}
// Method: varargs type, TargetClass return, default accessTargetClass methodToRefactor(String... args) {// Local inner class (source feature)class SourceLocalInner {// Varargs method (matches method feature type)protected TargetClass innerVarargsMethod(int num, String... innerArgs) {return targetField;}}
// Do statement (method feature)int count = 0;do {// Expression statement (method feature)String arg = args.length > 0 ? args[count] : "default";variableCall();count++;} while (count < 3);
// ExpressionMethodReference with numbers "3" (method feature)Function<TargetClass, Integer> fieldGetter = TargetClass::getField;int fieldVal = fieldGetter.apply(targetField);if (fieldVal == 3) {}
// Switch statement (matches method feature pos: "switch")switch (args.length) {case 1:// Inner class varargs method call (method feature)TargetClass innerResult = new SourceLocalInner().innerVarargsMethod(1, args);break;}
// used_by_reflection (method feature)try {Method reflectMethod = TargetClass.class.getMethod("getField");reflectMethod.invoke(targetField);} catch (Exception e) {}
return targetField;}
// Variable call target (method feature)private void variableCall() {}}
public class TargetClass {private int field = 1; // Field for "obj.field" & "1" (target_feature)
// Local inner class (target feature)public void targetMethod() {class TargetLocalInner {}new TargetLocalInner();}
public int getField() {return field;}}
// Diff-package class (for ReturnStatement pos: "diff_package_others")package test.other;import test.SourceClass;import test.TargetClass;
public class DiffPackageClass {public TargetClass useSourceMethod() {SourceClass source = new SourceClass();TargetClass result = source.methodToRefactor("a", "b");
// ReturnStatement with target_feature "obj.field" & "1" (method feature)if (result.getField() == 1) {return result;}return new TargetClass();}}