package test;
import java.lang.reflect.Method;
protected abstract class SourceClass {// Two member inner classes (required by source_class feature)protected class FirstMemberInner {}protected class SecondMemberInner {// Private static method (matches method_position: source_inner)private static TargetClass staticMethod() throws ReflectiveOperationException {TargetClass targetField = new TargetClass(); // Constructor invocation + meets per_conditionlabeledBlock: {synchronized (SecondMemberInner.class) { // Synchronized statementint i = 0;while (i < 1) { // While statement// Private MethodInvocation expressionprivateMethodCall(targetField);// Variable callvariableCall(targetField);i++;}if (targetField != null) break labeledBlock;}}
// Used by reflectionClass<?> innerClass = TargetClass.TargetMemberInner.class;Method reflectMethod = innerClass.getMethod("process");reflectMethod.invoke(targetField.new TargetMemberInner());
return targetField; // Return statement}
// Private method for MethodInvocation featureprivate static void privateMethodCall(TargetClass target) {}
private static void variableCall(TargetClass target) {TargetClass localTarget = target;TargetClass.TargetMemberInner localInner = target.new TargetMemberInner();}}}
/**
Javadoc for TargetClass (required by target_class target_feature)
Abstract target class with member inner class
*/
public abstract class TargetClass {
// Member inner class (required by target_class target_feature)
public class TargetMemberInner {
public void process() {}
}
}