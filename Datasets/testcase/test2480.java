package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
interface DataProcessor {Object process();}
class TargetParent {protected String parentField;
public TargetParent(String parentField) {this.parentField = parentField;}
public TargetClass createTarget(String data) {return new TargetClass(data);}}
protected class TargetClass extends TargetParent {public String data;public int count;
public TargetClass(String data) {super(data);this.data = data;this.count = 0;}
public class TargetInner implements DataProcessor {private String innerData;
public TargetInner(String innerData) {this.innerData = innerData;}
public String getInnerData() {return innerData;}
@Overridepublic Object process() {return innerData + "_" + data;}}}
strictfp class SourceClass extends TargetParent implements DataProcessor {class SourceInner1 {}class SourceInner2 {}
public SourceClass(String parentField) {super(parentField);}
protected Object process(TargetClass.TargetInner... inners) {// Type declaration statementList<Object> results = new ArrayList<>();
// Constructor invocationTargetClass target = new TargetClass("source_target");TargetClass.TargetInner newInner = target.new TargetInner("new_inner");
// Variable callObject varCall = newInner.getInnerData();
// Depends on inner classresults.add(newInner.process());
// While statement with parent class method callint index = 0;while (index < inners.length) {TargetClass parentCreated = outerInstance.new TargetClass().createTarget("loop_" + index);results.add(parentCreated.data);index++;}
// TryStatement with obj.field (2 fields)static void processFields(TargetClass target) {try {String field1 = target.data;int field2 = target.count;System.out.println("Processed fields: " + field1 + ", " + field2);} catch (NullPointerException e) {throw new RuntimeException("Field processing failed", e);}}processFields(target);
// NullPointerExceptionif (inners == null) {throw new NullPointerException("Inner array cannot be null");}
return results;}
@Overridepublic Object process() {return parentField;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3178 {@Testpublic void testVarargsMethod() {SourceClass outerInstance = new SourceClass("outer_context");TargetClass target = new TargetClass("test_data");TargetClass.TargetInner inner1 = target.new TargetInner("inner1");TargetClass.TargetInner inner2 = target.new TargetInner("inner2");
Object result = outerInstance.process(inner1, inner2);assertTrue(result instanceof List);assertEquals(4, ((List<?>) result).size());}}