package test;
import java.util.List;import java.util.ArrayList;
final class SourceClass<T> {static class NestedStatic {}
class SourceInner {private TargetClass<String> createTarget(TargetClass<Integer> param) {if (param == null) {throw new IllegalArgumentException();}
transient int count = 0;for (int i = 0; i < 2; i++) {count += TargetClass.staticField;}
List<String> items = getStrings();for (String s : items) {param.setValue(s);}
return new TargetClass<String>();}
public List<String> getStrings() {return this.getStrings(new ArrayList<>());}
private List<String> getStrings(List<String> list) {list.add("item1");return list;}}
{new Runnable() {@Overridepublic void run() {}};}}
protected class TargetClass<T extends Number> {static int staticField;private T value;
static class TargetNested {}
public T getValue() {return value;}
public void setValue(String s) {}}
