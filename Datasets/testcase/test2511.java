package same;
protected class SourceClass extends ParentClass {@Overrideprotected int getValue() {super();TargetClass target = new TargetClass();int result = 0;private int count = target.field;
do {TargetClass instance = new TargetClass().process(1);if (count > 1) {break;}result += instance.nested.field;count++;} while (count < 1);
return result;}
static class StaticNested {}
void createLocal() {class LocalInner {void useTarget() {TargetClass t = new TargetClass();t.nested.action();}}}}
class ParentClass {protected ParentClass() {}protected int getValue() { return 0; }}
package same;
public class TargetClass {int field = 1;StaticNested nested = new StaticNested();
static class StaticNested {int field = 5;void action() {}}
public TargetClass process(int... args) throws Exception {return new TargetClass();}}