package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
// Parent class for target_class extends featureclass ParentTarget {protected static String STATIC_FIELD = "parent_static"; // For depends_on_static_fieldprotected int parentField = 1;}
/**
Public target class with extends and local inner class (target_features)*/public class TargetClass extends ParentTarget {public int thisField = 1; // this.field=1 for AssertStatement
/**
Member inner recursive class (target_inner_rec)*/public class TargetInnerRec {private int depth;
public TargetInnerRec(int depth) {this.depth = depth;}
public List<String> process() {List<String> result = new ArrayList<>();result.add("inner_rec_" + depth);if (depth > 0) {result.addAll(new TargetInnerRec(depth - 1).process());}return result;}
public void doInnerTask() {}}
public void doTask() {}
// Local inner class (target_feature)public List<String> targetLocalProcess() {class LocalProcessor {public List<String> collect() {return new ArrayList<>(List.of("target_local", STATIC_FIELD));}}return new LocalProcessor().collect();}}
// Sub class for method_featureclass TargetSubClass extends TargetClass {private String subData;
// Public constructor with 2 parameters (method_feature)public TargetSubClass(String subData, int value) {this.subData = subData;this.thisField = value;}
public String getSubData() {return subData;}}
/**
Public source class with two local inner classes*/public class SourceClass {protected String outerProtected = "source_protected"; // For access_outer_protected
/**
Protected instance method (position: source)*/protected void process(TargetClass target) {// Public AssertStatement (target_feature: this.field=1, pos: source)assert target.thisField == 1 : "this.field must be 1";
// Access outer protectedString protectedCombined = outerProtected + "_" + target.parentField;
// Depends on static fieldString staticData = ParentTarget.STATIC_FIELD;
// Variable callvariableCall(target);TargetClass.TargetInnerRec innerRec = target.new TargetInnerRec(2);innerRec.doInnerTask();
// Functional interfaces (constructor method_feature position)Supplier<List<String>> subClassSupplier = () -> {// instanceReference::methodNameTargetSubClass sub = new TargetSubClass("sub_data", 1);return sub.new TargetInnerRec(1).process();};List<String> subResult = subClassSupplier.get();
// First local inner class (source_class feature)class LocalHandler1 {void handle() {target.targetLocalProcess().addAll(subResult);}}new LocalHandler1().handle();
// Second local inner class (source_class feature)class LocalHandler2 {void validate() {if (!staticData.equals("parent_static")) {throw new IllegalStateException("Static field mismatch");}}}new LocalHandler2().validate();
// Used by reflectiontry {Method method = TargetClass.TargetInnerRec.class.getMethod("process");@SuppressWarnings("unchecked")List<String> reflectedResult = (List<String>) method.invoke(innerRec);assert reflectedResult.size() == 3 : "Reflection result mismatch";} catch (Exception e) {throw new RuntimeException("Reflection failed", e);}}
private void variableCall(TargetClass target) {target.doTask();}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass();
SourceClass source = new SourceClass();
source.process(target);
System.out.println("Refactoring test passed");
}
}