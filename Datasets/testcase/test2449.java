package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
class BaseTarget {}
protected abstract class TargetClass extends BaseTarget {public static class TargetInner {public static String staticField = "target_inner_static";private String data;
public TargetInner(String data) {this.data = data;}
public String getData() {return data;}
public void setData(String data) {this.data = data;}}}
class SubTargetClass extends TargetClass {private void processData(TargetInner inner) {inner.setData(inner.getData() + "_sub");}}
class SamePackageOtherClass {public static void handleTarget(TargetClass.TargetInner inner) {// TypeDeclarationStatement with transient modifier and ClassName.fieldtransient String fieldValue = TargetClass.TargetInner.staticField;System.out.println("Handling: " + fieldValue);}}
abstract class SourceClass {private String outerPrivate = "private_data";static class SourceStaticNested {}
protected static List<String> process(TargetClass.TargetInner inner) {// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {System.out.println("Processing started");}};initializer.run();
// Constructor invocationList<String> results = new ArrayList<>();TargetClass.TargetInner newInner = new TargetClass.TargetInner("new_data");
// Variable callObject varCall = inner.getData();if (varCall == null) {throw new NullPointerException("Inner data cannot be null"); // Throw statement}
// Access outer privateresults.add(outerPrivate + "_" + inner.getData());
// TypeMethodReference (2 occurrences)Consumer<String> stringConsumer = String::toUpperCase;results.add(stringConsumer.accept(inner.getData()));
Consumer<TargetClass.TargetInner> innerConsumer = SubTargetClass::new;innerConsumer.accept(inner);
// Functional interface with sub class instance methodConsumer<TargetClass.TargetInner> processor = SubTargetClass::processData;processor.accept(inner);
// Used by reflectiontry {Method method = TargetClass.TargetInner.class.getMethod("getData");results.add((String) method.invoke(inner));} catch (Exception e) {throw new RuntimeException(e);}
// Overload existsprocess(inner, results);
// Return statementif (results.isEmpty()) {return new ArrayList<>();}return results;}
private static void process(TargetClass.TargetInner inner, List<String> results) {// Overloaded methodresults.add(inner.getData() + "_overloaded");}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodTest3141 {@Testpublic void testStaticMethod() {TargetClass.TargetInner inner = new TargetClass.TargetInner("test");List<String> result = SourceClass.process(inner);assertFalse(result.isEmpty());assertTrue(result.contains("private_data_test"));}}