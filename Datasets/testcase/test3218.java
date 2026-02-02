package test;
// Different package others class (diff_package_others)package other;public class DiffPackageHelper {public static <T extends ParentTarget> void validateSuperField(T target) {// Private ExpressionStatement (target_feature: super.field=3)private int fieldValue = target.superField;if (fieldValue != 3) throw new IllegalArgumentException("super.field must be 3");}}
package test;import other.DiffPackageHelper;import java.util.function.Predicate;
// Parent class for super.fieldclass ParentTarget {protected int superField = 3;}
/**
Default target class with member inner class (target_feature)*/class TargetClass extends ParentTarget {private String data;
public TargetClass(String data) {this.data = data;}
public void doTask() {}
/**
Member inner class (target_feature)
*/
public class TargetInner {
public void processData(String input) {
data = data + "inner" + input;
}
}
public TargetInner getTargetInner() {return new TargetInner();}
public String getData() {return data;}}
// Interface for source_class implements featureinterface Processable<T> {void process(T target);}
/**
Private generic source class with type parameter, implements, anonymous inner and static nested classes*/private class SourceClass<T extends TargetClass> extends ParentTarget implements Processable<T> {protected String outerProtectedField = "source_protected";
// Static nested class (source_class feature)public static class SourceStaticNested {public static <T extends TargetClass> void assist(T target) {target.doTask();}}
class InnerClass {class InnerRec {/**
Public instance method (position: source_inner_rec)*/public void process(T target) {// Access outer protected fieldString combinedProtected = outerProtectedField + "_" + target.superField;
// diff_package_others position (ExpressionStatement)DiffPackageHelper.validateSuperField(target);
// Public CaseDefaultExpression (numbers=2)Predicate<String> case1 = s -> s.length() > 5 ? true : false;Predicate<String> case2 = s -> s.contains("_") ? true : false;
// if statementif (case1.test(target.getData()) && case2.test(combinedProtected)) {target.getTargetInner().processData(combinedProtected);}
// while statementint count = 0;while (count < 2) {// expression statementSourceStaticNested.assist(target);count++;}
// Variable callvariableCall(target);
// Anonymous inner class (source_class feature)Runnable anonTask = new Runnable() {@Overridepublic void run() {target.getTargetInner().processData("anonymous_" + count);}};anonTask.run();}
private void variableCall(T target) {target.doTask();}}}
/**
Implement interface method
*/
@Override
public void process(T target) {
new InnerClass().new InnerRec().process(target);
}
}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass("test_data");
SourceClass<TargetClass> source = new SourceClass<>();
source.process(target);
assert target.getData().contains("inner") && target.getData().contains("anonymous_") : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}