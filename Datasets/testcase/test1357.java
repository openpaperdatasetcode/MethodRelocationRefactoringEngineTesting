package test.refactoring.movemethod;
/**
Parent class for source record extension
*/
class ParentClass {}
/**
Interface for target record implementation
*/
interface TargetInterface {}
/**
Source record class (default modifier)/
record SourceRecord(String id, ParentClass parent) extends ParentClass {
/*
First static nested class in source record
*/
static class FirstStaticNested {}
/**
Second static nested class in source record (duplicate feature)
*/
static class SecondStaticNested {}
/**
Inner class depended by the refactored method
*/
class DependentInnerClass {
String innerField = "dependentField";
}
/**
Private varargs method to be refactored (source position)
Meets per_condition: contains TargetRecord parameter
@param target target record parameter
@param args varargs parameters*/private void refactorTargetMethod(TargetRecord target, String... args) {// Super keywords + super constructor invocation (implicit in record, explicit super reference)super.toString();
// Constructor invocation + raw_typeDependentInnerClass innerObj = new DependentInnerClass(); // Constructor invocationList rawList = new ArrayList(); // Raw type (List/ArrayList without generics)
// Variable call + depends_on_inner_class + obj.fieldString fieldValue = innerObj.innerField; // Variable call + inner class field access
// While statement + expression statementint i = 0;while (i < args.length) {System.out.println(args[i]); // Expression statementi++;}
// ThrowStatement (private, pos: inner class, obj.field, count 1)class ThrowInnerClass {private void throwMethod() {if (target == null) {throw new IllegalArgumentException(target.id()); // obj.field (target record component) + 1 throw statement}}}new ThrowInnerClass().throwMethod();
// No new exception (uses existing IllegalArgumentException)}}
/**
Target record class (strictfp modifier)
/
strictfp record TargetRecord(String id, int value) implements TargetInterface {
/*
Member inner class in target record
*/
class TargetMemberInner {
void innerMethod() {}
}
}
/**
Test class for Move Method refactoring (id: 5325)*/public class MoveMethodRefactoringTest5325 {public static void main(String[] args) {ParentClass parent = new ParentClass();SourceRecord source = new SourceRecord("source1", parent);TargetRecord target = new TargetRecord("target1", 100);
// Invoke the method to be refactoredsource.refactorTargetMethod(target, "arg1", "arg2");}}