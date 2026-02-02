package otherpkg;
import test.TargetClass;
public class DiffPackageHelper {public static void validateSynchronized(TargetClass target) throws InterruptedException {// Private SynchronizedStatement (target_feature: this.field=3)private int fieldValue = target.field;if (fieldValue != 3) throw new IllegalArgumentException("this.field must be 3");
synchronized (target) {Thread.sleep(10);target.field = fieldValue + 1;}}}
package test;
import otherpkg.DiffPackageHelper;
/**
Default target class with static nested class (target_feature)*/class TargetClass {int field = 3; // this.field=3private String data;
public TargetClass(String data) {this.data = data;}
public void doTask() {}
/**
Static nested class (target_feature)
*/
public static class TargetStaticNested {
public static String process(String input) {
return input + "_nested_static";
}
}
public String getData() {return data;}}
// Interface for overriding featureinterface Processable {int process(TargetClass target) throws InterruptedException;}
/**
Private source class with local inner and anonymous inner classes*/private class SourceClass implements Processable {private String outerPrivateField = "source_private"; // For access_outer_private
/**
Private overriding method (position: source)*/@Overrideprivate int process(TargetClass target) throws InterruptedException { // requires_throws// Type declaration statementclass LocalType {int processValue(String input) {return input.length() + target.field;}}LocalType local = new LocalType();
// Access outer private fieldString combined = outerPrivateField + "_" + target.getData();int baseValue = local.processValue(combined);
// Variable callvariableCall(target);TargetClass.TargetStaticNested.process(combined);
// diff_package_others position (SynchronizedStatement)DiffPackageHelper.validateSynchronized(target);
// Anonymous inner class (source_class feature)Runnable anonTask = new Runnable() {@Overridepublic void run() {target.doTask();}};anonTask.run();
// Local inner class (source_class feature)class LocalCalculator {int getFinalValue() {return baseValue + target.field;}}int finalValue = new LocalCalculator().getFinalValue();
// Return statementreturn finalValue;}
private void variableCall(TargetClass target) {target.doTask();}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) throws InterruptedException {
TargetClass target = new TargetClass("test_data");
SourceClass source = new SourceClass();
int result = source.process(target);
assert result > 0 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}