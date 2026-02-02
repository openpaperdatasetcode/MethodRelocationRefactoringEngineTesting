package test.refactoring;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.Collection;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
private class SourceClass {private TargetClass target = new TargetClass();
class MemberInner {void useInstanceMethod() {Object result = processTarget(target.new TargetInner(5));}}
{Runnable anon = new Runnable() {@Overridepublic void run() {processTarget(target.new TargetInner(10));}};}
@MethodAnnotationprivate Object processTarget(TargetClass.TargetInner targetInner) {do {if (targetInner.getCount() % 2 == 0) {targetInner.increment();continue;}variableCall(targetInner);targetInner.increment();} while (targetInner.getCount() < 5);
Collection<String> coll = new ArrayList<>();int sum1 = OthersClass.calculate(coll, "a", "b");int sum2 = OthersClass.calculate(coll, "x");
return super.toString() + sum1 + sum2;}
private Object processTarget(TargetClass.TargetInner targetInner, String extra) {return targetInner.getValue() + extra;}
private void variableCall(TargetClass.TargetInner inner) {inner.setValue(inner.getValue() + 10);}}
/**
TargetClass contains static nested class for refactoring test
Supports instance method calls and collection operations
/
public class TargetClass {
/*
TargetInner is nested inner class for data processing*/public static class TargetInner {private int count;private Object value;
public TargetInner(int initialCount) {this.count = initialCount;this.value = initialCount;}
public void increment() {count++;}
public int getCount() {return count;}
public Object getValue() {return value;}
public void setValue(Object value) {this.value = value;}}}
class OthersClass {public static int calculate(Collection<String> coll, String... items) {coll.addAll(java.util.List.of(items));return coll.size();}}