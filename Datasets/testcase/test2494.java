package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
class BaseClass {protected String baseField;
public BaseClass(String baseField) {this.baseField = baseField;}
public String getBaseInfo() {return baseField + "_base";}}
// Protected target class with static nested classprotected class TargetClass extends BaseClass {public static String staticField1 = "static1";public static int staticField2 = 100;
public TargetClass(String baseField) {super(baseField);}
public static class TargetStaticNested {private String nestedData;
public TargetStaticNested(String nestedData) {this.nestedData = nestedData;}
public class NestedInner {private int count;
public NestedInner(int count) {this.count = count;}
public int getCount() {return count;}
public void setCount(int count) {this.count = count;}
public String getFullData() {return nestedData + "" + count + "" + baseField;}}}}
class SourceClass extends BaseClass {private String outerData;
public SourceClass(String baseField, String outerData) {super(baseField);this.outerData = outerData;}
// Member inner classclass SourceInner {public String getInnerData() {return outerData + "_inner";}}
// Overloading methods (overload_exist)public void process(TargetClass.TargetStaticNested.NestedInner nested) {process(nested, 0);}
public void process(TargetClass.TargetStaticNested.NestedInner nested, int offset) {// Type declaration statementList<String> results = new ArrayList<>();
// Variable callObject varCall = nested.getCount();
// Uses outer thisresults.add(this.outerData);
// Local inner classclass NestedProcessor {void updateCount(TargetClass.TargetStaticNested.NestedInner inner) {inner.setCount(inner.getCount() + offset);}}NestedProcessor processor = new NestedProcessor();
// Constructor invocationTargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested("nested_data");TargetClass.TargetStaticNested.NestedInner newInner = staticNested.new NestedInner(5);
// ConstructorInvocation with ClassName.field (2 fields)private TargetClass createTarget() {return new TargetClass(TargetClass.staticField1 + "_" + TargetClass.staticField2);}TargetClass target = createTarget();
// If statementif (nested.getCount() > 10) {processor.updateCount(nested);}
// While statement with inner class accessor method chainint index = 0;while (index < 3) {int current = nested.getCount();nested.setCount(current + 1);results.add(String.valueOf(nested.getFullData().length()));index++;}
// If/else conditions with synchronized source constructor call and super methodif (offset > 0) {synchronized (this) {SourceInner inner = new SourceInner();results.add(inner.getInnerData() + "_" + super.getBaseInfo());}} else {results.add(super.getBaseInfo());}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3198 {@Testpublic void testOverloadingMethod() {SourceClass source = new SourceClass("source_base", "source_outer");TargetClass target = new TargetClass("target_base");TargetClass.TargetStaticNested staticNested = target.new TargetStaticNested("test_nested");TargetClass.TargetStaticNested.NestedInner nested = staticNested.new NestedInner(3);
source.process(nested);assertEquals(6, nested.getCount());}}