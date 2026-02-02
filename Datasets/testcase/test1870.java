package test;
import java.util.ArrayList;import java.util.List;import java.util.stream.Collectors;
protected class SourceClass {// Static nested classpublic static class SourceStatic {public static int STATIC_FIELD = 100;}
// Member inner classpublic class SourceInner {public void instanceMethod(TargetClass.Inner targetInner) {// Constructor invocationTargetClass.StaticNested nested = new TargetClass.StaticNested(targetInner.value);
// Variable callint base = targetInner.count;base += nested.calculate();
// Lambda expression: () -> System.out.println(this.value)Runnable printer = () -> System.out.println(targetInner.value);printer.run();
// With boundsclass BoundedHandler<T extends Number & Comparable<T>> {void handle(T num) {targetInner.setValue(num.toString());}}new BoundedHandler<Integer>().handle(5);
// Depends on static fieldtargetInner.count += SourceStatic.STATIC_FIELD;
// Collection operations with 2 sub_class normal methods (super call)List<TargetClass.Inner> list = new ArrayList<>();list.add(targetInner);list.add(new TargetClass.Inner("second"));
List<Integer> results = list.stream().map(inner -> TargetSubclass.processOne(inner)).collect(Collectors.toList());
int total = list.stream().mapToInt(inner -> TargetSubclass.processTwo(inner, 2)).sum();
results.add(total);}}}
public class TargetClass {// Static nested classpublic static class StaticNested {private String data;
public StaticNested(String data) {this.data = data;}
public int calculate() {return data.length() * 2;}}
// Member inner classpublic class Inner {public String value;public int count = 0;
public Inner(String value) {this.value = value;}
public void setValue(String val) {this.value = val;}}}
public class TargetSubclass extends TargetClass {public static int processOne(Inner inner) {return super.new StaticNested(inner.value).calculate();}
public static int processTwo(Inner inner, int factor) {return processOne(inner) * factor;}}
