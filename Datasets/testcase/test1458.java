package test.refactoring;
import java.io.IOException;
// Source class: normal, strictfp, same package, two anonymous inner classesstrictfp class SourceClass {// Source contains target's field (per_condition)protected TargetClass targetField = new TargetClass();protected String outerProtectedField = "outer_protected";
// First anonymous inner class (source feature)private Runnable firstAnonymous = new Runnable() {@Overridepublic void run() {new SourceInnerClass().new SourceInnerRecClass().moveTargetMethod();}};
// Second anonymous inner class (source feature - duplicate)private Runnable secondAnonymous = new Runnable() {@Overridepublic void run() {new SourceInnerClass().new SourceInnerRecClass().moveTargetMethod();}};
// Member inner class (source_inner_rec parent)class SourceInnerClass {// Member inner class (source_inner_rec: method's original position)class SourceInnerRecClass {/**
Method Javadoc (feature: method javadoc)
Performs operations with required features*/@MyAnnotation // has_annotationprotected void moveTargetMethod() {// Type declaration statementclass LocalType {public void localInstanceMethod() {System.out.println("Local instance method");}}
// Variable call + access_outer_protectedString var = outerProtectedField;System.out.println(var);
// requires_try_catch (checked exception requires try-catch)try {// Access instance method (local class instance method)LocalType localObj = new LocalType();localObj.localInstanceMethod();
// Access target field's instance methodtargetField.targetInnerClass.new TargetInnerClass().targetInstanceMethod();} catch (IOException e) {e.printStackTrace();}}
// Override violation: target has same-signature method with stricter accessprotected void moveTargetMethod(String param) {}}}
// Instance method for access_instance_methodprotected void sourceInstanceMethod() {System.out.println("Source instance method");}}
// Annotation for has_annotation feature@interface MyAnnotation {}
// Target class: normal, protected, has member inner class (target_feature)protected class TargetClass {// Member inner class (target_feature)public class TargetInnerClass {// Target instance methodpublic void targetInstanceMethod() throws IOException {}
// Override violation: same method signature with private access (stricter than protected)private void moveTargetMethod() {}}
// Target inner class instancepublic TargetInnerClass targetInnerClass = new TargetInnerClass();}
// Super type for call_method's superTypeReferenceclass SuperCaller {}
// Other class containing call_methodclass OtherCallerClass extends SuperCaller {// Call method: others_class, protected, normal, superTypeReference.methodName, pos: ternary, return voidprotected void callMethod(SourceClass.SourceInnerClass.SourceInnerRecClass innerRec) {SourceClass source = new SourceClass();// pos: ternary operatorsboolean flag = true;flag ? superCallMethod(innerRec) : superCallMethod(innerRec);}
// Helper method for superTypeReferenceprotected void superCallMethod(SourceClass.SourceInnerClass.SourceInnerRecClass innerRec) {// target_feature: superTypeReference.methodName(arguments)super.toString();innerRec.moveTargetMethod();}}