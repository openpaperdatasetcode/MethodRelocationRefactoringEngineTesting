package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;
// Source class: abstract, default modifier, same package, features[member inner class, local inner class]abstract class SourceClass {// Member inner class (source class feature)public class SourceMemberInner {private TargetClass targetData;
public TargetClass getTargetData() { // Accessor for inner classreturn targetData;}
public void setTargetData(TargetClass targetData) {this.targetData = targetData;}}
// Method to refactor: instance, default access, return TargetClass Type, source positionTargetClass refactorMethod(TargetClass targetParam) { // Pre-condition: contains target parameter// Super constructor invocation (source class inherits Object, explicit super() call)super();
// Local inner class (source class feature)class SourceLocalInner {public TargetClass processTarget(TargetClass target) {return target;}}
// Accessor feature: public, method_feature[1, inner_class, accessor, ClassName.methodName(arguments)], pos=collection operationsList<TargetClass> targetList = new ArrayList<>();TargetClass accessedTarget = SourceMemberInner.getTargetData(targetParam); // ClassName.methodName(arguments)targetList.add(accessedTarget);targetList.forEach(t -> t.process()); // Collection operation position
// Variable callSourceLocalInner localInner = new SourceLocalInner();TargetClass variableCallResult = localInner.processTarget(targetParam);
// Empty statement;
// Override violation: TargetClass has final method with same signature (simulated)return variableCallResult;}
// Static accessor method for SourceMemberInner (matches method_feature[inner_class, accessor])public static TargetClass getTargetData(TargetClass target) {return target;}}
// Target class: abstract, public modifier, target_feature[member inner class]public abstract class TargetClass {// Member inner class (target class feature)public class TargetMemberInner {public void doSomething() {}}
// Final method to cause override violation if moved (method feature: override_violation)public final void process() {}
// Accessor method (matches return_type: TargetClass Type)public TargetClass getTargetInstance() {return new TargetClass() {};}}
// Test class to verify pre-condition and functionalitypublic class MoveMethodTest5487 {public static void main(String[] args) {SourceClass source = new SourceClass() {};TargetClass target = new TargetClass() {};
// Pre-condition verification: method contains target parameterTargetClass result = source.refactorMethod(target);}}