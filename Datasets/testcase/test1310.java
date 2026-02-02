package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;
// Parent class for call_method (parent_class type)strictfp class ParentClass {// call_method: instance, this.methodName(arguments), pos: expressionpublic void parentMethod(String data) {System.out.println("Parent processed: " + data);}}
// Sealed source class (permits feature)public sealed class SourceClass extends ParentClass permits SourceSubClass {// Static field for depends_on_static_fieldprotected static final String STATIC_FIELD = "StaticData";
// Feature: member inner classpublic class MemberInnerClass {// Recursive inner structure (source_inner_rec)public class NestedInnerClass {// Method to be refactored: overriding, public, List<String> return@Overridepublic List<String> processTarget(TargetClass<String> targetParam) throws IllegalArgumentException { // per_condition + requires_throws// Type declaration statementList<String> result = new ArrayList<>();TargetClass.MemberInnerClass targetInner = targetParam.new MemberInnerClass();
// ConstructorInvocation (protected, source pos, this.field x1)ProtectedConstructorHelper helper = new ProtectedConstructorHelper(targetParam.thisField);
// Expression statementtargetInner.execute(targetParam.getTypeParameter());result.add(STATIC_FIELD); // depends_on_static_field
// Generic method (inner_class, generic, instanceReference.methodName(arguments), pos: for)for (int i = 0; i < 3; i++) {int genericResult = targetInner.genericMethod(i, "param" + i);result.add(String.valueOf(genericResult));}
// Variable call + call_method (this.methodName(arguments) in expression)String combined = targetParam.targetField + STATIC_FIELD;this.parentMethod(combined); // call parent class method
// Protected modifier exp:Name (numbers:1)ProtectedNameFeature feature = new ProtectedNameFeature();result.add(feature.getName());
// Validate target parameter (requires_throws)if (targetParam == null) {throw new IllegalArgumentException("Target cannot be null");}
return result;}}}
// Overridden method declaration (for overriding feature)public abstract List<String> processTarget(TargetClass<String> targetParam) throws IllegalArgumentException;
// Feature: anonymous inner classpublic void useAnonymous() {Runnable anonymous = new Runnable() {@Overridepublic void run() {MemberInnerClass inner = new MemberInnerClass();NestedInnerClass nested = inner.new NestedInnerClass();try {nested.processTarget(new TargetClass<>("TargetData", "FieldValue"));} catch (IllegalArgumentException e) {e.printStackTrace();}}};anonymous.run();}
// Protected exp:Name (numbers:1)protected static class ProtectedNameFeature {public String getName() {return "ProtectedName1";}}
// Protected ConstructorInvocation helperprotected static class ProtectedConstructorHelper {public ProtectedConstructorHelper(String field) {// this.field usage in constructor}}}
// Permitted subclass of sealed SourceClasspublic final class SourceSubClass extends SourceClass {@Overridepublic List<String> processTarget(TargetClass<String> targetParam) throws IllegalArgumentException {MemberInnerClass inner = new MemberInnerClass();NestedInnerClass nested = inner.new NestedInnerClass();return nested.processTarget(targetParam);}}
// Target class (public, target_feature: type parameter + member inner class)public class TargetClass<T> {public final String targetField;public final String thisField; // this.field for ConstructorInvocationprivate T typeParameter;
public TargetClass(String targetField, String thisField) {this.targetField = targetField;this.thisField = thisField;this.typeParameter = (T) "TypeParamValue";}
// Target_feature: member inner classpublic class MemberInnerClass {// Generic method (method_feature: 3, generic)public int genericMethod(U num, String str) {
return num.intValue() + str.length();
}
public void execute(T data) {System.out.println("Target inner executed: " + data);}}
public T getTypeParameter() {return typeParameter;}}
// Test classpublic class MoveMethodTest5244 {public static void main(String[] args) throws IllegalArgumentException {SourceClass source = new SourceSubClass();TargetClass<String> target = new TargetClass<>("TestTarget", "TargetThisField");List<String> result = source.processTarget(target);System.out.println("Result: " + result);source.useAnonymous();}}