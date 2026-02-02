package test.refactor.movemethod;
import java.util.List;import java.util.ArrayList;
// Parent class for source_class and target_class extends featureabstract class ParentBaseClass {protected static String STATIC_FIELD = "staticData"; // For depends_on_static_field
// For call_method overridingpublic List<String> baseMethod(int count) {return new ArrayList<>();}}
// Source record: public, extends ParentBaseClass, same package with targetpublic record SourceRecord(String sourceProp, TargetRecord targetField) extends ParentBaseClass { // per_condition: source contains target's field// Static nested class (source_class feature)public static class StaticNestedClass {public static void staticMethod() {}}
// Member inner class (source_class feature)public class MemberInnerClass {public void innerMethod() {}}
/**
Method javadoc (feature: method javadoc)
@param varargs variable arguments
@return TargetRecord instance*/protected TargetRecord varargsMethod(String... varargs) { // method.type: varargs, return TargetClass Type// Super constructor invocation (implicit in record, explicit via local class)class SuperInvoker extends ParentBaseClass {public SuperInvoker() {super(); // Explicit super constructor invocation}}new SuperInvoker();
// Overloading feature (type=overloading, modifier=public, pos=property assignment)int overloadResult = overloadedMethod(targetField, "propertyAssign");
// Switch statementswitch (varargs.length) {case 0:throw new IllegalArgumentException("No arguments");case 1:System.out.println("Single argument: " + varargs[0]);break;default:System.out.println("Multiple arguments");}
// Variable callMemberInnerClass memberInner = new MemberInnerClass();memberInner.innerMethod();StaticNestedClass.staticMethod();
// Access instance methodtargetField.instanceMethod();
// Depends on static fieldString staticData = ParentBaseClass.STATIC_FIELD;
// Call_method: pos=for, target_feature=(parameters)->methodBody (lambda)List<String> callResult = new ArrayList<>();for (int i = 0; i < varargs.length; i++) {callResult = ((count) -> {List<String> list = new ArrayList<>();for (int j = 0; j < count; j++) list.add(varargs[j]);return list;}).baseMethod(varargs.length); // overriding feature (implements parent method)}
// No new exceptionreturn targetField;}
// Overloading method (matches method.features.overloading)public int overloadedMethod(TargetRecord target, String prop) { // return_type: base type (int)target.setProperty(prop); // property assignment posreturn 1; // method_feature: 1}
// Call_method: type=source, modifier=default, target_feature=overriding@Overridepublic List<String> baseMethod(int count) {return new ArrayList<>(List.of("sourceMethodResult"));}}
// Target record: public, extends ParentBaseClass (target_feature: extends)public record TargetRecord(String targetProp) extends ParentBaseClass {// Instance method for access_instance_methodpublic void instanceMethod() {}
// Property for overloading feature's property assignmentprivate String property;public void setProperty(String property) {this.property = property;}
// Override violation: method signature conflicts with moved varargsMethodprotected TargetRecord varargsMethod(String... varargs) {return this;}
// Inner recursive context (target_class: target_inner_rec)public TargetRecord recursiveMethod() {return this.targetProp().length() > 0 ? recursiveMethod() : this;}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5283Test {@Testpublic void testVarargsMethodBeforeRefactoring() {TargetRecord target = new TargetRecord("targetValue");SourceRecord source = new SourceRecord("sourceValue", target);
// Execute target methodTargetRecord result = source.varargsMethod("arg1", "arg2");assertSame(target, result);
// Verify overloading methodint overloadResult = source.overloadedMethod(target, "testProp");assertEquals(1, overloadResult);
// Verify call_methodList<String> callResult = source.baseMethod(2);assertEquals(List.of("sourceMethodResult"), callResult);
// Verify override violationTargetRecord targetOverride = new TargetRecord("override");assertSame(targetOverride, targetOverride.varargsMethod("test"));}}