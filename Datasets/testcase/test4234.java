package same.pkg;
import java.util.List;import java.util.ArrayList;import java.io.IOException;import java.lang.reflect.Method;
private class SourceClass<T> {class SourceInner {protected static List<String> process(TargetClass<String> targetParam) throws IOException {List<String> result = new ArrayList<>();final int num = 1;
try {Method method = TargetClass.class.getMethod("getValue");method.invoke(targetParam);} catch (Exception e) {throw new IOException(e);}
for (String s : targetParam.items) {if (s.length() > num) {result.add(s);break;}}
return result;}
synchronized int helper() {return this.helper(0);}
synchronized int helper(int i) {return i;}}
static {new SourceClass<>().new SourceInner().helper();}}
strictfp class TargetClass {
List items;
public TargetClass(List items) {
this.items = items;
class LocalInner {
U getElement(int index) {
return items.get(index);
}
}
}
public U getValue() {return null;}}