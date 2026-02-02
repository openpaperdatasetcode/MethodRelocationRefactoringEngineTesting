package test.refactoring.movemethod;
import java.sql.SQLException;import java.util.function.Consumer;
public class TargetClass {public static class TargetInner {private int count;
public void increment() {count++;}
public int getCount() {return count;}}
public int value;}
private class SourceClass {static class StaticNested {}
class SourceInner {int process(TargetClass... targets) {if (targets.length == 0) {return 0;}
// Local inner classclass LocalProcessor {protected void handle(TargetClass target, int val) {target.value = val;}}LocalProcessor processor = new LocalProcessor();
// Object initialization with lambdaConsumer<TargetClass> consumer = (t) -> processor.handle(t, 5);
// Variable callObject varCall = targets[0].value;
// Assignment expressions (2)int a = 0;a = targets[0].value;TargetClass.TargetInner inner = new TargetClass.TargetInner();inner = new TargetClass.TargetInner();
// Labeled statementloop: while (inner.getCount() < 3) {inner.increment();if (inner.getCount() == 2) {break loop;}}
// SQLException handlingtry {if (targets.length < 2) {throw new SQLException("Not enough targets");}} catch (SQLException e) {// No new exception thrown}
return inner.getCount();}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3098 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();TargetClass target1 = new TargetClass();TargetClass target2 = new TargetClass();
int result = inner.process(target1, target2);assertEquals(2, result);}}