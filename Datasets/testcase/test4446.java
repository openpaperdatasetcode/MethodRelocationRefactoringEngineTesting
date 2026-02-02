package test;
import java.lang.reflect.Method;
interface Processor<T> {void process(T data);}
private class SourceClass<T> implements Processor<T> {class InnerRec {/**
Recursive method to process TargetClass instances
@param target TargetClass instance
@param depth Recursion depth*/@SuppressWarnings("unchecked")private void recursiveMethod(TargetClass<T> target, int depth) {class LocalType {}LocalType local = new LocalType();
try {Method method = TargetClass.class.getMethod("getValue");Object value = method.invoke(target);} catch (Exception e) {}
if (depth <= 0) {return;}
Object result = this.handleVarargs(target, "param1");T var = target.dataField;recursiveMethod(target, depth - 1);}
public Object handleVarargs(TargetClass<T> target, String... args) {return target.dataField;}
{SourceClass<T> outer = new SourceClass<>();outer.new InnerRec().callMethod(new TargetClass<>());}
protected void callMethod(TargetClass<T> target) {TargetClass.Nested<T> nested = new TargetClass.Nested<>();nested.overrideMethod();}}
@Overridepublic void process(T data) {}}
private class TargetClass<K> {K dataField;
static class Nested<V> {public void overrideMethod() {}}
public K getValue() {return dataField;}}