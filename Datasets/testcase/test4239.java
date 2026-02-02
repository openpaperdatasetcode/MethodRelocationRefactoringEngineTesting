package same.pkg;
interface MyInterface {int getValue();}
class ParentEnum {}
protected enum SourceEnum implements MyInterface permits SubSourceEnum {INSTANCE;
static class StaticNested {int process(TargetEnum targetParam) {return targetParam.new Inner().calculate();}}
class MemberInner {void useTarget(TargetEnum target) {TargetEnum.Inner inner = target.new Inner();inner.action();}}
@Overridepublic strictfp int getValue() {TargetEnum target = TargetEnum.VALUE;int result = 0;switch (target.ordinal()) {case 0:result = TargetEnum.STATIC_FIELD;break;default:result = 1;}
MemberInner inner = new MemberInner();inner.useTarget(target);
while (result < 5) {result += super.getValue();}
return result;}
private int getValue(int a) {return a + 1;}}
enum SubSourceEnum extends SourceEnum {}
final enum TargetEnum extends ParentEnum {VALUE;
static final int STATIC_FIELD = 10;
class Inner {int calculate() {return STATIC_FIELD;}
void action() {}}}