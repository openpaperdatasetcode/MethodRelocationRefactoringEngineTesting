package test.refactoring.movemethod;
protected record TargetRecord(String data) {public class TargetInner {public class NestedInner {private int count;
public NestedInner(int count) {this.count = count;}
public int getCount() {return count;}
public void increment() {count++;}
public String getCombined() {return data + "_" + count;}}}}
record SourceRecord(String id) {class SourceInner {protected Object process(TargetRecord.TargetInner.NestedInner nested) {// Local inner classclass Processor {Object handle(TargetRecord.TargetInner.NestedInner ni) {return ni.getCombined();}}
// Anonymous inner classRunnable runner = new Runnable() {@Overridepublic void run() {nested.increment();}};runner.run();
// Variable callObject varCall = nested.getCount();
// Access instance methodString combined = nested.getCombined();
// ExpressionStatement with volatile modifiervolatile int fieldValue = 1;fieldValue = nested.getCount();
// Another expression statementTargetRecord target = new TargetRecord("source");TargetRecord.TargetInner inner = target.new TargetInner();
// Super keywords in inner contextclass DerivedProcessor extends Processor {@OverrideObject handle(TargetRecord.TargetInner.NestedInner ni) {return super.handle(ni) + "_derived";}}
// If/else conditions with static method callObject result;if (nested.getCount() > 1) {result = SourceRecord.SourceInner.staticMethod(inner.new NestedInner(1));} else {result = new DerivedProcessor().handle(nested);}
return result;}
public static Object staticMethod(TargetRecord.TargetInner.NestedInner nested) {return nested.getCombined() + "_static";}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3124 {@Testpublic void testInstanceMethod() {SourceRecord source = new SourceRecord("src1");SourceRecord.SourceInner inner = source.new SourceInner();TargetRecord target = new TargetRecord("test");TargetRecord.TargetInner targetInner = target.new TargetInner();TargetRecord.TargetInner.NestedInner nested = targetInner.new NestedInner(1);
Object result = inner.process(nested);assertEquals("test_2_static", result);}}