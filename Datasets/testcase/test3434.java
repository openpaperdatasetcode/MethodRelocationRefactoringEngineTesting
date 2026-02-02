package test;
import java.util.List;import java.util.ArrayList;import otherpkg.OtherPackageClass;
class SourceClass {// Static nested class (source feature)public static class SourceStaticNested {}
public void outerMethod() {// Local inner class (source feature)class SourceInner {// Varargs method (base type parameter, returns List<String>)List<String> varargsMethod(String... baseTypeParams) {List<String> result = new ArrayList<>();
// NullPointerExceptionif (baseTypeParams == null) {throw new NullPointerException("Varargs parameter cannot be null");}
try {// ConstructorInvocation (private, target_feature: this.field x1, pos: diff_package_others)OtherPackageClass other = new OtherPackageClass();private TargetClass target = new TargetClass(other.field);
// Super constructor invocation (target's parent class)TargetInnerChild innerChild = new TargetInnerChild(target, "data");
// Type declaration statementTargetClass.TargetLocalInner targetInner = target.createLocalInner();
// Variable call + overload_existtargetInner.variableCall();targetInner.variableCall(baseTypeParams);
// Add varargs params to resultfor (String param : baseTypeParams) {result.add(param);}} catch (Exception e) {// No new exception}
return result;}}
SourceInner inner = new SourceInner();inner.varargsMethod("a", "b", "c");}}
// Target parent class for super constructor invocationclass TargetParentClass {protected String parentField;
public TargetParentClass(String field) {this.parentField = field;}}
// Target class (protected modifier, with local inner class)protected class TargetClass extends TargetParentClass {public int targetField; // Field for per_condition
public TargetClass(String field) {super(field); // Super constructor invocationthis.targetField = 100;}
// Local inner class (target_feature)public TargetLocalInner createLocalInner() {class TargetLocalInner {public void variableCall() {}public void variableCall(String... params) {} // Overload_exist}return new TargetLocalInner();}}
// Target inner child class for super constructor invocationclass TargetInnerChild extends TargetClass {public TargetInnerChild(TargetClass target, String data) {super(data); // Super constructor invocationthis.targetField = target.targetField;}}
// Diff package class (separate package)package otherpkg;
public class OtherPackageClass {public String field = "other_field";}
