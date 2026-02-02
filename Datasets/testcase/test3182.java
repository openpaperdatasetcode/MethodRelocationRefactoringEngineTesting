package test;
import java.lang.reflect.Method;import java.util.function.IntSupplier;
sealed class SourceClass<T> permits SourceSubClass<T> {private T privateField;
// Static nested class (source feature)public static class SourceStaticNested {}
public class SourceInner {// Source inner recursive class (method_position: source_inner_rec)public class SourceInnerRec {// Varargs method (protected access modifier, returns TargetClass Type)protected TargetClass<T> varargsMethod(TargetClass<T>... targetParams) {// Access outer private fieldT outerPrivate = SourceClass.this.privateField;
// Uses outer thisSourceClass.this.helperMethod();
TargetClass<T> result = null;for (TargetClass<T> target : targetParams) {result = target;
// This.methodName(arguments)this.innerHelper();
// Variable calltarget.targetMethod(outerPrivate);TargetClass.TargetStaticNested.staticMethod();
// Call method (sub_class, public, overloading, super.methodName(), pos: functional interfaces)TargetSubClass<T> sub = new TargetSubClass<>();IntSupplier supplier = () -> sub.overloadedMethod(10);int callResult = supplier.get();}
// Used by reflectiontry {Method method = TargetClass.class.getMethod("targetMethod", Object.class);method.invoke(result, outerPrivate);} catch (Exception e) {// No new exception}
return result;}
private void innerHelper() {}}}
private void helperMethod() {}
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public void localMethod() {}}new SourceLocalInner().localMethod();}}
// Permitted subclass for sealed SourceClassnon-sealed class SourceSubClass<T> extends SourceClass<T> {}
// Target parent class (for target's extends feature)class TargetParentClass<T> {public void parentMethod() {}public void parentMethod(int param) {}}
// Target class (public modifier, extends + static nested class)public class TargetClass<T> extends TargetParentClass<T> {public void targetMethod(T data) {}
// Static nested class (target_feature)public static class TargetStaticNested {public static void staticMethod() {}}}
// Target sub class (for call_method)class TargetSubClass<T> extends TargetClass<T> {// Overloading methodspublic int overloadedMethod() {super.parentMethod();return 0;}
public int overloadedMethod(int param) {super.parentMethod(param);return param;}}