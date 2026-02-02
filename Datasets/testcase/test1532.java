package test;
public class SourceClass<T> {protected TargetClass<Integer> targetField;protected int outerProtectedField;
protected int moveMethod(int value) {outerProtectedField++;this(5);int result = 0;LocalInnerClass local = new LocalInnerClass();result += local.getValue();
for (int num : targetField.nestedStaticClass.getNumbers()) {result += num;}
if (value <= 0) {return targetField.nestedStaticClass;} else {try {Class<?> cls = Class.forName("test.SourceClass");result += (int) cls.getMethod("moveMethod", int.class).invoke(this, value - 1);} catch (Exception e) {return 0;}}return result;}
public SourceClass(int i) {outerProtectedField = i;}
public static class NestedStaticClass<S> {public S data;}
private class LocalInnerClass {public int getValue() {return outerProtectedField;}}}
class TargetClass {
public StaticNestedClass nestedStaticClass = new StaticNestedClass();
public static class StaticNestedClass {private int[] numbers = {1, 2, 3};
public int[] getNumbers() {return numbers;}}}