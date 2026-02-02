package same.pkg;
import java.util.List;import java.util.ArrayList;
private class ParentClass {protected int protectedField;}
private class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass();
private TargetClass getTarget() {TargetClass target = super.new TargetClass();OthersClass others = new OthersClass();List<String> list = others.create(target.new InnerClass().process(this));
protectedField = 3;targetField.value = protectedField;
class LocalInner {TargetClass.InnerClass getInner() {return targetField.new InnerClass();}}
TargetClass.InnerClass inner = new LocalInner().getInner();inner.action();return target;}
static class StaticNested {void useTarget(SourceClass source) {TargetClass tc = source.getTarget();}}}
private class TargetClass {int value;
class InnerClass {List<String> process(SourceClass source) {return new ArrayList<>();}
void action() {}}
void method() {class LocalInner {void access() {value = 5;}}}}
class OthersClass {protected List<String> create(List<String> input) {return new ArrayList<>(input);}}