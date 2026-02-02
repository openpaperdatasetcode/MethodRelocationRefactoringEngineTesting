package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
/**
Default record source class with static nested and local inner class features*/record SourceRecord() {// Per_condition: source contains the field of the targetprivate TargetRecord targetField = new TargetRecord("target_data");
// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Source feature: local inner classpublic void sourceWithLocalInner() {class SourceLocalInnerClass {void localMethod() {}}new SourceLocalInnerClass().localMethod();}
/**
Varargs method to be refactored (protected access, return Object)
@param targetParam target record parameter (per_condition)
@param varargs varargs parameters
@return Object result*/protected Object refactorTargetMethod(TargetRecord targetParam, Object... varargs) {// Constructor invocation (target record constructor)TargetRecord newTarget = new TargetRecord("new_target_data");
// Variable callTargetRecord tempTarget = targetField;
// Access instance method (target class instance method)String methodResult = tempTarget.invokeAnonymousInner();
// VariableDeclarationStatement in same_package_target (pos: same_package_target)private String objFieldValue = tempTarget.data(); // obj.field (target record's component field)private int count = 1; // "1" in target_feature
// Property assignment position for abstract nested methodAbstractHelper helper = new ConcreteHelper();TargetRecord abstractResult = helper.abstractNestedMethod(tempTarget, 3);
// No new exception thrownreturn tempTarget;}
// Abstract helper class for nested abstract methodprotected abstract static class AbstractHelper {/**
Abstract nested method (type: abstract, modifier: protected, return_type: TargetClass Type)
@param target target record instance
@param num "3" in method_feature
@return TargetRecord instance
*/
protected abstract TargetRecord abstractNestedMethod(TargetRecord target, int num);
}
// Concrete implementation of abstract helper classprivate static class ConcreteHelper extends AbstractHelper {@Overrideprotected TargetRecord abstractNestedMethod(TargetRecord target, int num) {int value = 3; // "3" in method_featuresuper.toString(); // super.methodName()return target; // "others_class" + "abstract" features}}}
/**
Public target record class with anonymous inner class (target_feature: target_inner)
*/
public record TargetRecord(String data) {
// Target feature: anonymous inner class (target_inner)
public String invokeAnonymousInner() {
Runnable anonymous = new Runnable() {
@Override
public void run() {
System.out.println("Target anonymous inner class: " + data);
}
};
anonymous.run();
return data;
}
}