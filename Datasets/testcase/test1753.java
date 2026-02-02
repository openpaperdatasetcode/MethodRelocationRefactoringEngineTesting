package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
class SuperSource {protected String superField;
public SuperSource(String val) {this.superField = val;}}
class Source extends SuperSource {String x;
class MemberInner {void doSomething() {}}
Runnable anonymous = new Runnable() {public void run() {getInnerData(new Target<String>());}};
protected Source() {super("base");}
protected List<String> getInnerData(Target<String> target) {if (target == null) {throw new IllegalArgumentException("Target cannot be null");}
List<String> data = new ArrayList<>();Target<String>.InnerTarget inner = target.new InnerTarget();
data.addAll(Helper.process(inner, Source.this.x, super.superField));
; // Empty statement
try {Method method = inner.getClass().getMethod("getValue");data.add((String) method.invoke(inner));} catch (Exception e) {}
return data;}}
class Target<T> {T value;
Runnable action = new Runnable() {public void run() {System.out.println(value);}};
class InnerTarget {private T value;
T getValue() {return value;}
void setValue(T val) {this.value = val;}}}
class Helper {public static List<String> process(Target.InnerTarget inner, String... args) {List<String> result = new ArrayList<>();for (String arg : args) {result.add(arg);}return result;}}