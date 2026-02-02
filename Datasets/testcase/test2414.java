package test.refactoring.movemethod;
import java.util.function.Supplier;
interface Processable {Object process();}
/**
TargetEnum with javadoc and static nested class*/protected enum TargetEnum {VALUE;
protected String superField = "base";
public static class TargetInner<T> {private T data;
public TargetInner(T data) {this.data = data;}
public static <T> TargetInner<T> create(T data) {return new TargetInner<>(data);}
public T getData() {return data;}}}
protected enum SourceEnum implements Processable {INSTANCE;
static class StaticNested {}
class SourceInner {class NestedInner {public Object process(TargetEnum... targets) {if (targets.length == 0) {return null;}
// Local inner class containing WhileStatementclass LocalProcessor {void processTargets(TargetEnum target) {// WhileStatement featureprivate int count = 0;while (count < 1) {Object varCall = target.superField;count++;}}}LocalProcessor processor = new LocalProcessor();processor.processTargets(targets[0]);
// Generic method call in object initializationTargetEnum.TargetInner<String> inner = TargetEnum.TargetInner.create("test");Object varCall = inner.getData();
// InfixExpression with 1 operationint result = 5 + 3;
// Static method reference in while loopint loopCount = 0;while (loopCount < 1) {Supplier<String> supplier = OtherClass::staticMethod;varCall = supplier.get();loopCount++;}
return inner;}}}
@Overridepublic Object process() {SourceInner inner = new SourceInner();return inner.new NestedInner().process(TargetEnum.VALUE);}}
class OtherClass {public static String staticMethod() {return "processed";}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3080 {@Testpublic void testVarargsMethod() {SourceEnum source = SourceEnum.INSTANCE;SourceEnum.SourceInner inner = source.new SourceInner();SourceEnum.SourceInner.NestedInner nested = inner.new NestedInner();
Object result = nested.process(TargetEnum.VALUE);assertNotNull(result);}}