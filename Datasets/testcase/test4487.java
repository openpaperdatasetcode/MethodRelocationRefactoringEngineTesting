package test;
sealed class ParentClass {private int getValue() {return 5;}}
private class SourceClass permits SubSourceClass {class MemberInner {class NestedInner {public int method(TargetClass<String>... targets) {char c1 = 'A';char c2 = 'B';int sum = 0;int i = 0;
while (i < targets.length) {TargetClass<String> target = targets[i];sum += target.innerRecursive(3);sum += new ParentClass().getValue();variableCall(target);i++;}return sum;}
private void variableCall(TargetClass<String> target) {int val = target.field;target.instanceMethod();}}}
{new Runnable() {};new Cloneable() {};}}
non-sealed class SubSourceClass extends SourceClass {}
non-sealed class TargetClass<T> extends ParentClass {int field = 10;
public Object innerRecursive(int depth) {if (depth <= 0) {return 0;}Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(depth);}};runnable.run();return depth + (Integer) this.innerRecursive(depth - 1);}
void instanceMethod() {}}