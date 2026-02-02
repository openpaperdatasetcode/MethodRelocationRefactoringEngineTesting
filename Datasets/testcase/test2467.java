package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
class TargetClass {private String name;private int value;
public TargetClass(String name, int value) {this.name = name;this.value = value;}
public class TargetInner {private String innerData;
public TargetInner(String innerData) {this.innerData = innerData;}
public String getInnerData() {return innerData;}
public void setInnerData(String innerData) {this.innerData = innerData;}}
public String getName() {return name;}
public int getValue() {return value;}
public void setValue(int value) {this.value = value;}}
private class SourceClass {class SourceInner {class NestedInner {// Accessor method (sets values)protected void setTargetValues(TargetClass target, TargetClass.TargetInner inner, String newName, String newInnerData) {// Type declaration statementList<String> dataList = new ArrayList<>();
// Variable callObject varCall = target.getName();dataList.add((String) varCall);
// Expression statementtarget.setValue(target.getValue() + 10);inner.setInnerData(newInnerData);dataList.add(inner.getInnerData());
// Use collected data (demonstrates accessor purpose)System.out.println("Updated data: " + dataList);}}}
// Anonymous inner classRunnable dataLogger = new Runnable() {@Overridepublic void run() {System.out.println("Logging target data changes");}};}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3161 {@Testpublic void testAccessorMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.NestedInner nested = inner.new NestedInner();
TargetClass target = new TargetClass("original", 5);TargetClass.TargetInner targetInner = target.new TargetInner("old_data");
nested.setTargetValues(target, targetInner, "updated", "new_data");
assertEquals(15, target.getValue());assertEquals("new_data", targetInner.getInnerData());}}