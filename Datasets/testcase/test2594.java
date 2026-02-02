package test.refactoring.movemethod;
import java.io.IOException;import java.util.function.Supplier;
public class TargetClass {class TargetInner {class NestedInner {private int count;
public NestedInner(int count) {this.count = count;}
public void increment() {this.count++;}
public int getCount() {return count;}}}
{new Runnable() {@Overridepublic void run() {TargetInner inner = new TargetInner();inner.new NestedInner(0);}}.run();}}
public class SourceClass {static class StaticNested<T extends Number> {T value;}
class MemberInner {}
<T extends Number> void process(TargetClass.TargetInner.NestedInner nested) throws IOException {StaticNested<Integer> staticNested = new StaticNested<>();staticNested.value = 5;Object varCall = staticNested.value;
// Static ReturnStatement feature with this.field and 3Supplier<Void> staticReturn = () -> {if (nested.getCount() >= 3) {nested.count = 3; // Access to this.field of targetreturn null;}return null;};staticReturn.get();
if (nested.getCount() > 3) {super.toString(); // Super keyword usagereturn;}
for (int i = 0; i < 3; i++) {nested.increment();if (nested.getCount() == 3) {throw new IOException("Reached count 3");}}}}
import org.junit.Test;
public class MoveMethodTest3048 {@Test(expected = IOException.class)public void testInstanceMethod() throws IOException {SourceClass source = new SourceClass();TargetClass target = new TargetClass();TargetClass.TargetInner inner = target.new TargetInner();TargetClass.TargetInner.NestedInner nested = inner.new NestedInner(1);source.process(nested);}}