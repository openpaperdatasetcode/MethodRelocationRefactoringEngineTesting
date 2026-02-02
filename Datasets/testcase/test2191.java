package test;
import java.sql.SQLException;import java.util.function.Function;
class SourceClass<T> {static class StaticNested {}
class MemberInner {}
/**
Abstract method to process TargetClass
*/
public abstract TargetClass<T> process(TargetClass<T> target) throws SQLException;
{// Implementation structure for abstract method featuresFunction<String, Integer> func = String::length;int num = 2;
do {TargetClass<?> target = new TargetClass<>();if (target.super.field == 1) {break;}} while (false);
for (int i = 0; i < 5; i++) {TargetClass.Inner inner = new TargetClass<>().new Inner();inner.value = inner.createInstance().super.method();}}}
/**
Javadoc for TargetClass generic class*/public class TargetClass<T> extends SuperClass {class Inner {String value;
Inner createInstance() {return new Inner() {};}}}
class SuperClass {int field;
String method() {return "super";}}