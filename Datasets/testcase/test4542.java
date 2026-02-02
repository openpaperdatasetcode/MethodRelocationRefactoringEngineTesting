package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
sealed class SourceClass<T extends CharSequence> permits SubSourceClass {static class SourceStaticNested {}
public List<String> processTarget(TargetClass<T> target) {class LocalType {}LocalType local = new LocalType();
Supplier<Void> anonSupplier = new Supplier<Void>() {@Overridepublic Void get() {target.updateField(target.getField() + "_anon");return null;}};anonSupplier.get();
List<String> result = new ArrayList<>();for (int i = 0; i < 3; i++) {List<String> innerResult = TargetClass.TargetInner.abstractMethod(target, i);result.addAll(innerResult);variableCall(target);}return result;}
private void variableCall(TargetClass<T> target) {result.add(target.getField().toString());result.add(String.valueOf(target.innerClass.getInnerVal()));}}
non-sealed class SubSourceClass<T extends CharSequence> extends SourceClass<T> {}
protected class TargetClass<T extends CharSequence> implements Supplier<T> {private T field;TargetInner innerClass = new TargetInner();
public TargetClass(T field) {this.field = field;}
public void updateField(T newField) {this.field = newField;}
public T getField() {return field;}
@Overridepublic T get() {return field;}
class TargetInner {int innerVal = 5;
public int getInnerVal() {return innerVal;}
public static final List<String> abstractMethod(TargetClass<?> target, int index) {List<String> list = new ArrayList<>();list.add(target.getField().toString() + "_" + index);return list;}}}