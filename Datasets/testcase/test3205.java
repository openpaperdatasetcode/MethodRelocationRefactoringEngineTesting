package test;
import java.util.ArrayList;import java.util.List;
// Interface for source_class implements featureinterface Processable {<T> List<String> process(TargetClass target, T... params);}
/**
Abstract target class with javadoc and anonymous inner class (target_features)/
abstract class TargetClass {
/*
Instance field for access_instance_field feature
*/
public String instanceField = "target_field";
private List<String> dataList = new ArrayList<>();
/**
Javadoc for target class constructor
*/
public TargetClass() {}
public abstract void doTask();
/**
Member inner class (target_inner)*/public class TargetInner {public void addData(String data) {dataList.add(data);}
public List<String> getDataList() {return new ArrayList<>(dataList);}}
/**
Anonymous inner class (target_feature)
*/
public Runnable getTargetTask() {
return new Runnable() {
@Override
public void run() {
dataList.add("anonymous_processed");
}
};
}
public TargetInner getTargetInner() {return new TargetInner();}}
/**
Public source class with implements, static nested and member inner classes*/public class SourceClass implements Processable {// Static nested class (source_class feature)public static class StaticNested {public static void assist(TargetClass.TargetInner inner, String data) {inner.addData(data + "_nested");}}
// Member inner class (source_class feature)class InnerClass {/**
Final varargs method (position: source_inner)*/@SafeVarargspublic final <T> List<String> process(TargetClass target, T... params) {// Type declaration statementclass LocalType {}LocalType local = new LocalType();
// Access instance fieldString fieldData = target.instanceField;
// Depends on inner classTargetClass.TargetInner targetInner = target.getTargetInner();StaticNested.assist(targetInner, fieldData);
// Super keywords (refers to interface method)super.toString();
// Variable callvariableCall(target);target.getTargetTask().run();
// Process varargs parametersfor (T param : params) {targetInner.addData(param.toString());}
return targetInner.getDataList();}
private void variableCall(TargetClass target) {target.doTask();}}
/**
Implement interface method
*/
@Override
@SafeVarargs
public final <T> List<String> process(TargetClass target, T... params) {
return new InnerClass().process(target, params);
}
}
/**
Concrete implementation of abstract TargetClass
*/
class TargetImpl extends TargetClass {
@Override
public void doTask() {}
}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetImpl();
SourceClass source = new SourceClass();
List<String> result = source.process(target, "param1", "param2");
assert result.size() == 4 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}