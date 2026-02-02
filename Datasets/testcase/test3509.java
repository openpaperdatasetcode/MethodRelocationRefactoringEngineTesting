package test;
protected class SourceClass {public class MemberInner {void helperMethod(TargetClass target) {class LocalHelper {void updateField(TargetClass t) {t.field = "updated_by_local";}}new LocalHelper().updateField(target);}}
final void instanceMethod(TargetClass target) {class LocalInner {void processTarget(TargetClass t) {if (t.field.equals("1")) {t.field = "modified";}}}
LocalInner local = new LocalInner();MemberInner member = new MemberInner();
for (int i = 0; i < 3; i++) {local.processTarget(target);member.helperMethod(target);
// ExpressionStatement accessing this.field (target's field)System.out.println(target.field);}}}
class TargetClass {String field;
public TargetClass(String field) {this.field = field;}
public void compute() {class LocalInnerTarget {void incrementCount() {field = field + "_count";}}new LocalInnerTarget().incrementCount();}}