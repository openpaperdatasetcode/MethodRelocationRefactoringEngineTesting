import java.util.ArrayList;import java.util.List;
// TargetClass: abstract, protected, with local inner class (meets target_class specs)protected abstract class TargetClass<T> {protected T targetInstanceField;public static final String TARGET_STATIC_FIELD = "TARGET_STATIC"; // For ClassName.field access
public abstract T processData(T data);
public String getFormattedData(T data) {// Local inner class (target_feature)class LocalFormatter {String format(T input) {return input.toString().toUpperCase();}}return new LocalFormatter().format(data);}
// Generic method for call_method (target_feature: generic)public String genericMethod(U input) {
return input.toString() + "_GENERIC";
}
}
// SourceClass: abstract, package-private, same package as target, with static nested + member inner class (meets source_class specs)abstract class SourceClass {private String outerPrivateField = "SOURCE_PRIVATE"; // For access_outer_private
// Source contains target field (per_condition)private TargetClass<String> sourceTargetField;
// Static nested class (source_feature)public static class SourceStaticNested {// Varargs method: meets method specs (varargs, base type return, public, source position)public int processTargetVarargs(TargetClass<String>... targets) {int totalLength = 0;for (TargetClass<String> target : targets) {try {// VariableDeclarationStatement: meets method_feature (private modifier, pos=diff_package_others, access ClassName.field)private String staticFieldRef = TargetClass.TARGET_STATIC_FIELD; // Access target's static field (ClassName.field 1)
// Access instance field (method_feature)target.targetInstanceField = "INPUT_" + staticFieldRef;
// Variable call (method_feature)String processed = target.processData(target.targetInstanceField);totalLength += processed.length();
// Access outer private field via enclosing instance (access_outer_private)SourceClass outer = new SourceConcrete();String combined = outer.outerPrivateField + "_" + processed;System.out.println("Combined: " + combined);
} catch (Exception e) {// requires_try_catch (method_feature)System.out.println("Handling exception: " + e.getMessage());}}return totalLength; // Base type (int) return}}
// Member inner class (source_feature)public class SourceMemberInner {public SourceMemberInner() {// this(arguments) (method_feature)this("DEFAULT_INIT");}
public SourceMemberInner(String initData) {// Constructor invocation + depends_on_inner_class (uses target's local inner class)sourceTargetField = new TargetClass<String>() {@Overridepublic String processData(String data) {return getFormattedData(data); // Depend on target's local inner class}};sourceTargetField.targetInstanceField = initData;}
// Call target's method in if/else (call_method specs: pos=if/else conditions)public String callTargetMethod(Object input) {if (input instanceof String) {// call_method: OuterClass.InnerClass.methodName() (TargetClass's genericMethod)return SourceStaticNested.this.processTargetVarargs(sourceTargetField) + "_"
sourceTargetField.genericMethod(input);
} else {
return sourceTargetField.genericMethod(input) + "_ELSE";
}
}
}
// Abstract method to enforce concrete subclasspublic abstract void initTarget();}
// Concrete subclass of SourceClass (to instantiate abstract class)class SourceConcrete extends SourceClass {@Overridepublic void initTarget() {sourceTargetField = new TargetClass<String>() {@Overridepublic String processData(String data) {return data + "_PROCESSED";}};}}
// Test entrypublic class SourceTest {public static void main(String[] args) {// Test varargs methodTargetClass<String> target1 = new TargetClass<String>() {@Overridepublic String processData(String data) {return data + "_T1";}};TargetClass<String> target2 = new TargetClass<String>() {@Overridepublic String processData(String data) {return data + "_T2";}};int total = SourceClass.SourceStaticNested.processTargetVarargs(target1, target2);System.out.println("Total length: " + total);
// Test member inner class and call_methodSourceClass source = new SourceConcrete();SourceClass.SourceMemberInner inner = source.new SourceMemberInner();String resultIf = inner.callTargetMethod("TEST_IF");String resultElse = inner.callTargetMethod(123);System.out.println("If result: " + resultIf);System.out.println("Else result: " + resultElse);}}