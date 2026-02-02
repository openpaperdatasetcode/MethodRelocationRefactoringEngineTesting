package test;
import java.util.function.Consumer;
abstract class SuperTarget {protected String superField = "super_value";
protected Object baseMethod(String input) {return input.toUpperCase();}}
abstract class Target extends SuperTarget {public String targetField = "target_data";
public class MemberInner {private int count = 0;
public void increment() {count++;}
public int getCount() {return count;}}
public abstract void process();}
final class Source<T> {private T sourceField;
public Source(T field) {this.sourceField = field;}
class MemberInner {void handleTargetInner(Target.MemberInner inner) {inner.increment();}}
protected int handle(Target target) {// Local inner classclass LocalProcessor {int processCount(Target.MemberInner inner) {return inner.getCount();}}
// Object initialization with call_method (lambda)Consumer<Target.MemberInner> consumer = (inner) -> {inner.increment();};
Target.MemberInner targetInner = target.new MemberInner();consumer.accept(targetInner); // Call lambda
// For loop with instance method call (1)for (int i = 0; i < 1; i++) {Object result = target.superMethod("loop_data");}
// Variable call (access target field)String data = target.targetField;
// SwitchStatement with 1 this.field accessswitch (sourceField.toString()) {case "case1":targetInner.increment();break;default:break;}
// No new exceptionreturn new LocalProcessor().processCount(targetInner);}}