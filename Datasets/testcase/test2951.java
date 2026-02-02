package test;
final class SourceClass extends ParentSource {protected int outerProtected = 10;private TargetClass<String> target = new TargetClass<>();
static class StaticNestedSource {}
Object methodToMove() {if (target == null) throw new NullPointerException("Target cannot be null");
// InfixExpression with numbers=1 and private modifierint result = target.field + 1;target.staticNestedTarget.setValue(result);
// Local inner classclass LocalInner {void process() {super.getClass(); // Super constructor invocationSystem.out.println(target.getField()); // Variable call}}new LocalInner().process();
// Expression statement and access_outer_protectedtarget.field = outerProtected;return target;}}
class ParentSource {}
public class TargetClass<T> {public int field = 5;public static class StaticNestedTarget {private int value;public void setValue(int val) {this.value = val;}}
public int getField() {return field;}}