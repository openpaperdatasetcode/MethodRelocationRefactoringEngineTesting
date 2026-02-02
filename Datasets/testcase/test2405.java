package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
class ParentClass {}
protected class TargetClass {static class TargetInner {static class NestedInner {private String value;
public NestedInner(String value) {this.value = value;}
public final String getValue() {return value;}}}}
protected class SourceClass extends ParentClass {static class StaticNested {}
class SourceInner {private List<String> process(TargetClass.TargetInner.NestedInner nested) {List<String> result = new ArrayList<>();Object varCall = nested.getValue();
class Base {Base() {}}class Derived extends Base {Derived() {super();}}Derived derived = new Derived();
int count = 0;loop: while (count < 5) {if (nested instanceof TargetClass.TargetInner.NestedInner) {result.add(nested.getValue());}if (nested instanceof Object) {result.add("object");}if (derived instanceof Base) {result.add("base");}count++;if (count == 3) {break loop;}}
Function<TargetClass.TargetInner.NestedInner, String> func = (n) -> n.getValue();TargetClass.TargetInner.NestedInner newInner = new TargetClass.TargetInner.NestedInner(func.apply(nested));result.add(newInner.getValue());
return result;}
private List<String> process(Integer num) {return new ArrayList<>();}}
{new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();TargetClass.TargetInner.NestedInner nested = new TargetClass.TargetInner.NestedInner("test");inner.process(nested);}}.run();}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3068 {@Testpublic void testOverloadingMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();TargetClass.TargetInner.NestedInner nested = new TargetClass.TargetInner.NestedInner("input");List<String> result = inner.process(nested);assertEquals(4, result.size());}}