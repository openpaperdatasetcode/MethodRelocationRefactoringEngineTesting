package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.Arrays;
// Source enum class (private modifier, same package)private enum SourceEnum {INSTANCE1, INSTANCE2;
// Target enum field (per_condition: source contains target's field)private TargetEnum targetField;// Private outer field for access_outer_privateprivate String outerPrivateField = "source_private";
// Anonymous inner class (source_class feature)private Runnable anonymousInner = new Runnable() {@Overridepublic void run() {// Local inner class (source_class feature)class LocalInner {void print() {System.out.println(outerPrivateField);}}new LocalInner().print();}};
// Overloading method 1 (for method.features: overloading)private synchronized int processOverload(String arg) {return arg.length();}
// Overloading method 2 (for method.features: overloading)private synchronized int processOverload(String arg1, String arg2) {return arg1.length() + arg2.length();}
// Instance method to be refactored (method.type: instance, access_modifier: public)@Overridepublic List<String> toString() { // return_type: List<String> (override enum's toString for test)List<String> result = new ArrayList<>();
// Access outer private field (method.features: access_outer_private)result.add(outerPrivateField);
// Variable call (method.features)String targetInnerValue = targetField.new TargetInnerClass().getInnerValue();result.add(targetInnerValue);
// Synchronized overloading method call in instance code blocks (method.features){int overloadResult1 = this.processOverload("arg1"); // 1st overloadingint overloadResult2 = this.processOverload("arg1", "arg2"); // 2nd overloadingresult.add(String.valueOf(overloadResult1 + overloadResult2));}
// Requires try-catch (method.features)try {List<String> data = Arrays.asList("try_data");result.addAll(data);} catch (Exception e) {// no_new_exception implied (no custom exception thrown)result.add("catch_block");}
return result;}}
// Target enum class (public modifier, same package)public enum TargetEnum {TARGET_INSTANCE1, TARGET_INSTANCE2;
// Member inner class (target_feature)public class TargetInnerClass { // target class: target_innerprivate String innerValue = "target_inner_value";
public String getInnerValue() {return innerValue;}
// Overloading methods for call_method (target_feature: overloading)protected Object callOverload(int num) {return num * 2;}
protected Object callOverload(String str) {return str.toUpperCase();}}
// Factory method for inner class instancepublic TargetInnerClass new TargetInnerClass() {return new TargetInnerClass();}}
// Call method usage (type: target, modifier: protected, return_type: Object)class CallMethodHost {protected Object invokeInArrayInit() {// pos: array initialization (call_method.pos)Object[] array = {new TargetEnum.TARGET_INSTANCE1.new TargetInnerClass().callOverload(10), // new ClassName().method()new TargetEnum.TARGET_INSTANCE2.new TargetInnerClass().callOverload("test") // overloading};return array;}}