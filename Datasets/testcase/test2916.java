package samepkg;
private class SourceClass<T> {public TargetClass<T> targetField; // Per condition: source contains target's field
public class MemberInnerClass {public record SourceInnerRec() {public static int processTarget() throws NullPointerException {// Raw type usageTargetClass rawTarget = SourceClass.this.targetField;
// Anonymous inner classRunnable anonymous = new Runnable() {@Overridepublic void run() {rawTarget.process();}};anonymous.run();
// Assert statementassert rawTarget != null : "Target instance must not be null";
// Expression statement + ThisExpression (1 occurrence)this.toString();
// Variable call + object initialization (calls instance method)SourceClass<T> outerInstance = new SourceClass<>();int baseVal = outerInstance.new MemberInnerClass().instanceMethod();
// ReturnStatement (inner class position, access this.field)if (rawTarget.field != 0) {return rawTarget.field + baseVal;}
// Requires_throws: NullPointerExceptionif (rawTarget == null) {throw new NullPointerException("Target is null");}
return baseVal;}}}
// Instance method for object initialization position callprotected int instanceMethod() {return 10;}}
package samepkg;
sealed class TargetClass extends ParentTargetClass permits TargetSubclass {
public int field;
public void process() {// Local inner classclass LocalInner {void updateField() {TargetClass.this.field = 20;}}new LocalInner().updateField();}
public record TargetInnerRec(int value) {public int getValue() {return value;}}}
class ParentTargetClass {}final class TargetSubclass extends TargetClass {}