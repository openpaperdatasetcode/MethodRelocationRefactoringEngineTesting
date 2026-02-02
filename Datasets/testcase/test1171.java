package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
// Source class: normal class, public modifier, same package with targetpublic class SourceClass {// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class execution");}};anonymous.run();}
// Source feature: local inner classpublic void sourceWithLocalInner() {class SourceLocalInnerClass {void localMethod() {}}new SourceLocalInnerClass().localMethod();}
// Method to be refactored: instance, default access, return base type (int)int refactorTargetMethod(TargetClass targetParam) { // per_condition: contains target parameter// Type declaration statementclass MethodLocalType {}MethodLocalType localInstance = new MethodLocalType();
// 1 CharacterLiteral (numbers: "1", modifier: protected)protected char charLiteral = 'A';
// Variable callTargetClass tempTarget = targetParam;
// Access instance method (target class instance method)String methodResult = tempTarget.targetInstanceMethod();
// Uses outer this (reference to SourceClass instance)SourceClass outerThis = SourceClass.this;outerThis.sourceWithLocalInner();
// No new exception thrownreturn charLiteral + methodResult.length();}}
// Target class: normal class, public modifier, extends parent + local inner class (target_feature)public class TargetClass extends TargetParentClass {// Target feature: local inner class (target_inner_rec)public String targetInstanceMethod() {class TargetLocalInnerClass {String getInnerData() {return "Local inner class data";}}TargetLocalInnerClass localInner = new TargetLocalInnerClass();return localInner.getInnerData();}}
// Parent class for target class extends featureclass TargetParentClass {}
// Call method container: inner_class type, synchronized modifier, pos in do-whileclass CallerClass {class CallerInnerClass {// Call method: inner_class type, synchronized, return List<String>synchronized List<String> callInnerOverriddenMethod(TargetSubClass targetSub) {List<String> result = new ArrayList<>();do {// do-while position + obj.m1().m2().m3() chained call + overridingString chainedResult = targetSub.m1().m2().m3();result.add(chainedResult);} while (result.size() < 2);return result;}}
// Middle class 1 for chained callstatic class MiddleClass1 {MiddleClass2 m2() {return new MiddleClass2();}}
// Middle class 2 for chained callstatic class MiddleClass2 {String m3() {return "Chained method result";}}
// Subclass of TargetClass for overriding featurestatic class TargetSubClass extends TargetClass {// Override parent method (target_feature: overriding)@Overridepublic String targetInstanceMethod() {return "Overridden method result";}
// Chained method 1 (for obj.m1().m2().m3())MiddleClass1 m1() {return new MiddleClass1();}}}