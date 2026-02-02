package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
interface MyInterface {}
non-sealed abstract class SourceClass implements MyInterface {private int outerPrivateField = 5;protected TargetClass targetField;private static int staticField = 10;
TargetClass moveMethod() {label: {if (targetField == null) {break label;}TargetClass.MemberInner inner = targetField.new MemberInner();inner.doSomething();System.out.println(super.toString());variableCallExample();System.out.println(outerPrivateField);accessInstanceMethod();System.out.println(staticField);System.out.println(SourceClass.this.outerPrivateField);}try {Method method = TargetClass.class.getMethod("moveMethod");method.invoke(targetField);} catch (Exception e) {}return targetField;}
private void variableCallExample() {}
private void accessInstanceMethod() {}
static class StaticNested {void createAnonymous() {new Runnable() {public void run() {targetField.new MemberInner().action();}}.run();}}}
final abstract class TargetClass<T> {class MemberInner {void doSomething() {}void action() {}}
TargetClass moveMethod() {return this;}}
class OthersClass {protected int callMethod() {List<TargetClass.MemberInner> list = new ArrayList<>();return list.stream().mapToInt(inner -> TargetClass.MemberInner.class.hashCode()).sum();}}