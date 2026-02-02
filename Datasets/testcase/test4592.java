package test;
// Source abstract class: protected, same package as target, with two static nested classesprotected abstract class SourceClass {protected String sourceField = "SourceProtectedData";
// First static nested class in SourceClasspublic static class SourceStaticNestedOne {public static String formatData(String input) {return "Formatted_" + input;}}
// Second static nested class in SourceClasspublic static class SourceStaticNestedTwo {public static int countElements(Object... elements) {return elements != null ? elements.length : 0;}}
// Method to be refactored: varargs, returns TargetClass type, uses outer 'this'public TargetClass<T> combineTargets<T>(TargetClass<T>... targets) {if (targets == null || targets.length == 0) {return new TargetClass<>(sourceField);}
TargetClass<T> result = null;int processedCount = 0;final Object lock = new Object();
// Do-while statementdo {// Synchronized statementsynchronized (lock) {TargetClass<T> current = targets[processedCount];// Switch statementswitch (current.getTargetData().length()) {case 0:result = new TargetClass<>("EmptyData");break; // Break statementcase 1:result = new TargetClass<>(SourceStaticNestedOne.formatData(current.getTargetData()));break; // Break statementdefault:result = new TargetClass<>(current.getTargetData() + "_" + SourceClass.this.sourceField);break; // Break statement}}processedCount++;// While statement condition} while (processedCount < SourceStaticNestedTwo.countElements(targets));
return result;}
public abstract void abstractMethod();}
// Target abstract class: public, same package as source, with type parameter and member inner classpublic abstract class TargetClass<T> {private T targetData;
public TargetClass(T targetData) {this.targetData = targetData;}
// Member inner class in TargetClasspublic class TargetMemberInner {public T getInnerData() {return TargetClass.this.targetData;}
public void updateInnerData(T newData) {TargetClass.this.targetData = newData;}}
public T getTargetData() {return targetData;}
public void setTargetData(T targetData) {this.targetData = targetData;}
public abstract TargetMemberInner createInnerInstance();}
// Test case class for Move Method refactoring enginepublic class MoveMethodRefactoringTest {public static void main(String[] args) {// Test 1: Create source and target instances, call method to be refactoredSourceClass source = new SourceClass() {@Overridepublic void abstractMethod() {System.out.println("Source abstract method implemented");}};
TargetClass<String> target1 = new TargetClass<>("TestData1") {@Overridepublic TargetMemberInner createInnerInstance() {return new TargetMemberInner();}};
TargetClass<String> target2 = new TargetClass<>("A") {@Overridepublic TargetMemberInner createInnerInstance() {return new TargetMemberInner();}};
// Call varargs method from sourceTargetClass<String> combined = source.combineTargets(target1, target2);assert combined.getTargetData().contains("TestData1_SourceProtectedData"): "Test 1 Failed: Combined target data mismatch";
// Test 2: Test with empty target dataTargetClass<String> emptyTarget = new TargetClass<>("") {@Overridepublic TargetMemberInner createInnerInstance() {return new TargetMemberInner();}};
TargetClass<String> emptyResult = source.combineTargets(emptyTarget);assert "EmptyData".equals(emptyResult.getTargetData()): "Test 2 Failed: Empty target data handling mismatch";
System.out.println("All Move Method refactoring tests passed!");}}