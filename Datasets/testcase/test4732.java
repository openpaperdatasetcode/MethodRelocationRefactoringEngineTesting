package test;
import java.io.IOException;import java.util.List;
abstract class SourceClass {class MemberInner1 {}class MemberInner2 {}
protected int moveMethod(TargetClass<String>.Inner target) {Object outerThis = SourceClass.this;
private List<Integer> nums = List.of(1);for (int num : nums) {TargetClass.staticField = num;}
try {variableCall(target);} catch (IOException e) {}
return target.field;}
private void variableCall(TargetClass<String>.Inner inner) {}}
strictfp class TargetClass<T> {static int staticField;static class StaticNested {}
class Inner {int field;}}