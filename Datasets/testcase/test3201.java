package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)// Final Annotation (numbers=1, modifier=final)final @interface OverloadAnnot {}
// Interface for source_class implements featureinterface Processable {}
// Sub class for call_method featureclass TargetSubClass extends TargetClass {public TargetSubClass(String data) {super(data);}
// Synchronized varargs call_method (new ClassName().method())public synchronized List<String> subProcess(String... params) {List<String> result = new ArrayList<>();for (String param : params) {result.add(getData() + "_" + param);}return result;}}
/**
Public target class (no target_features)*/public class TargetClass {private String data;
public TargetClass(String data) {this.data = data;}
public void doTask() {}
public String getData() {return data;}
public void setData(String data) {this.data = data;}}
/**
Protected source class with implements, local inner and member inner classes*/protected class SourceClass implements Processable {// Member inner classclass InnerClass {// Inner recursive class (source_inner_rec)class InnerRec {// Overloading method 1@OverloadAnnotprotected TargetClass process(TargetClass target) {return process(target, "default");}
// Overloading method 2 (core refactoring method)protected TargetClass process(TargetClass target, String param) {// Labeled statementprocessLabel: {// switch caseswitch (param.length()) {case 7:target.setData(target.getData() + "_case1");break processLabel;default:target.setData(target.getData() + "_default");}}
// Variable callvariableCall(target);
// Local inner class (source_class feature)class LocalProcessor {void validate() {if (target.getData().isEmpty()) throw new IllegalArgumentException("Data empty");}}new LocalProcessor().validate();
// Property assignment (call_method position)List<String> callResult;callResult = new TargetSubClass(target.getData()).subProcess("var1", "var2");
target.setData(target.getData() + "_" + callResult.size());return target;}
private void variableCall(TargetClass target) {target.doTask();}}}
// Trigger methodpublic TargetClass triggerOverload(TargetClass target) {return new InnerClass().new InnerRec().process(target);}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass("test");
SourceClass source = new SourceClass();
TargetClass result = source.triggerOverload(target);
assert result.getData().equals("test_default_2") : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}