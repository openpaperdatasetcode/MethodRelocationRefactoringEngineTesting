package test;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RecursionMethodAnnotation {}

// Others class for call_method (overriding + super.methodName(arguments))
class OthersClass {
public String othersProcess(String data) {
return "others_base:" + data;
}
}

class OthersSubClass extends OthersClass {
@Override
public String othersProcess(String data) {
// super.methodName(arguments)
String superResult = super.othersProcess(data);
return "others_sub:" + superResult;
}
}

// Diff-package target helper (for ReturnStatement pos: diff_package_target)
package other.diff;

import test.TargetClass;

public class DiffPackageHelper {
// Method containing ReturnStatement with target_feature
public static int getDiffPackageResult(TargetClass.TargetInnerRec targetInner) {
// ReturnStatement (modifier: private - implicit in method, target_feature: obj.field, "1")
return targetInner.innerNum == 1 ? targetInner.innerNum : targetInner.innerNum * 2;
}
}

// Source: abstract normal class with 2 static nested classes
abstract class SourceClass {
// Static nested class 1
public static class SourceStaticOne {
public static String staticProcess(String data) {
return "static1:" + data;
}
}

// Static nested class 2
public static class SourceStaticTwo {
public static <T> T genericStatic(T data) {
return data;
}
}

// Public recursive method to be refactored (return base type: int)
@RecursionMethodAnnotation // has_annotation
public int recursiveMethod(TargetClass target, int depth) {
// Variable call: use target parameter and its inner recursive class
TargetClass.TargetInnerRec targetInner = target.createInnerRec(depth);
String innerData = targetInner.getInnerData();
int baseResult = innerData.length();

// Expression statement
String exprResult = SourceStaticOne.staticProcess(innerData + "_expr");
baseResult += exprResult.length();

// Raw type usage
List rawList = new ArrayList();
rawList.add(targetInner);
baseResult += rawList.size();

// Depends on inner class: use target's inner recursive class method
baseResult += targetInner.calculateDepth(depth);

// Normal method in Lambda expressions (method_feature)
Function<TargetClass.TargetInnerRec, Object> lambdaFunc = inner -> {
// synchronized modifier (method_feature) + instanceReference.methodName(arguments) + "2" + target
synchronized (inner) {
return inner.updateData("lambda_" + 2, depth); // "2" + target + instanceReference.methodName
}
};
lambdaFunc.apply(targetInner);

// Used by reflection: access target inner class method
try {
Method reflectMethod = TargetClass.TargetInnerRec.class.getMethod("getInnerData");
String reflectedData = (String) reflectMethod.invoke(targetInner);
baseResult += reflectedData.hashCode() % 10;
} catch (Exception e) {
// No new exception thrown
}

// Call others_class method in property assignment (call_method)
OthersSubClass othersSub = new OthersSubClass();
String othersResult = othersSub.othersProcess(innerData);
targetInner.setInnerData(othersResult); // Property assignment

// Diff-package ReturnStatement call (pos: diff_package_target)
baseResult += other.diff.DiffPackageHelper.getDiffPackageResult(targetInner);

// Recursion base case
if (depth <= 0) {
return baseResult;
}

// Recursive invocation
return baseResult + recursiveMethod(target, depth - 1);
}

// Abstract method (required for abstract class)
public abstract String getSourceAbstractData();
}

// Target: normal class with anonymous inner class (target_feature)
class TargetClass {
private String targetBase = "target_base_data";

// Recursive inner class (target_inner_rec)
public class TargetInnerRec {
public String innerData;
public int innerNum;

public TargetInnerRec(String data, int num) {
this.innerData = data;
this.innerNum = num;
// Anonymous inner class (target_feature)
Runnable anonTask = new Runnable() {
@Override
public void run() {
System.out.println("TargetInnerRec anonymous: " + innerData);
}
};
anonTask.run();
}

public String getInnerData() {
return innerData;
}

public void setInnerData(String data) {
this.innerData = data;
}

public int calculateDepth(int depth) {
return depth * 3;
}

public String updateData(String prefix, int depth) {
this.innerData = prefix + "_depth" + depth;
return this.innerData;
}
}

public TargetInnerRec createInnerRec(int depth) {
return new TargetInnerRec(targetBase + "_depth" + depth, depth);
}

public String getTargetBase() {
return targetBase;
}
}