package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
abstract class SourceClass<T> {protected TargetClass targetField;protected String instanceField;
public abstract List<String> processData(String input, int count);
public default int helperMethod(boolean flag) {try {if (targetField == null) {throw new NullPointerException("Target field is null");}int result = this.processData(instanceField, 3).size();if (flag) {result += targetField.helperMethod(10);}return result;} catch (NullPointerException e) {return 0;}}
public default int helperMethod(int value) {return value * 2;}
static class NestedStaticSource {private String nestedField;}
void createAnonymousClass() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(instanceField);}};runnable.run();}}
public abstract class TargetClass implements Runnable {protected String targetInstanceField;
@Overridepublic abstract void run();
public default int helperMethod(int num) {return num + 5;}
static class NestedStaticTarget {private int nestedInt;}}
// Test class for Move Method refactoringpublic class MoveMethodTest5163 {public static void main(String[] args) {SourceClass<String> source = new SourceClass<String>() {@Overridepublic List<String> processData(String input, int count) {List<String> list = new ArrayList<>();for (int i = 0; i < count; i++) {list.add(input + i);}return list;}};source.targetField = new TargetClass() {@Overridepublic void run() {}};source.instanceField = "test";source.createAnonymousClass();System.out.println(source.helperMethod(true));}}