package test;
// Parent class for source class to extendclass ParentGenericClass<T> {protected T parentField;public ParentGenericClass(T field) { this.parentField = field; }}
// Source class: generic, default modifier, extends ParentGenericClass (source feature)class SourceClass<T extends Number> extends ParentGenericClass<T> {// Private outer field for "access_outer_private" featureprivate String outerPrivateField = "source-private-data";// Target class field (satisfies per_condition: source contains target field)private TargetClass<T> targetField;
public SourceClass(T parentField, TargetClass<T> targetField) {super(parentField);this.targetField = targetField;}
// Recursive method (method type: recursion) - refactored methodpublic TargetClass<T> recursiveMethod(int depth) {// Base case for recursionif (depth <= 0) {return targetField;}
// Constructor invocation (method feature)TargetClass<T> newTarget = new TargetClass<>(this.parentField);
// Expression statement (method feature)T currentValue = newTarget.getValue();String combinedData = outerPrivateField + "-" + currentValue;
// MethodInvocation with numbers "2" (method feature)if (newTarget.calculate(depth, 2) == 2) {variableCall(); // Variable call (method feature)}
// access_outer_private feature: use private outer fieldnewTarget.setAuxData(combinedData);
// Recursive call (target class: target_inner)return recursiveMethod(depth - 1);}
// Variable call target methodprivate void variableCall() {}}
// Target class: generic, public modifier, with local inner class (target feature)public class TargetClass<T extends Number> {private T value;private String auxData;
public TargetClass(T value) {this.value = value;}
// Method for MethodInvocation feature (protected modifier)protected int calculate(int a, int b) {// Local inner class (target feature)class CalculationInner {int compute(int x, int y) {return x > y ? y : x;}}return new CalculationInner().compute(a, b);}
public T getValue() { return value; }public void setAuxData(String auxData) { this.auxData = auxData; }}
