package same;
public sealed class SourceClass permits SubSourceClass {static class StaticNested {}
class MemberInner {}
@FunctionalInterfaceinterface Processable {void execute();}
public abstract void handle(TargetClass.InnerRec inner);
public void process(TargetClass target) {class LocalType {}LocalType local = new LocalType();
private int count = TargetClass.STATIC_FIELD;
int i = 0;do {handle(target.new InnerRec());i++;} while (i < count);}}
class SubSourceClass extends SourceClass {@Overridepublic void handle(TargetClass.InnerRec inner) {inner.setValue("processed");}}
package same;
public class TargetClass {static int STATIC_FIELD = 3;
class InnerRec {String value;
void setValue(String val) {class LocalHandler {void apply() {value = val;}}new LocalHandler().apply();}}}