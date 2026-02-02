package test.refactoring;
import java.util.ArrayList;import java.util.List;
// Target class: normal, sealed, has extends/local inner class (target_feature)sealed class TargetClass extends ParentTargetClass permits TargetSubclass {// Target field referenced by source (per_condition)public String targetField = "target_field";
public TargetClass() {super();}
// Local inner class (target_feature)public void targetLocalMethod() {class TargetLocalInner {public String getField() {return targetField;}}new TargetLocalInner().getField();}}
// Parent class for TargetClass's extends featureclass ParentTargetClass {}
// Permitted subclass for sealed TargetClassfinal class TargetSubclass extends TargetClass {}
// Source class: normal, public, same package, has type parameter/member inner/local inner classpublic class SourceClass<T> {protected T outerProtectedField; // For access_outer_protected// Source contains target's field (per_condition)private TargetClass targetField = new TargetClass();private String sourceVar = "source_variable";
// Member inner class (parent of source_inner_rec)public class SourceInnerClass {// Member inner class (source_inner_rec: method's original position)public class SourceInnerRecClass {// Target method: instance, List<String>, public, source_inner_rec positionpublic List<String> moveTargetMethod() {List<String> result = new ArrayList<>();T var = outerProtectedField; // variable call + access_outer_protected
// Super constructor invocation (via outer class's parent implication)super.toString();
// Uses_outer_this (refers to outer SourceClass instance)T outerThisVal = SourceClass.this.outerProtectedField;result.add(String.valueOf(outerThisVal));
// Empty statement;
// numbers:1, modifier:protected, exp:NullLiteralprotected Object nullLiteralObj = null;
// NullPointerExceptionif (var == null) {throw new NullPointerException("Outer protected field is null");}
// Generic method feature (private, inner_class, generic, new ClassName().method())GenericInnerClass<T> genericInner = new GenericInnerClass<>();// pos: array initializationObject[] array = {genericInner.execute(targetField)};
// Variable call to target fieldresult.add(targetField.targetField);result.add(sourceVar);
// No new checked exceptionreturn result;}
// Inner class for generic method featureprivate class GenericInnerClass {
// method_feature:1 (single method), generic
public Object execute(TargetClass target) {
// target_feature: new ClassName().method()
return new TargetSubclass().targetField;
}
}
}
}
// Local inner class (source feature)public void sourceLocalMethod() {class SourceLocalInner {public void invokeInnerMethod() {SourceInnerClass inner = new SourceInnerClass();inner.new SourceInnerRecClass().moveTargetMethod();}}new SourceLocalInner().invokeInnerMethod();}
public SourceClass(T outerProtectedField) {this.outerProtectedField = outerProtectedField;}}