package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.Arrays;import java.util.List;import java.util.function.Consumer;
/**
TargetEnum contains static nested class and demonstrates javadoc*/public enum TargetEnum {VALUE1("one"), VALUE2("two");
private final String label;
TargetEnum(String label) {this.label = label;}
public static class TargetInner {private int count;
public TargetInner(int count) {this.count = count;}
public void increment() {count++;}
public int getCount() {return count;}}
public String getLabel() {return label;}}
public sealed enum SourceEnum permits SourceSubEnum {INSTANCE;
private String outerPrivate = "private_data";static class StaticNested {}
class SourceInner {class NestedInner {public TargetEnum process(TargetEnum target) throws ReflectiveOperationException {// Local inner classclass LocalProcessor {TargetEnum processTarget(TargetEnum t) {return t;}}LocalProcessor processor = new LocalProcessor();Object varCall = processor.processTarget(target);
// VariableDeclarationStatement featureTargetEnum.TargetInner inner = target.new TargetInner(2);private TargetEnum.TargetInner[] innerArray = {inner, target.new TargetInner(5)};
// Enhanced for statementList<TargetEnum> targets = Arrays.asList(TargetEnum.VALUE1, TargetEnum.VALUE2);for (TargetEnum t : targets) {varCall = t.getLabel();}
// For statementfor (int i = 0; i < innerArray.length; i++) {innerArray[i].increment();}
// Assert statementassert inner.getCount() == 3 : "Count should be 3";
// Reflection usageMethod method = TargetEnum.class.getMethod("getLabel");varCall = method.invoke(target);
// Functional interface with sub class method callConsumer<TargetEnum> consumer = t -> new SourceSubEnum().processInner(t.new TargetInner(0));consumer.accept(target);
// Throw statementif (target == null) {throw new IllegalArgumentException("Target cannot be null");}
return target;}
public TargetEnum process(Integer num) {return TargetEnum.VALUE1;}}}}
non-sealed enum SourceSubEnum extends SourceEnum {SUB_INSTANCE;
private void processInner(TargetEnum.TargetInner inner) {inner.increment();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3076 {@Testpublic void testInstanceMethod() throws ReflectiveOperationException {SourceEnum source = SourceEnum.INSTANCE;SourceEnum.SourceInner inner = source.new SourceInner();SourceEnum.SourceInner.NestedInner nested = inner.new NestedInner();
TargetEnum result = nested.process(TargetEnum.VALUE1);assertNotNull(result);assertEquals(TargetEnum.VALUE1, result);}}