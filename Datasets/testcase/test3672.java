package test;
import java.util.ArrayList;import java.util.List;
private class SourceClass<T> {private T sourceField;
public final TargetClass<T> instanceMethod(TargetClass<T> target) {TargetClass<T> newTarget = new TargetClass<>(target.getData());newTarget.setValue(sourceField);
newTarget.staticField.add(sourceField);
ParentGenericClass<T> parent = new ParentGenericClass<>();TargetClass<T> processedTarget = parent.processTarget(newTarget);
return processedTarget;}}
public class TargetClass<T> {public static List<Object> staticField = new ArrayList<>();private T data;private T value;
public TargetClass(T data) {super();this.data = data;}
public T getData() {return data;}
public void setValue(T value) {this.value = value;}
public T getValue() {return value;}
public void process() {class LocalInner {void update(T val) {data = val;}}new LocalInner().update(value);}}
class ParentGenericClass<T> {TargetClass<T> processTarget(TargetClass<T> target) {target.process();return target;}}
