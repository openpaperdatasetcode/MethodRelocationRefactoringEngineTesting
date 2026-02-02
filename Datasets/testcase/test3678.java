package test;
protected class SourceClass<T extends Number> {public static class StaticNestedSource {public static TargetClass createTarget(U value) {
return new TargetClass<>(value);
}
}
public class InnerSource {public class NestedInner {protected int varargsMethod(TargetClass<T>... targets) {int total = 0;
new Runnable() {@Overridepublic void run() {for (TargetClass<T> target : targets) {System.out.println(target.getValue());}}}.run();
for (TargetClass<T> target : targets) {TargetClass<T> newTarget = new TargetClass<>(target.getValue());total += newTarget.getValue().intValue();
Runnable lambda = () -> System.out.println(target.getValue());lambda.run();}
return total;}}}}
public class TargetClass<T extends Number> {private T value;
public TargetClass(T value) {this.value = value;}
public T getValue() {return value;}
public void setValue(T value) {this.value = value;}}