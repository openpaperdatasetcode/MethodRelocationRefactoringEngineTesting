package test.refactoring.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
private class TargetClass<T> {public static int staticField = 1;
static class TargetNested {
private U value;
public TargetNested(U value) {this.value = value;}
public U getValue() {return value;}}
private T data;
public TargetClass(T data) {this.data = data;}
public T getData() {return data;}}
class ParentOtherClass {protected List<String> getDefaultList() {return new ArrayList<>();}}
class OtherClass extends ParentOtherClass {@Overridepublic List<String> getDefaultList() {List<String> list = super.getDefaultList();list.add("default");return list;}}
class SubClass extends OtherClass {public synchronized String processString(String input) {return input.toUpperCase();}}
public class SourceClass<T> {static class StaticNested {}
class SourceInner {@MethodAnnotpublic TargetClass<T> process(List<TargetClass<T>> targets) {if (targets.isEmpty()) {return new TargetClass<>(null);}
// Super constructor invocationclass Base {}class Derived extends Base {Derived() {super();}}
// AssertStatement featureassert TargetClass.staticField == 1 : "Static field mismatch";
// Normal method in constructor parameter listOtherClass other = new OtherClass();TargetClass.TargetNested<List<String>> nested =new TargetClass.TargetNested<>(other.getDefaultList());
// Super keyword usageList<String> superList = new SubClass().getDefaultList();
// Expression statementTargetClass<T> firstTarget = targets.get(0);Object varCall = firstTarget.getData();
// Sub class method reference in expressionSupplier<String> stringProcessor = SubClass::processString;String processed = stringProcessor.get().apply("test");
return firstTarget;}}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.Arrays;
public class MoveMethodTest3110 {@Testpublic void testInstanceMethod() {SourceClass<String> source = new SourceClass<>();SourceClass<String>.SourceInner inner = source.new SourceInner();TargetClass<String> target = new TargetClass<>("test");
TargetClass<String> result = inner.process(Arrays.asList(target));assertNotNull(result);assertEquals("test", result.getData());}}