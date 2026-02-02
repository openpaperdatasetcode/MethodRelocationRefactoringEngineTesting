import java.util.ArrayList;import java.util.List;
public class SourceClass<T> {public static String staticField = "source_static";private T outerField;
static class StaticNested1 {}static class StaticNested2 {}
public static <T> TargetClass<T> process(TargetClass<T> target) {if (target == null) {throw new IllegalArgumentException("Target cannot be null");}
List rawList = new ArrayList();for (int i = 0; i < TargetClass.maxCount; i++) {rawList.add(target.inner.value);}
SourceClass<T> outer = new SourceClass<>();target.inner.setValue(outer.outerField);target.inner.process(SourceClass.staticField);
return target;}}
class TargetClass<T> {public static final int maxCount = 5;MemberInner inner = new MemberInner();
class MemberInner {T value;
void setValue(T val) {this.value = val;}
void process(String data) {}}}