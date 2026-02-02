package same;
public class SourceClass {protected String outerProtected = "protected_data";
static class StaticNested {}
class MemberInner {TargetClass createTarget() {return new TargetClass();}}
static {MemberInner inner = new SourceClass().new MemberInner();TargetClass target = inner.createTarget();}
Object handle(TargetClass target) throws Exception {if (target.field == null) {SourceClass.StaticNested.method(target);throw new Exception("Field is null");}target.localInnerMethod(SourceClass.this.outerProtected);return target.field;}}
package same;
private class TargetClass {Object field;
void localInnerMethod(String data) {class LocalInner {void process() {field = data;}}new LocalInner().process();}}
