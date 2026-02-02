package test;
import java.util.ArrayList;import java.util.List;
// Others class for method_feature dependencyclass OthersClass {protected List<String> baseRecursion() {return new ArrayList<>(List.of("base"));}}
/**
Public target class with anonymous inner class (target_feature)*/public class TargetClass {private String data;
public TargetClass(String data) {this.data = data;}
public void doTask() {}
// Member inner class (target_inner)public class TargetInner {public String getInnerData() {return data + "_inner";}}
// Anonymous inner class (target_feature)public Runnable getTargetTask() {return new Runnable() {@Overridepublic void run() {data = data + "_anonymous";}};}
public TargetInner getTargetInner() {return new TargetInner();}
public String getData() {return data;}}
/**
Public source class with member inner and local inner classes/
public class SourceClass extends OthersClass {
// Member inner class
class InnerClass {
// Inner recursive class (source_inner_rec)
class InnerRec {
/*
Method javadoc: Processes target and returns base type result with recursion
@param target TargetClass instance
@return Processed integer count*/public int process(TargetClass target) {// With_bounds: Generic with boundsclass BoundedHelper<T extends CharSequence> {String processBound(T input) {return input.toString().toUpperCase();}}BoundedHelper<String> helper = new BoundedHelper<>();
// Property assignment (recursion method_feature position)List<String> recursiveResult;recursiveResult = recursiveMethod(target, 2);
// Variable callvariableCall(target);target.getTargetTask().run();String innerData = target.getTargetInner().getInnerData();
// Return statementreturn recursiveResult.size() + helper.processBound(innerData).length();}
/**
Public recursion method_feature (1 parameter, others_class, recursion)
*/
public List<String> recursiveMethod(TargetClass target, int depth) {
List<String> result = new ArrayList<>();
if (depth <= 0) {
// super.methodName()
result.addAll(super.baseRecursion());
return result;
}
// Recursive call
result.addAll(recursiveMethod(target, depth - 1));
result.add(target.getData() + "depth" + depth);
return result;
}
private void variableCall(TargetClass target) {target.doTask();}}}
// Local inner class (source_class feature)public int triggerProcess(TargetClass target) {class LocalTrigger {int execute() {return new InnerClass().new InnerRec().process(target);}}return new LocalTrigger().execute();}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass("test");
SourceClass source = new SourceClass();
int result = source.triggerProcess(target);
assert result == 3 + "TEST_INNER_ANONYMOUS".length() : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}