package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnot {}
abstract class TargetClass {protected String targetField = "targetInstanceField";public static String targetStaticField = "targetStaticField";
public TargetClass() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};anonymous.run();}
protected abstract TargetClass recursiveHelper(int depth);}
public class SourceClass {protected String outerProtectedField = "outerProtectedField";private TargetClass targetInstance = new TargetClass() {@Overrideprotected TargetClass recursiveHelper(int depth) {if (depth >= 1) return this;return super.recursiveHelper(depth + 1); // Super method call for recursion}};
// First member inner classpublic class FirstMemberInner {public String getInnerData() {return "firstInnerData";}}
// Second member inner classpublic class SecondMemberInner {public TargetClass processRecursion(int depth) {return targetInstance.recursiveHelper(depth); // Recursion via inner class}}
@RefactorAnnotpublic strictfp abstract List<String> refactorAbstractMethod();
// Concrete implementation (for test execution)public static class SourceImpl extends SourceClass {@Overridepublic strictfp List<String> refactorAbstractMethod() {List<String> result = new ArrayList<>();
// Recursion feature (protected, inner_class, recursion, super.methodName, pos:expression)SecondMemberInner inner = new SecondMemberInner();TargetClass recursionResult = inner.processRecursion(0);result.add(recursionResult.targetField); // Access target instance field (per_condition)
// For statementfor (int i = 0; i < 1; i++) {FirstMemberInner firstInner = new FirstMemberInner();result.add(firstInner.getInnerData()); // Variable call}
// Empty statement;
// Access outer protected fieldresult.add(outerProtectedField);
// Access instance fieldresult.add(targetInstance.targetField);
// Depends on static fieldresult.add(TargetClass.targetStaticField);
return result;}}}
// Test case classpublic class MoveMethodTest5161 {public static void main(String[] args) {SourceClass.SourceImpl source = new SourceClass.SourceImpl();List<String> result = source.refactorAbstractMethod();
assert result.contains(TargetClass.targetField) : "Target instance field check (per_condition)";assert result.contains(SourceClass.targetInstance.targetStaticField) : "Target static field check";assert result.contains(source.outerProtectedField) : "Outer protected field check";assert result.contains("firstInnerData") : "First member inner class check";assert result.size() == 5 : "Result list size check";assert source.refactorAbstractMethod() != null : "Abstract method implementation return check";}}