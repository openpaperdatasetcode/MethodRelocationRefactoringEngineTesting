package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface StaticVarargsAnnot {}
/**
Target record with static nested class (target_feature)/
record TargetRecord(String data) {
/*
Member inner class (target_inner)
*/
public class TargetInner {
public TargetRecord processInner(String input) {
return new TargetRecord(data + "inner" + input);
}
}
/**
Static nested class (target_feature)
*/
public static class TargetStaticNested {
public static TargetRecord staticProcess(TargetRecord target, String... params) {
String combined = target.data;
for (String param : params) {
combined += "static" + param;
}
return new TargetRecord(combined);
}
}
public void doTask() {}
public TargetInner getTargetInner() {return new TargetInner();}}
/**
Default source record with anonymous inner and local inner classes*/record SourceRecord(String sourceData) {private static String outerPrivateField = "source_private"; // For access_outer_private
/**
Protected varargs method_feature (1 parameter, target, varargs)
*/
protected static TargetRecord varargsHelper(TargetRecord target, String... params) {
// ClassName.methodName(arguments)
return TargetRecord.TargetStaticNested.staticProcess(target, params);
}
class InnerClass {/**
Protected static method (position: source_inner)*/@StaticVarargsAnnot // has_annotation@SafeVarargsprotected static TargetRecord process(TargetRecord target, String... params) {// Access outer private fieldString privateCombined = outerPrivateField + "_" + target.data();
// Raw typeList rawList = new ArrayList();rawList.add(privateCombined);rawList.add(target.data());
// Variable callvariableCall(target);TargetRecord.TargetInner targetInner = target.getTargetInner();
// Instance code blocks (varargs method_feature position)TargetRecord processedVarargs;{processedVarargs = SourceRecord.varargsHelper(target, params);}
// Local inner class (source_class feature)class LocalProcessor {public TargetRecord combineResults() {String combined = processedVarargs.data() + "_" + privateCombined;return targetInner.processInner(combined);}}
// Anonymous inner class (source_class feature)Runnable anonTask = new Runnable() {@Overridepublic void run() {target.doTask();processedVarargs.doTask();}};anonTask.run();
return new LocalProcessor().combineResults();}
private static void variableCall(TargetRecord target) {target.doTask();}}
/**
Trigger static method
*/
public static TargetRecord triggerProcess(TargetRecord target, String... params) {
return InnerClass.process(target, params);
}
}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetRecord target = new TargetRecord("test_data");
TargetRecord result = SourceRecord.triggerProcess(target, "param1", "param2");
assert result.data().contains("inner") && result.data().contains("static") : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}