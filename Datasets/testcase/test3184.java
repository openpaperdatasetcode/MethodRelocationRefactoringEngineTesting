package test;
import java.util.ArrayList;import java.util.List;
// Interface for target_class implements featureinterface TargetProcessable {<T> void processData(T data);}
/**
Public generic target class with implements and anonymous inner class (target_features)*/public class TargetClass<T> implements TargetProcessable {public T instanceField; // For access_instance_field featureprivate List<T> dataList = new ArrayList<>();
@Overridepublic <T> void processData(T data) {dataList.add(data);}
// Overloaded method 1 (overload_exist feature)public void addData(T data) {dataList.add(data);}
// Overloaded method 2 (overload_exist feature)public void addData(T... data) {for (T item : data) {dataList.add(item);}}
public void doTask() {}
// Anonymous inner class (target_feature)public Runnable getTargetTask() {return new Runnable() {@Overridepublic void run() {dataList.clear();}};}
public List<T> getDataList() {return dataList;}}
/**
Abstract generic source class with static nested and member inner classes*/abstract class SourceClass<T> {// Static nested class (source_class feature)public static class StaticNested<T> {public static <T> void assist(TargetClass<T> target, T data) {target.addData(data);}}
// Member inner class (source_class feature)public class MemberInner<T> {public void processInner(TargetClass<T> target) {target.instanceField = (T) "inner_processed";}}
/**
Public varargs method (position: source)*/@SafeVarargspublic final Object process(TargetClass<T> target, T... params) {// Access instance fieldtarget.instanceField = params[0];
// Depends on inner classStaticNested.assist(target, (T) "static_assist");new MemberInner<T>().processInner(target);
// do statementint count = 0;do {// switch caseswitch (count) {case 0:target.addData(params); // Call varargs overloaded methodbreak;case 1:target.processData(params[count]);break;default:break;}count++;} while (count < 2);
// Variable callvariableCall(target);target.getTargetTask().run();
return target.getDataList();}
private void variableCall(TargetClass<T> target) {target.doTask();}}
/**
Concrete implementation of abstract SourceClass
*/
class SourceImpl<T> extends SourceClass<T> {}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass<String> target = new TargetClass<>();
SourceClass<String> source = new SourceImpl<>();
Object result = source.process(target, "param1", "param2");
assert result instanceof List : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}