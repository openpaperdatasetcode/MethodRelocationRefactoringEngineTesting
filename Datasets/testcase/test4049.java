package test.refactor.movemethod;
import java.lang.reflect.Method;
/**
Abstract target class for Move Method refactoring test
Contains static nested class and core fields*/public abstract class TargetClass {protected String field1 = "target-field1";protected int field2 = 2024;protected boolean field3 = true;
/**
Static nested class for target's recursive data handling*/public static class TargetInnerRec {private TargetClass target;
public TargetInnerRec(TargetClass target) {this.target = target;}
public TargetClass getTarget() {return target;}
public TargetClass recursiveWrap(int depth) {if (depth <= 0) {return target;}return new TargetInnerRec(target).recursiveWrap(depth - 1);}}
public abstract TargetInnerRec createInnerRec();}
/**
Source class containing method to be refactored
Has two static nested classes and inner recursive structure/
public class SourceClass extends ParentSourceClass {
/*
First static nested class for validation
*/
public static class SourceValidator {
public static boolean isValid(TargetClass target) {
return target != null && target.field3;
}
}
/**
Second static nested class containing recursive inner structure
/
public static class SourceOuter {
public static class SourceInnerRec {
/*
Method to be refactored: processes TargetClass and returns TargetClass Type
Depends on TargetClass's static nested class and uses reflection
@param target TargetClass instance to process
@param depth Recursion depth
@return Processed TargetClass instance*/public TargetClass processTarget(TargetClass target, int depth) {if (!SourceValidator.isValid(target)) {return null;}
TargetClass.TargetInnerRec targetInner = target.createInnerRec();TargetClass result = null;int count = 0;
do {try {Method getField1Method = TargetClass.class.getMethod("getField1");String field1Val = (String) getField1Method.invoke(target);System.out.println("Field1 value: " + field1Val);
result = targetInner.recursiveWrap(depth);count++;} catch (Exception e) {e.printStackTrace();break;}} while (count < 2);
if (result != null) {protected String superData = super.getParentData();System.out.println("Super data: " + superData);return result;}
return target;}}}
public TargetClass startProcessing(TargetClass target, int depth) {return new SourceOuter.SourceInnerRec().processTarget(target, depth);}}
/**
Parent class for SourceClass providing super method
*/
class ParentSourceClass {
protected String getParentData() {
return "parent-source-data";
}
}
/**
Concrete implementation of abstract TargetClass for testing*/class ConcreteTargetClass extends TargetClass {@Overridepublic TargetInnerRec createInnerRec() {return new TargetInnerRec(this);}
public String getField1() {return this.field1;}}
/**
Test case for Move Method refactoring engine
/
import org.junit.Test;
import static org.junit.Assert.;
public class MoveMethodRefactorTest {@Testpublic void testProcessTargetMethodRefactoring() {// ArrangeTargetClass target = new ConcreteTargetClass();SourceClass source = new SourceClass();int testDepth = 2;
// Act: Execute method to be refactoredTargetClass result = source.startProcessing(target, testDepth);
// Assert: Verify method behavior before/after refactoringassertNotNull("Processed result should not be null", result);assertEquals("Target field1 should match", "target-field1", result.field1);assertTrue("Target field3 should be true", result.field3);assertEquals("Target field2 should match", 2024, result.field2);
// Verify recursive inner class dependencyTargetClass.TargetInnerRec innerRec = result.createInnerRec();assertNotNull("TargetInnerRec should be created", innerRec);assertSame("InnerRec should reference target", result, innerRec.getTarget());}}