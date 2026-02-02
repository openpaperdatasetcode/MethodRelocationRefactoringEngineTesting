package test;
public class SourceClass extends ParentClass {// Source contains target field (matches per_condition)private TargetClass targetField = new TargetClass();
// 1st anonymous inner class (source_class feature)Runnable anon1 = new Runnable() {@Overridepublic void run() {recursiveMethod(3);}};
// 2nd anonymous inner class (source_class feature)Runnable anon2 = new Runnable() {@Overridepublic void run() {new SourceClass().recursiveMethod(2);}};
// Overriding method (matches nested overriding feature)@Overridepublic void overrideMethod() {new ParentClass().overrideMethod();}
// Recursive method to be refactoredprotected TargetClass recursiveMethod(int depth) {if (depth <= 0) {return targetField;}
// For statementfor (int i = 0; i < depth; i++) {// Expression statement + Variable calltargetField.targetVal += i;}
// Recursionreturn recursiveMethod(depth - 1);}}
// Parent class for overriding featureclass ParentClass {public void overrideMethod() {}}
private class TargetClass {int targetVal = 0;
// Local inner class (target_class feature)void createLocalInner() {class TargetLocalInner {void updateVal(int val) {TargetClass.this.targetVal = val;}}new TargetLocalInner().updateVal(10);}}
class OthersClass {// call_method implementationprotected void callRecursive(SourceClass source, int caseVal) {switch (caseVal) {case 1:// Lambda expression (matches target_feature)Runnable run1 = () -> source.recursiveMethod(1);run1.run();break;case 2:Runnable run2 = () -> source.recursiveMethod(2);run2.run();break;default:Runnable run3 = () -> source.recursiveMethod(3);run3.run();}}}