package test;
sealed class SourceClass permits SourceSubClass {// Member inner classes (matches source_class.feature)class SourceInnerOne {}class SourceInnerTwo {}
// Refactored method: instance, return TargetClas Type, private accessprivate TargetClass moveMethod(TargetClass targetParam) {// super keywords (matches method.features)super.toString();
// Type declaration statement (matches method.features)class LocalType {int value;}LocalType local = new LocalType();
// Variable call: access target's fieldlocal.value = targetParam.targetField;
// Break statement (matches method.features)loop: {if (local.value > 5) {break loop;}local.value++;}
// Return TargetClas Typereturn targetParam;}
// Constructor with call_method in parameter listSourceClass() {this(defaultCallMethod());}
private SourceClass(Void dummy) {}
// call_method: source type, default modifier, normal, super.methodName()void defaultCallMethod() {super.hashCode();}}
non-sealed class SourceSubClass extends SourceClass {}
/**
Javadoc for TargetClass (matches target_class.target_feature)
Contains member inner class and target field*/private class TargetClass {// Target field (accessed by source method)int targetField;
// Member inner class (matches target_class.target_feature)class TargetInner {void innerMethod() {}}}