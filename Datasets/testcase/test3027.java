import java.io.IOException;import java.util.function.IntFunction;
class ParentSource {ParentSource() {}}
private class SourceClass<T extends Number> extends ParentSource {private TargetClass targetField = new TargetClass();private int outerPrivate = 100;
int process(T... params) throws IOException {super();TargetClass.StaticNested nested = new TargetClass.StaticNested();
IntFunction<Integer> func1 = SubClass::genericMethod1;IntFunction<Integer> func2 = SubClass::genericMethod2;IntFunction<Integer> func3 = SubClass::genericMethod3;
Integer[] array = {func1.apply(1),func2.apply(2),func3.apply(3)};
int sum = 0;for (T param : params) {sum += param.intValue();sum += outerPrivate;sum += targetField.instanceField;sum += nested.getStaticField();}
return this;}}
class ParentTarget {}
public class TargetClass extends ParentTarget {int instanceField = 50;
static class StaticNested {static int staticField = 30;int getStaticField() { return staticField; }}}
class SubClass extends TargetClass {static int genericMethod1(int num) { return num * 2; }static int genericMethod2(int num) { return num * 3; }static int genericMethod3(int num) { return num * 4; }}