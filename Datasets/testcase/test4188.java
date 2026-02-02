package test;
import java.lang.reflect.Method;import java.lang.reflect.InvocationTargetException;
private class SourceClass {static class SourceStaticNested1 {}static class SourceStaticNested2 {}
protected TargetClass varargsMethod(TargetClass... targets) {if (targets.length == 0) {return new TargetClass();}
TargetClass firstTarget = targets[0];String varCall = firstTarget.targetField;
TargetClass newTarget = new TargetClass();newTarget.targetField = varCall + "_copied";return newTarget;}
void invokeByReflection() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {Method method = SourceClass.class.getDeclaredMethod("varargsMethod", TargetClass[].class);method.setAccessible(true);TargetClass result = (TargetClass) method.invoke(new SourceClass(), (Object) new TargetClass[]{new TargetClass()});}}
protected class TargetClass {String targetField = "targetInstanceVal";
void methodWithLocalClass() {class TargetLocalInner {}}}