package same.pkg;
// Target class: public modifier, with member inner class (target_feature)public class TargetClass {private String targetField = "targetData";
// Target's member inner classpublic class TargetInner {private int innerField;
public TargetInner(int innerField) {this.innerField = innerField;}
// Inner class instance method (for source's call)public TargetClass getOuterTarget() {return TargetClass.this; // Return outer target instance}
public int getInnerField() {return innerField;}}
// Target's instance method (for access_instance_method)public String getTargetField() {return targetField;}}
// Source class: public modifier, implements interface, with anonymous/static nested classespublic class SourceClass implements RefactorTestInterface {// Contains target's field (per_condition)private static TargetClass targetField = new TargetClass();
// Source's static nested class (source_feature)public static class SourceStaticNested {// Nested class method using super keywordspublic String useSuper() {return super.toString(); // super keywords (matches method.feature)}}
// Static method: final modifier, returns Object (matches method.type/return_type)@Overridepublic final static Object staticMethod() {variableCall();
// Type declaration statementTypeDeclaration typeDecl = new TypeDeclaration();
// For statement with break statementObject result = null;for (int i = 0; i < 3; i++) {// Access target's instance method (access_instance_method)String targetVal = targetField.getTargetField();result = targetVal + "_" + i;
if (i == 1) {break; // Break statement (matches method.feature)}}
// 1. Instance method call (inner_class, public modifier) in expression// superTypeReference: TargetInner's super type (Object) method callTargetClass.TargetInner targetInner = targetField.new TargetInner(10);TargetClass innerResult = targetInner.getOuterTarget();String superTypeCall = Object::toString.apply(innerResult); // superTypeReference.methodNameresult = result + "|" + superTypeCall;
// Anonymous inner class (source_feature)Runnable anonRunnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in SourceClass static method");}};anonRunnable.run();
return result;}
// Variable call (uses target's field)private static void variableCall() {String localVar = targetField.getTargetField();}
// Nested type declaration (matches method.feature)private static class TypeDeclaration {}}
// Interface for source to implement (source_feature: implements)interface RefactorTestInterface {Object staticMethod();}