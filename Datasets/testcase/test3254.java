package test;
interface TestInterface {}
protected enum SourceEnum {INSTANCE;
class FirstSourceMemberInner {}class SecondSourceMemberInner {}
public void instanceMethodToMove(TargetEnum targetParam) {FirstSourceMemberInner inner1 = new FirstSourceMemberInner();SecondSourceMemberInner inner2 = new SecondSourceMemberInner();
assert targetParam != null : "Target parameter must not be null";privateAssert(targetParam.getInnerField());
try {targetParam.getInnerClass().doAction();inner1.toString();inner2.hashCode();} catch (Exception e) {}}
private void privateAssert(TargetEnum.TargetMemberInner inner) {assert inner != null : "Inner class instance must not be null";}}
public enum TargetEnum implements TestInterface {VALUE;
private TargetMemberInner innerField = new TargetMemberInner();
class TargetMemberInner {void doAction() {}}
TargetMemberInner getInnerClass() {return innerField;}
TargetMemberInner getInnerField() {return innerField;}}