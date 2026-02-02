package test;
import java.util.function.Supplier;
class ParentClass {}
abstract class SourceClass extends ParentClass {final void process(TargetClass.Inner... inners) {protected int a = 2, b = 2;
for (TargetClass.Inner inner : inners) {a += inner.count;b += inner.getNum();
Supplier<String> supplier = inner::convert;System.out.println(supplier.get());}}
@Overridepublic String toString() {return "SourceClass";}}
/**
Target class with static nested component*/protected class TargetClass {static class Inner {int count;
int getNum() {return count * 2;}
String convert() {return String.valueOf(count);}}}