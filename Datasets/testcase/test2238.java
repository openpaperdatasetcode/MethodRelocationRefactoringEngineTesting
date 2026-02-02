package source;
import target.TargetClass;
public class SourceClass {static class FirstStaticNested {}static class SecondStaticNested {}
protected int getValue() {class LocalType {}TargetClass target = new TargetClass();TargetClass.MemberInner inner = target.new MemberInner();int field = inner.obj.field;
if (field == 2) {recursiveMethod(1);} else {field++;}
return field;}
private void recursiveMethod(int num) {if (num > 0) {super.toString();new TargetClass.MemberInner().recursiveMethod(num - 1);}}}
package target;
class TargetClass {class MemberInner {SourceClass obj = new SourceClass();
private void recursiveMethod(int num) {if (num > 0) {recursiveMethod(num - 1);}}}}