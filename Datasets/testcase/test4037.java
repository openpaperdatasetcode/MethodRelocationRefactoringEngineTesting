package test;
class SourceClass {// Source contains target field (matches per_condition)private TargetClass targetField = new TargetClass();
// Local inner class (source_class feature)void createLocalInner() {class SourceLocalInner {void callRecursiveMethod() {new SourceInner().new SourceRecursiveInner().processTarget();}}new SourceLocalInner().callRecursiveMethod();}
// 1st anonymous inner class (source_class feature)Runnable anon1 = new Runnable() {@Overridepublic void run() {createLocalInner();}};
// Member inner class (for "source_inner" layer)class SourceInner {// Recursive inner class (for "source_inner_rec" method position)class SourceRecursiveInner {// Instance method to be refactoredstrictfp void processTarget() {// Call 2 source static methods (matches method_feature "2")TargetClass staticTarget1 = SourceStaticHelper.staticMethod1(targetField);TargetClass staticTarget2 = SourceStaticHelper.staticMethod2(targetField);
// Super constructor invocation (implicit for inner class)super();
int count = 0;while (true) {// Variable call: access target inner recursive class methodtargetField.new TargetInner().new TargetRecursiveInner().updateCount(count);
count++;if (count > 2) {// Break statementbreak;}}
// Override violation (target method is final in parent)targetField.overrideFinalMethod();}}}
// 2nd anonymous inner class (source_class feature)Runnable anon2 = new Runnable() {@Overridepublic void run() {new SourceInner().new SourceRecursiveInner().processTarget();}};}
// Static helper class for source static methodsclass SourceStaticHelper {// 1st source static method (for method_feature)private static TargetClass staticMethod1(TargetClass target) {target.count = 1;return target;}
// 2nd source static method (for method_feature)private static TargetClass staticMethod2(TargetClass target) {target.count = 2;return target;}}
/**
Javadoc for TargetClass
Implements Runnable and contains anonymous inner class*/public class TargetClass extends ParentTarget implements Runnable {int count;
// Member inner class (for "target_inner" layer)class TargetInner {// Recursive inner class (for "target_inner_rec" target class)class TargetRecursiveInner {void updateCount(int val) {TargetClass.this.count = val;}}}
// Anonymous inner class (target_class feature)private Runnable targetAnon = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class running");}};
// Override violation (parent method is final)@Overridepublic void overrideFinalMethod() {}
@Overridepublic void run() {targetAnon.run();}}
// Parent class for target "extends" and override violationclass ParentTarget {// Final method (causes override violation)public final void overrideFinalMethod() {}}