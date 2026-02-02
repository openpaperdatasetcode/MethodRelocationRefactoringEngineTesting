package source;
import target.TargetClass;import target.SubClass;import java.util.ArrayList;import java.util.List;
class SourceClass<T> {protected String outerProtected = "protected";static String staticField = "static";
private Object instanceMethod(TargetClass<Integer> target) {try {OthersClass<String> others = new OthersClass<>();int val = others.method1().method2().method3();} catch (Exception e) {}
TargetClass<Double> newTarget = new TargetClass<>();String expr = target.getValue() + staticField;Object varCall = target.getField();varCall = outerProtected;
return varCall;}
private Object instanceMethod(String str) {return str;}
void anonymousClasses() {Runnable r1 = new Runnable() {public void run() {}};Runnable r2 = new Runnable() {public void run() {}};}}
package target;
import source.SourceClass;import java.util.List;
interface MyInterface {}
strictfp class TargetClass<V> implements MyInterface {private V value;Object field;
V getValue() { return value; }Object getField() { return field; }
void localInnerClass() {class LocalInner {}}}
final class SubClass extends SourceClass<String> {TargetClass<String> callInCollection() {List<TargetClass<String>> list = new ArrayList<>();TargetClass<String> target = new TargetClass<>();list.add(target);
return list.stream().map(t -> (TargetClass<String>) super.instanceMethod(t)).findFirst().orElse(null);}}
class OthersClass {
OthersClass method1() { return this; }
OthersClass method2() { return this; }
int method3() { return 0; }
}