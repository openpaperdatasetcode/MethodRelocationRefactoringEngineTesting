package test.refactoring.movemethod;
import java.io.IOException;import java.util.ArrayList;import java.util.List;
public class TargetClass {private int count;
public TargetClass() {// Anonymous inner classnew Runnable() {@Overridepublic void run() {count = 0;}}.run();}
public void increment() {count++;}
public int getCount() {return count;}}
public class SourceClass {class FirstInner {class NestedInner {// Varargs methodint process(TargetClass... targets) throws IOException {if (targets == null || targets.length == 0) {throw new IOException("No targets provided");}
// Variable callObject varCall = targets[0].getCount();
// Raw typeList rawList = new ArrayList();rawList.add(targets);
// Do statementint total = 0;do {total += targets[0].getCount();targets[0].increment();} while (total < 5);
// For loop with inner class overloading methodfor (TargetClass target : targets) {handleTarget(target);handleTarget(target, "additional");}
// If statementif (targets.length > 1) {// Super keywords in inner contextclass Derived extends FirstInner {void check() {super.toString();}}new Derived().check();}
// Access instance methodreturn total + targets[0].getCount();}
// Overloading method 1void handleTarget(TargetClass target) {target.increment();}
// Overloading method 2 (with additional parameter)void handleTarget(TargetClass target, String info) {target.increment();System.out.println("Handling: " + info);}}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3145 {@Testpublic void testVarargsMethod() throws IOException {SourceClass source = new SourceClass();SourceClass.FirstInner firstInner = source.new FirstInner();SourceClass.FirstInner.NestedInner nestedInner = firstInner.new NestedInner();
TargetClass target1 = new TargetClass();TargetClass target2 = new TargetClass();
int result = nestedInner.process(target1, target2);assertEquals(7, result); // 5 (from do-while) + 2 (final count)}}