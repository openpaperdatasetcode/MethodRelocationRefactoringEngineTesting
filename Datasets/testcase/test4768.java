package test;
enum SourceEnum {VALUE;
protected int outerProtected;public int instanceField;
public class MemberInner {}public static class StaticNested {}
public Object instanceMethod(TargetEnum<String> target) {target.nested = new TargetEnum.Nested();target.nested.setValue(1);Object result = target.nested.getResult(target);
target.field = "test";outerProtected = 5;int val = instanceField;
MemberInner inner = new MemberInner();inner.toString();
return result;}
final void callInFor(TargetEnum<Integer> target) {for (int i = 0; i < 3; i++) {new SourceEnum.StaticNested();super.toString();}}}
protected enum TargetEnum<T> {TARGET;
public T field;public Nested nested;
public static class Nested {private Object value;
private Object setValue(int num) {this.value = num;return value;}
private Object getResult(TargetEnum<?> target) {return target.field;}}}