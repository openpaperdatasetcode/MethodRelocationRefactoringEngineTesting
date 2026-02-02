package test.refactoring.movemethod;
import other.DiffPackageHandler;import java.util.function.Consumer;
abstract class TargetClass {public static int staticField = 100;
public class TargetInner {public class NestedInner {private String value;
public NestedInner(String value) {this.value = value;}
public String getValue() {return value;}
public void setValue(String value) {this.value = value;}}}
public abstract void processValue(String value);}
class ConcreteTarget extends TargetClass {@Overridepublic void processValue(String value) {System.out.println("Processed: " + value);}
class InnerHandler {public static void log(String message) {System.out.println("Log: " + message);}}}
package other;
import test.refactoring.movemethod.TargetClass;
public class DiffPackageHandler {public static void handleNested(TargetClass.TargetInner.NestedInner nested) {// BreakStatement with ClassName.fieldloop: for (int i = 0; i < 5; i++) {if (i == TargetClass.staticField / 100) {break loop;}}}}
package test.refactoring.movemethod;
protected class SourceClass {private int outerField = 5;static class SourceStaticNested {}
class SourceInner {private abstract int process(TargetClass.TargetInner.NestedInner nested);
class ConcreteProcessor extends SourceInner {@Overrideprivate int process(TargetClass.TargetInner.NestedInner nested) {// Uses outer thisSourceClass outerThis = SourceClass.this;int base = outerThis.outerField;
// Constructor invocationTargetClass target = new ConcreteTarget();TargetClass.TargetInner inner = target.new TargetInner();
// Variable callObject varCall = nested.getValue();if (varCall == null) {throw new NullPointerException("Nested value is null");}
// Access instance methodtarget.processValue(nested.getValue());
// If statementif (nested.getValue().length() > 5) {base *= 2;}
// Exception handling with inner class static method referencetry {Integer.parseInt(nested.getValue());} catch (NumberFormatException e) {Consumer<String> logger = ConcreteTarget.InnerHandler::log;logger.accept("Invalid number: " + nested.getValue());}
// Call diff package handler with BreakStatement featureDiffPackageHandler.handleNested(nested);
return base;}}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3142 {@Testpublic void testAbstractMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.ConcreteProcessor processor = inner.new ConcreteProcessor();
TargetClass target = new ConcreteTarget();TargetClass.TargetInner targetInner = target.new TargetInner();TargetClass.TargetInner.NestedInner nested = targetInner.new NestedInner("test");
int result = processor.process(nested);assertEquals(5, result);}}