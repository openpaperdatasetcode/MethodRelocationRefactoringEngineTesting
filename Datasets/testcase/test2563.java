package test.refactoring.movemethod;
public enum TargetEnum {INSTANCE}
enum SourceEnum {VALUE1, VALUE2;
class InnerClass1 {}
class InnerClass2 {class NestedInnerClass {Object processArgs(String... args) {try {TargetEnum target = new TargetEnum();Object fieldVal = target.INSTANCE;
do {System.out.println("Processing varargs");} while (args.length < 1);
if (args == null) {throw new IllegalArgumentException("Varargs cannot be null");}
int num = 1;Object[] array = new Object[num];array[0] = args[0];
Object varCall = array.clone();Class<?> clazz = Class.forName("test.refactoring.movemethod.TargetEnum");return clazz.getDeclaredMethod("toString").invoke(target);} catch (ClassNotFoundException | ReflectiveOperationException e) {throw new RuntimeException(e);}}}}}
class SubClass extends SourceEnum.InnerClass2.NestedInnerClass {public TargetEnum invokeMethod() {SourceEnum.InnerClass2.NestedInnerClass instance = new SourceEnum.VALUE1.new InnerClass2().new NestedInnerClass();return (TargetEnum) ((Runnable) () -> instance.processArgs("arg1", "arg2")).run();}}
import org.junit.Test;import static org.junit.Assert.assertNotNull;
public class MoveMethodTest2905 {@Testpublic void testMoveVarargsMethodFromNestedInnerToTargetEnum() {SubClass subClass = new SubClass();TargetEnum result = subClass.invokeMethod();assertNotNull(result);}}