package test.refactoring.movemethod;
import diffpackage.DiffPackageChecker;import java.util.ArrayList;import java.util.List;
// Strictfp target classstrictfp class TargetClass {public String value;private int count;
public TargetClass(String value) {this.value = value;this.count = 0;}
public TargetClass(String value, int count) {this.value = value;this.count = count;}
public Object process() {return value + "processed" + count;}
public void increment() {count++;}}
// Subclass of TargetClass for overridingclass SubTargetClass extends TargetClass {public SubTargetClass(String value) {super(value);}
@Overridepublic Object process() {return "sub_" + super.process();}}
// Public source class with member inner class and static nested classpublic class SourceClass {// Member inner classpublic class SourceInner {private String innerData;
public SourceInner(String innerData) {this.innerData = innerData;}
public String getInnerData() {return innerData;}}
// Static nested classpublic static class SourceStaticNested {public static String format(String input) {return input.toUpperCase();}}
// Overloading methodspublic TargetClass process(TargetClass target) {return process(target, new ArrayList<>());}
public TargetClass process(TargetClass target, List<String> items) {// Variable callObject varCall = target.value;
// Enhanced for statementfor (String item : items) {target.value += "_" + item;}
// Assignment expressions (2 occurrences)String originalValue = target.value;target.value = originalValue + "_modified";
// Lambda expression with this.value referenceRunnable printer = () -> System.out.println(this.value); // Note: 'this' refers to enclosing context
// Lambda expressions with target overriding method callRunnable processor = () -> new SubTargetClass("lambda").process();processor.run();
// Raw typeList rawList = new ArrayList();rawList.add(target.value);
// Call diff package class for AssertStatementDiffPackageChecker.validate(target);
return new TargetClass(target.value, 1);}}
package diffpackage;
import test.refactoring.movemethod.TargetClass;
public class DiffPackageChecker {// AssertStatement with ClassName.field (1 occurrence)public static void validate(TargetClass target) {private String field = TargetClass.value; // Access static field (simulated)assert field != null : "Target value cannot be null";}}
import org.junit.Test;import static org.junit.Assert.*;import java.util.Arrays;
public class MoveMethodTest3090 {@Testpublic void testOverloadingMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass("initial");
TargetClass result = source.process(target, Arrays.asList("item1", "item2"));assertEquals("initial_item1_item2_modified", result.value);}}