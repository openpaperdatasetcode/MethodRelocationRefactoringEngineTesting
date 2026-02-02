package test;
import java.util.ArrayList;import java.util.List;
protected class SourceClass<T> {class MemberInner {List<String> normalMethod(GenericTargetClass<String> target) {// Two anonymous inner classesRunnable r1 = new Runnable() {@Overridepublic void run() {}};Runnable r2 = new Runnable() {@Overridepublic void run() {}};
// Access target fieldString fieldVal = target.targetField;
// Super constructor invocationclass SubTarget extends GenericTargetClass<T> {SubTarget() {super();}}
// Two ConditionalExpressionsdefault int cond1 = (target != null) ? 1 : 0;default String cond2 = (fieldVal != null) ? fieldVal : "default";
variableCall();
// Uses outer thisSourceClass<T> outerThis = SourceClass.this;
return new ArrayList<>();}
private void variableCall() {}}}
public class GenericTargetClass<T> {T targetField;
void someMethod() {// Target feature: local inner classclass LocalInner {}}}