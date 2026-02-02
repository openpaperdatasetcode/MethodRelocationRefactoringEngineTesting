package test;
abstract class SourceClass {protected int outerProtected;
public class SourceInner {public class InnerRec {private void overloadMethod(TargetClass<String>.Inner target) {if (target == null) return;
do {outerProtected = target.field;} while (outerProtected < 5);
TargetClass<Integer> tc = new TargetClass<>();TargetClass.IntegerNested nested = new TargetClass.IntegerNested();}
private void overloadMethod(int value) {}}}}
private class TargetClass<T> {public class Inner {public T field;
Object assignCall() {TargetClass<String> target = new TargetClass<>();return target.new Inner().overloadMethod(target.new Inner());}}
public static class IntegerNested {}
private void overloadMethod(Inner inner) {}}