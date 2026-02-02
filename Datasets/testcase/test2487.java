package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
interface StringProcessor {String process(String input);}
// Final target class with member inner classfinal class TargetClass {private String baseData;
public TargetClass(String baseData) {this.baseData = baseData;}
public class TargetInner {private int innerValue;
public TargetInner(int innerValue) {this.innerValue = innerValue;}
public class NestedInner implements StringProcessor {private String nestedData;
public NestedInner(String nestedData) {this.nestedData = nestedData;}
public String getNestedData() {return nestedData;}
public void setNestedData(String nestedData) {this.nestedData = nestedData;}
@Overridepublic String process(String input) {return input + "" + nestedData + "" + innerValue + "_" + baseData;}}}}
protected class SourceClass {private String instanceData;
public SourceClass(String instanceData) {this.instanceData = instanceData;}
// Static nested classstatic class SourceStaticNested implements StringProcessor {@Overridepublic String process(String input) {return input.toUpperCase();}}
// Static code block with call to overriding methodstatic {StringProcessor processor = new SourceStaticNested();System.out.println("Static init: " + processor.process("initial"));}
// Anonymous inner classRunnable logger = new Runnable() {@Overridepublic void run() {System.out.println("Processing logs: " + instanceData);}};
// Static method in sourcestatic List<String> process(TargetClass.TargetInner.NestedInner nested) {List<String> results = new ArrayList<>();
// Variable callObject varCall = nested.getNestedData();results.add(varCall.toString());
// Expression statementnested.setNestedData(nested.getNestedData() + "_updated");results.add(nested.getNestedData());
// Uses outer this (access instance data via outer class instance)SourceClass outer = new SourceClass("outer_context");results.add(outer.instanceData);
// Call overriding method (new ClassName().method())results.add(new SourceStaticNested().process(nested.getNestedData()));
return results;}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.List;
public class MoveMethodTest3189 {@Testpublic void testStaticMethod() {TargetClass target = new TargetClass("base_data");TargetClass.TargetInner inner = target.new TargetInner(100);TargetClass.TargetInner.NestedInner nested = inner.new NestedInner("nested_data");
List<String> result = SourceClass.process(nested);assertEquals(4, result.size());assertTrue(result.contains("nested_data"));assertTrue(result.contains("nested_data_updated"));assertTrue(result.contains("outer_context"));assertTrue(result.contains("NESTED_DATA_UPDATED"));}}