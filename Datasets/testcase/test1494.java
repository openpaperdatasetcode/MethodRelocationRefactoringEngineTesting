package test;
import java.util.ArrayList;import java.util.List;
public class Source<T> {protected String outerProtectedField = "protected_value";
class FirstInner {T data;
FirstInner(T data) {this.data = data;}}
class SecondInner {List<String> collect(Target.StaticNested nested) {List<String> list = new ArrayList<>();list.add(nested.getInfo());return list;}}
public List<String> process(Target target, T... items) {List<String> result = new ArrayList<>();SecondInner inner = new SecondInner();Target.StaticNested nested = new Target.StaticNested();
// Access outer protected fieldresult.add(outerProtectedField);
for (T item : items) {if (item == null) {continue; // Continue statement}FirstInner first = new FirstInner(item);nested.setInfo(first.data.toString());result.addAll(inner.collect(nested)); // Depends on inner class}
return result;}}
abstract class Target {static class StaticNested {private String info;
String getInfo() {return info;}
void setInfo(String info) {this.info = info;}}}