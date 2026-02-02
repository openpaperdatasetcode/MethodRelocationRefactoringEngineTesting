package test;
// Interface for source enum to implementinterface TargetConstructible {TargetEnum createTarget();}
// Source enum: default modifier, implements interface, with member inner & static nested classesenum SourceEnum implements TargetConstructible {INSTANCE;
// Static nested class (source feature)static class SourceStaticNested {private int nestedField;
// Final constructor (matches method_feature "final" "constructor")public final SourceStaticNested(int field) {this.nestedField = field;}
public int getNestedField() {return nestedField;}}
// Member inner class (for source_inner_rec method position)class SourceInner {private int innerCount = 0;
/**
Constructor (method type: constructor) - refactored method
@param targetParam Target enum parameter (satisfies per_condition)*/// Method javadoc (method feature)public SourceInner(TargetEnum targetParam) {// Uses outer this (uses_outer_this feature)SourceEnum outerEnum = SourceEnum.this;
// Lambda expressions (method feature pos)Runnable lambda = () -> {// Call static nested class constructor (method_feature "ClassName.methodName(arguments)")SourceStaticNested nested = new SourceStaticNested(1);if (nested.getNestedField() == 1) {variableCall(); // Variable call (method feature)innerCount++;if (innerCount > 1) {breakLoop: // Label for break statementfor (int i = 0; i < 3; i++) {if (i == 1) {break breakLoop; // Break statement (method feature)}}}}};lambda.run();}
// Recursive method (source_inner_rec)public TargetEnum recursiveBuild(TargetEnum target, int depth) {// Base case for recursionif (depth <= 0) {return target;}// Call inner class constructor (source_inner_rec position)new SourceInner(target);// Recursive callreturn recursiveBuild(target, depth - 1);}
// Variable call target methodprivate void variableCall() {}}
// Implement method from TargetConstructible@Overridepublic TargetEnum createTarget() {TargetEnum target = TargetEnum.VALUE;// Trigger inner class constructor & recursion (contains target parameter)return new SourceInner(target).recursiveBuild(target, 2);}}
// Target enum: private modifier, with anonymous inner class (target feature)private enum TargetEnum {VALUE;
// Anonymous inner class (target feature)private Runnable anonTask = new Runnable() {@Overridepublic void run() {System.out.println("Target enum anonymous task");}};
// Method for source recursion callpublic void executeTask() {anonTask.run();}}