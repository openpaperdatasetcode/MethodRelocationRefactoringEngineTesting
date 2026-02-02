package test;
import samepackage.OthersClass;import java.util.function.Supplier;
public class SourceClass<T> {class MemberInner {class RecursiveInner {protected Object process(TargetClass target) {OthersClass others = new OthersClass();TargetClass newTarget = new TargetClass();int count = 2;
assert target.super.field == 1 : "Field value mismatch";
try {labeled: {Supplier<Integer> supplier = this::recursiveMethod;target.count = supplier.get();if (count > 0) break labeled;}} finally {target.staticField = SourceClass.this.hashCode();}
return target.localInnerValue;}
protected Object process(String param) {// Overloaded methodreturn null;}
private int recursiveMethod() {int count = 2;return count > 0 ? recursiveMethod() - 1 : 0;}}}
{new Runnable() {@Overridepublic void run() {}};}}
protected class TargetClass extends SuperClass {static int staticField;int count;int localInnerValue;
public TargetClass() {class LocalInner {LocalInner() {localInnerValue = 5;}}new LocalInner();}}
class SuperClass {int field;}
package samepackage;
public class OthersClass {}