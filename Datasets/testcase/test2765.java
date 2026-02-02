package test;
import java.lang.reflect.Method;import java.util.List;
// Interface for source_class's implements featureinterface TestInterface {void testMethod();}
// Target: public generic class (type parameter + local inner class)public class TargetClass<T> {public T field1;public T field2;
public TargetClass(T f1, T f2) {this.field1 = f1;this.field2 = f2;}
// Local inner class (target_class target_feature)public TargetClass<T> createLocalInner() {class LocalInner extends TargetClass<T> {public LocalInner(T f1, T f2) {super(f1, f2);}}return new LocalInner(field1, field2);}
protected TargetClass<T> super superMethod(TargetClass<T> target) {return target.createLocalInner();}}
// Source: protected normal class (implements + static nested + local inner class)protected class SourceClass implements TestInterface {protected int outerProtected = 100; // For access_outer_protected
// Static nested class (source_class feature)public static class SourceStaticNested {public static <T> Object staticMethod(TargetClass<T> target) {return target.field1;}}
// Member inner class (method_position: source_inner)protected class SourceInner {private TargetClass<String> targetField = new TargetClass<>("a", "b"); // Contains target field
/**
Javadoc for varargs method
Processes variable arguments of TargetClass, no new exception
@param targets variable arguments*/protected final <T> void varargsMethod(TargetClass<T>... targets) {// Instance code blocks (pos for recursion feature){if (targets.length > 0) {TargetClass<T> recursiveResult = recursiveMethod(targets[0]);}}
// Same_package_target (pos for SuperConstructorInvocation)class NestedInInner extends TargetClass<T> {private NestedInInner(TargetClass<T> target) {// SuperConstructorInvocation: private modifier, target_feature: this.field + 2super(target.field1, target.field2);}}
for (int i = 0; i < targets.length; i++) {TargetClass<T> target = targets[i];// Variable callvariableCall(target);
// Access outer protectedint val = outerProtected + i;
// Switch statementswitch (i) {case 0:continue; // Continue statementcase 1:break;}
// Used by reflectiontry {Method method = TargetClass.class.getMethod("createLocalInner");method.invoke(target);} catch (Exception e) {// No new exception}}
// Exception handling statements (pos for call_method)try {throw new Exception();} catch (Exception e) {// Lambda expression: (parameters) -> methodBodySourceClass.<T>lambdaMethod(targets, (t) -> SourceStaticNested.staticMethod(t));}}
// Recursion feature methodprivate <T> TargetClass<T> recursiveMethod(TargetClass<T> target) {if (target == null) return null;// Recursion with super.methodName(arguments)return superMethod(target.recursiveMethod(target.createLocalInner()));}
private <T> TargetClass<T> superMethod(TargetClass<T> target) {return target.superMethod(target);}
private <T> void variableCall(TargetClass<T> target) {TargetClass<T> local = target;local.createLocalInner();}}
// Call_method: source type, static featureprotected static <T> Object lambdaMethod(TargetClass<T>[] targets, java.util.function.Function<TargetClass<T>, Object> func) {return func.apply(targets[0]);}
// Local inner class (source_class feature)public void useLocalInner() {class LocalInner {void process() {new SourceInner().varargsMethod(new TargetClass<>("x", "y"));}}new LocalInner().process();}
@Overridepublic void testMethod() {}}