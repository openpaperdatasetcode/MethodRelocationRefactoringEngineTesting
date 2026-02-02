package test.refactoring.movemethod;
class TargetClass {class TargetInner {private int value;
public TargetInner(int value) {this.value = value;}
public int getValue() {return value;}
public void setValue(int value) {this.value = value;}}
public static int staticField = 5;}
class SourceClass {protected String outerProtected = "protected_value";static class StaticNested1 {}static class StaticNested2 {}
public int process(TargetClass target) {// Variable callTargetClass.TargetInner inner = target.new TargetInner(10);Object varCall = inner.getValue();
// Access outer protectedint protectedLength = outerProtected.length();
// Depends on static fieldint total = TargetClass.staticField + inner.getValue();
// Override violationclass InvalidRunnable implements Runnable {public void run(int count) {} // Invalid override of Runnable.run()}
return total + protectedLength;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3122 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();int result = source.process(target);assertEquals(20, result); // 5 (staticField) + 10 (inner value) + 5 (protected string length)}}