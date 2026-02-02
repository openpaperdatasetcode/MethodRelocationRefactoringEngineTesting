package test;
import java.util.ArrayList;import java.util.List;
private sealed enum SourceSourceEnum permits SubSourceEnum {INSTANCE;
static class StaticNested {}
class MemberInner {private TargetEnum getTargetInstance() {return TargetEnum.VALUE;}}
private static List<String> process(TargetEnum target) throws Exception {List<String> result = new ArrayList<>();RawType raw = new RawType();MemberInner inner = SourceEnum.INSTANCE.new MemberInner();
int count = 0;while (count < 5) {TargetEnum.Inner targetInner = target.new Inner();TargetEnum.Inner.RecursiveInner recursiveInner = targetInner.new RecursiveInner();
if (inner.getTargetInstance() != null) {result.add(String.valueOf(recursiveInner.obj.field));if (recursiveInner.obj.field == 3) {break;}} else {result.add("null");}count++;}
return result;}}
non-sealed enum SubSourceEnum extends SourceEnum {}
protected enum TargetEnum extends SuperEnum {VALUE;
class Inner {class RecursiveInner {TargetField obj = new TargetField();}}
void action() {class LocalInner {int value;}}}
class SuperEnum {}
class TargetField {int field;}
class RawType<T> {}