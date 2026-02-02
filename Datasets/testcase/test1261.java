package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.List;
// Source abstract class (default modifier, same package)abstract class SourceClass {// Target class field (per_condition: source contains target's field)private TargetClass targetField;
// Static nested class (source_class feature)static class SourceStaticNested {}
// Member inner class (host for refactored method: method_position: source_inner)class SourceInnerClass {// Overloading method 1 (method.type: overloading)public int compute(int num) {return num * 2;}
// Overloading method to be refactored (access_modifier: public, return_type: base type)public int compute(TargetClass param) {// NullPointerException (method.features)if (param == null) throw new NullPointerException("Target param is null");
// Protected InfixExpression (1 instance, method.features)protected int infixResult = param.getCount() + 10;
// Variable call (method.features)int nestedValue = new SourceStaticNested().hashCode();int varCallResult = infixResult * nestedValue;
// Expression statement (method.features)param.updateCount(varCallResult);
// Private generic method call in while loop (method.features)int i = 0;while (i < 3) {Object genericCall = this.<String>genericMethod("arg");i++;}
// Depends on inner class (method.features)LocalInner localInner = new LocalInner();varCallResult += localInner.getValue();
// Used by reflection (method.features)try {Method reflectMethod = SourceInnerClass.class.getMethod("compute", TargetClass.class);reflectMethod.invoke(this, param);} catch (Exception e) {// no_new_exception (method.features)}
return varCallResult;}
// Private generic method (method.features: generic)private <T> Object genericMethod(T arg) {return arg.toString();}
// Local inner class (source_class feature)class LocalInner {int getValue() { return 5; }}}}
// Target abstract class (public modifier, same package)public abstract class TargetClass {private int count = 0;
// Local inner class (target_class feature)public void hostLocalInner() {class TargetLocalInner {int process(int value) { return value * 3; }}}
// Target inner class to receive moved method (target class: target_inner)class TargetInner {}
// Helper methods for method logicpublic int getCount() { return count; }public void updateCount(int value) { this.count = value; }}