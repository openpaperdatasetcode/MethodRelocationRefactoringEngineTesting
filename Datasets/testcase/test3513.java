package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
protected class SourceClass<T> {public class InnerSource {public class DeepInner {public void instanceMethod(TargetClass<T> target) {// Three accessor method invocations in collection operationsList<Object> values = new ArrayList<>();values.add(new SourceClass<T>().getTargetField(target));values.add(new SourceClass<T>().getInnerField(target));values.add(new SourceClass<T>().getFormattedValue(target));
// For statementfor (int i = 0; i < values.size(); i++) {System.out.println("Value " + (i + 1) + ": " + values.get(i));}
// First anonymous inner classnew Runnable() {@Overridepublic void run() {target.getInner().updateValue(target.getData());}}.run();
// Second anonymous inner classnew Runnable() {@Overridepublic void run() {try {Method accessorMethod = TargetClass.Inner.class.getMethod("getValue");Object reflectedValue = accessorMethod.invoke(target.getInner());System.out.println("Reflected value: " + reflectedValue);} catch (Exception e) {e.printStackTrace();}}}.run();}}}
// Three accessor methodspublic Object getTargetField(TargetClass<T> target) {return target.getData();}
public Object getInnerField(TargetClass<T> target) {return target.getInner().getValue();}
public Object getFormattedValue(TargetClass<T> target) {return target.getInner().getValue().toString() + "_formatted";}
public void triggerRefactor(TargetClass<T> target) {new InnerSource().new DeepInner().instanceMethod(target);}}
private class TargetClass<T> {private T data;
public TargetClass(T data) {this.data = data;}
public T getData() {return data;}
public Inner getInner() {return new Inner();}
public class Inner {private T value;
public T getValue() {return value;}
public void updateValue(T newValue) {this.value = newValue;}}}
