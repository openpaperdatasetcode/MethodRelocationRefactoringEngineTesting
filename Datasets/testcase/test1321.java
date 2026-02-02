package test.refactor.movemethod;
import java.lang.reflect.Method;
// Diff package class for WhileStatement pos: diff_package_otherspackage diffpackage;public class OthersDiffPackage {public static int staticField = 1;}
package test.refactor.movemethod;import diffpackage.OthersDiffPackage;
// Source class: final generic class, same package with targetfinal class SourceClass<T> {protected T outerProtectedField; // For access_outer_protectedprivate int outerInstanceField = 5; // For access_instance_field
// Static nested class (source_class feature)public static class StaticNestedClass {
// Overloading methods (method.type: overloading) - source_inner position
public void overloadedMethod(TargetClass<?> targetParam) {
// Uses outer this (uses_outer_this)
SourceClass.this.outerProtectedField = (T) "value";
// While statement (top-level feature)int count = 0;while (count < 3) {count++;}
// Private WhileStatement (feature: type=WhileStatement, modifier=private, pos=diff_package_others)privateWhileLogic(targetParam);
// Expression statementSystem.out.println("Overloaded method 1");
// Variable callStaticNestedClass<String> nested = new StaticNestedClass<>();nested.helperMethod();
// Access outer protectedSystem.out.println(SourceClass.this.outerProtectedField);
// Access instance fieldSystem.out.println(SourceClass.this.outerInstanceField);
// Raw type (feature: raw_type)TargetClass rawTarget = new TargetClass() {@Overridepublic void abstractMethod() {}};
// Used by reflection (feature: used_by_reflection)try {Method refMethod = StaticNestedClass.class.getMethod("overloadedMethod", TargetClass.class);refMethod.invoke(this, targetParam);} catch (Exception e) {// No new exception (feature: no_new_exception)}}
// Overloading method (second overload)public void overloadedMethod(TargetClass<?> targetParam, String extra) {// Override violation (feature: override_violation) - conflicts with target's method signatureSystem.out.println("Overloaded method 2: " + extra);}
private void privateWhileLogic(TargetClass<?> targetParam) {// Target_feature: ClassName.field (OthersDiffPackage.staticField), 1while (OthersDiffPackage.staticField == 1) {targetParam.doSomething();break; // Avoid infinite loop}}
private void helperMethod() {}}
// Anonymous inner class (source_class feature)public void createAnonymousInner() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class");}};runnable.run();}}
private void helperMethod() {}}
// Target class: abstract generic class (target_class.type: generic, modifier: abstract)abstract class TargetClass<V> {// Member to support variable callpublic void doSomething() {}
// Abstract method (potential override violation when moving overloaded methods)public void overloadedMethod(TargetClass<V> targetParam) {} // Override violation candidate
public abstract void abstractMethod(); // For raw type instantiation}
// Call_method: type=source, modifier=protected, target_feature=[overriding, this.methodName(arguments)], pos=exception handlingclass CallerClass extends SourceClass<String> {@Overrideprotected Object methodCaller() { // call_method.return_type: Objecttry {TargetClass<Integer> target = new TargetClass<Integer>() {@Overridepublic void abstractMethod() {}};// Target_feature: this.methodName(arguments)this.new StaticNestedClass<>().overloadedMethod(target);return new Object();} catch (Exception e) {// pos: exception handling statementsreturn null;}}
// Call_method: type=source, modifier=protected, target_feature=overridingprotected Object methodCaller(String arg) {return this.methodCaller(); // Overriding-like behavior}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5263Test {@Testpublic void testOverloadedMethodsBeforeRefactoring() {SourceClass<String>.StaticNestedClass<Integer> nested = new SourceClass<String>().new StaticNestedClass<>();TargetClass<Integer> target = new TargetClass<Integer>() {@Overridepublic void abstractMethod() {}};
// Verify per_condition: method contains target parameternested.overloadedMethod(target);nested.overloadedMethod(target, "test");
// Verify call_methodCallerClass caller = new CallerClass();assertNotNull(caller.methodCaller());assertNotNull(caller.methodCaller("arg"));}}