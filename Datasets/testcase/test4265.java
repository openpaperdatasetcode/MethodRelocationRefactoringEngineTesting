package test;
public sealed enum SourceEnum extends ParentEnum permits SourceEnum.Impl {INSTANCE;
protected TargetEnum targetField = TargetEnum.VALUE;
class FirstInner {protected void overloadedMethod() {assert targetField != null;SecondInner inner = new SecondInner();super.toString();}
protected void overloadedMethod(int param) {this.field = param;super.toString();targetField.process();}
private int field;}
class SecondInner {SecondInner() {super();}}
static final class Impl extends SourceEnum {}}
abstract class ParentEnum {}
protected enum TargetEnum extends ParentTarget {VALUE;
void process() {}}
abstract class ParentTarget {}
class OthersClass {public String callOverloaded(TargetEnum target) {SourceEnum source = SourceEnum.INSTANCE;SourceEnum.FirstInner inner = source.new FirstInner();if (target == TargetEnum.VALUE) {inner.overloadedMethod();return "called";} else {inner.overloadedMethod(10);return "called with param";}}}