package test.same;
import java.io.IOException;
public enum SourceClass extends ParentEnum {INSTANCE;
static class StaticNested {}
class MemberInner {record InnerRec(TargetClass target) {private Object instanceMethod() throws IOException {class LocalInner {void process() {private Object var1 = target.new MemberInner().field1;private Object var2 = target.new MemberInner().field2;private Object var3 = target.new MemberInner().field3;
var1 = var2;var2 = var3;var3 = super.toString();
Object call = target.new MemberInner().field1;}}new LocalInner().process();return null;}}}}
abstract enum TargetClass {VALUE;
class MemberInner {Object field1;Object field2;Object field3;}}
enum ParentEnum {}