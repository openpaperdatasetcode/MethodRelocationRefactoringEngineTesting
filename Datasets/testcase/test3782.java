import java.util.ArrayList;import java.util.List;
// TargetClass: normal, public, with anonymous inner class (meets target_class specs)public class TargetClass {private List<String> dataList = new ArrayList<>();
// Anonymous inner class (target_feature)private final Runnable listInitializer = new Runnable() {@Overridepublic void run() {dataList.add("init_data_1");dataList.add("init_data_2");}};
// Instance method for access_instance_method featurepublic void addData(String data) {dataList.add(data);}
// Instance method for access_instance_method featurepublic List<String> getDataList() {return new ArrayList<>(dataList);}
// Method to trigger anonymous inner class (for depends_on_inner_class)public void initData() {listInitializer.run();}}
// Parent class for SourceClass to support access_outer_protected featureclass ParentSource {protected String outerProtectedField = "parent_protected_data";}
// SourceClass: normal, private, same package as target, with 2 static nested classes (meets source_class specs)private class SourceClass extends ParentSource {// 1st static nested class (source_feature)public static class FirstStaticNested {// Recursion method: meets method specs (recursion, Object return, public, source position)public Object recursiveProcess(TargetClass target, int depth) {// Ensure method contains target parameter (per_condition)if (target == null || depth <= 0) {return new ArrayList<String>();}
// Type declaration statement (method_feature)List<String> currentData = new ArrayList<>();
// Variable call: call target's instance method (method_feature)target.initData();// Access target's instance method (access_instance_method feature)currentData.addAll(target.getDataList());
// Access outer protected field (access_outer_protected feature)SourceClass source = new SourceClass();currentData.add(source.outerProtectedField);
// Depends on target's inner class (depends_on_inner_class: uses listInitializer's data)target.addData("recursion_data_" + depth);currentData.add(target.getDataList().getLast());
// Recursion callList<String> recursiveData = (List<String>) recursiveProcess(target, depth - 1);currentData.addAll(recursiveData);
return currentData; // No exception thrown (no_new_exception)}}
// 2nd static nested class (source_feature)public static class SecondStaticNested {// Helper method to start recursionpublic Object startRecursion(TargetClass target, int depth) {return FirstStaticNested.recursiveProcess(target, depth);}}}
// Test entry to trigger the flowpublic class SourceTest {public static void main(String[] args) {TargetClass target = new TargetClass();SourceClass.SecondStaticNested nested = new SourceClass.SecondStaticNested();
// Execute recursion (depth=2)List<String> result = (List<String>) nested.startRecursion(target, 2);System.out.println("Recursion result: " + result);
// Verify no exception (no_new_exception)assert result != null && !result.isEmpty();}}