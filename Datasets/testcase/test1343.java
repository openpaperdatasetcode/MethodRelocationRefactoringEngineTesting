package test.refactor.movemethod;
import java.util.function.Supplier;
// Interface for target_class implements featureinterface TargetInterface {void interfaceMethod();}
// Sub class for method_feature: sub_classclass TargetSubClass extends TargetRecord {public TargetSubClass(String data) {super(data);}
public int subClassMethod(String arg) {return arg.length();}}
// Source record: public, same package with target, 2 member inner classespublic record SourceRecord(String sourceData, TargetRecord targetField) { // per_condition: source contains target's field// Member inner class 1 (source_class feature)public class MemberInnerClass1 {public void innerMethod1() {}}
// Member inner class 2 (source_class feature)public class MemberInnerClass2 {// Target method: varargs, public, return TargetClass Type (method_position: source_inner)public TargetRecord publicVarargsMethod(String... varargs) {// Uses outer this (uses_outer_this)String outerData = SourceRecord.this.sourceData;
// Constructor feature (type=constructor, modifier=default, pos=Lambda expressions)Supplier<Integer> lambda = () -> new TargetSubClass(outerData).subClassMethod(varargs[0]); // instanceReference.methodName(arguments)int constructorResult = lambda.get();
// Constructor invocationMemberInnerClass1 inner1 = new MemberInnerClass1();TargetSubClass subClass = new TargetSubClass("subData");
// Type declaration statementclass LocalType {}LocalType localInstance = new LocalType();
// Super keywordssuper.toString();
// Variable callinner1.innerMethod1();MemberInnerClass2 inner2 = new MemberInnerClass2();inner2.innerMethod2();targetField.interfaceMethod(); // target_class implements TargetInterface
// Depends on inner classTargetRecord.LocalInnerClass targetLocal = targetField.createLocalInnerClass();targetLocal.innerMethod();
// No new exceptionreturn targetField;}
public void innerMethod2() {}}}
// Target record: private, implements TargetInterface (target_feature), local inner classprivate record TargetRecord(String targetData) implements TargetInterface {@Overridepublic void interfaceMethod() {}
// Local inner class (target_feature) - target_inner contextpublic LocalInnerClass createLocalInnerClass() {class LocalInnerClass {public void innerMethod() {}}return new LocalInnerClass();}}
// Test classimport org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoring5309Test {@Testpublic void testVarargsMethodBeforeRefactoring() {TargetRecord target = new TargetRecord("targetValue");SourceRecord source = new SourceRecord("sourceValue", target);SourceRecord.MemberInnerClass2 inner = source.new MemberInnerClass2();
// Execute target methodTargetRecord result = inner.publicVarargsMethod("arg1", "arg2");assertSame(target, result);
// Verify per_condition: source contains target's fieldassertEquals("targetValue", source.targetField().targetData());
// Verify target_feature: implements and local inner classassertTrue(TargetInterface.class.isAssignableFrom(TargetRecord.class));assertNotNull(target.createLocalInnerClass());
// Verify uses_outer_thisassertEquals("sourceValue", source.sourceData());}}