package test;
// Others class for method feature dependenciesclass OthersClass {protected int superField = 3;
public int getSuperField() {return superField;}}
// Functional interface for method feature pos: "functional interfaces"@FunctionalInterfaceinterface MyFunctionalInterface {Object execute(int param);}
// Source interface: default modifier, with two static nested classesinterface SourceInterface {// 1st static nested class (source feature)static class SourceStaticNested1 extends OthersClass {public static int staticField1 = 3;}
// 2nd static nested class (source feature)static class SourceStaticNested2 {// Recursive method (matches method type: "recursion")public static int recursiveMethod(TargetInterface.TargetStaticNested targetInner, int depth) {// Base case for recursionif (depth <= 0) {return 0;}
// Type declaration statement (method feature)MyFunctionalInterface func;// Expression statement (method feature)int currentDepth = depth - 1;
// EmptyStatement with target_feature (super.field & 3) (method feature)SourceStaticNested1 nested1 = new SourceStaticNested1();if (nested1.superField == 3) {; // Empty statement}
// Abstract method usage in functional interface (method feature)func = (param) -> {if (param == 3) {variableCall(); // Variable call (method feature)// access_instance_method (method feature)return nested1.getSuperField();}return new Object();};func.execute(3);
// Recursive call (target class: target_inner_rec)return SourceStaticNested1.staticField1 + func.execute(3).hashCode() + recursiveMethod(targetInner, currentDepth);}
// Variable call target methodprivate static void variableCall() {}}
// Default method to trigger refactoring (contains target parameter: per_condition)default int triggerRefactor(TargetInterface.TargetStaticNested targetInner, int depth) {return SourceStaticNested2.recursiveMethod(targetInner, depth);}}
// Target interface: protected modifier, with static nested class (target feature)protected interface TargetInterface {// Static nested class (target feature) for "target_inner_rec"static class TargetStaticNested extends OthersClass {public int getTargetValue() {return superField;}}}