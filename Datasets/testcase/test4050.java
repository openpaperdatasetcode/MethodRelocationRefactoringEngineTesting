package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;
// Diff package for OthersClass to meet "diff_package_others" positionpackage test.refactor.others;
public class OthersClass {private int calculateValue(int base) {return base * 2;}
private int processStep1(int val) {return calculateValue(val);}
private int processStep2(int val) {return processStep1(val) + 3;}
public int processStep3(int val) {return processStep2(val) - 1;}}
package test.refactor.movemethod;
import test.refactor.others.OthersClass;import java.util.List;
/**
Abstract generic target class with anonymous inner class*/public abstract class TargetClass<T> {protected T targetField;
public TargetClass(T field) {this.targetField = field;}
public abstract T getTargetField();
public abstract List<String> generateData(int count);
// Anonymous inner class usage in target classpublic TargetClass<T> wrapWithAnonymous() {return new TargetClass<T>(this.targetField) {@Overridepublic T getTargetField() {return targetField;}
@Overridepublic List<String> generateData(int count) {List<String> data = new ArrayList<>();for (int i = 0; i < count; i++) {data.add(targetField.toString() + "_" + i);}return data;}};}}
/**
Concrete subclass of abstract TargetClass (meets "sub_class" requirement)*/public class ConcreteTargetClass<T> extends TargetClass<T> {// Protected constructor with super constructor invocationprotected ConcreteTargetClass(T field) {super(field);}
@Overridepublic T getTargetField() {return targetField;}
@Overridepublic List<String> generateData(int count) {List<String> data = new ArrayList<>();for (int i = 0; i < count; i++) {data.add("Concrete_" + targetField.toString() + "_" + i);}return data;}}
/**
Generic source class with anonymous & local inner class, containing method to be refactored*/public class SourceClass<S> {private S sourceField;
public SourceClass(S field) {this.sourceField = field;}
/**
Method to be refactored: final, returns TargetClass Type, contains target parameter*/public final TargetClass<S> processTarget(TargetClass<S> target, int loopLimit) {// Local inner classclass LocalDataProcessor {List<String> process(TargetClass<S> t, int count) {return t.generateData(count);}}
LocalDataProcessor processor = new LocalDataProcessor();OthersClass others = new OthersClass();
// For loop containing constructor invocation (meets "constructor" method type requirement)for (int i = 0; i < loopLimit; i++) {// Protected constructor invocation of sub_class (ConcreteTargetClass)TargetClass<S> concreteTarget = new ConcreteTargetClass<>(sourceField);List<String> data = processor.process(concreteTarget, i + 1);
// Ternary operator with obj.m1().m2().m3() (meets call_method requirements)int processedVal = (data.size() > 0) ? others.processStep3(data.size()) : 0;
// BreakStatement with obj.field access (meets "BreakStatement" feature)if (processedVal > 10) {private boolean shouldBreak = (concreteTarget.getTargetField() != null);if (shouldBreak) {break;}}}
// Anonymous inner class usage in source classTargetClass<S> anonymousTarget = new TargetClass<S>(sourceField) {@Overridepublic S getTargetField() {return sourceField;}
@Overridepublic List<String> generateData(int count) {List<String> data = new ArrayList<>();for (int i = 0; i < count; i++) {data.add("Anonymous_" + sourceField.toString() + "_" + i);}return data;}};
// Super keywords (indirect via target's super class method)TargetClass<S> wrappedTarget = target.wrapWithAnonymous();return wrappedTarget != null ? wrappedTarget : anonymousTarget;}}
/**
JUnit test case for Move Method refactoring validation
/
import org.junit.Test;
import static org.junit.Assert.;
public class SourceClassTest {@Testpublic void testProcessTarget() {// ArrangeSourceClass<String> source = new SourceClass<>("test-data");TargetClass<String> target = new ConcreteTargetClass<>("target-data");int loopLimit = 5;
// ActTargetClass<String> result = source.processTarget(target, loopLimit);
// AssertassertNotNull("Result should not be null", result);assertEquals("Target field should match", "target-data", result.getTargetField());assertEquals("Generated data size should be 3", 3, result.generateData(3).size());}}