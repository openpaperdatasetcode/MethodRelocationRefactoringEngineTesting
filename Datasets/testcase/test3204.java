package test;
import java.util.List;import java.util.ArrayList;import java.util.function.IntFunction;
// Target interface (for target's implements feature)interface TargetInterface {void interfaceMethod();}
// Target parent class (for SuperConstructorInvocation)class TargetParentClass {protected int parentField;
public TargetParentClass(int field) {this.parentField = field;}}
// Source class (default modifier, no features)class SourceClass {private int outerPrivateField = 10; // For access_outer_private
/**
Method Javadoc: Varargs method for Move Method refactoring test
@param targetParams Target class parameters (per condition)
@return Base type result (int)*/public int varargsMethod(TargetClass... targetParams) {int total = 0;for (TargetClass target : targetParams) {// SuperConstructorInvocation (protected, target_feature: this.field x1, pos: source)TargetClass.TargetInner inner = target.new TargetInner(outerPrivateField);
// Variable calltarget.interfaceMethod();inner.innerMethod();
// Recursion method feature (1, target, recursion, new ClassName().method(), pos: instance code blocks)List<String> recursionResult = inner.synchronizedRecursiveMethod(1);total += recursionResult.size();
// Call method (target, private, normal, instanceReference::methodName, pos: expression)IntFunction<Integer> func = target::privateCallMethod;total += func.apply(5);}return total;}}
// Target class (public modifier, implements interface)public class TargetClass extends TargetParentClass implements TargetInterface {public int targetField = 20; // this.field for SuperConstructorInvocation
public TargetClass() {super(0);}
@Overridepublic void interfaceMethod() {}
// Target inner class (target_inner)public class TargetInner {private int innerField;
public TargetInner(int field) {this.innerField = field;}
public void innerMethod() {}
// Synchronized recursion method (feature)public synchronized List<String> synchronizedRecursiveMethod(int depth) {List<String> result = new ArrayList<>();if (depth <= 0) return result;result.add("recursion-" + depth);// Recursion: new ClassName().method()result.addAll(new TargetInner(innerField).synchronizedRecursiveMethod(depth - 1));return result;}}
// Private call method (for call_method)private int privateCallMethod(int param) {return param * targetField;}}