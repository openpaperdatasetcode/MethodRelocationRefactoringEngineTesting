import java.util.Objects;
sealed class SourceGeneric<T extends Comparable<T>> permits SourceConcrete {protected T genericField;
public SourceGeneric(T field) {this.genericField = field;}
public static class StaticNested {
public U process(U input) {
return input;
}
}
protected int instanceMethod(TargetGeneric<T> target, int depth) {if (target == null) {throw new IllegalArgumentException("TargetGeneric cannot be null");}if (depth <= 0) {return 0;}
class LocalInner {int calculate(TargetGeneric<T> t) {variableCall(t);return t.getData().compareTo(genericField);}}
LocalInner local = new LocalInner();int currentResult = local.calculate(target);int recursiveResult = instanceMethod(target, depth - 1);
return currentResult + recursiveResult;}
private void variableCall(TargetGeneric<T> target) {target.setData(genericField);}}
final class SourceConcrete extends SourceGeneric<Integer> {public SourceConcrete(Integer field) {super(field);}}
class TargetGeneric<T> {private T data;
public void setData(T data) {this.data = data;}
public T getData() {return data;}}