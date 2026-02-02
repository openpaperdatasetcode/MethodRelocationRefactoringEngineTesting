package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
@interface SourceAnnotation {// Static nested class (source @interface feature)class SourceStaticNested {TargetAnnotation.InnerRecursive innerRecursiveField;}
// Anonymous inner class 1 (source @interface feature)Runnable anon1 = new Runnable() {@Overridepublic void run() {}};
// Anonymous inner class 2 (source @interface feature)Comparable<String> anon2 = new Comparable<String>() {@Overridepublic int compareTo(String o) {return 0;}};
// Instance method (matches method type/return/access modifier)@MethodAnnotation // has_annotation (method feature)List<String> process(TargetAnnotation.InnerRecursive targetParam) throws Exception {List<String> result = new ArrayList<>();String[] dataArr = {"a", "b", "c"};
// EnhancedForStatement (method feature)for (String s : dataArr) {if (targetParam.field == 1) { // this.field (target_feature)continue; // continue statement (method feature)}result.add(s);}
int count = 0;while (count < 3) { // pos: "while" (method feature)// OthersClass instance method call (method_feature)OthersClass.callInstanceMethod(3);// Expression statement (method feature)String temp = targetParam.toString();result.add(temp);count++;}
// If statement (method feature)if (result.size() > 0) {variableCall(); // variable call (method feature)}
// Super keywords (method feature)Object superObj = super.getClass();result.add(superObj.getSimpleName());
@MethodAnnotation // has_annotation (method feature)int annotatedVar = 0;result.add(String.valueOf(annotatedVar));
return result;}
// Variable call target methodvoid variableCall() {}}
public @interface TargetAnnotation {// InnerRecursive class (for target_inner_rec)class InnerRecursive {int field; // Target field for "this.field"}
// Anonymous inner class (target @interface feature)Runnable targetAnon = new Runnable() {@Overridepublic void run() {}};}
// OthersClass for method_feature "others_class"class OthersClass {// Instance method (matches method_feature "instance")public void instanceMethod(int num) {}
// Static method to wrap instance call (matches "ClassName.methodName(arguments)")public static void callInstanceMethod(int num) {new OthersClass().instanceMethod(num);}}