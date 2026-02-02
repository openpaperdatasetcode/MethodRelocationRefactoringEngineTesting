package same;
import java.util.List;import java.util.ArrayList;
public class SourceClass {static class StaticNested {}
class MemberInner {Object process (TargetClass target) {TargetClass.Inner inner = target.new Inner ();
TargetClass.Inner subInner = new TargetClass.Inner () {@Overridepublic int calculate () {return super.calculate () + target.baseValue;}};int result = subInner.calculate (); inner.setData ("processed");
inner.items = this.getItems (inner, 5);inner.items = this.getItems (inner, "extra");
return inner.getData ();}
protected List<String> getItems(TargetClass.Inner inner, int count) {return inner.generate(count);}
protected List<String> getItems(TargetClass.Inner inner, String suffix) {return inner.generate(suffix);}}}
package same;
import java.util.List;import java.util.ArrayList;
class TargetClass {int baseValue = 10;
class Inner {List<String> items;private String data;
public int calculate () {return baseValue * 2;}
List<String> generate(int count) {List<String> list = new ArrayList<>();for (int i = 0; i < count; i++) {list.add("item-" + i);}return list;}
List<String> generate(String suffix) {List<String> list = new ArrayList<>();list.add("data-" + suffix);return list;}
void setData(String data) {this.data = data;}
String getData() {return data;}}}