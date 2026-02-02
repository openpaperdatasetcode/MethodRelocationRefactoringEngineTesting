package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface AccessorAnnot {}
// Parent class for source_class extends featureclass ParentSource {protected String outerProtectedField = "parent_protected";}
/**
Abstract target class with javadoc (target_feature)/
abstract class TargetClass {
/*
obj.field=1 (target_feature)
*/
public int field = 1;
private String data;
/**
Javadoc for target class constructor
@param data Initial data
*/
public TargetClass(String data) {
this.data = data;
}
public abstract void doTask();
public String getData() {return data;}}
/**
Public generic source class (type parameter) with extends and two static nested classes*/public class SourceClass<T> extends ParentSource {// Two static nested classes (source_class feature)public static class StaticNested1 {public static void assist(TargetClass target) {target.getData();}}
public static class StaticNested2 {public static void process(TargetClass target) {target.doTask();}}
class InnerClass {class InnerRec {@AccessorAnnot // has_annotation// strictfp accessor method (position: source_inner_rec)strictfp List<String> getProcessedData(TargetClass target) {List<String> result = new ArrayList<>();
// Super constructor invocation (in local inner class)class LocalHelper extends ParentSource {LocalHelper() {super(); // Super constructor invocation}
String getCombinedData() {return outerProtectedField + "_" + target.getData();}}
// Public ForStatement (target_feature: obj.field=1, pos: source)for (int i = 0; i < target.field; i++) {if (i == 1) break; // break statement
// Access outer protected fieldString combined = new LocalHelper().getCombinedData();result.add(combined + "_" + i);}
// Variable callvariableCall(target);StaticNested1.assist(target);StaticNested2.process(target);
return result;}
private void variableCall(TargetClass target) {target.doTask();}}}
// Trigger methodpublic List<String> triggerAccessor(TargetClass target) {return new InnerClass().new InnerRec().getProcessedData(target);}}
/**
Concrete implementation of abstract TargetClass*/class TargetImpl extends TargetClass {public TargetImpl(String data) {super(data);}
@Overridepublic void doTask() {}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetImpl("test_data");
SourceClass<String> source = new SourceClass<>();
List<String> result = source.triggerAccessor(target);
assert result.size() == 1 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}