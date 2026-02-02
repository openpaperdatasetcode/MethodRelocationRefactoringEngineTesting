package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnot {}
// Parent class for super keywordsclass ParentSource {protected String superField = "parent_data";}
/**
Public target class with local inner class (target_feature)*/public class TargetClass {private String data;
public TargetClass(String data) {this.data = data;}
public void doTask() {}
/**
Member inner class (target_inner)*/public class TargetInner {// Overloaded method 1 (overload_exist)public String process(String input) {return data + "inner" + input;}
// Overloaded method 2 (overload_exist)public String process(String input1, String input2) {return data + "inner" + input1 + "_" + input2;}}
public TargetInner getTargetInner() {return new TargetInner();}
public String getData() {return data;}
public void setData(String data) {this.data = data;}
// Local inner class (target_feature)public List<String> localProcess() {class LocalCollector {public List<String> collect() {List<String> result = new ArrayList<>();result.add(data);result.add(superField); // Inherited field (if any)return result;}}return new LocalCollector().collect();}
// Dummy field to match local inner class referenceprotected String superField = "target_super";}
/**
Protected source class with two anonymous inner classes*/protected class SourceClass extends ParentSource {private String sourceVar = "initial_source";
// First anonymous inner class (source_class feature)private final Runnable anonTask1 = new Runnable() {@Overridepublic void run() {sourceVar = sourceVar + "_anon1";}};
/**
Protected instance method (position: source)*/@ProcessAnnot // has_annotationprotected List<String> process(TargetClass target) {List<String> result = new ArrayList<>();
// Super keywordsString superData = super.superField;result.add(superData);
// this.var = varthis.sourceVar = target.getData() + "_updated";result.add(this.sourceVar);
// Variable callvariableCall(target);TargetClass.TargetInner targetInner = target.getTargetInner();
// Overload_exist: Call both overloaded methodsresult.add(targetInner.process("param1"));result.add(targetInner.process("param1", "param2"));
// while statementint count = 0;while (count < 2) {target.setData(target.getData() + "loop" + count);count++;}
// Second anonymous inner class (source_class feature)Runnable anonTask2 = new Runnable() {@Overridepublic void run() {result.add(target.localProcess().get(0) + "_anon2");}};anonTask1.run();anonTask2.run();
result.addAll(target.localProcess());return result;}
private void variableCall(TargetClass target) {target.doTask();}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass("test_data");
SourceClass source = new SourceClass();
List<String> result = source.process(target);
assert result.size() == 8 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}
