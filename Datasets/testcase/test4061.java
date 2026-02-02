package test;
// Source annotation: has anonymous inner & member inner classes@interface SourceAnnotation {// Member inner class (source_class feature)class SourceMemberInner {// Static method to be refactored (contains target parameter)private static int processTarget(TargetAnnotation targetParam) {// Type declaration statementTargetProcessor processor = new TargetProcessor();
int total = 0;for (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue; // Continue statement}; // Empty statement
// Variable call: access target annotation attributeString targetVal = targetParam.value();total += targetVal.length() + i;
// Constructor invocation: create helper instanceTargetHelper helper = new TargetHelper(targetParam);total += helper.getAdjustValue();
// Super keywords: call parent class method of helpertotal += helper.callSuperMethod();}return total;}}
// Anonymous inner class (source_class feature)Runnable sourceAnon = new Runnable() {@Overridepublic void run() {// Use target annotation instance (per_condition: method has target parameter)TargetAnnotation target = new TargetAnnotation() {};int result = SourceMemberInner.processTarget(target);}};
// Nested helper class for constructor invocation & super keywordsclass TargetHelper extends HelperParent {private TargetAnnotation target;
// Constructor invocation (used in processTarget)TargetHelper(TargetAnnotation target) {super(); // Super keywords: explicit parent constructor callthis.target = target;}
int getAdjustValue() {return target.value().length();}
int callSuperMethod() {return super.getBaseValue(); // Super keywords: call parent method}}
// Parent class for TargetHelper (super keywords usage)class HelperParent {protected int getBaseValue() {return 10;}}
// Helper interface for target annotation attributeinterface TargetProcessor {}}
// Target annotation (default modifier, no extra features)@interface TargetAnnotation {// Attribute for variable call in source methodString value() default "target_default";}