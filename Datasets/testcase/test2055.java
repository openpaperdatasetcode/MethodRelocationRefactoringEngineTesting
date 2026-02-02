package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
abstract class SourceClass {static class StaticNested1 {public static Object staticMethod() {return new Object();}}static class StaticNested2 {}
protected List<String> methodToMove(TargetClass... targets) {List<String> result = new ArrayList<>();Object[] array = new Object[3];
for (int i = 0; i < array.length; i++) {TargetClass.StaticNested nested = new TargetClass.StaticNested();array[i] = nested;nested.instanceMethod(targets[i % targets.length]);}
try {Method method = SourceClass.class.getMethod("methodToMove", TargetClass[].class);method.invoke(null, (Object) targets);} catch (Exception e) {}
ArrayList rawList = new ArrayList();rawList.add(TargetClass.staticField);
return result;}
protected List<String> methodToMove(String... strs) {return new ArrayList<>();}}
protected class TargetClass {static int staticField;
static class StaticNested {void instanceMethod(TargetClass target) {SourceClass.StaticNested1.staticMethod();}}}