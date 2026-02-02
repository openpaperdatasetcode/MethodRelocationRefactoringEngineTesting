package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
/**
Target class with static nested class (target_feature)*/class TargetClass {public static final String STATIC_FIELD = "target_static"; // For depends_on_static_fieldprivate String data;
public TargetClass(String data) {this.data = data;}
public void doTask() {}
/**
Static nested class (target_feature)
*/
public static class TargetStaticNested {
public static String processStatic(String input) {
return input + "_nested_static";
}
}
public String getData() {return data;}
public void setData(String data) {this.data = data;}}
/**
Public source class with static nested and local inner classes*/public class SourceClass extends ParentSource {// Static nested class (source_class feature)public static class SourceStaticNested {public static void assist(TargetClass target) {target.setData(target.getData() + "_source_nested");}}
/**
strictfp static call_method (this.methodName(arguments))
*/
public strictfp static String sourceStaticCall(TargetClass target, List<String> collection) {
StringBuilder sb = new StringBuilder();
for (String item : collection) {
sb.append(this.processItem(item, target.STATIC_FIELD));
}
return sb.toString();
}
private static String processItem(String item, String staticField) {return item + "_" + staticField;}
/**
Protected instance method (position: source)*/protected int process(TargetClass target) {// Super constructor invocation (in local inner class)class LocalProcessor extends ParentSource {LocalProcessor() {super(); // Super constructor invocation}
public void validateStaticField() {if (!TargetClass.STATIC_FIELD.equals("target_static")) {throw new IllegalStateException("Static field mismatch");}}}LocalProcessor processor = new LocalProcessor();processor.validateStaticField();
// Depends on static fieldString combinedData = target.getData() + "_" + TargetClass.STATIC_FIELD;
// Collection operations (call_method position)List<String> collection = new ArrayList<>();collection.add(target.getData());collection.add(combinedData);String callResult = SourceClass.sourceStaticCall(target, collection);
// For statement with continueint count = 0;for (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue; // continue statement}count++;target.setData(target.getData() + "_" + i);}
// Variable callvariableCall(target);SourceStaticNested.assist(target);
// Local inner class (source_class feature)class LocalCounter {public int getTotalLength() {return target.getData().length() + callResult.length();}}int totalLength = new LocalCounter().getTotalLength();
// Used by reflectiontry {Method method = TargetClass.TargetStaticNested.class.getMethod("processStatic", String.class);String reflectedResult = (String) method.invoke(null, target.getData());totalLength += reflectedResult.length();} catch (Exception e) {throw new RuntimeException("Reflection failed", e);}
return totalLength;}
private void variableCall(TargetClass target) {target.doTask();}}
// Parent class for super constructor invocationclass ParentSource {protected ParentSource() {}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass("test");
SourceClass source = new SourceClass();
int result = source.process(target);
assert result > 0 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}