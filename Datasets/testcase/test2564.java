package test.refactoring.movemethod;
public class TargetClass<T extends Number> {public int targetField;
public TargetClass() {Runnable anon = new Runnable() {@Overridepublic void run() {targetField = 42;}};anon.run();}
public void targetInstanceMethod() {targetField *= 2;}}
public class SourceClass<T extends CharSequence> {private TargetClass<Integer> targetField;
public SourceClass(TargetClass<Integer> target) {this.targetField = target;}
class InnerClass1 {}
class InnerClass2 {}
public final Object sourceMethod() {class LocalType {int value;LocalType(int v) { value = v; }}
LocalType localVar = new LocalType(targetField.targetField);Object varCall = localVar.value;
targetField.targetInstanceMethod();return SourceClass.this.targetField.targetField;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3005 {@Testpublic void testMoveMethod() {TargetClass<Integer> target = new TargetClass<>();SourceClass<String> source = new SourceClass<>(target);Object result = source.sourceMethod();assertEquals(84, result);}}