package test;
abstract class SourceClass {class SourceInner {class SourceInnerRec {protected Object methodToMove(TargetClass target) {if (target == null) throw new NullPointerException("Target cannot be null");
// Access target's member inner class and its fieldTargetClass.MemberInner inner = target.new MemberInner();inner.field = 10; // Access_instance_fieldObject fieldVal = inner.field; // Variable call
// Expression statementinner.updateField(5);
// Call target's private static method in if/elseif (fieldVal instanceof Integer) {int result = TargetClass.staticCall(inner);inner.field = result;} else {TargetClass.staticCall(inner);}
return inner.field;}}}
// First local inner classpublic void firstLocalInner() {class LocalInnerOne {void useTarget(TargetClass target) {target.new MemberInner().field = 3;}}new LocalInnerOne().useTarget(new TargetClass());}
// Second local inner classpublic void secondLocalInner() {class LocalInnerTwo {int getTargetField(TargetClass target) {return target.new MemberInner().field;}}new LocalInnerTwo().getTargetField(new TargetClass());}}
public class TargetClass {public class MemberInner {public int field;
public void updateField(int val) {this.field = val;}}
// Call_method: target type, private static, called in if/elseprivate static int staticCall(MemberInner inner) {return inner.field * 2;}}