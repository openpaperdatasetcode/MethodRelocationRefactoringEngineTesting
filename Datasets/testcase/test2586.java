package test.refactoring.movemethod;
/**
Target enum with javadoc and type parameter*/protected enum TargetEnum<T> {INSTANCE;
private T targetField;
{new Runnable() {@Overridepublic void run() {targetField = (T) "initialized";}}.run();}
class TargetInner {T getValue() {return targetField;}}}
public enum SourceEnum<S> {VALUE;
private static int staticField = 5;private S outerPrivateField;
static class StaticNested {}
class SourceInner {class NestedInner {strictfp TargetEnum<TargetEnum.InnerType>.TargetInner process(TargetEnum<TargetEnum.InnerType>.TargetInner... targets) {class LocalInner {int compute() {return staticField + 3;}}
LocalInner local = new LocalInner();Object varCall = local.compute();
if (targets.length == 0) {TargetEnum<TargetEnum.InnerType> target = TargetEnum.INSTANCE;return target.new TargetInner();}
outerPrivateField = (S) targets[0].getValue();return targets[0];}}}
public static class InnerType {}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3036 {@Testpublic void testVarargsMethod() {SourceEnum<SourceEnum.InnerType> source = SourceEnum.VALUE;SourceEnum.SourceInner inner = source.new SourceInner();SourceEnum.SourceInner.NestedInner nested = inner.new NestedInner();
TargetEnum<TargetEnum.InnerType> target = TargetEnum.INSTANCE;TargetEnum<TargetEnum.InnerType>.TargetInner targetInner = target.new TargetInner();
TargetEnum<TargetEnum.InnerType>.TargetInner result = nested.process(targetInner);assertNotNull(result);}}