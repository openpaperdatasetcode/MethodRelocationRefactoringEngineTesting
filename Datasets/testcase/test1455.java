package test.refactoring;
// Source class: abstract, default modifier, same package, has member inner/static nested classabstract class SourceAbstractClass {private int outerPrivateField = 10; // For access_outer_private feature
// Static nested class (source feature)static class SourceStaticNested {}
// Member inner class (source_inner_rec: method's original position)class SourceInnerRecClass {// Target method: instance, base type (int), protected, source_inner_rec positionprotected int moveTargetMethod(int var, TargetAbstractClass targetParam) { // per_condition: contains target parameter// Variable callint result = outerPrivateField; // access_outer_privateint localVar = var;
// Try statement + no_new_exceptiontry {// Type declaration statementclass LocalType {}// ConstructorInvocation (private, pos: source, target_feature: obj.field x1)LocalType localObj = new LocalType();String fieldVal = targetParam.targetField; // obj.field (target_feature)
// TypeMethodReference (numbers:1, abstract modifier)SourceFunctionalInterface func = LocalType::new;
// Method type parameter is keywords (void as keyword-like parameter)processKeywordParam(void.class);} catch (RuntimeException e) {// No new checked exception}
return result + localVar;}
// Helper method: method type parameter is keywordsprivate <T> void processKeywordParam(Class<T> param) {}}
// Call method: source class, protected, constructor, ClassName::methodName, pos: switch, return Stringprotected String callMethod(int choice) {switch (choice) { // call_method pos: switchcase 1:// target_feature: ClassName::methodName (static method reference)SourceFunctionalInterface func = SourceStaticNested::new;return "Case 1";default:// target_feature: constructor invocationSourceInnerRecClass inner = new SourceInnerRecClass();return String.valueOf(inner.moveTargetMethod(5, TargetAbstractClass.createInstance()));}}
// Functional interface for TypeMethodReferenceprivate abstract interface SourceFunctionalInterface {void apply();}}
// Target class: abstract, no modifier, has static nested class (target_feature)abstract class TargetAbstractClass {// Target field referenced in ConstructorInvocationString targetField = "target_abstract_field";
// Static nested class (target_feature)static class TargetStaticNested {}
// Factory method to create instance (since abstract class can't be instantiated directly)public static TargetAbstractClass createInstance() {return new TargetAbstractClass() {};}}
