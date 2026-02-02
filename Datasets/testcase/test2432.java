package other;
import java.util.ArrayList;import java.util.List;
/**
TargetClass with javadoc and anonymous inner class*/class TargetClass {public static int staticField = 2;
class TargetInner {class NestedInner {String field1;String field2;
public NestedInner(String f1, String f2) {this.field1 = f1;this.field2 = f2;}
public String getCombined() {return field1 + field2;}}}
public TargetClass() {new Runnable() {@Overridepublic void run() {System.out.println("Target initialized");}}.run();}}
package test.refactoring.movemethod;
import other.TargetClass;import java.io.IOException;import java.util.List;import java.util.ArrayList;
public class SourceClass<T> {protected String outerProtected = "protected_data";
class SourceInner {void process(TargetClass.TargetInner.NestedInner... inners) throws IOException {
// Type declaration statement with inner class
class Processor {
void handle(TargetClass.TargetInner.NestedInner inner) {
// ConstructorInvocation feature
TargetClass target = new TargetClass();
TargetClass.TargetInner innerObj = target.new TargetInner();
TargetClass.TargetInner.NestedInner newInner = innerObj.new NestedInner(
inner.field1,
inner.field2
);
}
}
Processor processor = new Processor<>();
// Super constructor invocationclass Base {}class Derived extends Base {Derived() {super();}}
// Variable callObject varCall = inners.length > 0 ? inners[0].getCombined() : null;
// If statementif (inners.length == 0) {throw new IOException("No inner objects provided");}
// Access outer protectedString data = outerProtected + inners[0].field1;
// Depends on static fieldif (TargetClass.staticField == 2) {processor.handle(inners[0]);}
// Switch statement with others class lambdaswitch (inners.length) {case 1:List<String> result = OtherClass.process(() -> {List<String> list = new ArrayList<>();list.add(inners[0].field1);return list;});varCall = result;break;default:break;}
// Override violationclass InvalidRunnable implements Runnable {public void run(int count) {} // Invalid override}}}}
package test.refactoring.movemethod;
import java.util.List;import java.util.function.Supplier;
class OtherClass {private static List<String> process(Supplier<List<String>> supplier) {return supplier.get();}}
import org.junit.Test;import static org.junit.Assert.*;import other.TargetClass;import java.io.IOException;
public class MoveMethodTest3111 {@Testpublic void testGenericMethod() throws IOException {SourceClass<String> source = new SourceClass<>();SourceClass<String>.SourceInner inner = source.new SourceInner();TargetClass target = new TargetClass();TargetClass.TargetInner targetInner = target.new TargetInner();TargetClass.TargetInner.NestedInner nested = targetInner.new NestedInner("a", "b");
inner.process(nested);}}