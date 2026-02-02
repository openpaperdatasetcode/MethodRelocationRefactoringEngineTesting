package test.refactor.movemethod;
import java.lang.reflect.Method;import java.util.function.Consumer;
// Interface for source_class implements featureinterface Processable<T> {TargetClass<T> process(TargetClass<T> target);}
// Parent class for super constructor invocationclass ParentBaseClass<T> {protected T parentField;
public ParentBaseClass(T parentField) {this.parentField = parentField;}}
// Others_class for overriding method featureabstract class OtherAbstractClass<T> {public abstract void otherMethod(T data);}
class OtherConcreteClass<T> extends OtherAbstractClass<T> {@Overridepublic void otherMethod(T data) {System.out.println("OverriddenOtherMethod: " + data);}}
// Source generic class (protected, same package, implements + permits + two static nested classes)protected sealed class SourceClass<T> extends ParentBaseClass<T> implements Processable<T> permits ConcreteSourceClass {// Private field for access_outer_privateprivate String outerPrivateField = "SourcePrivateValue";
// Feature: first static nested classpublic static class StaticNested1 {public static void staticMethod1(U data) {
System.out.println("StaticNested1: " + data);
}
}
// Feature: second static nested classpublic static class StaticNested2 {public static void staticMethod2(U data) {
System.out.println("StaticNested2: " + data);
}
}
// Super constructor invocationpublic SourceClass(T parentField) {super(parentField);}
// Member inner class (source_inner_rec: recursive inner structure)public class SourceInner {public class RecursiveInner {// Method to be refactored: instance, public, TargetClass return@Overridepublic TargetClass<T> process(TargetClass<T> targetParam) { // per_condition// Per_condition: contains target parameterif (targetParam == null) {throw new IllegalArgumentException("Target cannot be null");}
// Access_outer_privateString privateValue = SourceClass.this.outerPrivateField;
// Super constructor invocation (via inner class)class SuperConstructorHelper extends ParentBaseClass<T> {public SuperConstructorHelper(T data) {super(data); // Super constructor invocation}}SuperConstructorHelper superHelper = new SuperConstructorHelper(targetParam.getTargetField());
// Public ParenthesizedExpression (numbers:2, modifier:public, exp:ParenthesizedExpression)public final int expr1 = (10 + 20) * 2; // 1st ParenthesizedExpressionpublic final String expr2 = (privateValue + "_" + targetParam.getTargetField().toString()); // 2nd ParenthesizedExpression
// Constructor invocation + overriding method in parameter listOtherConcreteClass<T> other = new OtherConcreteClass<>();Consumer<T> consumer = data -> other.otherMethod((T) (data + "_constructorParam")); // overriding featureTargetClass<T>.TargetInner targetInner = targetParam.new TargetInner(consumer); // parameter list pos
// WhileStatement (private, source pos, obj.field x1)private int count = 0;while (count < 2) {String objField = targetParam.targetField; // obj.field 1System.out.println("WhileIteration " + count + ": " + objField);count++;}
// Expression statementtargetParam.updateField((T) (expr2 + "_updated"));StaticNested1.staticMethod1(privateValue);StaticNested2.staticMethod2(superHelper.parentField);
try {// Used_by_reflectionMethod innerMethod = TargetClass.TargetInner.class.getDeclaredMethod("processInner", Object.class);innerMethod.invoke(targetInner, privateValue);
// Variable call: target_inner usagetargetInner.processInner(expr1);
// Override_violation: attempt to override final method (simulated via reflection)try {Method finalMethod = TargetClass.class.getDeclaredMethod("finalMethod");finalMethod.setAccessible(true); // Violate access control} catch (SecurityException e) {// Handle override violationSystem.err.println("Override violation attempted: " + e.getMessage());}} catch (ReflectiveOperationException e) {// requires_try_catch: handle reflection exceptionsthrow new RuntimeException("Reflection failed", e);} catch (Exception e) {// no_new_exception: rethrow without wrappingthrow e;}
return targetParam; // TargetClass Type return}}}
// Delegate to recursive inner class method@Overridepublic TargetClass<T> process(TargetClass<T> target) {SourceInner inner = new SourceInner();SourceInner.RecursiveInner recursive = inner.new RecursiveInner();return recursive.process(target);}}
// Concrete subclass of SourceClass (permitted by sealed class)final class ConcreteSourceClass<T> extends SourceClass<T> {public ConcreteSourceClass(T parentField) {super(parentField);}}
// Target generic class (default modifier, no target_features)class TargetClass<T> {// Target field (per_condition)public T targetField;
public TargetClass(T targetField) {this.targetField = targetField;}
// Target_inner: member inner classpublic class TargetInner {private Consumer<T> consumer;
// Constructor with overriding method parameterpublic TargetInner(Consumer<T> consumer) {this.consumer = consumer;}
public void processInner(Object data) {System.out.println("TargetInnerProcess: " + data);consumer.accept(targetField);}}
public T getTargetField() {return targetField;}
public void updateField(T field) {this.targetField = field;}
// Final method for override_violationpublic final void finalMethod() {System.out.println("FinalMethod: " + targetField);}}
// Test classpublic class MoveMethodTest5319 {public static void main(String[] args) {SourceClass<String> source = new ConcreteSourceClass<>("ParentData");TargetClass<String> target = new TargetClass<>("TargetData");TargetClass<String> result = source.process(target);System.out.println("Refactored Target Field: " + result.targetField);}}