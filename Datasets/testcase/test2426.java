package test.refactoring.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Function;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
class TargetClass {static class TargetInner<T> {private T value;
public TargetInner(T value) {this.value = value;}
public T getValue() {return value;}
public static String convert(Object obj) {return obj.toString();}}}
class SourceClass {class SourceInner {@MethodAnnotpublic int process(TargetClass.TargetInner... generics) { // Local inner classes (two) class LocalProcessor1 { int processItem(TargetClass.TargetInner item) {return item.getValue().toString().length();}}class LocalProcessor2 {LocalProcessor2() {}}
// Type declaration statementTargetClass.TargetInner rawType = new TargetClass.TargetInner("raw");Object varCall = rawType.getValue();
// Super constructor invocationclass Base {}class Derived extends Base {Derived() {super();}}
// Array initialization with source method callString[] results = {SourceClass.convert(generics[0].getValue())};
// TypeMethodReferenceFunction<Object, String> converter = TargetClass.TargetInner::convert;
// For statementint total = 0;for (int i = 0; i < generics.length; i++) {if (generics[i] == null) {throw new NullPointerException("Generic item cannot be null");}total += new LocalProcessor1().processItem(generics[i]);}
// Switch statementswitch (generics.length) {case 0:throw new IllegalArgumentException("No items provided");case 1:total *= 2;break;default:total /= 2;}
return total;}}
static String convert(Object obj) {return obj.toString();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3104 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();TargetClass.TargetInner<String> inner1 = new TargetClass.TargetInner<>("test");TargetClass.TargetInner<Integer> inner2 = new TargetClass.TargetInner<>(1234);
int result = inner.process(inner1, inner2);assertEquals(4, result);}}