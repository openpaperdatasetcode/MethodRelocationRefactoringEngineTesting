package test;
import java.util.List;
// Parent class for call_method featureclass ParentHelper {// Synchronized instance method (parent_class, call_method feature)public synchronized String parentProcess(TargetRecord<?> target) {// obj.m1().m2().m3() method chainreturn target.processLocal().getHelper().generateString();}}
// Public source record with static nested and anonymous inner classespublic record SourceRecord(String data) extends ParentHelper {// Static nested class (source_class feature)public static class StaticNested {public <T> void assist(TargetRecord<T> target) {target.setValue((T) (target.value().toString() + "_assisted"));}}
// Default access normal method (position: source)Object process(TargetRecord<String> target) {// Volatile ArrayAccess (numbers=1)volatile String[] volatileArray = new String[1];volatileArray[0] = target.value(); // ArrayAccess with index 1 (numbers=1)
// Uses outer this (source record instance)SourceRecord source = this;StaticNested.assist(target);
// Anonymous inner class (source_class feature)Runnable task = new Runnable() {@Overridepublic void run() {variableCall(target);volatileArray[0] = source.data() + "_anonymous";}};
// Raw typeList rawList = List.of(volatileArray[0], target.value());
// Variable callvariableCall(target);task.run();
// Overload_exist: Call overloaded method of targettarget.processLocal(10);
// Object initialization (call_method position)String parentResult = super.parentProcess(target);
return List.of(volatileArray[0], rawList, parentResult);}
private void variableCall(TargetRecord<String> target) {target.doTask();}}
// Target record with type parameter and local inner class (target_features)record TargetRecord<T>(T value) {private T data;
public void setValue(T value) {this.data = value;}
public void doTask() {}
// Overloaded method (overload_exist feature)public TargetRecord<T> processLocal() {// Local inner class (target_feature)class LocalProcessor {public Helper getHelper() {return new Helper();}}return new TargetRecord<>(data);}
// Overloaded method (overload_exist feature)public TargetRecord<T> processLocal(int multiplier) {this.data = (T) (data.toString() + "overload" + multiplier);return this;}
public class Helper {public String generateString() {return data.toString() + "_parent_result";}}}
// Test class for Move Method refactoring verificationpublic class RefactoringTest {public static void main(String[] args) {TargetRecord<String> target = new TargetRecord<>("test");SourceRecord source = new SourceRecord("source_data");Object result = source.process(target);assert result instanceof List : "Refactoring test failed";System.out.println("Refactoring test passed");}}