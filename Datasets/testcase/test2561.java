package test.refactoring.movemethod;
strictfp class TargetClass extends ParentClass {static class TargetInner {private int value;
public TargetInner(int v) {this.value = v;}
public int getValue() {return value;}
protected int overloadedMethod() {return 0;}}}
class ParentClass {public synchronized Object parentMethod(TargetClass.TargetInner inner) {return inner.getValue();}}
protected class SourceClass {static class StaticNested {}
class MemberInner {private int count = 0;
public TargetClass.TargetInner createInner(int v) {return new TargetClass.TargetInner(v);}}
protected int overloadedMethod(TargetClass.TargetInner targetInner) {if (targetInner == null) {throw new NullPointerException("Target inner is null");}
Object varCall = targetInner.getValue();int result = (Integer) varCall;
synchronized (this) {result *= 2;}
MemberInner inner = new MemberInner();if (count < 3) {count++;result += overloadedMethod(inner.createInner(result));}
return result;}
private int count = 0;
public SourceClass() {this(new TargetClass().new TargetInner(1).getValue());}
private SourceClass(int value) {TargetClass.TargetInner inner = new TargetClass.TargetInner(value);new ParentClass().parentMethod(inner).toString().hashCode();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3020 {@Testpublic void testOverloadedMethod() {SourceClass source = new SourceClass();TargetClass.TargetInner inner = new TargetClass.TargetInner(2);int result = source.overloadedMethod(inner);assertTrue(result > 0);}}