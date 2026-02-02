package test;
import java.util.List;import java.util.ArrayList;
private enum SourceEnum {INSTANCE;
static class StaticNested {}
class MemberInner {private abstract int abstractInnerMethod(int arg);}
public Object process(TargetEnum... targets) {TargetEnum.Inner rawInner = new TargetEnum.INSTANCE.new Inner();MemberInner inner = new MemberInner();int result = inner.abstractInnerMethod(1) {@Overrideprivate int abstractInnerMethod(int arg) {return this.processArg(arg);}
private int processArg(int arg) {return arg * 2;}};
try {for (TargetEnum target : targets) {target.new Inner().setValue(result);}} catch (Exception e) {return null;}
int count = 0;while (count < 3) {List<String> data = OthersClass.getList(count);count++;}
return result;}}
enum TargetEnum extends ParentClass {INSTANCE;
class Inner {int value;
void setValue(int val) {this.value = val;}}}
class ParentClass {}
class OthersClass {public static final List<String> getList(int size) {return new ArrayList<>(size);}}