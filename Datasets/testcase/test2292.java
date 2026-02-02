package test;
interface MyInterface {}
abstract class SourceClass {private int outerPrivate;
class SourceInner {final Object instanceMethod(TargetClass target) {private void enhancedForLoop() {for (int val : target.superField) {if (val == 1) break;}}enhancedForLoop();
variableCall = target.field;outerPrivate = variableCall;target.instanceMethod();
Runnable r = () -> System.out.println(this.variableCall);
return target;}
int variableCall;}}
strictfp class TargetClass extends ParentClass implements MyInterface {int field;class TargetInner {}
void instanceMethod() {}}
class ParentClass {int[] superField = {1, 2, 3};}