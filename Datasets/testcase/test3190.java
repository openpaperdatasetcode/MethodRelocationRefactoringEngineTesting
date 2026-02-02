package test;
import java.lang.reflect.Method;
public class SourceClass {// Static nested class (source feature)public static class SourceStaticNested {}
// Member inner class (source feature) + method_position: source_innerpublic class SourceInner extends OtherClass {// Varargs method (protected access modifier, returns void)protected void varargsMethod(TargetClass... targetParams) {// NullPointerExceptionif (targetParams == null) {throw new NullPointerException("Varargs parameter cannot be null");}
for (TargetClass target : targetParams) {// Constructor invocationTargetClass.TargetInner inner = target.new TargetInner();
// ThrowStatement (public, target_feature: super.field x1, pos: source)if (target.getParentField() == null) {public IllegalArgumentException ex = new IllegalArgumentException("Super field is null");throw ex;}
// Variable calltarget.targetMethod();inner.innerMethod();
// Override violation: target's anonymous inner class method without @OverrideRunnable overrideAnon = new Runnable() {public void run() {}};
// Used by reflectiontry {Method method = TargetClass.class.getMethod("targetMethod");method.invoke(target);} catch (Exception e) {// No new exception}
// Call method (source, synchronized, normal, new ClassName().method(), pos: for)for (int i = 0; i < 1; i++) {synchronized (this) {new SourceInner().synchronizedCallMethod();}}}}
// Overriding method (final, 1, others_class, overriding, this.methodName(arguments), pos: exception throwing statements)@Overridepublic final int overridingMethod() {try {this.varargsMethod(new TargetClass());} catch (NullPointerException e) {throw e;}return 0;}
// Synchronized call method (for call_method)public synchronized void synchronizedCallMethod() {}}}
// Other class for overriding method featureclass OtherClass {public int overridingMethod() {return 0;}}
// Target parent class for super.fieldclass TargetParentClass {protected String parentField;
public String getParentField() {return parentField;}}
// Target class (default modifier, anonymous inner class)class TargetClass extends TargetParentClass {public void targetMethod() {}
// Member inner classpublic class TargetInner {public void innerMethod() {}}
// Anonymous inner class (target_feature)public void createAnonymous() {Runnable anon = new Runnable() {@Overridepublic void run() {}};anon.run();}}