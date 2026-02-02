package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
/**
TargetClass with javadoc and local inner class*/private class TargetClass {private int targetField;static int staticField = 3;
public TargetClass(int value) {this.targetField = value;class LocalInTarget {int getValue() {return targetField;}}}
class TargetInner {class NestedInner {private String data;
public NestedInner(String data) {this.data = data;}
public String getData() {return data;}}}}
class OtherClass {protected synchronized int calculate() {return super.hashCode();}}
class SourceClass<T> {private T outerPrivate;static class StaticNested1 {}static class StaticNested2 {}
protected TargetClass.TargetInner.NestedInner process(TargetClass.TargetInner.NestedInner nested) {List<TargetClass.TargetInner.NestedInner> list = new ArrayList<>();list.add(nested);Object varCall = nested.getData();
// Enhanced for statementfor (TargetClass.TargetInner.NestedInner item : list) {varCall = item.getData();}
// ContinueStatement featureloop: for (int i = 0; i < 5; i++) {if (i < 3) {continue loop;}TargetClass target = new TargetClass(i);varCall = target.targetField;}
// Functional interface with others class method callSupplier<Integer> supplier = new OtherClass()::calculate;int result = supplier.get();
// Access static fieldif (TargetClass.staticField == 3) {outerPrivate = (T) nested.getData();}
return nested;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3082 {@Testpublic void testInstanceMethod() {SourceClass<String> source = new SourceClass<>();TargetClass target = new TargetClass(5);TargetClass.TargetInner inner = target.new TargetInner();TargetClass.TargetInner.NestedInner nested = inner.new NestedInner("test");
TargetClass.TargetInner.NestedInner result = source.process(nested);assertNotNull(result);assertEquals("test", result.getData());}}