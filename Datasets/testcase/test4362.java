package test;
import java.util.List;
abstract class SourceClass<T> {private TargetClass<String> targetInstance;
public final void moveMethod(int baseTypeParam) {TargetClass.InnerTarget inner = targetInstance.new InnerTarget();int targetField = targetInstance.targetField;
List<Integer> numList = List.of(1, 2, 3);for (int num : numList) {int sum = baseTypeParam + num + targetField;inner.useValue(sum);}}}
public class TargetClass {
int targetField;
class InnerTarget {void useValue(int value) {// Use value without new exception}}}