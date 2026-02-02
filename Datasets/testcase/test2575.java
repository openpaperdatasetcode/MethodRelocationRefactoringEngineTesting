package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.function.Supplier;
interface MyInterface {void doSomething();}
public enum TargetEnum implements MyInterface {INSTANCE;
public int targetField = 1;
static class TargetInner {void innerMethod() {}}
@Overridepublic void doSomething() {}}
protected enum SourceEnum extends ParentClass implements MyInterface {VALUE1, VALUE2;
private String outerPrivateField = "private";
class SourceInner {class NestedInner {void sourceMethod(TargetEnum.TargetInner targetInner) {try {Object varCall = outerPrivateField;assert targetInner != null : "Target inner is null";
if (TargetEnum.INSTANCE.targetField != 1) {throw new IllegalStateException("Invalid field value");}
Method method = TargetEnum.TargetInner.class.getMethod("innerMethod");method.invoke(targetInner);} catch (ReflectiveOperationException e) {throw new RuntimeException(e);}}}}
{new Supplier<Void>() {@Overridepublic Void get() {SourceInner inner = new SourceInner();inner.new NestedInner().sourceMethod(TargetEnum.INSTANCE.new TargetInner());return null;}}.get();
new Runnable() {@Overridepublic void run() {VALUE1.new SourceInner().new NestedInner().sourceMethod(TargetEnum.INSTANCE.new TargetInner());}}.run();}
@Overridepublic void doSomething() {}}
class ParentClass {}
import org.junit.Test;
public class MoveMethodTest3023 {@Testpublic void testMethod() {SourceEnum source = SourceEnum.VALUE1;SourceEnum.SourceInner inner = source.new SourceInner();SourceEnum.SourceInner.NestedInner nested = inner.new NestedInner();nested.sourceMethod(TargetEnum.INSTANCE.new TargetInner());}}