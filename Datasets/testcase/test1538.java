package test;
import java.util.ArrayList;import java.util.List;import java.util.Collection;
public class SourceClass {public class MemberInner {String value;}
List<String> process(TargetClass targetParam) {MemberInner inner = new MemberInner();inner.value = "test";
Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println(inner.value);}};anon.run();
List<String> result = new ArrayList<>();targetParam.add(inner.value);result = targetParam.getItems();
TargetClass.NestedInner nested = targetParam.new NestedInner();nested.process(5);
return process(targetParam, result);}
List<String> process(TargetClass targetParam, List<String> initial) {initial.addAll(targetParam.getItems());return initial;}}
public class TargetClass implements Collection<String> {private List<String> items = new ArrayList<>();
class NestedInner {<T extends Number> void process(T num) {class LocalInner {void addToItems() {items.add(num.toString());}}new LocalInner().addToItems();}}
void add(String s) {items.add(s);}
List<String> getItems() {return new ArrayList<>(items);}
@Overridepublic int size() { return items.size(); }
@Overridepublic boolean isEmpty() { return items.isEmpty(); }
@Overridepublic boolean contains(Object o) { return items.contains(o); }
@Overridepublic java.util.Iterator<String> iterator() { return items.iterator(); }
@Overridepublic Object[] toArray() { return items.toArray(); }
@Overridepublic <T> T[] toArray(T[] a) { return items.toArray(a); }
@Overridepublic boolean add(String s) { return items.add(s); }
@Overridepublic boolean remove(Object o) { return items.remove(o); }
@Overridepublic boolean containsAll(Collection<?> c) { return items.containsAll(c); }
@Overridepublic boolean addAll(Collection<? extends String> c) { return items.addAll(c); }
@Overridepublic boolean removeAll(Collection<?> c) { return items.removeAll(c); }
@Overridepublic boolean retainAll(Collection<?> c) { return items.retainAll(c); }
@Overridepublic void clear() { items.clear(); }}