package test.refactoring.movemethod;
import java.io.IOException;
/**
Parent class with abstract process method
*/
abstract class ParentSource {
protected abstract void process(TargetClass target) throws IOException;
}
/**
TargetClass with javadoc and local inner class*/protected class TargetClass {public String instanceField;private int count;
public TargetClass(String field) {this.instanceField = field;// Local inner class in targetclass Counter {void increment() {count++;}}new Counter().increment();}
class TargetInner {void updateField(String value) {instanceField = value;}}}
class SourceClass<T> extends ParentSource {static class StaticNested1 {}static class StaticNested2 {}private T outerData;
@Overrideprotected void process(TargetClass target) throws IOException {// Variable callObject varCall = target.instanceField;
// Access instance fieldString fieldValue = target.instanceField;
// Depends on inner classTargetClass.TargetInner inner = target.new TargetInner();inner.updateField("processed: " + fieldValue);
// Uses outer thisOtherClass.useSource(this);
// Requires throws (checked exception)if (target.instanceField == null) {throw new IOException("Field cannot be null");}}}
class OtherClass {static <T> void useSource(SourceClass<T> source) {}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3105 {@Testpublic void testOverridingMethod() throws IOException {SourceClass<String> source = new SourceClass<>();TargetClass target = new TargetClass("test");source.process(target);assertEquals("processed: test", target.instanceField);}}