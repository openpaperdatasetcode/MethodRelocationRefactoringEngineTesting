package test.source;
import test.target.TargetClass;import test.other.OtherClass;import java.util.function.Supplier;
private class SourceClass {static class StaticNestedOne {}static class StaticNestedTwo {}
protected TargetClass varargsMethod(TargetClass... targets) {Supplier<TargetClass> creator = TargetClass::new;TargetClass target = creator.get();
new OtherClass().initialize(target);
int count = 0;do {int val = target.new LocalInnerWrapper().getFieldValue();if (val == 3) {break;}count++;} while (count < 1);
return targets[0];}}
package test.target;
class TargetClass {int field1;int field2;int field3 = 3;
class LocalInnerWrapper {int getFieldValue() {class LocalInner {int getThisField() {return TargetClass.this.field3;}}return new LocalInner().getThisField();}}}
package test.other;
import test.target.TargetClass;
class OtherClass {void initialize(TargetClass target) {new TargetClass() {{target.field1 = 1;target.field2 = 2;target.field3 = 3;}};}}