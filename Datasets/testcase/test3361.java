package test;
public abstract class SourceClass<S> {public TargetClass<Integer> targetField;
{Runnable r1 = new Runnable() {public void run() {}};Runnable r2 = new Runnable() {public void run() {}};}
@MyAnnotationpublic TargetClass<S> varargsMethod(String... strs) {super.toString();int val = targetField.instanceField;for (String s : strs) {System.out.println(s);}return targetField;}}
public abstract class TargetClass<T> {public int instanceField;
public static class NestedClass {
public TargetClass targetInnerRec;
}
}
@interface MyAnnotation {}