package other;
import java.util.ArrayList;import java.util.List;
private class TargetClass {class TargetInner {class NestedInner {private String value;
public NestedInner(String value) {this.value = value;}
public Object process() {return value;}
public Object process(int num) {return value + num;}
public Object process(String suffix) {return value + suffix;}}}
public TargetClass() {new Runnable() {@Overridepublic void run() {new TargetInner().new NestedInner("init");}}.run();}}
package test.refactoring.movemethod;
import other.TargetClass;import java.util.ArrayList;import java.util.List;
class SourceClass {class MemberInner {TargetClass.TargetInner.NestedInner createNested(String value) {return new TargetClass().new TargetInner().new NestedInner(value);}}
private List<String> process(TargetClass.TargetInner.NestedInner nested) {List<String> result = new ArrayList<>();Object varCall = nested.process();
// Type declaration statement with local inner classclass Processor {void handle(NestedInner inner) {result.add(inner.process().toString());}}Processor processor = new Processor();processor.handle(nested);
// Super constructor invocationclass Base {}class Derived extends Base {Derived() {super();}}new Derived();
// Overloading methods in collection operationsresult.add(nested.process(1).toString());result.add(nested.process("suffix").toString());
// Access instance method and variable callvarCall = nested.value;result.add(nested.process().toString());
return result;}
void useMethod() {MemberInner inner = new MemberInner();process(inner.createNested("test"));}}
import org.junit.Test;import static org.junit.Assert.*;import other.TargetClass;
public class MoveMethodTest3094 {@Testpublic void testNormalMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();TargetClass.TargetInner inner = target.new TargetInner();TargetClass.TargetInner.NestedInner nested = inner.new NestedInner("data");
List<String> result = source.process(nested);assertEquals(3, result.size());}}