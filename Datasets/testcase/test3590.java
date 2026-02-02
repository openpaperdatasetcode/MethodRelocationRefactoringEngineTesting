package test;
import java.lang.reflect.Method;
sealed abstract class SourceClass permits SourceImpl {class MemberInner {}static class StaticNested {}
private int moveMethod(TargetClass target) {OthersClass others = new OthersClass();private int fieldVal = others.getTargetField(target);
fieldVal += target.staticNested.process("data");target.inner.innerRec.action(fieldVal);
try {Method method = SourceClass.class.getDeclaredMethod("moveMethod", TargetClass.class);method.setAccessible(true);method.invoke(this, target);} catch (Exception e) {}
return fieldVal;}}
final class SourceImpl extends SourceClass {}
private abstract class TargetClass {static class StaticNested {static int process(String input) {return input.length();}}
TargetInner inner = new TargetInner();
class TargetInner {TargetInnerRec innerRec = new TargetInnerRec();
class TargetInnerRec {void action(int val) {}}}}
class OthersClass {int getTargetField(TargetClass target) {return target.inner.innerRec.hashCode();}}