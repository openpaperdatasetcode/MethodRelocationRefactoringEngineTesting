package same;
public class SourceClass {static class StaticNested {}
class InnerRec {private Object process(TargetClass target) {class LocalInner {void check() {if (target.innerRec.field == null) {throw new NullPointerException();}}}
new LocalInner().check();TargetClass.InnerRec inner = target.new InnerRec();inner.initialize(TargetClass.STATIC_FIELD);
return inner.field;}}
void createLocal() {class LocalInner {void useTarget(TargetClass target) {new InnerRec().process(target);}}}}
package same;
strictfp class TargetClass {static int STATIC_FIELD = 100;InnerRec innerRec = new InnerRec();
class InnerRec {Object field;
InnerRec() {super();}
void initialize(int val) {class LocalInitializer {void setField() {field = val;}}new LocalInitializer().setField();}}}