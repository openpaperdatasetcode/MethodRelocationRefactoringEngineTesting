package source;
import target.TargetClass;import java.io.IOException;
class ParentClass {void methodToOverride(TargetClass target) throws IOException {}}
class SourceClass extends ParentClass {class SourceMemberInner {record SourceInnerRec(String data) {@Overridevoid methodToOverride(TargetClass targetParam) throws IOException {class LocalInner {}new LocalInner();
assert targetParam != null : "Target must not be null";privateConstructor(targetParam, targetParam.staticNested.field);
switch (targetParam.value) {case 1 -> targetParam.doAction();case 2 -> targetParam.staticNested.doStaticAction();default -> {}}}
private void privateConstructor(TargetClass target, int field) {new TargetClass.StaticNested(target.value, field);}}}}
package target;
public class TargetClass {int value;static StaticNested staticNested = new StaticNested(0, 0);
static class StaticNested {int field;
StaticNested(int val1, int val2) {this.field = val1 + val2;}
void doStaticAction() {}}
void doAction() {}}