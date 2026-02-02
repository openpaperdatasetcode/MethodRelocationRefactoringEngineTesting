package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
private class SourceClass {public class InnerSource<T extends CharSequence> {private T data;
public InnerSource(T data) {super();this.data = data;}
public TargetClass processTarget() {return new TargetClass(data.toString());}}
@Deprecatedpublic List<String> instanceMethod() {List<String> result = new ArrayList<>();TargetClass target = new TargetClass("initial");InnerSource<String> inner = new InnerSource<>("source_data");
class LocalInner {public TargetClass createTarget(String val) {return new TargetClass(val);}
public TargetClass modifyTarget(TargetClass t) {t.setValue(t.getValue() + "_modified");return t;}}
LocalInner local = new LocalInner();TargetClass localTarget = local.createTarget(inner.data);
if (localTarget.getValue().length() > 5) {result.add(local.modifyTarget(localTarget).getValue());} else {result.add(target.getValue());}
List<TargetClass.StaticNested> nestedList = new ArrayList<>();nestedList.add(new TargetClass.StaticNested(1));nestedList.add(new TargetClass.StaticNested(2));
for (TargetClass.StaticNested nested : nestedList) {Function<Integer, Object> func = (num) -> nested.increment(num);result.add(func.apply(5).toString());}
return result;}}
public class TargetClass {private String value;
public TargetClass(String value) {this.value = value;}
public String getValue() {return value;}
public void setValue(String value) {this.value = value;}
public static class StaticNested {private int count;
public StaticNested(int count) {this.count = count;}
public int increment(int num) {return count + num;}}}