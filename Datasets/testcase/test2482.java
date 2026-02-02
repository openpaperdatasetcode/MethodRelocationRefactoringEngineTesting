package test.refactoring.movemethod;
import java.util.List;
abstract class TargetClass {protected String baseData;
public TargetClass(String baseData) {this.baseData = baseData;}
public abstract <T> TargetInner<T> createInner(T value);
public static class TargetStaticNested {public static String formatValue(U value) {
return "Formatted: " + value;
}
}
public class TargetInner<T> {private T innerValue;
public TargetInner(T innerValue) {this.innerValue = innerValue;}
public T getInnerValue() {return innerValue;}
public void setInnerValue(T innerValue) {this.innerValue = innerValue;}}}
private abstract class SourceClass extends TargetClass {protected int outerProtected = 100;
public SourceClass(String baseData) {super(baseData);}
@Overridepublic <T extends CharSequence> TargetInner<T> createInner(T value) {// Super constructor invocation in inner contextclass DerivedInner extends TargetInner<T> {DerivedInner(T value) {super(value);}}
// Variable callObject varCall = value.toString();
// Access outer protectedint adjusted = outerProtected + value.length();
// With bounds (T extends CharSequence)TargetInner<T> inner = new DerivedInner(value);inner.setInnerValue((T) (value + "processed" + adjusted));
return inner;}}
class ConcreteSource extends SourceClass {public ConcreteSource(String baseData) {super(baseData);}}
class ConcreteTarget extends TargetClass {public ConcreteTarget(String baseData) {super(baseData);}
@Overridepublic <T> TargetInner<T> createInner(T value) {return new TargetInner<>(value);}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3181 {@Testpublic void testOverridingMethod() {SourceClass source = new ConcreteSource("source_base");TargetClass.TargetInner<String> inner = source.createInner("test_value");
assertEquals("test_value_processed_110", inner.getInnerValue());}}