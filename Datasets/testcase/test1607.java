package test;
import other.OthersClass;
/**
Sealed generic source class
permits its subclasses
/
sealed class SourceClass<T> permits SourceSubClass<T> {
public static class StaticNested {
/*
Member inner class containing overloading methods/
public class Inner {
/*
Overloading method 1
@param target target generic class instance
@return processed target instance*/@SuppressWarnings("unchecked")public <T> TargetClass<T> process(TargetClass<T> target) {// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {target.setValue((T) "initial");}};initializer.run();
return process(target, 0);}
/**
Overloading method 2
@param target target generic class instance
@param factor processing factor
@return processed target instance*/public <T> TargetClass<T> process(TargetClass<T> target, int factor) {try {// Overriding method from parent class in exception handlingthis.validate(target);
// ParenthesizedExpression (2 occurrences, abstract)abstract Object expr1 = (target.getValue());abstract Object expr2 = (factor * 2);
// Super keywordSystem.out.println(super.toString());
// Variable call - access target's fieldT data = target.value;target.setValue((T) (data + "_processed"));
// Call others_class's overloading method in expressionObject result = OthersClass.process(StaticNested.Inner.class, target);
} catch (Exception e) {// no new exception}
return target;}
// Default overriding method (overrides parent class)protected void validate(TargetClass<?> target) {if (target.value == null) {throw new IllegalArgumentException("Value cannot be null");}}}}}
final class SourceSubClass<T> extends SourceClass<T> {}
package other;
import test.TargetClass;import test.SourceClass;
public class OthersClass {// Overloading method 1static Object process(Class clazz, TargetClass target) {return clazz.getSimpleName() + ": " + target.getValue();}
// Overloading method 2static Object process(SourceClass.StaticNested.Inner inner, TargetClass<?> target) {return inner.getClass().getSimpleName() + ": " + target.getValue();}}
package test;
/**
Final generic target class*/final class TargetClass<T> {public T value;
public T getValue() {// Local inner class in targetclass ValueWrapper {T wrap(T val) {return val;}}return new ValueWrapper().wrap(value);}
public void setValue(T value) {this.value = value;}}
abstract class ParentValidator {protected abstract void validate(TargetClass<?> target);}