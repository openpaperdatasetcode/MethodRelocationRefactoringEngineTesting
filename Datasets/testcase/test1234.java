package test.refactoring.movemethod;
import test.refactoring.other.DiffPackageHelper;import java.lang.reflect.Method;import java.util.function.Consumer;
/**
Private source class: same package with target, contains anonymous inner class and static nested class*/private class SourceClass {// Private field for access_outer_private featureprivate String outerPrivateField = "source_private_value";
// Per_condition: source contains the field of the target (via concrete subclass instantiation)private TargetClass<String> targetField;
// Source feature: static nested classpublic static class SourceStaticNestedClass {public static void staticMethod(String data) {System.out.println("Source static nested class: " + data);}}
// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class: " + outerPrivateField);}};anonymous.run();}
/**
Overloading method to be refactored (protected access, returns TargetClass type) - Overload 1
@param targetParam target class parameter (per_condition)
@return TargetClass instance
@throws ReflectiveOperationException required checked exception (requires_throws)
*/
protected TargetClass<String> refactorTargetMethod(TargetClass<String> targetParam) throws ReflectiveOperationException {
this.targetField = targetParam;
return processTarget(targetParam, "default_arg");
}
/**
Overloading method to be refactored (protected access, returns TargetClass type) - Overload 2
@param targetParam target class parameter (per_condition)
@param arg additional parameter for overloading
@return TargetClass instance
@throws ReflectiveOperationException required checked exception (requires_throws)
*/
protected TargetClass<String> refactorTargetMethod(TargetClass<String> targetParam, String arg) throws ReflectiveOperationException {
this.targetField = targetParam;
return processTarget(targetParam, arg);
}
/**
Core processing method to unify logic for overloaded methods*/private TargetClass<String> processTarget(TargetClass<String> target, String arg) throws ReflectiveOperationException {// Variable callTargetClass<String> tempTarget = target;TargetClass<String>.TargetMemberInner targetInner = tempTarget.new TargetMemberInner();
// Access outer private field (access_outer_private)System.out.println("Access outer private field: " + outerPrivateField);
// Type declaration statement: local class inside methodclass MethodLocalProcessor {void process(String data) {System.out.println("Local processor: " + data + ", " + tempTarget.getField());}}MethodLocalProcessor processor = new MethodLocalProcessor();
// Expression statementSourceStaticNestedClass.staticMethod(tempTarget.getField());targetInner.innerMethod(arg);
// MethodReference feature (numbers: "2", modifier: "public")Consumer<String> methodRef1 = processor::process; // Instance method referenceConsumer<String> methodRef2 = SourceStaticNestedClass::staticMethod; // Static method referencemethodRef1.accept("method_ref_arg1");methodRef2.accept("method_ref_arg2");
// While loop position for constructor featureint count = 1;while (count <= 1) { // "1" in method_feature// Constructor feature (type: constructor, modifier: public, return_type: void)TargetClass.ConstructorHelper helper = new TargetClass.ConstructorHelper(tempTarget, count, outerPrivateField);helper.invokeConstructor(); // ClassName.methodName(arguments)count++;}
// Used_by_reflection: invoke target method via reflectionMethod targetMethod = TargetClass.TargetMemberInner.class.getDeclaredMethod("innerMethod", String.class);targetMethod.setAccessible(true);targetMethod.invoke(targetInner, "reflection_arg");
// Switch statement position for call methodString switchArg = tempTarget.getField().length() > 5 ? "long" : "short";String callResult;switch (switchArg) {case "long":callResult = OthersClass.callTargetMethod(tempTarget, "long_arg");break;case "short":callResult = OthersClass.callTargetMethod(tempTarget, "short_arg");break;default:callResult = OthersClass.callTargetMethod(tempTarget, "default_arg");break;}System.out.println("Call method result: " + callResult);
// WhileStatement in diff_package_others (type: WhileStatement, modifier: public)DiffPackageHelper.publicWhileStatement(tempTarget, 1); // "1" in target_feature
return tempTarget;}}
/**
Abstract generic target class: target_feature: type parameter + member inner class
@param <T> type parameter (target_feature)*/abstract class TargetClass<T> extends TargetParentClass {// Target class field for this.field featureprivate T field;
public TargetClass(T field) {this.field = field;}
// Target feature: member inner classpublic class TargetMemberInner {public void innerMethod(String arg) {System.out.println("Target inner class: " + arg + ", field: " + field + ", super field: " + superField);}}
// Getter for this.field featurepublic T getField() {return field;}
// Constructor helper class for constructor feature (inner_class)public static class ConstructorHelper {private final TargetClass<?> target;private final int count;private final String outerField;
// Public constructor (type: constructor)public ConstructorHelper(TargetClass<?> target, int count, String outerField) {this.target = target;this.count = count;this.outerField = outerField;}
// ClassName.methodName(arguments) featurepublic void invokeConstructor() {System.out.println("Constructor helper: count=" + count + ", outerField=" + outerField + ", targetField=" + target.getField());}}}
/**
Parent class for target class super.methodName(arguments) feature*/class TargetParentClass {protected String superField = "target_parent_field";
public void parentMethod(String arg) {System.out.println("Parent class method: " + arg + ", superField: " + superField);}}
/**
Diff package class for WhileStatement pos: diff_package_others
*/
package test.refactoring.other;
import test.refactoring.movemethod.TargetClass;
public class DiffPackageHelper {/**
Public WhileStatement method (modifier: public)
@param target target class instance (to access this.field)
@param count "1" in target_feature
*/
public static <T> void publicWhileStatement(TargetClass<T> target, int count) {
int i = 0;
while (i < count) {
System.out.println("Diff package while: this.field=" + target.getField() + ", count=" + count);
i++;
}
}
}
/**
Others class for call_method (type: others_class)
*/
package test.refactoring.movemethod;
public class OthersClass {/**
Call method: others_class type, public modifier, pos in switch
@param target target class instance
@param arg input argument
@return String result
*/
public static <T> String callTargetMethod(TargetClass<T> target, String arg) {
// instance + super.methodName(arguments) features
target.parentMethod(arg); // Call parent class method via target instance
return "OthersClass_result: " + arg + ", targetField=" + target.getField();
}
}
// Concrete subclass of abstract TargetClass (for instantiation)class ConcreteTargetClass<T> extends TargetClass<T> {public ConcreteTargetClass(T field) {super(field);}}
// Container class to access private SourceClassclass SourceClassContainer {public SourceClass createSourceClass() {return new SourceClass();}}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) throws ReflectiveOperationException {SourceClassContainer sourceContainer = new SourceClassContainer();SourceClass source = sourceContainer.createSourceClass();
TargetClass<String> target = new ConcreteTargetClass<>("test_field");
// Test overloaded method 1TargetClass<String> result1 = source.refactorTargetMethod(target);System.out.println("Overload 1 result: " + result1.getField());
// Test overloaded method 2TargetClass<String> result2 = source.refactorTargetMethod(target, "overload_arg");System.out.println("Overload 2 result: " + result2.getField());}}