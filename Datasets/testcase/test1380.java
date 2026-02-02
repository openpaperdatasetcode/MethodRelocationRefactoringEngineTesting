package test.refactoring.movemethod;
// Source enum class: default modifier, same package with target, has static nested + member inner classenum SourceEnum {INSTANCE;
// Target class field (per_condition: source contains target's field)private TargetEnum targetField = TargetEnum.VALUE;
// Static nested class (source feature)static class SourceStaticNestedClass {}
// Member inner class (source feature)class SourceMemberInnerClass {// EnhancedForStatement in inner class (method feature)public void enhancedForInInner() {String[] arr = {"test"};for (String s : arr) { // EnhancedForStatementSystem.out.println(this); // this.field (implicit this reference)int count = 1; // "1" in target_feature}}}
// Method to be refactored: instance, public, return voidpublic void refactorTargetMethod() {// Empty statement;
// Variable callTargetEnum tempTarget = targetField;
// Access instance method (target enum's instance method)tempTarget.targetInstanceMethod();
// Functional interfaces (pos for nested instance method)Runnable functionalInterface = () -> {// Instance method in functional interface: default modifier, return voidthis.refactorTargetMethod("arg"); // this.methodName(arguments)};functionalInterface.run();
// Lambda expression: () -> System.out.println(this.value)Runnable lambda = () -> System.out.println(this.targetField.value());
// Call enhanced for statement in inner classSourceMemberInnerClass inner = new SourceMemberInnerClass();inner.enhancedForInInner();}
// Nested instance method (method_feature: inner_class, instance, this.methodName(arguments))default void refactorTargetMethod(String arguments) {// Method feature: "1" (constant 1)int num = 1;}}
// Target enum class: private modifier, has local inner class (target_feature)private enum TargetEnum {VALUE(10);
private final int value;
TargetEnum(int value) {this.value = value;}
// Instance method for access_instance_method featurepublic void targetInstanceMethod() {// Local inner class (target_feature)class TargetLocalInnerClass {void localMethod() {}}TargetLocalInnerClass localInner = new TargetLocalInnerClass();localInner.localMethod();}
// Getter for lambda expressionpublic int value() {return this.value;}}