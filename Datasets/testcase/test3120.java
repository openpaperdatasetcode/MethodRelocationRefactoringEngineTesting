package test;
import java.lang.reflect.Method;
abstract class TargetClass {String targetField;
public void instanceMethod() {} // Instance method to access}
abstract class SourceClass {class Inner1 {}class Inner2 {}
// Overloading method 1 (with bounds)public <T extends TargetClass> TargetClass methodToMove(T target) {// Constructor invocationInner1 inner1 = new Inner1();Inner2 inner2 = new Inner2();
// Variable callString var = target.targetField;
// Access instance methodtarget.instanceMethod();
// Used by reflectiontry {Method method = TargetClass.class.getMethod("instanceMethod");method.invoke(target);} catch (Exception e) {}
return target;}
// Overloading method 2 (different parameter type, with bounds)public <T extends TargetClass & Runnable> TargetClass methodToMove(T target, String arg) {// Variable calltarget.targetField = arg;
// Access instance methodtarget.instanceMethod();target.run();
return target;}}
// Implementation class for bounded typeclass TargetImpl extends TargetClass implements Runnable {@Overridepublic void run() {}}