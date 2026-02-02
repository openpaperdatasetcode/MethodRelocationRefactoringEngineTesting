package test;
import java.lang.reflect.Method;
// Others class for call_method featureclass OthersClass {// strictfp constructor call_method (new ClassName().method())public strictfp int processOthers(String data) {return new OthersClass.OthersInner(data).calculate();}
private static class OthersInner {private String data;
public OthersInner(String data) {this.data = data;}
public int calculate() {return data.length() * 2;}}}
/**
Default target class with local inner class (target_feature)*/class TargetClass {private String data;
public TargetClass(String data) {this.data = data;}
public void doTask() {}
public String getData() {return data;}
public void setData(String data) {this.data = data;}
public int processLocal() {// Local inner class (target_feature)class LocalCalculator {public int sumLengths(String... params) {int sum = 0;for (String param : params) {sum += param.length();}return sum;}}return new LocalCalculator().sumLengths(data, "local");}}
/**
strictfp source class with anonymous inner and member inner classes*/strictfp class SourceClass {private String outerPrivateField = "source_private";
// Member inner class (source_class feature)class MemberInner {public void assist(TargetClass target) {target.setData(target.getData() + "_inner_assist");}}
/**
Default access varargs method (position: source)*/@SafeVarargsObject process(TargetClass target, String... params) {// Access outer private fieldString combinedData = outerPrivateField + "_" + target.getData();
// For statementint totalLength = 0;for (String param : params) {totalLength += param.length();target.setData(target.getData() + "_" + param);}
// Variable callvariableCall(target);new MemberInner().assist(target);
// Anonymous inner class (source_class feature)Runnable task = new Runnable() {@Overridepublic void run() {target.doTask();totalLength += outerPrivateField.length();}};task.run();
// if/else conditions (call_method position)int callResult;if (totalLength > 10) {callResult = new OthersClass().processOthers(target.getData());} else {callResult = new OthersClass().processOthers(combinedData);}
// Used by reflectiontry {Method method = TargetClass.class.getMethod("processLocal");int reflectedResult = (int) method.invoke(target);return List.of(target.getData(), callResult, reflectedResult);} catch (Exception e) {throw new RuntimeException("Reflection failed", e);}}
private void variableCall(TargetClass target) {target.doTask();}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass("test");
SourceClass source = new SourceClass();
Object result = source.process(target, "param1", "param2");
assert result instanceof List : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}