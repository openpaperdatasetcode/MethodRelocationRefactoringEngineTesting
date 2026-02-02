package test.same;
import java.util.ArrayList;import java.util.List;
public class SourceClass<T> {class MemberInner {record InnerRec() {List<String> varargsMethod(TargetClass<T>... targets) {List<String> result = new ArrayList<>();
new OtherClass() {{new TargetClass<T>() {{superField = 1;}};}};
int count = 0;do {int val = new SourceClass<T>().staticMethod(count);result.add(String.valueOf(val));count++;} while (count < 2);
for (TargetClass<T> target : targets) {Object var = target.field;result.add(var.toString());}
return result;}
private static int staticMethod(int num) {return num * 2;}}}}
class OtherClass {}
private class TargetClass<V> extends ParentClass {V field;}
class ParentClass {Object superField;}