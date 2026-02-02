package source;
import target.TargetClass;import java.util.function.Function;
strictfp class SourceClass {public class SourceInner {// Overloading method 1 (base type parameter)public void process(TargetClass target, int value) {processWithBounds(target, value);}
// Overloading method 2 (another base type parameter)public void process(TargetClass target, String data) {int value = Integer.parseInt(data);processWithBounds(target, value);}
// Overloading method with boundsprivate <T extends Number> void processWithBounds(TargetClass target, T num) {// Super constructor invocation (implicit via Object)super.getClass();
// Type declaration statementTargetClass.TargetInner inner = target.new TargetInner(num.intValue());
// Local inner class (source_feature)class LocalProcessor {void handle() {// IfStatement: private modifier effect, pos=diff_package_others, access this.field (1)if (inner.getCount() < 0) {inner.setCount(0);}
// Variable callinner.increment();System.out.println("Processed: " + inner.getCount());}}
new LocalProcessor().handle();}}
public void start(TargetClass target) {SourceInner inner = new SourceInner();inner.process(target, 5);inner.process(target, "10");}}
package target;
public class TargetClass {public class TargetInner {private int count;
public TargetInner(int count) {this.count = count;}
public void increment() {count++;}
public int getCount() {return count;}
public void setCount(int count) {this.count = count;}}
public TargetInner createInner(int value) {// Local inner class (target_feature)class InnerCreator {TargetInner create() {return new TargetInner(value);}}return new InnerCreator().create();}}
package test;
import source.SourceClass;import target.TargetClass;
public class Test {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();source.start(target);}}