package test.refactoring.source;
import test.refactoring.target.TargetClass;import java.lang.reflect.Method;import java.util.Objects;
/**
Final generic source class: different package with target, sealed with permits clause
@param <S> type parameter (generic class feature)
@permits Sealed class permitted subclasses (required for permits feature)*/final sealed class SourceClass<S> permits SourceSubClass1, SourceSubClass2 {// Protected field for access_outer_protected featureprotected String outerProtectedField = "source_protected_value";
// Per_condition: source contains the field of the targetprivate TargetClass<S> targetField;
// Constructor to initialize target field (access different package target)public SourceClass(TargetClass<S> target) {this.targetField = target;}
/**
Overriding method to be refactored (private access, returns TargetClass type)
Note: Private methods cannot override superclass methods directly; simulated via method shadowing + functional interface
@param targetParam target class parameter (per_condition)
@return TargetClass instance*/private TargetClass<S> refactorTargetMethod(TargetClass<S> targetParam) {// Variable callTargetClass<S> tempTarget = targetParam;Objects.requireNonNull(tempTarget, "Target parameter cannot be null");
// Access outer protected field (access_outer_protected)System.out.println("Source protected field: " + this.outerProtectedField);
// Super keywords (reference superclass method)super.toString();
// This.methodName(arguments)this.auxiliaryMethod(tempTarget);
// Requires_try_catch: Handle reflection and runtime exceptionstry {// Used_by_reflection: Invoke target method via reflectionMethod targetMethod = TargetClass.class.getDeclaredMethod("targetInstanceMethod", String.class);targetMethod.setAccessible(true);targetMethod.invoke(tempTarget, "reflection_arg");
// Lambda expressions position for call methodRunnable lambda = () -> tempTarget.callTargetMethod(this, "lambda_arg");lambda.run();} catch (Exception e) {// No new exception thrown (handle internally)System.err.println("Handled exception: " + e.getMessage());}
return tempTarget;}
/**
Auxiliary method for this.methodName(arguments) feature
@param target target class instance
*/
private void auxiliaryMethod(TargetClass<S> target) {
System.out.println("Auxiliary method processes target: " + target.getData());
}
// Public wrapper method to access private refactor method (for testing)public TargetClass<S> invokeRefactor() {return refactorTargetMethod(this.targetField);}}
// Permitted subclasses of sealed SourceClass (sealed class requirement)final class SourceSubClass1<S> extends SourceClass<S> {public SourceSubClass1(TargetClass<S> target) {super(target);}}
final class SourceSubClass2<S> extends SourceClass<S> {public SourceSubClass2(TargetClass<S> target) {super(target);}}
// Different package: test.refactoring.targetpackage test.refactoring.target;
/**
Non-sealed generic target class: target_feature: implements + member inner class
@param <T> type parameter (generic class feature)*/non-sealed class TargetClass<T> implements TargetInterface<T> {private T data;
public TargetClass(T data) {this.data = data;}
// Target feature: member inner classpublic class TargetMemberInner {public void innerMethod(String arg) {System.out.println("Target inner class method: " + arg + ", data: " + data);}}
/**
Instance method for variable call and reflection invocation
@param arg input argument
*/
public void targetInstanceMethod(String arg) {
System.out.println("Target instance method: " + arg);
}
/**
Call method: target type, protected modifier, pos in Lambda expressions
@param source source class instance (for cross-package access)
@param arg input argument
@return Object result
*/
protected Object callTargetMethod(SourceClass<?> source, String arg) {
// instance + ClassName.methodName(arguments) features
TargetMemberInner inner = new TargetMemberInner();
inner.innerMethod(arg);
return "Call result: " + arg + ", source protected field: " + source.outerProtectedField;
}
// Getter for data@Overridepublic T getData() {return data;}}
/**
Interface for target class implements feature
@param <T> type parameter
*/
interface TargetInterface<T> {
T getData();
}
// Test class to verify functionality (source package)package test.refactoring.source;
import test.refactoring.target.TargetClass;
public class SourceClassTest {public static void main(String[] args) {// Initialize target class (different package)TargetClass<String> target = new TargetClass<>("test_data");
// Initialize source subclassSourceClass<String> source = new SourceSubClass1<>(target);
// Invoke refactor methodTargetClass<String> result = source.invokeRefactor();System.out.println("Refactor result: " + result.getData());}}