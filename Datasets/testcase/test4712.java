package test;
import java.util.function.Supplier;
protected class SourceClass<T> implements Supplier<T> {private TargetClass<T> targetField = new TargetClass<>();
class SourceInner {public Object getTargetData() {targetField.count++;; // EmptyStatementString data = targetField.prefix + targetField.count;
targetField.setValue((T) data);return targetField.getValue();}}
@Overridepublic T get() {Runnable anon1 = new Runnable() {@Overridepublic void run() {Object result = new SourceInner().getTargetData();}};anon1.run();
Supplier<Object> anon2 = new Supplier<>() {@Overridepublic Object get() {return new SourceInner().getTargetData();}};return (T) anon2.get();}
// Override violation: No superclass method to override intentionallypublic String toString() {return "SourceClass";}}
/**
TargetClass - generic class with inheritance and interface implementation
@param <T> Type of value stored in TargetClass*/final class TargetClass<T> extends SuperClass implements Cloneable {String prefix = "data-";int count = 0;private T value;
public void setValue(T value) {this.value = value;}
public T getValue() {return value;}
@Overridepublic Object clone() throws CloneNotSupportedException {return super.clone();}}
class SuperClass {protected String baseField = "base-data";}