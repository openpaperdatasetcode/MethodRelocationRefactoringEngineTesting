package test;
import java.util.List;
private class SourceClass<T extends CharSequence> {// Overload exist: method with different parametersprotected TargetClass<T> methodToMove(TargetClass<T> target) {return process(target, 1);}
protected TargetClass<T> methodToMove(TargetClass<T> target, int factor) {return process(target, factor);}
private TargetClass<T> process(TargetClass<T> target, int factor) {// Variable call + contains target field (per_condition)target.toString();TargetClass<T>.Inner inner = target.new Inner();
// Raw type usageList rawList = target.getRawList();rawList.add(inner.getInnerField());
// Instance method in constructor parameter list (super.methodName())TargetClass<T>.StaticNested nested = new TargetClass<T>.StaticNested(calculateSuper(target));
// Requires try-catch (checked exception simulation)try {inner.validate(factor);} catch (IllegalArgumentException e) {// Mandatory try-catch handling}
// Local inner class (source_feature)class LocalProcessor {public void updateInner() {inner.setInnerField((T) (inner.getInnerField() + "_processed"));}}new LocalProcessor().updateInner();
// Anonymous inner class (source_feature)Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Inner field: " + inner.getInnerField());}};anon.run();
return target;}
// Source instance method for method_featurepublic int calculateSuper(TargetClass<T> target) {return target.getTargetField().length() * 2;}}
public class TargetClass<T extends CharSequence> {private T targetField; // Source contains target's field (per_condition)
public TargetClass(T targetField) {this.targetField = targetField;}
// Static nested class (target_feature)public static class StaticNested {private int value;
public StaticNested(int value) {this.value = value;}}
public class Inner {private T innerField = (T) "innerDefault";
public T getInnerField() {return innerField;}
public void setInnerField(T innerField) {this.innerField = innerField;}
public void validate(int factor) {if (factor < 0) {throw new IllegalArgumentException("Negative factor");}}}
// Raw type providerpublic List getRawList() {return new java.util.ArrayList();}
public T getTargetField() {return targetField;}}