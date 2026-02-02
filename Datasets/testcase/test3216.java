package test;
import java.util.function.IntFunction;
// Interface for target_class implements featureinterface Processable {TargetClass process(TargetClass target);}
/**
Final abstract target class with implements and static nested class (target_features)*/final abstract class TargetClass implements Processable {protected int count = 0;private String data;
public TargetClass(String data) {this.data = data;}
public abstract void doTask();
/**
Member inner class (target_inner)
*/
public class TargetInner {
public int recursiveCount(int depth) {
if (depth <= 0) return count;
count++;
return recursiveCount(depth - 1);
}
}
/**
Static nested class (target_feature)
*/
public static class TargetStaticNested {
public static int staticProcess(int value) {
return value * 2;
}
}
public TargetInner getTargetInner() {return new TargetInner();}
public String getData() {return data;}
public void setData(String data) {this.data = data;}}
/**
Default abstract source class with two anonymous inner classes*/abstract class SourceClass {// First anonymous inner class (source_class feature)private final Runnable anonTask1 = new Runnable() {@Overridepublic void run() {System.out.println("First anonymous task");}};
/**
Public recursion method_feature (2 parameters, target, recursion)
*/
public int recursiveHelper(TargetClass target, int depth) {
// ClassName::methodName
IntFunction<Integer> staticMapper = TargetClass.TargetStaticNested::staticProcess;
if (depth <= 0) {
return staticMapper.apply(target.getTargetInner().recursiveCount(0));
}
// Recursive call
return recursiveHelper(target, depth - 1) + 1;
}
/**
Public overriding method (position: source)*/@Overridepublic TargetClass process(TargetClass target) {// Super constructor invocation (in local inner class)class LocalHelper extends TargetClass {LocalHelper() {super(target.getData() + "_local"); // Super constructor invocation}@Overridepublic void doTask() {}@Overridepublic TargetClass process(TargetClass t) { return t; }}new LocalHelper();
// Uses outer thisSourceClass source = this;
// Private MethodInvocation (numbers=2)private int invoke1 = target.getTargetInner().recursiveCount(1);private int invoke2 = recursiveHelper(target, 1);
// if statementif (invoke1 + invoke2 > 2) {target.setData(target.getData() + "_over_threshold");}
// Variable callvariableCall(target);anonTask1.run();
// Second anonymous inner class (source_class feature)Runnable anonTask2 = new Runnable() {@Overridepublic void run() {target.setData(target.getData() + "_anon2");source.variableCall(target);}};anonTask2.run();
// do-while (recursion method_feature position)int resultDepth = 2;do {recursiveHelper(target, resultDepth);resultDepth--;} while (resultDepth >= 0);
return target;}
private void variableCall(TargetClass target) {target.doTask();}}
/**
Concrete implementation of SourceClass
*/
class SourceImpl extends SourceClass {}
/**
Concrete implementation of TargetClass*/class TargetImpl extends TargetClass {public TargetImpl(String data) {super(data);}
@Overridepublic void doTask() {}
@Overridepublic TargetClass process(TargetClass target) {return super.process(target);}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetImpl("test_data");
SourceClass source = new SourceImpl();
TargetClass result = source.process(target);
assert result.getData().contains("_over_threshold") && result.getData().contains("_anon2") : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}