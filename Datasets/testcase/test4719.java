package test;
import java.util.function.Supplier;
protected class SourceClass<T> {private TargetClass<T> targetField = new TargetClass<>();
class FirstSourceInner {void initTarget() {targetField.setValue((T) "init-value");}}
class SecondSourceInner {void useProcess() {Object result = process(targetField.new TargetInnerRec(1), "arg1", "arg2");}}
protected Object process(TargetClass<T>.TargetInnerRec innerRec, T... values) {super();int i = 0;do {TargetClass<T> staticResult = OtherClass.createTarget();innerRec.setData(staticResult.getValue());i++;} while (i < values.length);
switch (values.length) {case 1:innerRec.setData(values[0]);break;case 2:innerRec.setData((T) (values[0].toString() + values[1].toString()));break;default:innerRec.setData((T) "default");}
String fieldVal = innerRec.data;return fieldVal;}
void callInArrayInit() {FirstSourceInner inner1 = new FirstSourceInner();SecondSourceInner inner2 = new SecondSourceInner();Runnable[] tasks = {() -> inner1.initTarget(),() -> inner2.useProcess()};tasks[0].run();}}
sealed class TargetClass<T> permits SubTargetClass {private T value;
public TargetClass() {Supplier<T> anonSupplier = new Supplier<>() {@Overridepublic T get() {return value;}};anonSupplier.get();}
record TargetInnerRec(int seq) {String data;
void setData(T data) {this.data = data.toString();}}
public void setValue(T value) {this.value = value;}
public T getValue() {return value;}}
final class SubTargetClass<T> extends TargetClass<T> {}
class OtherClass {public static <T> TargetClass<T> createTarget() {return new TargetClass<>();}}