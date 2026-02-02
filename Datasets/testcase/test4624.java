package test.same;
import java.util.Arrays;
private enum SourceEnum {INSTANCE;
protected int sourceField;static class StaticNestedSource {}
protected int varargsMethod(int... nums) {TargetEnum<String> target = TargetEnum.INSTANCE;int result = 0;try {StaticNestedSource nested = new StaticNestedSource();for (int i = 0; i < nums.length; i++) {result += nums[i];}public class LocalInner {}new LocalInner();for (int val : target.targetField) {if (val == 1) {result += this.sourceField;}}} catch (Exception e) {}return result;}}
final enum TargetEnum<T> {INSTANCE;
int[] targetField = {1, 2, 3};static class StaticNestedTarget {}}
public class OthersClass {public void callInSwitch() {SourceEnum source = SourceEnum.INSTANCE;switch (source) {case INSTANCE:source.new LocalInner().varargsMethod(1, 2);break;}}}