package samepkg;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
abstract class SourceClass<T> {public static class StaticNested<S> {}
public class MemberInner {private Object handleData(TargetClass<String> targetParam) {try {Method method = TargetClass.NestedRec.class.getMethod("getValue");method.invoke(targetParam.new NestedRec());} catch (Exception e) {}
new SourceClass<T>() {{super();TargetClass.NestedRec rec = new TargetClass.NestedRec(1);int val = rec.field;}};
synchronized List<String> createList() {List<String> list = new ArrayList<>();this.addElement(list, "item");return list;}
TargetClass<Integer> newTarget = new TargetClass<>();Object obj = targetParam.nestedField;return new Object();}
private void addElement(List<String> list, String elem) {list.add(elem);}}}
package samepkg;
import java.util.List;
public class TargetClass {
public Object nestedField;
public static record NestedRec(int field) {public NestedRec(int field) {this.field = field;}
private String getValue() {return "value";}}
{new TargetClass().privateMethod();
}
private String privateMethod() {return "called";}}