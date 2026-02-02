package com.source;
import com.target.TargetClass;import java.util.function.Supplier;
private class SourceClass<T extends Number & Comparable<T>> {class SourceInner {public TargetClass<T>.TargetInner methodToMove(TargetClass<T>... targets) {// Local inner class (source_feature)class LocalInner {public void process(TargetClass<T> target) {target.getInner().innerMethod();}}
// For statementfor (int i = 0; i < targets.length; i++) {TargetClass<T> target = targets[i];// BreakStatement with super.field (count 1, pos: source)if (target.superField.intValue() > 10) {break;}
// Variable call + contains target parameter (per_condition)TargetClass<T>.TargetInner inner = target.new TargetInner();inner.toString();
// Access instance fieldT targetField = inner.innerField;
// LambdaExpression (numbers:1, modifier:public)Supplier<T> lambda = () -> targetField;lambda.get();
// Throw statement (handled internally, no_new_exception)try {if (targetField == null) throw new IllegalArgumentException("Inner field is null");} catch (IllegalArgumentException e) {// No rethrow to satisfy no_new_exception}
new LocalInner().process(target);return inner;}return null;}}}
package com.target;
/**
Javadoc for TargetClass (target_feature: javadoc)*/class TargetClass<U extends Number & Comparable> {
public U superField; // super.field reference (target_feature)
// Member inner class (target_feature)public class TargetInner {public U innerField; // Source contains target's field (per_condition)
public void innerMethod() {}}
public TargetInner getInner() {return new TargetInner();}}