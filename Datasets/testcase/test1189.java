package test.refactoring.source;
import test.refactoring.target.TargetClass;import java.util.List;import java.util.ArrayList;import java.io.IOException;
/**
Source class: public normal class, different package with target, extends parent class*/public class SourceClass extends ParentClass {// Protected field for access_outer_protected featureprotected String outerProtectedField = "protected_field";
// Source feature: member inner classpublic class SourceMemberInnerClass {// Source inner class (method_position: source_inner)public class NestedInnerClass extends ParentNestedClass {/**
Varargs method to be refactored (protected access, void return)
@param targetParam target class parameter (per_condition)
@param varargs varargs parameters
@throws IOException required checked exception (requires_throws)/
// method javadoc feature
/*
Javadoc for the refactored varargs method
@param targetParam target class instance
@param varargs variable-length argument list
@throws IOException if target operation fails*/@Overrideprotected void refactorTargetMethod(TargetClass targetParam, String... varargs) throws IOException {// Super method invocation (from parent nested class)super.refactorTargetMethod(targetParam, varargs);
// Variable callTargetClass tempTarget = targetParam;
// Access outer protected field (access_outer_protected)System.out.println(SourceClass.this.outerProtectedField);
// Depends on inner class (target's member inner class)TargetClass.TargetMemberInner inner = tempTarget.new TargetMemberInner();inner.innerMethod();
// Do statementint doCount = 0;do {inner.innerMethod();doCount++;} while (doCount < 2);
// Enhanced for statementfor (String arg : varargs) {System.out.println("Vararg: " + arg);}
// Throw statement (controlled checked exception)if (varargs == null || varargs.length == 0) {throw new IOException("Varargs cannot be empty");}
// Instance code blocks (pos for overriding nested method){TargetClass result = overridingNestedMethod(tempTarget, 3);}}
/**
Overriding nested method (type: overriding, modifier: public, return_type: TargetClass Type)
@param target target class instance
@param num "3" in method_feature
@return TargetClass instance
*/
@Override
public TargetClass overridingNestedMethod(TargetClass target, int num) {
int count = 3; // "3" in method_feature
super.overridingNestedMethod(target, count); // super.methodName()
return target; // "inner_class" + "overriding" features
}
}
}
// Source feature: local inner classpublic void sourceWithLocalInner() {class SourceLocalInnerClass {void localMethod() {System.out.println(outerProtectedField);}}new SourceLocalInnerClass().localMethod();}
/**
Call method: sub_class type, final modifier, pos in object initialization
@param subTarget subclass of TargetClass
@return List<String> result
*/
public final List<String> callSubClassMethod(TargetSubClass subTarget) {
// Object initialization position + ClassName::methodName + instance feature
List<String> result = new ArrayList<>();
result.add(subTarget.processData("arg"));
subTarget.processWithMethodRef(result::add); // ClassName::methodName (method reference)
return result;
}
}
// Parent class for source class extends featureclass ParentClass {}
// Parent nested class for overriding featureabstract class ParentNestedClass {public abstract void refactorTargetMethod(TargetClass targetParam, String... varargs) throws IOException;public abstract TargetClass overridingNestedMethod(TargetClass target, int num);}
// Different package: test.refactoring.targetpackage test.refactoring.target;
import java.util.List;
/**
Target class: public normal class, target_feature: member inner class*/public class TargetClass {// Target feature: member inner classpublic class TargetMemberInner {public void innerMethod() {}}
public String processData(String arg) {return "Processed: " + arg;}
public void processWithMethodRef(java.util.function.Consumer<String> consumer) {consumer.accept("method_ref_arg");}}
// Subclass of TargetClass for call_method (sub_class type)package test.refactoring.target;
public class TargetSubClass extends TargetClass {// Instance feature (inherits from parent class)@Overridepublic String processData(String arg) {return "Subclass processed: " + arg;}}