package test.refactor.movemethod;
// Parent generic class for overridingclass ParentGenericClass<T> {public int overridingMethod(TargetClass<T> targetParam) {return 0; // Base implementation}}
// Source class: public generic class, same package with targetpublic class SourceClass<T> extends ParentGenericClass<T> {// Static field for depends_on_static_fieldpublic static final int STATIC_FIELD = 10;private String instanceField = "sourceInstance"; // For access_instance_field
// Member inner class (method_position: source_inner)public class SourceInnerClass {// Overriding method (method.type: overriding)@Overridepublic int overridingMethod(TargetClass<T> targetParam) { // per_condition: contains target parameter// Super constructor invocationclass SuperInvoker extends ParentGenericClass<T> {public SuperInvoker() {super(); // Explicit super constructor invocation}}new SuperInvoker();
// NumberLiteral feature (numbers: "2", modifier: public)public int numberLiteral = 2;
// Variable callTargetClass<T>.TargetInnerClass targetInner = targetParam.new TargetInnerClass();targetInner.innerMethod();
// Access instance fieldSystem.out.println(SourceClass.this.instanceField);
// Depends on static fieldint result = STATIC_FIELD * numberLiteral;
// No new exceptionreturn result;}}}
// Target class: public generic class, target_feature=local inner classpublic class TargetClass {
// Local inner class (target_feature) - target_inner context
public class TargetInnerClass {
public void innerMethod() {}
}
}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5280Test {@Testpublic void testOverridingMethodBeforeRefactoring() {SourceClass<String> source = new SourceClass<>();SourceClass<String>.SourceInnerClass sourceInner = source.new SourceInnerClass();TargetClass<String> targetParam = new TargetClass<>();
// Execute overriding methodint result = sourceInner.overridingMethod(targetParam);
// Verify return value (base type: int)assertEquals(20, result); // STATIC_FIELD (10) * numberLiteral (2)
// Verify per_condition: method contains target parameterassertNotNull(targetParam);
// Verify local inner class in targetTargetClass<String>.TargetInnerClass targetInner = targetParam.new TargetInnerClass();assertNotNull(targetInner);}}