package test.refactor.movemethod;
import java.util.Arrays;import java.util.List;
strictfp class SourceClass {public static class SourceInnerClass {private TargetClass targetField;
public SourceInnerClass(TargetClass target) {this.targetField = target;}
/**
Varargs method to be refactored, contains target class parameter
@param targets target class parameters (per_condition)
@return Object instance
*/
public abstract Object methodToMove(TargetClass... targets);
// Call method (type: source, modifier: protected)protected <T extends Number> int callMethod(List<T> list, TargetClass target) {// obj.m1().m2().m3()return target.getNested().process().compute();}}
// Another static nested class (source_class feature: two static nested classes)public static class AnotherSourceNestedClass {}}
public class TargetClass {// Target feature: static nested classpublic static class TargetNestedClass {public TargetNestedClass process() { return this; }public int compute() { return 0; }}
public TargetNestedClass getNested() {return new TargetNestedClass();}
// Refactored method should be placed here (target class: target)public abstract Object methodToMove(TargetClass... targets);}
// Test class for refactoring verificationpublic class MoveMethodTest5050 {public static void main(String[] args) {TargetClass target1 = new TargetClass();TargetClass target2 = new TargetClass();SourceClass.SourceInnerClass sourceInner = new SourceClass.SourceInnerClass(target1);
// Original method call (before refactoring)Object originalResult = sourceInner.methodToMove(target1, target2);
// Call method usage (pos: constructor parameter list)List<Integer> intList = Arrays.asList(1, 2, 3);SourceClass.SourceInnerClass innerWithCall = new SourceClass.SourceInnerClass(target2) {@Overridepublic Object methodToMove(TargetClass... targets) {// ForStatement (type: ForStatement, modifier: protected, pos: diff_package_others)protected void forLoopExample() {for (int i = 0; i < targets.length; i++) {// obj.fieldSystem.out.println(targets[i].getNested());}}
// Enhanced for statementfor (TargetClass target : targets) {target.getNested();}
// Super constructor invocation (in anonymous subclass)super.methodToMove(targets);
// Synchronized statementsynchronized (this) {// Variable callTargetClass nestedTarget = targets[0];nestedTarget.getNested();}
// Array initialization (pos: array initialization)TargetClass[] targetArray = {target1, target2};// ClassName::methodNameArrays.stream(targetArray).forEach(TargetClass::getNested);
forLoopExample();return new Object();}};
Object refactoredResult = new TargetClass() {@Overridepublic Object methodToMove(TargetClass... targets) {// Replicated method logic after refactoringprotected void forLoopExample() {for (int i = 0; i < targets.length; i++) {System.out.println(targets[i].getNested());}}
for (TargetClass target : targets) {target.getNested();}
synchronized (this) {TargetClass nestedTarget = targets[0];nestedTarget.getNested();}
TargetClass[] targetArray = {target1, target2};Arrays.stream(targetArray).forEach(TargetClass::getNested);
forLoopExample();return new Object();}}.methodToMove(target1, target2);}}