package test.refactoring.movemethod;
import java.io.IOException;import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
// Source record class: default modifier, same package with target, has static nested + anonymous inner classrecord SourceRecord() {// Source feature: static nested classstatic class SourceStaticNestedClass {}
// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class");}};anonymous.run();}
// Method to be refactored: instance, public, return Object, contains target parameter (per_condition)public Object refactorTargetMethod(TargetRecord targetParam) throws IOException {// IOException feature (declared throws)if (targetParam == null) throw new IOException("Target parameter cannot be null");
// If statementif (targetParam.value() > 0) {targetParam.getLocalInnerData();}
// While statementint count = 0;while (count < 2) {count++;}
// 2 PrefixExpression (numbers: "2", modifier: protected)protected int num1 = 5;++num1; // PrefixExpression 1protected int num2 = 10;++num2; // PrefixExpression 2
// Variable callTargetRecord tempTarget = targetParam;Object targetData = tempTarget.value();
// With bounds (generic type with bounds)List> boundedList = new ArrayList<>();
// Depends on inner class (target's local inner class)String innerClassData = tempTarget.getLocalInnerData();
// Used by reflectiontry {Method reflectMethod = SourceRecord.class.getDeclaredMethod("refactorTargetMethod", TargetRecord.class);reflectMethod.invoke(this, targetParam);} catch (Exception e) {// No new exception thrown}
return tempTarget;}
// Call method: parent_class type, public, pos in exception throwing statements, return intpublic int callParentOverriddenMethod(ParentClass parentInstance) throws IOException {try {// Exception throwing statements position + instanceReference.methodName(arguments)return parentInstance.calculate(10, 20);} catch (IllegalArgumentException e) {// Throw exception (pos: exception throwing statements)throw new IOException("Call parent method failed", e);}}}
// Target record class: default modifier, has local inner class (target_feature: target_inner)record TargetRecord(int value) {// Target feature: local inner class (target_inner)public String getLocalInnerData() {class TargetLocalInnerClass {String innerData = "Local inner class data";String getData() {return innerData;}}TargetLocalInnerClass localInner = new TargetLocalInnerClass();return localInner.getData();}}
// Parent class for call_method (parent_class type)class ParentClass {// Method to be overridden (target_feature: overriding)public int calculate(int a, int b) {return a + b;}}
// Subclass of ParentClass for overriding featureclass ChildClass extends ParentClass {// Overriding parent class method (target_feature: overriding)@Overridepublic int calculate(int a, int b) {return a * b; // Overridden logic}}