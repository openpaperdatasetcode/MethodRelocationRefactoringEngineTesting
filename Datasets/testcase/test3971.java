package test;
import java.util.List;import java.util.ArrayList;
protected class SourceClass<T> {private TargetClass<Integer>.InnerClass targetInnerField;
class MemberInner {}
public void createLocalInner() {class LocalInner {}}
public Object recursiveMethod(T target, int depth) {if (depth <= 0) {return new Object();}
TargetClass<Integer> targetObj = new TargetClass<>();TargetClass<Integer>.InnerClass inner = new TargetClass<Integer>.InnerClass();inner.field = 2;this.targetInnerField = inner;
raw_type var;var = new raw_type();
synchronized (this) {targetObj.value = depth;}
try {List<String> list = super.parentMethod();} catch (Exception e) {}
OthersClass.callWithVarargs(new TargetClass<String>().createInner(), 1, "a");
return recursiveMethod(target, depth - 1);}
@Overridepublic String toString() {return "";}}
protected class TargetClass<V> {V value;
class InnerClass {int field;}
public InnerClass createInner() {class LocalInner {}return new InnerClass();}}
class ParentClass {protected List<String> parentMethod() {return new ArrayList<>();}}
class raw_type {}
class OthersClass {static <V> TargetClass<V> callWithVarargs(TargetClass<V>.InnerClass inner, Object... args) {return new TargetClass<>();}}