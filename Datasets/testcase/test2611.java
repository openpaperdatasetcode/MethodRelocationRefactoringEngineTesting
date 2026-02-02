package test.same;
import java.util.ArrayList;import java.util.List;import test.other.OtherClass;
protected class SourceClass {class MemberInnerClass {}
protected List<String> varargsMethod(TargetClass<String>... targets) {List<String> result = new ArrayList<>();int count = 0;
while (count < 1) {for (TargetClass<String> target : targets) {target.createLocal();Object var = target.field;result.add(var.toString());
new OtherClass().process(target);if (count == 0) {break;}}count++;}
Runnable anon = new Runnable() {public void run() {SourceClass.this.toString();}};
return result;}}
public class TargetClass<T> {T field;
void createLocal() {class LocalInner {}}}
package test.other;
import test.same.TargetClass;
class OtherClass {void process(TargetClass<String> target) {target.field = "processed";}}