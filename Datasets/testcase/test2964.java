package test;
protected class SourceClass {protected int outerProtectedField = 10;
public class InnerA {public class InnerRecursive {public InnerRecursive() {this.initialize(outerProtectedField);}
private Object initialize(int initValue) {return initValue * 2;}
@SuppressWarnings("unchecked")protected <T> TargetClass<T> process(TargetClass<T>... targets) {TargetClass<T> target = targets.length > 0 ? targets[0] : new TargetClass<>(outerProtectedField);TargetClass<T>.StaticNested targetStatic = new TargetClass.StaticNested<>();
class LocalType {T wrapValue(T value) {return value;}}LocalType wrapper = new LocalType();
targetStatic.setValue(wrapper.wrapValue(target.targetField));target.setStaticNested(targetStatic);
SubTargetClass<T> subTarget = new SubTargetClass<>();int result = subTarget.calculate(target, t -> t.targetField.hashCode() + outerProtectedField);
target.update(result);return target;}}}
public class InnerB {public int getInnerValue() {return outerProtectedField + 5;}}}
protected class TargetClass<T> extends ParentClass<T> {public T targetField;private StaticNested<T> staticNested;
public TargetClass(int initValue) {this.targetField = (T) Integer.valueOf(initValue);}
public static class StaticNested<T> {private T value;
public void setValue(T value) {this.value = value;}
public T getValue() {return value;}}
public void setStaticNested(StaticNested<T> staticNested) {this.staticNested = staticNested;}
public void update(int num) {this.targetField = (T) Integer.valueOf(((Number) targetField).intValue() + num);}}
protected class ParentClass<T> {public T getParentValue() {return null;}}
protected class SubTargetClass<T> extends TargetClass<T> {public SubTargetClass() {super(0);}
protected int calculate(TargetClass<T> target, java.util.function.Function<TargetClass<T>, Integer> func) {return func.apply(target);}}