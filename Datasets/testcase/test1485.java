package test.refactor.movemethod;
class ParentClass {public TargetClass createTargetInstance() {return new TargetClass();}}
private class TargetClass extends ParentClass {private String targetField;
public void processInner() {class TargetLocalInner {public void setField(String value) {targetField = value;}}TargetLocalInner inner = new TargetLocalInner();inner.setField("init");}
public class TargetInner {public void updateField(String... values) {for (String val : values) {targetField += val;}}}}
private class SourceClass extends ParentClass {private static class StaticNestedClass {public static String getStaticValue() {return "staticNested";}}
private int calculateSum(int... nums) {int sum = 0;labeledBlock: {class SourceLocalInner {public int processVarargs(int... args) {int res = 0;for (int arg : args) {res += arg;if (arg < 0) break labeledBlock;}return res;}}
SourceLocalInner localInner = new SourceLocalInner();sum += localInner.processVarargs(nums);
TargetClass targetInstance = super.createTargetInstance();TargetClass.TargetInner targetInner = targetInstance.new TargetInner();
String name1 = "Alice";String name2 = "Bob";String name3 = StaticNestedClass.getStaticValue();targetInner.updateField(name1, name2, name3);
this.targetField = targetInstance.targetField;}return sum;}
private String targetField;
protected String callSourceMethod() {SourceClass source = new SourceClass();int sum = source.calculateSum(1, 2, 3);source.targetField = source.calculateSum(4, 5) + "_" + sum;return source.targetField;}}
// Test case classpublic class MoveMethodTest5149 {public static void main(String[] args) {SourceClass source = new SourceClass();String result = source.callSourceMethod();assert result != null : "Call method return check";assert source.calculateSum(10, 20) == 30 : "Varargs method calculation check";}}