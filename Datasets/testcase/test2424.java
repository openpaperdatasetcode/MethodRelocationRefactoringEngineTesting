package test.refactoring.movemethod;
import java.lang.reflect.Method;import java.util.function.Consumer;
interface Operation {void execute();}
protected class TargetClass implements Operation {class TargetInner {private String value;
public TargetInner(String value) {this.value = value;}
public static void log1(String msg) {System.out.println("Log1: " + msg);}
public static void log2(String msg) {System.out.println("Log2: " + msg);}
public static void log3(String msg) {System.out.println("Log3: " + msg);}
public String getValue() {return value;}}
private String data;
public TargetClass(String data) {this.data = data;}
@Overridepublic void execute() {System.out.println("Executing: " + data);}}
class SourceClass {public TargetClass process(TargetClass target) {// Local inner classes (two)class LocalHandler1 {void handle() {TargetClass.TargetInner.log1("Handler1");}}class LocalHandler2 {void handle() {TargetClass.TargetInner.log2("Handler2");}}new LocalHandler1().handle();new LocalHandler2().handle();
// Object initialization with static method callsTargetClass.TargetInner inner = target.new TargetInner("inner_data");Consumer<String> logger = msg -> {this.logUsingInner(inner, msg);};logger.accept("Initialization");
// Variable callObject varCall = inner.getValue();
// TypeMethodReference with volatile modifiervolatile Consumer<String> refLogger = TargetClass.TargetInner::log3;refLogger.accept("Reference log");
// Reflection usagetry {Method method = TargetClass.TargetInner.class.getMethod("getValue");varCall = method.invoke(inner);} catch (Exception e) {// No new exception thrown}
return target;}
private void logUsingInner(TargetClass.TargetInner inner, String msg) {TargetClass.TargetInner.log1(msg);TargetClass.TargetInner.log2(msg);TargetClass.TargetInner.log3(msg);}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3102 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass("test");TargetClass result = source.process(target);assertNotNull(result);}}