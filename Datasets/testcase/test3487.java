package test;
final class SourceClass<T> {private TargetClass<T> targetField = new TargetClass<>() {};
static class StaticNested1 {static class InnerRec {public int moveMethod(SourceClass<?> source) {int sum = 0;for (T item : source.targetField.getItems()) {sum += item.hashCode();}
{TargetClass<?> targetInstance = source.thisInstanceMethod(source.targetField);source.variableCall(targetInstance);}
new TargetClass<T>() {{super();}};
return sum + SourceClass.this.targetField.getCount();}}}
static class StaticNested2 {}
private TargetClass<T> thisInstanceMethod(TargetClass<T> target) {return target;}
private void variableCall(TargetClass<?> target) {target.localInnerAction();}}
abstract class TargetClass<T> {public int moveMethod(SourceClass<?> source) {return 0;}
public List<T> getItems() {return new ArrayList<>();}
public int getCount() {return 0;}
public void localInnerAction() {class LocalInner {void useTargetField() {}}new LocalInner().useTargetField();}}