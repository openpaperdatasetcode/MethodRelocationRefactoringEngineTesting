package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
interface GenericInterface<T> {Object process(TargetClass<T> target);}
public class SourceClass<T> implements GenericInterface<T> {private Object privateMethod(TargetClass<T> target) {return target.getData();}
public static class StaticNested {
public U convert(U value) {
return value;
}
}
@Overrideprotected Object process(TargetClass<T> target) {// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {target.setData((T) "initialized");}};initializer.run();
// Constructor invocationTargetClass<T> newTarget = new TargetClass<>();// Super constructor invocationclass SubTarget<T> extends TargetClass<T> {SubTarget(T data) {super();this.setData(data);}}SubTarget<T> subTarget = new SubTarget<>((T) "sub_data");
// Private instance method reference in array initialization (1 occurrence)Object[] array = { SourceClass.this::privateMethod };
// Overloaded methods existprocessOverload(target, 1);processOverload(target, "string");
// Switch case with target's final static method referenceswitch (target.getType()) {case 1:Object result1 = TargetClass::staticProcess;break;case 2:Object result2 = TargetClass::staticProcess;break;default:break;}
// Variable call - access target's fieldList<T> items = new ArrayList<>();items.add(target.getData());
// Used by reflectiontry {Method method = TargetClass.class.getMethod("staticProcess", Object.class);method.invoke(null, target.getData());} catch (Exception e) {// no new exception}
return newTarget;}
// Overloaded methodsprivate void processOverload(TargetClass<T> target, int code) {target.setType(code);}
private void processOverload(TargetClass<T> target, String code) {target.setType(Integer.parseInt(code));}}
private class TargetClass<T> {private T data;private int type;
public TargetClass() {// Local inner classclass DataHolder {T getDefault() {return (T) "default";}}this.data = new DataHolder().getDefault();}
public T getData() {return data;}
public void setData(T data) {this.data = data;}
public int getType() {return type;}
public void setType(int type) {this.type = type;}
// Final static method for method referencepublic static final <T> Object staticProcess(T data) {return data.toString();}}