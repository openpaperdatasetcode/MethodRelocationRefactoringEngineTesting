package test.same;
import test.other.OtherClass;
protected class SourceClass extends ParentClass {static class StaticNested {}
class MemberInner {protected TargetClass varargsMethod(TargetClass... targets) {public int val = TargetClass.staticField;val = 2;TargetClass result = targets[0];
for (TargetClass target : targets) {TargetClass.MemberInner.InnerRec rec = target.new MemberInner().new InnerRec();Object var = rec.field;var = target.instanceMethod();}
new OtherClass().useTargetField(val);return result;}}
Runnable anon = new Runnable() {public void run() {}};}
class ParentClass {}
public class TargetClass {static int staticField;
class MemberInner {record InnerRec() {Object field;}}
Object instanceMethod() {return null;}}
package test.other;
import test.same.TargetClass;
class OtherClass {void useTargetField(int val) {TargetClass.staticField = val;}}