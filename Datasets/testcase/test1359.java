package test.refactor.movemethod;
import java.lang.reflect.Method;
/**
Javadoc for PrivateTargetEnum - target enum for move method refactoring with anonymous inner class*/private enum PrivateTargetEnum {INSTANCE;
public int targetField = 42;
public void targetMethod() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in target enum");}};runnable.run();}}
enum SourceEnum {VALUE;
private PrivateTargetEnum targetField = PrivateTargetEnum.INSTANCE;
class InnerClass {strictfp <T extends Number> int genericMethod(T param) {class NestedInner {protected PrivateTargetEnum createTarget() {return new PrivateTargetEnum();}}
NestedInner nested = new NestedInner();PrivateTargetEnum target = nested.createTarget();int value = target.targetField;
while (value < 100) {SubClass sub = new SubClass();String result = sub.callSubClassMethod();value += result.length();}
new PrivateTargetEnum().targetMethod();variableCall(target);return this;}
private void variableCall(PrivateTargetEnum target) {System.out.println(target.targetField);}}
public InnerClass getInnerClass() {return new InnerClass();}}
class SubClass extends SourceEnum.InnerClass {protected String callSubClassMethod() {SourceEnum source = SourceEnum.VALUE;SourceEnum.InnerClass inner = source.getInnerClass();return new SourceEnum().getInnerClass().genericMethod(10);}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoringTest5326 {@Testpublic void testMoveMethodRefactoring() throws Exception {SourceEnum source = SourceEnum.VALUE;SourceEnum.InnerClass inner = source.getInnerClass();
// Test direct callint directResult = inner.genericMethod(5);assertEquals(42, directResult);
// Test reflection call (used_by_reflection feature)Method reflectMethod = SourceEnum.InnerClass.class.getDeclaredMethod("genericMethod", Number.class);reflectMethod.setAccessible(true);int reflectResult = (int) reflectMethod.invoke(inner, 15);assertEquals(42, reflectResult);}}