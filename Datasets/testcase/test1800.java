package test;
public enum SourceEnum {VALUE {private int var;
@Overrideprivate int overridingMethod(TargetEnum.NestedRecord param) {this.var = param.value();TargetEnum.NestedRecord inner = new TargetEnum.NestedRecord(5);return this.var + inner.value();}};
public class MemberInner {MemberInner() {strictfp void recursiveCall(int n) {if (n <= 0) return;this.recursiveCall(n - 1);}}}
{new Runnable() {@Overridepublic void run() {new MemberInner().recursiveCall(3);}};}
private abstract int overridingMethod(TargetEnum.NestedRecord param);}
public enum TargetEnum {INSTANCE;
public record NestedRecord(int value) {NestedRecord(int value) {new SourceEnum.MemberInner().recursiveCall(value);this.value = value;}}}