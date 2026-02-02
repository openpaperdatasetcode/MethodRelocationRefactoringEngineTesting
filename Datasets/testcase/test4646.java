package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass<T> implements Runnable {private TargetClass target;private String data;
/**
Generates a TargetClass instance
@return TargetClass instance
*/
protected TargetClass createTarget() {
synchronized (this) {
TargetClass.Nested nested = new TargetClass.Nested();
List<String> values = getGenericData(nested);
TargetClass result = new TargetClass();
result.setValue(SourceClass.this.data);
result.setList(values);
return result;
}
}
protected <E> List<String> getGenericData(TargetClass.Nested nested) {List<String> list = new ArrayList<>();list.add(nested.process());list.add(String.valueOf(target.hashCode()));return list;}
public void run() {}
class LocalInner {void useOuter() {createTarget();}}
static class StaticNested {static SourceClass<String> create() {return new SourceClass<>();}}}
public class TargetClass {private String value;private List<String> list;
public void setValue(String value) {this.value = value;}
public void setList(List<String> list) {this.list = list;}
static class Nested {String process() {return "processed";}}}