package test;
// Interface for source class to implementinterface Calculable {int calculate(int num);}
// Source class: protected modifier, implements interface, with static nested & member inner classesprotected class SourceClass implements Calculable {// Target class field (satisfies per_condition: source contains target field)private TargetClass targetField = new TargetClass();// Instance field for "OuterClass.this.x" featureprivate int outerField = 5;
// Static nested class (source feature)static class SourceStaticNested {int staticNestedField = 2;}
// Member inner class (source feature)class SourceInnerClass {// Use outer class instance via "OuterClass.this.x"int getOuterField() {return SourceClass.this.outerField;}}
// Recursive method: private access, base type (int) returnprivate int recursiveMethod(int depth) {// Base case for recursionif (depth <= 0) {return 0;}
// Switch case (method feature)SourceStaticNested staticNested = new SourceStaticNested();switch (staticNested.staticNestedField) {case 2:variableCall(); // Variable call (method feature)break;default:break;}
// Use target class field (per_condition) and "OuterClass.this.x"SourceInnerClass inner = new SourceInnerClass();int sum = targetField.TargetStaticNested.targetStaticField + inner.getOuterField();
// Recursive callreturn sum + recursiveMethod(depth - 1);}
// Variable call target methodprivate void variableCall() {}
// Implement method from Calculable interface@Overridepublic int calculate(int num) {return recursiveMethod(num);}}
// Target class: private modifier, with static nested class (target feature)private class TargetClass {// Static nested class (target feature)static class TargetStaticNested {static int targetStaticField = 3;}}