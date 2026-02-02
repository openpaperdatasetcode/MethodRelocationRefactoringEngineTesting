package test.refactoring.movemethod;
import java.util.function.IntConsumer;
public class SourceClass extends SuperClass {private TargetClass targetField = new TargetClass();
public class SourceInnerClass {private int sumValues(Integer... values) throws NullPointerException {if (values == null) {throw new NullPointerException("Values cannot be null");}
int total = 0;TargetClass.AnonymousInnerInterface inner = new TargetClass.AnonymousInnerInterface() {@Overridepublic void accept(int num) {total += num;}};
{TargetClass newTarget = new TargetClass();newTarget.superMethod();inner.accept(newTarget.getValue());}
for (Integer val : values) {assert val != null : "Individual value cannot be null";total += val;targetField.process(val, inner);}
return total;}}
public int triggerInnerMethod(Integer... values) throws NullPointerException {SourceInnerClass inner = new SourceInnerClass();return inner.sumValues(values);}}
class SuperClass {protected void superMethod() {}}
private class TargetClass {public interface AnonymousInnerInterface extends IntConsumer {}
private int value = 5;
public TargetClass() {super();}
public int getValue() {return value;}
public void process(int num, AnonymousInnerInterface consumer) {consumer.accept(num * 2);}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
public class MoveMethod5348Test {@Testvoid testMoveMethod_Success() throws NullPointerException {SourceClass source = new SourceClass();int expected = 5 + 1 + 2 + 3 + (12) + (22) + (3*2); // 5 (target init) + 6 (values sum) + 12 (processed sum) = 23int actual = source.triggerInnerMethod(1, 2, 3);
assertEquals(expected, actual);}
@Testvoid testMoveMethod_NullValues() {SourceClass source = new SourceClass();assertThrows(NullPointerException.class, () -> source.triggerInnerMethod(null));}
@Testvoid testMoveMethod_NullElement() {SourceClass source = new SourceClass();assertThrows(AssertionError.class, () -> source.triggerInnerMethod(1, null, 3));}
@Testvoid testMoveMethod_Refactored() throws NullPointerException {TargetClass target = new TargetClass();SourceClass source = new SourceClass();SourceClass.SourceInnerClass inner = source.new SourceInnerClass();
int expected = 5 + 4 + 5 + 6 + (42) + (52) + (6*2); // 5 + 15 + 30 = 50int actual = target.sumValues(source, inner, 4, 5, 6);
assertEquals(expected, actual);}}