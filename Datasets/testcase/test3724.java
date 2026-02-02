import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;import java.util.function.Supplier;
protected class SourceGeneric<T extends Number> extends ParentGeneric<T> {public SourceGeneric(T value) {super(value);}
public static class StaticNested {
public U process(U input) {
return input;
}
}
void instanceMethod(TargetGeneric<T> target, int depth) throws ReflectiveOperationException {if (target == null) {throw new IllegalArgumentException("Target cannot be null");}
class LocalInner {void handleTarget(TargetGeneric<T> t) {variableCall(t.staticNested);}}new LocalInner().handleTarget(target);
TargetGeneric<T> newTarget = new TargetGeneric<>(this.value);Method nestedMethod = TargetGeneric.StaticNested.class.getMethod("append", Object.class);nestedMethod.invoke(newTarget.staticNested, "reflected_" + depth);
Supplier<List<String>> recursiveSupplier = () -> {try {return synchronizedRecursion(target, depth - 1);} catch (ReflectiveOperationException e) {return new ArrayList<>();}};List<String> result = recursiveSupplier.get();result.forEach(System.out::println);}
private synchronized List<String> synchronizedRecursion(TargetGeneric<T> target, int depth) throws ReflectiveOperationException {List<String> result = new ArrayList<>();if (depth <= 0) {return result;}
if (target.staticNested.getCount() < 5) {result.add("Depth: " + depth);result.addAll(this.synchronizedRecursion(target, depth - 1));}return result;}
private void variableCall(TargetGeneric.StaticNested<T> nested) {nested.increment();nested.setData(this.value);}}
class ParentGeneric<T> {protected T value;
public ParentGeneric(T value) {this.value = value;}}
protected class TargetGeneric<T> {public StaticNested<T> staticNested = new StaticNested<>();private T data;
public TargetGeneric(T data) {this.data = data;this.staticNested.setData(data);}
public static class StaticNested {
private U data;
private int count = 0;
public void setData(U data) {this.data = data;}
public U getData() {return data;}
public void increment() {count++;}
public int getCount() {return count;}
public String append(U input) {return input.toString() + "_appended";}}}