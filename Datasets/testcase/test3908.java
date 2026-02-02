package test;
// Interface for source class to implementinterface Buildable {TargetClass buildTarget();}
// Source class: strictfp modifier, implements interface, with member & local inner classesstrictfp class SourceClass implements Buildable {// Protected outer field for "access_outer_protected" featureprotected String outerProtectedField = "source-protected-data";
// Member inner class (source feature)class SourceMemberInner {// Method using outer instance via "uses_outer_this"String getOuterData() {return SourceClass.this.outerProtectedField;}}
// Constructor (method type: constructor) - refactored methodpublic SourceClass(TargetClass targetParam) { // Contains target parameter (per_condition)// Variable call featureinitHelper();
// Access outer protected field (access_outer_protected feature)String protectedData = this.outerProtectedField;targetParam.setData(protectedData);
// Local inner class (source feature)class SourceLocalInner {void updateTarget(TargetClass target) {// Uses outer this (uses_outer_this feature)target.setData(SourceClass.this.outerProtectedField + "-updated");}}new SourceLocalInner().updateTarget(targetParam);}
// Variable call target methodprivate void initHelper() {SourceMemberInner inner = new SourceMemberInner();System.out.println("Init with outer data: " + inner.getOuterData());}
// Implement method from Buildable interface@Overridepublic TargetClass buildTarget() {TargetClass target = new TargetClass();return new SourceClass(target).createTargetCopy(target); // Trigger constructor}
// Helper to return TargetClass Type (matches method return_type)private TargetClass createTargetCopy(TargetClass original) {return new TargetClass(original.getData());}}
/**
Javadoc for TargetClass (target_feature: javadoc)
Normal class with static nested class for auxiliary operations*/class TargetClass {// Static nested class (target_feature)public static class TargetStaticNested {public static String processData(String data) {return "processed-" + data;}}
private String data;
// Default constructorpublic TargetClass() {}
// Copy constructorpublic TargetClass(String data) {this.data = TargetStaticNested.processData(data);}
public String getData() {return data;}
public void setData(String data) {this.data = data;}}