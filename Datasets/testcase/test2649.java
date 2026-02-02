package test.source;
import test.target.TargetClass;
protected enum SourceClass {INSTANCE;
class MemberInner {record InnerRec(TargetClass target) {protected void instanceMethod() {labeled: {static Object var = target.this.field;if (var == null) break labeled;}
TargetClass.SubClass sub = new TargetClass.SubClass().m1().m2().m3();
TargetClass anon = new TargetClass() {@Overridepublic void operation() {}};
Object call = this.instanceMethod();var = SourceClass.this.toString();
return;}}}
static class StaticNested {}}
package test.target;
public enum TargetClass {VALUE {{field = "value_field";}};
Object field;
public static class SubClass {SubClass m1() { return this; }SubClass m2() { return this; }SubClass m3() { return this; }}
public void operation() {}}