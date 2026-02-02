package test.refactoring.movemethod;
sealed class Parent permits SourceClass {public int baseValue() {return 0;}
public int baseValue(int a) {return a;}}
private class TargetClass {int targetField;
private TargetClass(int val1, int val2, int val3) {this.targetField = val1 + val2 + val3;}
public TargetClass createNew(int v) {return new TargetClass(v, v, v);}
public int getValue() {return targetField;}}
protected class SourceClass extends Parent {private TargetClass targetField = new TargetClass(1, 1, 1);
class SourceInner {/**
Javadoc for overriding method
@return base type result*/@Overridepublic int baseValue() {class LocalInner {int compute() {return targetField.targetField * 2;}}
LocalInner local = new LocalInner();Object varCall = local.compute();
int count = 0;do {TargetClass newTarget = targetField.createNew(3);int value = newTarget.getValue().baseValue().baseValue(1);count++;} while (count < 1);
return targetField.targetField;}
public int baseValue(String s) {return s.length();}}
{new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();inner.baseValue();}}.run();}
public SourceClass() {this(new TargetClass(2, 2, 2).baseValue());}
private SourceClass(int init) {targetField = new TargetClass(init, init, init);}
protected <T> void callMethod(T param) {new TargetClass(1, 1, 1).baseValue();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3030 {@Testpublic void testOverridingMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();int result = inner.baseValue();assertEquals(6, result);}}