package test.refactoring.movemethod;
import java.util.function.Consumer;
interface Operation {int execute();}
public class TargetClass<T> implements Operation {protected T value;
public TargetClass(T value) {this.value = value;}
class TargetInner {private String data;
public TargetInner(String data) {this.data = data;}
protected int getLength() {return data.length();}}
@Overridepublic int execute() {return 0;}}
class OtherClass {public void process(TargetClass.TargetInner inner) {System.out.println("Processing: " + inner.getLength());}}
protected class SourceClass {protected String outerProtected = "protected_value";static class StaticNested {}
public int process(TargetClass<String>.TargetInner inner) {// Local inner classclass LocalHandler {LocalHandler() {}}new LocalHandler();
// Super constructor invocationclass Base {}class Derived extends Base {Derived() {super();}}
// Variable callObject varCall = inner.getLength();
// Access outer protected fieldString combined = inner.data + outerProtected;
// Lambda expression with others class instance methodConsumer<TargetClass<String>.TargetInner> consumer = OtherClass::new;new OtherClass().process(inner);
// Depends on inner classint length = inner.getLength();
// Switch statement with target's overriding method callswitch (length) {case 3:length += ((Operation) new TargetClass<>("test")).execute();break;default:length += 1;}
// Override violation demonstration (if any)class InvalidOperation implements Operation {@Overridepublic void execute() {} // Intentionally invalid signature}
return length;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3106 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();TargetClass<String> target = new TargetClass<>("test");TargetClass<String>.TargetInner inner = target.new TargetInner("abc");
int result = source.process(inner);assertEquals(4, result);}}