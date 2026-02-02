package test.refactoring.movemethod;
import other.OtherClass;
/**
TargetClass with javadoc and local inner class*/class TargetClass {private int thisField;
public TargetClass(int value) {this.thisField = value;// Local inner class in targetclass LocalCalculator {int add(int a) {return thisField + a;}}}
class TargetInner {class NestedInner {private String data;
public NestedInner(String data) {this.data = data;}
public int getLength() {return data.length();}}}}
final class SourceClass {private String outerPrivate = "private_data";static class StaticNested {}
class SourceInner {class NestedInner {int process(int... values) {// Anonymous inner classRunnable runner = new Runnable() {@Overridepublic void run() {System.out.println("Processing");}};runner.run();
// Constructor invocation with this.fieldTargetClass target = new TargetClass(1);OtherClass.process(target);
// Raw type usageTargetClass.TargetInner rawInner = new TargetClass(2).new TargetInner();TargetClass.TargetInner.NestedInner nested = rawInner.new NestedInner("test");
// Variable call and access instance methodObject varCall = nested.getLength();int length = nested.getLength();
// this.var = varthis.outerPrivate = "updated";varCall = outerPrivate;
// Sum input values (base type parameters)int sum = 0;for (int val : values) {sum += val;}
return sum + length;}
private String outerPrivate;}}}
package other;
import test.refactoring.movemethod.TargetClass;
public class OtherClass {public static void process(TargetClass target) {// Processing target from different package}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3108 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.NestedInner nested = inner.new NestedInner();
int result = nested.process(1, 2, 3);assertEquals(9, result);}}