import java.util.ArrayList;import java.util.List;import java.util.function.Function;
// Parent class for TargetClass to support super constructor invocationabstract class ParentTarget {protected String parentField;
public ParentTarget(String parentField) {this.parentField = parentField;}
// Generic method for source's generic method featureprotected <T> void parentGenericMethod(T data) {System.out.println("Parent generic process: " + data);}}
// TargetClass: abstract, public, with anonymous inner class (meets target_class specs)public abstract class TargetClass extends ParentTarget {protected List<String> targetDataList;
// Super constructor invocation (called by subclass)public TargetClass(String parentField) {super(parentField);// Anonymous inner class (target_feature)Runnable listIniter = new Runnable() {@Overridepublic void run() {targetDataList = new ArrayList<>();targetDataList.add("target_init_" + parentField);}};listIniter.run();}
// Abstract method to be overriddenpublic abstract Object processData(Object data);
// Overloaded method (for overload_exist feature)public abstract Object processData(Object data, int count);
// Method for variable callpublic void addToDataList(String data) {targetDataList.add(data);}
// Method for method referencepublic String formatData(String data) {return data.toUpperCase();}}
// Concrete subclass of TargetClass (for constructor invocation)class ConcreteTarget extends TargetClass {public ConcreteTarget(String parentField) {super(parentField);}
@Overridepublic Object processData(Object data) {addToDataList(data.toString());return targetDataList;}
@Overridepublic Object processData(Object data, int count) {for (int i = 0; i < count; i++) {addToDataList(data + "_" + i);}return targetDataList;}}
// SourceClass: abstract, package-private, same package as target, with static nested + local inner class (meets source_class specs)abstract class SourceClass {// Source contains target field (per_condition)protected TargetClass sourceTargetField;
// Static nested class (source_feature)public static class SourceStaticNested {// Overriding method (meets method specs: overriding, Object return, strictfp, source_inner_rec)@Overridepublic strictfp Object clone() throws CloneNotSupportedException {return super.clone();}
// Generic method: meets method_feature (generic, protected, parent_class, ClassName.methodName, pos=if/else)protected <T> void genericProcess(TargetClass target, T data) {// Constructor invocation (method_feature)TargetClass newTarget = new ConcreteTarget("source_parent_data");
// If/else conditions (pos=if/else conditions)if (data instanceof String) {// ClassName.methodName(arguments): call parent class generic methodParentTarget.parentGenericMethod(target, data);} else {ParentTarget.parentGenericMethod(newTarget, data);}
// Method reference (numbers=1, default modifier, exp=MethodReference)Function<String, String> formatRef = TargetClass::formatData;String formatted = formatRef.apply(data.toString());
// This.methodName(arguments)this.processWithOverload(newTarget, formatted, 2);
// Variable call (method_feature)newTarget.addToDataList(formatted);}
// Overloaded method (for overload_exist feature)private void processWithOverload(TargetClass target, String data) {target.processData(data);}
private void processWithOverload(TargetClass target, String data, int count) {target.processData(data, count);}}
// Abstract method to enforce subclass implementationpublic abstract void initTarget();
// Method with local inner class (source_feature)public void useLocalInner(TargetClass target) {// Local inner class (source_feature)class LocalProcessor {void handleTarget() {SourceStaticNested nested = new SourceStaticNested();// Call generic method (no_new_exception)nested.genericProcess(target, "local_inner_data");}}new LocalProcessor().handleTarget();}}
// Concrete subclass of SourceClassclass ConcreteSource extends SourceClass {@Overridepublic void initTarget() {// Constructor invocation + super constructor invocation (indirect)sourceTargetField = new ConcreteTarget("concrete_parent_data");}}
// Test entrypublic class SourceTest {public static void main(String[] args) {ConcreteSource source = new ConcreteSource();source.initTarget();
// Trigger flow (no_new_exception)source.useLocalInner(source.sourceTargetField);
// Verify target dataList<String> result = (List<String>) source.sourceTargetField.processData("test_data");System.out.println("Final target data: " + result);}}