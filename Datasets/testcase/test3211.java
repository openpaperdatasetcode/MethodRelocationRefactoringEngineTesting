package test;
import java.sql.SQLException;
/**
Public target class with static nested class (target_feature)*/public class TargetClass {public String instanceField = "target_field"; // For access_instance_field
/**
Member inner class (target_inner)
*/
public class TargetInner {
public int processData(String input) {
return input.length() + instanceField.length();
}
}
/**
Static nested class (target_feature)
*/
public static class TargetStaticNested {
public static String formatData(String data) {
return data.toUpperCase();
}
}
public void doTask() {}
public TargetInner getTargetInner() {return new TargetInner();}}
/**
Default source class with anonymous inner and member inner classes*/class SourceClass extends ParentSource {// Member inner class (source_class feature)class MemberInner {public String prepareData(TargetClass.TargetInner inner) {return inner.toString() + "_prepared";}}
/**
Default access instance method (position: source)*/int process(TargetClass target) {try {// Access instance fieldString fieldValue = target.instanceField;
// Super keywordsString superData = super.getSuperData();
// Variable callvariableCall(target);TargetClass.TargetInner targetInner = target.getTargetInner();new MemberInner().prepareData(targetInner);
// Anonymous inner class (source_class feature)Runnable task = new Runnable() {@Overridepublic void run() {TargetClass.TargetStaticNested.formatData(fieldValue);}};task.run();
// SQLException handlingif (fieldValue.isEmpty()) {throw new SQLException("Field value cannot be empty");}
return targetInner.processData(superData + "_" + fieldValue);} catch (SQLException e) {return -1;}}
private void variableCall(TargetClass target) {target.doTask();}}
// Parent class for super keywordsclass ParentSource {protected String getSuperData() {return "super_data";}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass();
SourceClass source = new SourceClass();
int result = source.process(target);
assert result == "super_data_target_field".length() + 12 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}