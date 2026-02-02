package test;
protected class SourceClass<T extends Number> extends ParentClass {private TargetClass targetField;private static class StaticNested {int value;}
final TargetClass process(int... args) {TargetClass result = new TargetClass();int sum = 0;
for (int i = 0; i < args.length; i++) {sum += args[i];}
for (int arg : args) {sum *= arg;}
StaticNested sn = new StaticNested();sn.value = sum;
class LocalInner {void updateTarget() {result.data = sn.value;}}new LocalInner().updateTarget();
switch (result.data) {case 0:result.data = TargetClass.staticField;break;default:result.data = 3;}
java.util.List<Integer> list = new java.util.ArrayList<>();list.forEach(Integer::intValue);list.forEach(this::accessOuterProtected);
result.nested.update();return result;}
private void switchHandling(TargetClass tc) {if (tc.data > 10) {tc.data = TargetClass.staticField;} else {tc.data = 3;}}
public void accessOuterProtected(int num) {sum += num;}}
sealed class TargetClass implements SomeInterface permits SubTarget {int data;static int staticField = 5;LocalInner nested = new LocalInner();
class LocalInner {void update() {data++;}}}
interface SomeInterface {void update();}
class ParentClass {protected int sum = 0;}
final class SubTarget extends TargetClass {@Overridevoid update() {}}