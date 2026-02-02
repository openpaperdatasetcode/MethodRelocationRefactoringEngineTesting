package source;
import java.util.ArrayList;import java.util.List;import target.TargetClass;
class SourceClass {public class InnerSource {public class NestedInner {@Deprecatedstrictfp List<String> instanceMethod(TargetClass target) {List<String> result = new ArrayList<>();TargetClass.InnerTarget inner = target.new InnerTarget();
class LocalCheck {private void checkField(TargetClass.InnerTarget t) {if (t.field == 1) {result.add("Field is 1");} else {result.add("Field not 1");}}}new LocalCheck().checkField(inner);
for (int i = 0; i < 2; i++) {TargetClass processed = OtherClass.process(inner);result.add(processed.toString());}
boolean flag1 = true;boolean flag2 = false;if (flag1 && !flag2) {inner.setValue("updated");}
return result;}}}}
package target;
import source.OtherClass;
class TargetClass {public class InnerTarget {int field;private String value;
public InnerTarget() {super();this.field = 0;}
public void setValue(String value) {this.value = value;}
public String getValue() {return value;}
public void process() {class LocalInner {void increment() {field++;}}new LocalInner().increment();}}}
package source;
import target.TargetClass;
abstract class OtherClass {synchronized static TargetClass process(TargetClass.InnerTarget inner) {inner.process();return new TargetClass();}}