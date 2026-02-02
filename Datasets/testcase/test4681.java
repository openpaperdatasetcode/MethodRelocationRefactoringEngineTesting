package test;
sealed enum SourceEnum extends BaseEnum {FIRST(10), SECOND(20);
private final int sourceValue;
SourceEnum(int sourceValue) {super(sourceValue);this.sourceValue = sourceValue;}
class MemberInner {public int getCombinedValue(TargetEnum<String> target) {return sourceValue + target.targetValue;}}
static class StaticNested {public static TargetEnum<String> createTarget(int value, String data) {return TargetEnum.create(value, data);}}
public TargetEnum<String> processTarget(TargetEnum<String> target, int depth) {if (depth <= 0) {try {throw new IllegalArgumentException("Depth cannot be non-positive");} catch (IllegalArgumentException e) {System.out.println("Handled exception: " + e.getMessage());return target;}}
labeledBlock: {MemberInner inner = new MemberInner();System.out.println("Combined Value: " + inner.getCombinedValue(target));
if (target.targetValue == 1) {break labeledBlock;}}
TargetEnum<String> newTarget = TargetEnum.StaticNestedTarget.update(target, depth);return processTarget(newTarget, depth - 1);}}
abstract class BaseEnum {protected int baseValue;
public BaseEnum(int baseValue) {this.baseValue = baseValue;}
public int getBaseValue() {return baseValue;}}
protected enum TargetEnum<T> {A(1, "DataA"), B(2, "DataB");
int targetValue;T targetData;
TargetEnum(int targetValue, T targetData) {this.targetValue = targetValue;this.targetData = targetData;}
static <T> TargetEnum<T> create(int value, T data) {return value == 1 ? (TargetEnum<T>) A : (TargetEnum<T>) B;}
static class StaticNestedTarget {public static <T> TargetEnum<T> update(TargetEnum<T> target, int increment) {int newVal = target.targetValue + increment;return create(newVal, target.targetData);}}}