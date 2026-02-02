package test.refactoring.movemethod;
/**
Final generic source class with two member inner classes
@param <S> type parameter (generic class feature)/
final class SourceClass<S> {
// Source feature: first member inner class
public class SourceMemberInner1 {
// Source inner class (method_position: source_inner_rec)
public class NestedInnerClass {
/*
Instance method to be refactored (default access, return base type int)
@param targetParam target class parameter (per_condition)
@return int base type result*/int refactorTargetMethod(TargetClass<String> targetParam) {// Variable callTargetClass<String> tempTarget = targetParam;String targetData = tempTarget.getData();
// Ternary operators position for nested instance methodboolean flag = targetData != null;flag ? nestedInstanceMethod(tempTarget, "arg1") : nestedInstanceMethod(tempTarget, "arg2");
// No new exception thrownreturn targetData.length();}
/**
Nested instance method (type: instance, modifier: public, return_type: void)
@param target target class instance
@param arg method argument
*/
public void nestedInstanceMethod(TargetClass<String> target, String arg) {
int num = 1; // "1" in method_feature
// ClassName.methodName(arguments) + "inner_class" + "instance" features
SourceHelperClass.processTargetData(target, arg);
}
}
}
// Source feature: second member inner classpublic class SourceMemberInner2 {public void innerMethod2() {}}
// Helper class for ClassName.methodName(arguments) featurepublic static class SourceHelperClass {public static <T> void processTargetData(TargetClass<T> target, T data) {System.out.println("Processed: " + target.getData() + ", " + data);}}
// Container method to access nested inner class methodpublic int invokeRefactorMethod(TargetClass<String> target) {SourceMemberInner1 inner1 = new SourceMemberInner1();NestedInnerClass nested = inner1.new NestedInnerClass();return nested.refactorTargetMethod(target);}}
/**
Generic target class: public modifier, target_feature: javadoc
@param <T> type parameter (generic class feature)/
// target_feature: javadoc
/*
Target class for Move Method refactoring
Provides generic data storage and access functionality.
@param <T> type of data stored in the target class*/public class TargetClass<T> {private final T data;
public TargetClass(T data) {this.data = data;}
/**
Gets the stored data (target_inner_rec: core method of target class)
@return stored data of type T
*/
public T getData() {
return data;
}
}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) {SourceClass<Integer> source = new SourceClass<>();TargetClass<String> target = new TargetClass<>("test_data");int result = source.invokeRefactorMethod(target);System.out.println("Refactor result: " + result); // Output: 9}}