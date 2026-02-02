package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
private class TargetClass {private int value;
public TargetClass(int value) {this.value = value;// Anonymous inner class in targetnew Runnable() {@Overridepublic void run() {System.out.println("Target initialized with value: " + value);}}.run();}
class TargetInner {class NestedInner {private String data;
public NestedInner(String data) {this.data = data;}
public String getData() {return data;}
public void setData(String data) {this.data = data;}}}
public int getValue() {return value;}}
class SubTargetClass extends TargetClass {public SubTargetClass(int value) {super(value);}
public Object processNested(TargetClass.TargetInner.NestedInner nested) {return nested.getData() + "_processed";}}
protected class SourceClass {class MemberInner {TargetClass.TargetInner.NestedInner createNested(TargetClass target, String data) {return target.new TargetInner().new NestedInner(data);}}
protected Object process(TargetClass.TargetInner.NestedInner nested) {// Anonymous inner classRunnable logger = new Runnable() {@Overridepublic void run() {System.out.println("Processing nested: " + nested.getData());}};logger.run();
// Type declaration statementList<TargetClass.TargetInner.NestedInner> nestedList = new ArrayList<>();nestedList.add(nested);nestedList.add(new MemberInner().createNested(new TargetClass(2), "second"));
// Expression statementObject varCall = nested.getData();
// ForStatement feature with obj.field (2 occurrences)int totalLength = 0;for (TargetClass.TargetInner.NestedInner ni : nestedList) {totalLength += ni.data.length(); // Access to private fieldif (totalLength > 10) {break; // Break statement}}
// Do-while with sub class normal methodint count = 0;Object result = null;do {result = new SubTargetClass(count).processNested(nested);count++;} while (count < 1);
// Return statementif (totalLength > 0) {return result;}return null;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3129 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass(1);TargetClass.TargetInner inner = target.new TargetInner();TargetClass.TargetInner.NestedInner nested = inner.new NestedInner("test");
Object result = source.process(nested);assertEquals("test_processed", result);}}