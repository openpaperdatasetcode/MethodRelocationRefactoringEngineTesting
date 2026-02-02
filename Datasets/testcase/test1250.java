package test.pkg;
protected class SourceClass extends ParentClass {// Member inner class (source_class feature)public class MemberInnerClass {public void innerMethod(String data) {}}
int processData(TargetClass<String> targetParam) {// Anonymous inner class (source_class feature)Runnable runnable = new Runnable() {@Overridepublic void run() {MemberInnerClass memberInner = new MemberInnerClass();memberInner.innerMethod(targetParam.getTargetField()); // Access target field via var call}};runnable.run();
// Variable call: target's local inner class (via target's method)targetParam.invokeLocalInnerClass();
// SwitchStatement (pos: diff_package_others, target_feature: obj.field + 1)String targetField = targetParam.getTargetField(); // obj.fieldswitch (targetField.length()) {case 1: // target_feature "1"// Call_method: parent_class type (overriding, OuterClass.InnerClass.methodName()) in if/elseif (targetField.equals("a")) {String parentResult = SourceClass.super.parentInnerClass.overriddenMethod(targetField);System.out.println(parentResult);} else {String parentResult = SourceClass.super.parentInnerClass.overriddenMethod("default");System.out.println(parentResult);}break;default:break;}
return targetField.length(); // Return base type (int)}}
public class TargetClass<T> {private T targetField;
public TargetClass(T targetField) {this.targetField = targetField;}
// Local inner class (target_feature)public void invokeLocalInnerClass() {class LocalInnerClass {public void localMethod() {System.out.println("Target Local Inner: " + targetField);}}LocalInnerClass localInner = new LocalInnerClass();localInner.localMethod();}
// Accessor for target field (obj.field)public T getTargetField() {return targetField;}
// Method will be moved here:// int processData(TargetClass<String> targetParam) { ... }}
class ParentClass {// Parent class inner class for call_method (OuterClass.InnerClass)protected ParentInnerClass parentInnerClass = new ParentInnerClass();
public static class ParentInnerClass {// Overriding method (call_method target_feature)public String overriddenMethod(String arg) {return "ParentInner: " + arg;}}
// Overridden method (demonstrates overriding feature)public String overriddenMethod(String arg) {return "Parent: " + arg;}}