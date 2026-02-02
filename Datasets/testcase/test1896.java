package test;
import java.lang.reflect.Constructor;import java.util.ArrayList;import java.util.List;
public class SourceClass<T> {// Static nested class with generic typepublic static class SourceStaticNested {
private U data;
public SourceStaticNested(U data) {this.data = data;}
public U getData() {return data;}}
// Member inner class with generic typepublic class SourceMemberInner<V> {private V value;
public SourceMemberInner(V value) {this.value = value;}
public V getValue() {return value;}}
// Varargs method returning TargetClass TypeTargetClass<T> varargsMethod(TargetClass<T>... targets) {if (targets.length == 0) {// Constructor invocationreturn new TargetClass<>("default");}
// Super constructor invocation in anonymous subclassTargetClass<T> result = new TargetClass<T>(targets[0].getBase()) {{super.additionalInit();}};
// Variable callfor (TargetClass<T> target : targets) {result.addData(target.getData());result.addInner(target.new Inner(target.getBase()));}
// Empty statement;
// Labeled statementprocessLoop:for (int i = 0; i < targets.length; i++) {if (i == 2) break processLoop;result.setCount(i);}
// Assert statementassert result.getCount() >= 0 : "Count cannot be negative";
// Used by reflectiontry {Constructor<TargetClass<T>.Inner> constructor =(Constructor<TargetClass<T>.Inner>) TargetClass.Inner.class.getConstructor(TargetClass.class, Object.class);TargetClass<T>.Inner reflectedInner = constructor.newInstance(result, "reflected");result.addInner(reflectedInner);} catch (Exception e) {// No new exception}
return result;}}
public class TargetClass<T> {private String base;private List<T> dataList = new ArrayList<>();private List<Inner> innerList = new ArrayList<>();private int count;
public TargetClass(String base) {this.base = base;}
protected void additionalInit() {this.count = 0;}
public String getBase() {return base;}
public List<T> getData() {return new ArrayList<>(dataList);}
public void addData(List<T> data) {this.dataList.addAll(data);}
public void addInner(Inner inner) {this.innerList.add(inner);}
public int getCount() {return count;}
public void setCount(int count) {this.count = count;}
// Member inner class with generic typepublic class Inner {private T value;
public Inner(T value) {this.value = value;}
public T getValue() {return value;}}}