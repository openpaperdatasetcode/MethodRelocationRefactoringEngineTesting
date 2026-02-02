package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Consumer;
interface MyInterface<T> {List<String> process(T param);}
abstract class SourceClass<T> implements MyInterface<T> {class SourceInner {@Overridepublic List<String> process(TargetClass<T>.TargetInner inner) {class LocalInner {}LocalInner local = new LocalInner();
String[] arr1 = {"one", "two"};int[] arr2 = {1, 2};
try {if (inner.field == null) {throw new NullPointerException();}
TargetClass<T> target = new TargetClass<>();Consumer<String> consumer;
if (inner.field.length() > 0) {consumer = this::protectedMethod;} else {consumer = this::protectedMethod;}
List<String> result = new ArrayList<>();result.add(inner.field);return result;} catch (NullPointerException e) {e.printStackTrace();return new ArrayList<>();}}
protected void protectedMethod(String str) {}}}
public class TargetClass<V> {class TargetInner {String field;}}