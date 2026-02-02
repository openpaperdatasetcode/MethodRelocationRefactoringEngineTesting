package test;
private enum SourceEnum {ENUM_ONE, ENUM_TWO;
private int sourceField = 10;
class MemberInner {public abstract int calculate(TargetEnum target);
public int process(TargetEnum target) {return calculate(target) + SourceEnum.this.sourceField;}}
static class StaticNested {public static MemberInner createInner() {return new SourceEnum.ENUM_ONE.MemberInner() {@Overridepublic int calculate(TargetEnum target) {return target.targetValue;}};}}
public int handleTarget(TargetEnum target) {MemberInner inner = StaticNested.createInner();return inner.process(target);}}
/**
Target enum with value storage for calculation
Provides target value for SourceEnum's inner class processing*/public enum TargetEnum {TARGET_A(20), TARGET_B(30);
int targetValue;
TargetEnum(int targetValue) {this.targetValue = targetValue;}
public int getTargetValue() {return targetValue;}}