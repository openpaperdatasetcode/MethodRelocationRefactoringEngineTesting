package test;
import java.util.List;
// Interface for source_class's implements featureinterface TestInterface {void testMethod();}
// Target: default normal class with static nested class (target_feature)class TargetClass {public String targetField = "targetValue";
// Static nested class (target_class target_feature)public static class TargetStaticNested {}
// Member inner class (for target_inner_rec structure)public class TargetInner {// Synchronized normal method (call_method feature)public synchronized int innerMethod() {return targetField.length();}}}
// Source: abstract normal class (implements + two static nested classes)abstract class SourceClass implements TestInterface {// Two static nested classes (source_class feature)public static class FirstStaticNested {}public static class SecondStaticNested {}
// Default instance method (void return, meets requirements)void instanceMethod(TargetClass target) { // Contains target parameter (meets per_condition)// DoStatement: public modifier, target_feature: obj.field + 1, pos: sourcedo {String fieldVal = target.targetField;break;} while (true);
// If statementif (target != null) {// Expression statementFirstStaticNested staticNested = new FirstStaticNested();}
// Variable callvariableCall(target);
// With_bounds: generic method with upper boundprocessBoundedType(target);
// Ternary operators (pos for call_method)int callResult = (target != null)? target.new TargetInner().innerMethod() // outerInstance.new InnerClass().methodName(): 0;
// No new exception thrown}
// Generic method with bounds (with_bounds feature)private <T extends TargetClass> void processBoundedType(T target) {}
private void variableCall(TargetClass target) {TargetClass local = target;local.new TargetInner().innerMethod();}
@Overridepublic void testMethod() {} // Implement interface method}