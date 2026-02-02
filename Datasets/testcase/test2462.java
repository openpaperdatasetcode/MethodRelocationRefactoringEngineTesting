package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
/**
TargetClass with Javadoc and member inner class*/class TargetClass {private int field1;private String field2;private boolean field3;
/**
Constructs a TargetClass with initial values
@param field1 numeric field
@param field2 string field
@param field3 boolean field
*/
public TargetClass(int field1, String field2, boolean field3) {
this.field1 = field1;
this.field2 = field2;
this.field3 = field3;
}
/**
Member inner class for data processing
*/
public class TargetInner {
public String processField2() {
return field2.toUpperCase();
}
}
public int getField1() {return field1;}
public boolean isField3() {return field3;}}
protected abstract class SourceClass {private String outerPrivate = "outer_private_value";
/**
Abstract method to process TargetClass and return string list
@param target the TargetClass instance to process
@return list of processed strings
*/
private abstract List<String> process(TargetClass target);
/**
Concrete implementation of the abstract method*/public static class ConcreteSource extends SourceClass {@Overrideprivate List<String> process(TargetClass target) {List<String> results = new ArrayList<>();
// Super constructor invocationclass DerivedTarget extends TargetClass {DerivedTarget() {super(0, "", false);}}new DerivedTarget();
// Variable callObject varCall = target.getField1();
// Access outer privateresults.add(outerPrivate);
// Assert statementassert target.isField3() : "Field3 must be true";
// SynchronizedStatement with this.field (3 occurrences)synchronized (target) {results.add(String.valueOf(target.field1));results.add(target.field2);results.add(String.valueOf(target.field3));}
// Use member inner classTargetClass.TargetInner inner = target.new TargetInner();results.add(inner.processField2());
return results;}}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodTest3156 {@Testpublic void testAbstractMethod() {SourceClass.ConcreteSource source = new SourceClass.ConcreteSource();TargetClass target = new TargetClass(10, "test", true);
try {java.lang.reflect.Method method = SourceClass.ConcreteSource.class.getDeclaredMethod("process", TargetClass.class);method.setAccessible(true);List<String> result = (List<String>) method.invoke(source, target);
assertEquals(5, result.size());assertTrue(result.contains("outer_private_value"));assertTrue(result.contains("TEST"));} catch (Exception e) {fail("Test failed: " + e.getMessage());}}}