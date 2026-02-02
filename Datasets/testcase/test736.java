import java.util.ArrayList;import java.util.List;
// Target class (strictfp, static nested class for target_inner_rec)strictfp class TargetClass {String superField = "superField1";
// Static nested class (target_inner_rec)static class TargetNestedClass {void instanceMethod(String arg) {System.out.println(arg);}
void instanceMethod(int arg) { // Overload for overload_existSystem.out.println(arg);}}
public TargetClass() {}}
// Source class (private, type parameter, anonymous inner class, static nested class)private class SourceClass<T extends Number> {// Field of target class (per_condition)TargetClass targetField = new TargetClass();
// Static nested classstatic class SourceStaticNested {int field = 3;
// Inner class for method_featureclass InnerClass {void callInstanceMethod(TargetNestedClass instance) {instance.instanceMethod("arg1"); // instanceReference.methodName(arguments)}}}
// Anonymous inner classRunnable anonymousRunnable = new Runnable() {@Overridepublic void run() {System.out.println(targetField.superField);}};
// Abstract method in inner recursive class (source_inner_rec)abstract class SourceInnerRecursiveClass {String localVar;
// Annotation with attribute using instance method (pos: annotation attributes)@MyAnnotation(value = "call: " + new SourceStaticNested.InnerClass().callInstanceMethod(null))abstract TargetClass method(); // Abstract, TargetClass return, default access
// Instance method (public, 3, inner_class, instance, instanceReference.methodName)@SuppressWarnings("unused")public void helperMethod() {SourceStaticNested nested = new SourceStaticNested();nested.InnerClass inner = nested.new InnerClass();TargetNestedClass targetInstance = new TargetNestedClass();inner.callInstanceMethod(targetInstance);}
// Overload exist (overload of helperMethod)public void helperMethod(int num) {System.out.println(num);}
// Method implementation for abstract method (to satisfy structure)TargetClass methodImpl() {// Enhanced for statementList<Integer> list = new ArrayList<>();list.add(1);for (int num : list) {System.out.println(num);}
// Type declaration statementclass LocalType {int val = 1;}
// SuperFieldAccess (numbers: 1, modifier: default)String superField = targetField.superField + 1;
// this.var = varthis.localVar = superField;
// Variable callint varCall = new SourceStaticNested().field;
// Raw typeList rawList = new ArrayList();rawList.add(varCall);
// Access instance methodtargetInstance.instanceMethod(1);
// No new exceptionreturn new TargetClass();}
// Variable for this.var = varString localVar;TargetNestedClass targetInstance = new TargetNestedClass();}
// Custom annotation for has_annotation@interface MyAnnotation {String value();}}
// Alias for TargetClass.TargetNestedClass to match "TargetNestedClass Type" contextclass TargetNestedClass extends TargetClass.TargetNestedClass {}
