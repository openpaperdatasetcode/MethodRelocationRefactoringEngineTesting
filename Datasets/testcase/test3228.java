package test;
import java.io.IOException;import java.util.ArrayList;import java.util.List;
// Parent class for super keywordsclass ParentSource {protected String superField = "parent_data";}
/**
strictfp target class with type parameter and local inner class (target_features)*/strictfp class TargetClass<T> {private T data;
public TargetClass(T data) {this.data = data;}
public void doTask() {}
public T getData() {return data;}
public void setData(T data) {this.data = data;}
/**
Member inner class for depends_on_inner_class
*/
public class TargetInner {
public String process(T input) {
return data.toString() + "inner" + input.toString();
}
}
// Local inner class (target_feature)public List<String> processLocal() {class LocalProcessor {public List<String> process(T... params) {List<String> result = new ArrayList<>();result.add(data.toString());for (T param : params) {result.add(param.toString());}return result;}}return new LocalProcessor().process(data);}}
/**
Protected source class with local inner and member inner classes*/protected class SourceClass extends ParentSource {// Member inner class (source_class feature)class MemberInner {public <T> String assist(TargetClass<T> target) {return target.getData().toString() + "_member_assist";}}
/**
Public normal method (position: source)*/public <T> Object process(TargetClass<T> target) throws IOException { // requires_throws// Super keywordsString superData = super.superField;
// Depends on inner classTargetClass<T>.TargetInner targetInner = target.new TargetInner();String innerResult = targetInner.process(target.getData());String memberResult = new MemberInner().assist(target);
// Variable callvariableCall(target);
// Local inner class (source_class feature)class LocalHandler {public Object combineResults() {List<String> result = new ArrayList<>();result.add(superData);result.add(innerResult);result.add(memberResult);result.addAll(target.processLocal());return result;}}
// Check if source contains target's fieldif (target.getData() == null) {throw new IOException("Target data cannot be null");}
return new LocalHandler().combineResults();}
private <T> void variableCall(TargetClass<T> target) {target.doTask();}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) throws IOException {
TargetClass<String> target = new TargetClass<>("test_data");
SourceClass source = new SourceClass();
Object result = source.process(target);
assert result instanceof List : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}