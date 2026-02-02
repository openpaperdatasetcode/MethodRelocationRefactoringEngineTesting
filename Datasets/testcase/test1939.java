package test;
import java.io.IOException;import java.util.ArrayList;import java.util.List;
public class SourceClass<T> implements Runnable {static class StaticNested1 {}
static class StaticNested2<V> {}
private T data;
public SourceClass(T data) {this.data = data;}
@Overridepublic void run() {}
static List<String> method(TargetClass<String> target) throws IOException {// Type declaration statementclass LocalType {}LocalType local = new LocalType();
// Instance method chain call in property assignmentSourceClass<String> source = new SourceClass<>("test");target.innerRec.nestedList = source.getData().process().filter().collect();
// Access target's fields and inner class methodstarget.field.add("item1");target.inner.addToField("item2");target.inner.innerRec.addToNestedList("item3");
return target.field;}
// Method chain componentspublic DataProcessor getData() {return new DataProcessor((String) data);}
public static class DataProcessor {private String value;
public DataProcessor(String value) {this.value = value;}
public DataFilter process() {return new DataFilter(value);}}
public static class DataFilter {private String value;
public DataFilter(String value) {this.value = value;}
public DataCollector filter() {return new DataCollector(value);}}
public static class DataCollector {private String value;
public DataCollector(String value) {this.value = value;}
public List<String> collect() {List<String> list = new ArrayList<>();list.add(value);return list;}}}
private class TargetClass<S> {List<String> field = new ArrayList<>();MemberInner inner = new MemberInner();
class MemberInner {InnerRec innerRec = new InnerRec();
void addToField(String item) {field.add(item);}
class InnerRec {List<String> nestedList = new ArrayList<>();
void addToNestedList(String item) {nestedList.add(item);}}}}