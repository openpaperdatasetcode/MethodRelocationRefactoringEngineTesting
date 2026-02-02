  }
package test;
public enum SourceEnum {INSTANCE;
protected TargetEnum targetField;
public class LocalInner {public static class NestedInner {protected NestedInner(List<Integer> list) {try {int sum = 0;for (int num : list) {sum += num;}SourceEnum.this.targetField = TargetEnum.VALUE;} catch (NullPointerException e) {e.printStackTrace();}}}}}
protected enum TargetEnum {VALUE;
protected static class TargetNested {}}