package test;
import java.util.ArrayList;import java.util.List;
private class SourceClass<T extends Comparable<T>> {private T sourceField;
public static class StaticNestedSource {public static <U extends Comparable> TargetClass.StaticNestedTarget createNestedTarget(U value) {
return new TargetClass.StaticNestedTarget<>(value);
}
}
public class InnerSource {public T getInnerData() {return sourceField;}}
@Deprecatedprotected void instanceMethod(TargetClass<T> target) throws IllegalArgumentException {class LocalType {private List<String> privateInstanceMethod(TargetClass<T> t) {super.toString();List<String> result = new ArrayList<>();result.add(t.getTargetField().toString());return result;}}
LocalType local = new LocalType();TargetClass.StaticNestedTarget<T> nested = new TargetClass.StaticNestedTarget<>(sourceField);
T[] dataArray = (T[]) new Comparable[2];dataArray[0] = sourceField;dataArray[1] = target.getTargetField();
target.setTargetField(dataArray[0]);expressionStatement: nested.setValue(dataArray[1]);
List<String> methodResult = local.privateInstanceMethod(target);System.out.println(methodResult);
if (dataArray[0] == null || dataArray[1] == null) {throw new IllegalArgumentException("Array elements cannot be null");}}}
sealed class TargetClass<T extends Comparable<T>> permits SubTargetClass<T> {private T targetField;
public static class StaticNestedTarget<U extends Comparable> {
private U nestedField;
public StaticNestedTarget(U value) {this.nestedField = value;}
public void setValue(U value) {this.nestedField = value;}
public U getValue() {return nestedField;}}
public T getTargetField() {return targetField;}
public void setTargetField(T targetField) {this.targetField = targetField;}}
final class SubTargetClass<T extends Comparable<T>> extends TargetClass<T> {}
