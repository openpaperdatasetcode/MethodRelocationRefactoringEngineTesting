package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
/**
Abstract target class with javadoc, type parameter and member inner class/
abstract class TargetClass<T> {
/*
this.field=1 (target_feature)
*/
public int field = 1;
private T data;
/**
Javadoc for target class constructor
@param data Initial data
*/
public TargetClass(T data) {
this.data = data;
}
/**
Member inner class (target_feature)
*/
public class InnerRec {
public List<String> processRecursive(int depth) {
List<String> result = new ArrayList<>();
if (depth <= 0) {
result.add(data.toString());
return result;
}
// Recursive call
result.addAll(processRecursive(depth - 1));
return result;
}
}
public abstract void doTask();
public T getData() {return data;}
public InnerRec getInnerRec() {return new InnerRec();}}
/**
Public generic source class with type parameter and two local inner classes/
public class SourceClass<T> {
class InnerClass {
/*
Default access instance method (position: source_inner)*/int process(TargetClass<T> target) {// Public ExpressionMethodReference (numbers=1)Function<TargetClass<T>, String> dataExtractor = TargetClass::getData;String extracted = dataExtractor.apply(target);
// Private ConstructorInvocation (target_feature: this.field=1, pos: source)private class NestedTarget extends TargetClass<T> {NestedTarget() {super((T) (extracted + "_nested"));if (this.field != 1) throw new IllegalArgumentException("this.field must be 1");}}new NestedTarget();
// First local inner class (source_class feature)class LocalProcessor1 {List<String> processRec(TargetClass<T> t, int depth) {return recursionHelper(t, depth);}}
// Second local inner class (source_class feature)class LocalProcessor2 {void validate(TargetClass<T> t) {if (t.field != 1) throw new IllegalStateException("Field mismatch");}}
// Requires_try_catchtry {new LocalProcessor2().validate(target);variableCall(target);
// if/else conditions (recursion method_feature position)List<String> recursiveResult;if (extracted.length() > 3) {recursiveResult = new LocalProcessor1().processRec(target, 2);} else {recursiveResult = recursionHelper(target, 1);}
return recursiveResult.size();} catch (Exception e) {return -1;}}
/**
Default recursion method_feature (1 parameter, target, recursion)
*/
private List<String> recursionHelper(TargetClass<T> target, int depth) {
// outerInstance.new InnerClass().methodName()
return target.new InnerRec().processRecursive(depth);
}
private void variableCall(TargetClass<T> target) {target.doTask();}}
// Trigger methodpublic int triggerProcess(TargetClass<T> target) {return new InnerClass().process(target);}}
/**
Concrete implementation of abstract TargetClass*/class TargetImpl extends TargetClass<String> {public TargetImpl(String data) {super(data);}
@Overridepublic void doTask() {}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass<String> target = new TargetImpl("test");
SourceClass<String> source = new SourceClass<>();
int result = source.triggerProcess(target);
assert result == 3 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}