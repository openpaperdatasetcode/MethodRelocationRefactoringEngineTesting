package test;
import otherpackage.DiffPackageChecker;import java.util.ArrayList;import java.util.List;
// Public generic source class (same package) with anonymous inner and local inner classespublic class SourceClass<T extends Comparable<T>> {// Anonymous inner class (source_class feature)private final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous task executed");}};
// Final normal method (position: source)public final TargetClass<T> process(TargetClass<T> target, T data) {// Super constructor invocation (in local inner class)class LocalProcessor extends ParentHelper<T> {LocalProcessor() {super(data); // Super constructor invocation}
List<String> processTarget(TargetClass<T> t) {// For loop (constructor method_feature position)List<String> result = new ArrayList<>();for (int i = 0; i < 1; i++) {result.add(super.getData().toString()); // Super keywords}return result;}}
// Local inner class (source_class feature)class LocalValidator {void checkField(TargetClass<T> t) {DiffPackageChecker.validateObjField(t); // BreakStatement position}}
new LocalValidator().checkField(target);List<String> processedList = new LocalProcessor().processTarget(target);
// Variable callvariableCall(target);anonymousTask.run();
// Ternary operator (call_method position)int callResult = (target.getField() == 1) ? OtherClass.callChain(target) : -1;
return new TargetClass<>(data, target.getField() + callResult);}
private void variableCall(TargetClass<T> target) {target.doTask();}
// Protected constructor method_feature (1 parameter, sub_class, constructor)protected static class ParentHelper<T> {private final T data;
protected ParentHelper(T data) {this.data = data;}
protected T getData() {return data;}}
// Call_method: others_class, private modifierprivate static class OtherClass {private static <T> int callChain(TargetClass<T> target) {// Method chain: obj.m1().m2().m3()return target.getHelper().process().getCount();}}}
// Abstract generic target class with anonymous inner class (target_feature)abstract class TargetClass<T extends Comparable<T>> {private final T data;public int field = 1; // obj.field=1
public TargetClass(T data, int field) {this.data = data;this.field = field;}
public int getField() {return field;}
public Helper<T> getHelper() {return new Helper<>();}
public abstract void doTask();
// Anonymous inner class (target_feature)public class Helper<T> {public Processor process() {return new Processor();}
public class Processor {public int getCount() {Runnable task = new Runnable() {@Overridepublic void run() {}};task.run();return 2;}}}}
// Different package class for BreakStatement positionpackage otherpackage;
import test.TargetClass;
public class DiffPackageChecker {public static <T> void validateObjField(TargetClass<T> target) {// Private BreakStatement (target_feature: obj.field=1)private int count = 0;while (true) {if (target.getField() == 1) break;if (count++ >= 3) throw new IllegalArgumentException("obj.field mismatch");}}}
// Test class for Move Method refactoring verificationpackage test;
public class RefactoringTest {public static void main(String[] args) {TargetClass<Integer> target = new TargetClass<>(5, 1) {@Overridepublic void doTask() {}};SourceClass<Integer> source = new SourceClass<>();TargetClass<Integer> result = source.process(target, 10);assert result.getField() == 1 + 2 : "Refactoring test failed";System.out.println("Refactoring test passed");}}