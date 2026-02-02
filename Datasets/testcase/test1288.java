package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;
// Target enum class (public, with static nested class)public enum TargetEnum<T> {INSTANCE;
private T targetField;
// Static nested class (target_feature)public static class TargetStaticNestedClass {private String nestedData;
public TargetStaticNestedClass(String nestedData) {this.nestedData = nestedData;}
// Accessor method (for call_method target_feature)public String getNestedData() {return nestedData;}}
public T getTargetField() {return targetField;}
public void setTargetField(T targetField) {this.targetField = targetField;}
// Method to be overridden (base for overriding method in source)public List<String> processData(TargetEnum<T> targetParam) {return new ArrayList<>();}}
// Parent enum for source enum (permits requirement)enum ParentEnum<T> {;
// Parent class method (for call_method type: parent_class)public TargetEnum.TargetStaticNestedClass parentAccessorMethod(String data) {return new TargetEnum.TargetStaticNestedClass(data);}}
// Source enum class (default modifier, same package, permits, anonymous inner class, static nested class)enum SourceEnum<T> permits ParentEnum<T> {VALUE1, VALUE2;
private String sourceField = "sourceValue";
// Static nested class (first)public static class FirstSourceStaticNestedClass {private int count = 2;
public int getCount() {return count;}}
// Static nested class (second)static class SecondSourceStaticNestedClass {// Overriding method to be refactored (method_position: source_inner)@Overridepublic List<String> processData(TargetEnum<T> targetParam) { // Contains target parameter (per_condition)// ConstructorInvocation (private, this.field, 2, pos: diff_package_others)private FirstSourceStaticNestedClass nested = new FirstSourceStaticNestedClass();int num = nested.getCount(); // 2 featurethis.sourceField = "updated_" + num; // this.field
// Do statementList<String> result = new ArrayList<>();int i = 0;do {result.add("do_loop_" + i);i++;} while (i < 2);
// Expression statementtargetParam.setTargetField((T) "targetData"); // Expression statement
// Variable callString varCall = this.sourceField;result.add(varCall);result.add(targetParam.getTargetField().toString());
// Anonymous inner class (source_class feature)Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in SourceEnum");}};anonymous.run();
// Call method (parent_class, public, accessor, instanceReference::methodName, pos: expression)ParentEnum<T> parentInstance = ParentEnum.values()[0];TargetEnum.TargetStaticNestedClass nestedFromParent = parentInstance.parentAccessorMethod("parentData"); // accessorRunnable methodRef = nestedFromParent::getNestedData; // instanceReference::methodName (pos: expression)methodRef.run();
// No new exceptionreturn result;}}}
// Test classpublic class MoveMethodRefactorTest_5204 {public static void main(String[] args) {TargetEnum<String> target = TargetEnum.INSTANCE;SourceEnum.SecondSourceStaticNestedClass sourceInner = new SourceEnum.SecondSourceStaticNestedClass();List<String> result = sourceInner.processData(target);System.out.println(result);}}