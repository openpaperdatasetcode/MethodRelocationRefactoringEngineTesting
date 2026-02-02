package test.same;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
public class SourceClass<T> {class MemberInner {public List<String> varargsMethod(TargetClass<String>... targets) {List<String> result = new ArrayList<>();
for (TargetClass<String> target : targets) {TargetClass<String>.LocalInner inner = target.new LocalInner();OthersClass overrideObj = new OthersClass() {@Overridepublic List<String> method() {return inner.m1().m2().m3();}};
assert inner.field != null : "Field cannot be null";Object var = inner.field;
Function<String, Integer> func = inner::overloadMethod;if (var != null) {result.add(func.apply((String) var).toString());} else {result.add("null");}}
return result;}}}
public class TargetClass<V> {LocalInner new LocalInner() {class LocalInner {V field;
LocalInner m1() { return this; }LocalInner m2() { return this; }List<String> m3() { return new ArrayList<>(); }
int overloadMethod(V value) {return value.toString().length();}
int overloadMethod(V value, int offset) {return value.toString().length() + offset;}}return new LocalInner();}}
class OthersClass {public List<String> method() {return new ArrayList<>();}}