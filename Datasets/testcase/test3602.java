package test;
public class SourceClass<T> {private TargetClass<Integer> target = new TargetClass<>();
public void recursiveMethod(int num, String... args) {if (num <= 0) {return;}TargetClass.NestedClass nc = new TargetClass.NestedClass();nc.value = callParentMethod(args);
Runnable r = () -> System.out.println(num);r.run();
InnerClass ic = new InnerClass();int val = ic.calculate(num);
target.process(num);recursiveMethod(num - 1, args);}
public void recursiveMethod(String str) {System.out.println(str);}
protected String callParentMethod(String... args) {return ParentClass.format(args);}
public class InnerClass {public int calculate(int x) {return x * 2;}}
{new Runnable() {public void run() {recursiveMethod(5, "init");}}.run();}}
private class TargetClass<V> {static class NestedClass {int value;}
public void process(V v) {System.out.println(v);}}
class ParentClass {static String format(String... parts) {return String.join("", parts);}}