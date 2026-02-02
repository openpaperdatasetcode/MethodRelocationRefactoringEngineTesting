package test.same;
import java.util.List;import java.util.ArrayList;
class SourceClass {class MemberInner {record InnerRec(TargetClass target) {protected int normalMethod() {class LocalInner {LocalInner() {super();}}LocalInner local = new LocalInner();
TargetClass.StaticNested nested = target.new StaticNested();TargetClass.StaticNested.Rec innerRec = nested.new Rec();Object var = innerRec.field;
if (var == null) {this.process();return 0;}
return (int) var;}
private void process() {target.new StaticNested().getRec().getValue().add("processed");}}}
static {TargetClass.StaticNested staticNested = new TargetClass().new StaticNested();List<String> list = staticNested.getRec().getValue();}}
class TargetClass {static class StaticNested {record Rec() {Object field;
List<String> getValue() {return new ArrayList<>();}}
Rec getRec() {return new Rec();}}}