package test.refactoring.movemethod;
import java.util.function.Supplier;
interface DataProcessor<T> {T process(T data);}
sealed class TargetClass<T> implements DataProcessor<T> permits TargetSubClass {protected T targetField;static int staticField = 3;
public TargetClass(T value) {this.targetField = value;class LocalInTarget {T getValue() {return targetField;}}}
class TargetInner {T innerValue;
public TargetInner(T val) {this.innerValue = val;}
T getInnerValue() {return innerValue;}}
@Overridepublic T process(T data) {return data;}}
final class TargetSubClass<T> extends TargetClass<T> {public TargetSubClass(T value) {super(value);}}
abstract class SourceClass<S> {private S outerPrivate;static class StaticNested {}
protected TargetClass<TargetClass.InnerType>.TargetInner createInner(TargetClass<TargetClass.InnerType>.TargetInner targetInner) {class LocalInner {TargetClass<TargetClass.InnerType>.TargetInner copy(TargetClass<TargetClass.InnerType>.TargetInner inner) {return new TargetClass<TargetClass.InnerType>(inner.getInnerValue()).new TargetInner(inner.getInnerValue());}}
LocalInner local = new LocalInner();Object varCall = local.copy(targetInner);
TargetClass<TargetClass.InnerType> target = new TargetSubClass<>(TargetClass.InnerType.DEFAULT);TargetClass<TargetClass.InnerType>.TargetInner newInner = target.new TargetInner(TargetClass.InnerType.NEW);
// Synchronized statement with this.field and 1synchronized (this) {outerPrivate = (S) targetInner.getInnerValue();}
// Overload exists demonstrationprocessValue(1);processValue("string");
return newInner;}
private void processValue(int num) {}private void processValue(String str) {}
public static class InnerType {public static final InnerType DEFAULT = new InnerType();public static final InnerType NEW = new InnerType();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3058 {@Testpublic void testInstanceMethod() {SourceClass<TargetClass.InnerType> source = new SourceClass<>() {};TargetClass<TargetClass.InnerType> target = new TargetSubClass<>(TargetClass.InnerType.DEFAULT);TargetClass<TargetClass.InnerType>.TargetInner targetInner = target.new TargetInner(TargetClass.InnerType.DEFAULT);
TargetClass<TargetClass.InnerType>.TargetInner result = source.createInner(targetInner);assertNotNull(result);}}