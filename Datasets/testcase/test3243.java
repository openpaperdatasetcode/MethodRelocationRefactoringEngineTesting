package test;
import java.util.ArrayList;import java.util.List;import java.util.function.ToIntFunction;
/**
Abstract target class with javadoc and static nested class (target_features)/
abstract class TargetClass {
/*
Instance field for access_instance_field
(javadoc target_feature)
*/
public String instanceField = "target_instance";
public TargetClass() {}
public abstract void doTask();
/**
Final normal call_method (ClassName::methodName)
*/
public final int targetNormalCall(String input) {
return input.length() + instanceField.length();
}
/**
Static nested class (target_feature)
*/
public static class TargetStaticNested {
public static String staticProcess(String input) {
return input.toUpperCase();
}
}
}
/**
Public source class with local inner and static nested classes*/public class SourceClass extends ParentSource {// Static nested class (source_class feature)public static class SourceStaticNested {public static void assist(TargetClass target) {target.doTask();}}
class InnerClass {class InnerRec {/**
Synchronized instance method_feature (1 parameter, source, instance)
*/
public synchronized List<String> instanceHelper(TargetClass target) {
List<String> result = new ArrayList<>();
// super.methodName()
result.add(super.getParentData());
result.add(target.instanceField);
return result;
}
// Overloading method 1public Object process(TargetClass target) {return process(target, "default_param");}
/**
Default access overloading method 2 (position: source_inner_rec)*/public Object process(TargetClass target, String param) {List<String> result = new ArrayList<>();
// Super keywordsString superData = super.getParentData();result.add(superData);
// Access instance fieldString fieldData = target.instanceField;result.add(fieldData);
// Default InfixExpression (numbers=1)String infixResult = param + "_" + fieldData;result.add(infixResult);
// Variable callvariableCall(target);SourceStaticNested.assist(target);
// Constructor invocationTargetClass concreteTarget = new TargetClass() {@Overridepublic void doTask() {}};result.add(concreteTarget.instanceField);
// Instance code blocks (method_feature position)List<String> helperResult;{helperResult = instanceHelper(target);}result.addAll(helperResult);
// Local inner class (source_class feature)class LocalProcessor {public void validate() {if (fieldData.isEmpty()) {throw new IllegalStateException("Instance field empty");}}}new LocalProcessor().validate();
// do-while (call_method position)int callCount = 0;do {// ClassName::methodNameToIntFunction<TargetClass> callMapper = TargetClass::targetNormalCall;int callResult = callMapper.applyAsInt(target, param + "_" + callCount);result.add(String.valueOf(callResult));callCount++;} while (callCount < 1);
result.add(TargetClass.TargetStaticNested.staticProcess(infixResult));return result;}
private void variableCall(TargetClass target) {target.doTask();}}}
// Trigger methodpublic Object triggerOverload(TargetClass target) {return new InnerClass().new InnerRec().process(target);}}
// Parent class for super.methodName() and super keywordsclass ParentSource {protected String getParentData() {return "parent_source_data";}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass() {
@Override
public void doTask() {}
};
SourceClass source = new SourceClass();
Object result = source.triggerOverload(target);
assert result instanceof List && ((List<?>) result).size() == 9 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}