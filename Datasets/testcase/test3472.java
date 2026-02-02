package test;
// Private source enum (same package) with member inner and anonymous inner classesprivate enum SourceEnum {INSTANCE;
// Member inner class (source_class feature)class MemberInner extends ParentHelper {@Overridestrictfp void processInstance(TargetEnum target, int param1, int param2) {super.toString(); // super.methodName(arguments)target.setData("processed_" + param1 + param2);}}
// Anonymous inner class (source_class feature)private final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {variableCall(TargetEnum.VALUE1);}};
// Private static method (position: source)private static Object process(TargetEnum target) {// Protected NumberLiteral (numbers=1)protected int literalValue = 1;
// Uses outer this (enum singleton instance)SourceEnum source = SourceEnum.INSTANCE;MemberInner inner = source.new MemberInner();
// Array initialization (method_feature position)TargetEnum[] targetArray = new TargetEnum[]{target, TargetEnum.VALUE2};
// Requires_try_catchtry {for (TargetEnum t : targetArray) {// Call strictfp instance method_feature (2 parameters, target, instance)inner.processInstance(t, literalValue, literalValue * 2);variableCall(t);}} catch (Exception e) {return e.getMessage();}
source.anonymousTask.run();return targetArray;}
private static void variableCall(TargetEnum target) {target.doTask();TargetEnum.StaticNested.helper();}}
// Parent class for super.methodName(arguments)class ParentHelper {void processInstance(TargetEnum target, int param1, int param2) {}}
// Public target enum with static nested class (target_feature)public enum TargetEnum {VALUE1, VALUE2;
private String data;
// Static nested class (target_feature)public static class StaticNested {public static void helper() {}}
public void doTask() {}
public void setData(String data) {this.data = data;}
public String getData() {return data;}}
// Test class for Move Method refactoring verificationpublic class RefactoringTest {public static void main(String[] args) {TargetEnum target = TargetEnum.VALUE1;Object result = SourceEnum.process(target);assert result instanceof TargetEnum[] : "Refactoring test failed";assert target.getData().equals("processed_12") : "Data processing failed";System.out.println("Refactoring test passed");}}