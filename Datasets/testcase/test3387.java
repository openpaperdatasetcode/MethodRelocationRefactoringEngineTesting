package test;
import java.util.function.ToIntFunction;
public class SourceClass implements Runnable {private TargetClass targetField = new TargetClass();
// Member inner class (source feature)public class SourceMemberInner {}
// Anonymous inner class (source feature){Runnable anonymous = new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();inner.instanceMethod();}};}
@Overridepublic void run() {}
// Source inner class (method_position: source_inner)private class SourceInner {// Instance method (method type) returning TargetClass typeprivate TargetClass instanceMethod() {// NullPointerExceptionif (targetField == null) {throw new NullPointerException("Target field cannot be null");}
// ForStatement (private, target_feature: obj.field x1, pos: inner class)privateFor:for (int i = 0; i < 5; i++) {if (targetField.field == i) {break privateFor;}}
// With bounds: target's static nested class with type boundsTargetClass.StaticNested<? extends Number> boundedNested = new TargetClass.StaticNested<>();
// Expression statement + variable callboundedNested.variableCall();targetField.targetMethod();
// Overload exist: call overloaded methods of target's static nested classboundedNested.overloadedMethod();boundedNested.overloadedMethod(10);
// Call method (sub_class, public, target_feature: normal, ClassName::methodName, pos: do-while)ToIntFunction<TargetSubClass> func = TargetSubClass::subClassMethod;TargetSubClass subClass = new TargetSubClass();int result;do {result = func.applyAsInt(subClass);} while (result < 5);
return targetField;}}}
// Target class (protected modifier) with extends/implements/static nested classprotected class TargetClass extends TargetParentClass implements TargetInterface {public int field = 3;
// Static nested class (target feature)public static class StaticNested<T extends Number> {public void variableCall() {}public void overloadedMethod() {}public void overloadedMethod(int param) {}}
public void targetMethod() {}}
// Target parent class (for target's extends feature)class TargetParentClass {}
// Target interface (for target's implements feature)interface TargetInterface {}
// Target sub class (for call_method type: sub_class)class TargetSubClass extends TargetClass {public int subClassMethod() {return 3;}}