package test;
// Sub class for method_feature dependencyclass SubClass extends TargetClass {public SubClass(String data) {super(data);}}
// Others class for call_method featureclass OthersClass {// Private constructor call_method (instanceReference.methodName(arguments))private static <T extends TargetClass> T createTarget(String data) {TargetClass target = new TargetClass(data);target.doTask();return (T) target;}}
/**
Public target class (no target_features)*/public class TargetClass {private String data;
public TargetClass(String data) {this.data = data;}
public void doTask() {}
public String getData() {return data;}
public void setData(String data) {this.data = data;}}
/**
Abstract source class with two static nested classes*/abstract class SourceClass {private static String outerPrivateField = "source_private";
// Two static nested classes (source_class feature)public static class StaticNested1 {public static void assist(TargetClass target) {target.setData(target.getData() + "_nested1");}}
public static class StaticNested2 {public static void process(TargetClass target) {target.doTask();}}
/**
Final generic method_feature (1 parameter, sub_class, generic)
*/
public static final <T extends SubClass> void genericHelper(T target) {
// ClassName.methodName(arguments)
StaticNested1.assist(target);
}
/**
strictfp static method (position: source)*/public static strictfp Object process(TargetClass target) throws Exception { // requires_throws// Access outer private fieldString combinedData = outerPrivateField + "_" + target.getData();
// Parameter list of constructors (method_feature position)SubClass subTarget = new SubClass(combinedData) {{SourceClass.genericHelper(this);}};
// switch caseswitch (subTarget.getData().length()) {case 15:StaticNested2.process(subTarget);break;default:StaticNested1.assist(subTarget);}
// Variable callvariableCall(subTarget);
// do-while (call_method position)TargetClass callResult;int count = 0;do {// instanceReference.methodName(arguments)callResult = OthersClass.createTarget("do_while_" + count);count++;} while (count < 1);
return List.of(subTarget.getData(), callResult.getData());}
private static void variableCall(TargetClass target) {target.doTask();}}
/**
Concrete implementation of abstract SourceClass
*/
class SourceImpl extends SourceClass {}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) throws Exception {
TargetClass target = new TargetClass("test");
Object result = SourceClass.process(target);
assert result instanceof List : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}