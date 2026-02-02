package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;
// Parent class for super constructor invocation and super.methodName()abstract class ParentBaseClass {protected ParentBaseClass(String param) {}public int parentInstanceMethod() {return 10;}}
// Source class (abstract, same package, no features)abstract class SourceClass extends ParentBaseClass {// Super constructor invocationpublic SourceClass() {super("sourceSuperParam");}
// Abstract method to be overridden (overriding feature)public abstract void processTarget(TargetClass target);}
// Subclass of SourceClass (for call_method: sub_class type)class SourceSubClass extends SourceClass {// Method to be refactored: overriding, default access, void return@Overridevoid processTarget(TargetClass target) { // per_condition// Per_condition: source contains target's fieldString targetField = target.targetField;
// Type declaration statement + raw_typeList rawList = new ArrayList();rawList.add(targetField);
// Variable call: target's anonymous inner classRunnable anonymousAction = target.createAnonymousAction();anonymousAction.run();
// do-while statement with target instance method (method_feature)int count = 0;do {// target instance method: super.methodName(), returns base typeint baseValue = target.targetInstanceMethod();rawList.add(baseValue);
if (count >= 2) {break; // break statement}count++;} while (true);
// for statement with call_method (outerInstance.new InnerClass().methodName())for (int i = 0; i < 3; i++) {// call_method: sub_class, protected, overloading, returns StringString callResult = this.callInFor(target, i);rawList.add(callResult);}
// return statementif (rawList.size() > 5) {return;}
try {// Variable call: target's inner class methodTargetClass.TargetInner inner = target.new TargetInner();inner.useTargetField(targetField);} catch (IllegalArgumentException e) {// no_new_exception: rethrow without wrappingthrow e;}}
// call_method: protected modifier, overloading featureprotected String callInFor(TargetClass target, int num) {return callInFor(target, num, target.targetField);}
// Overloaded call_method (overloading feature)protected String callInFor(TargetClass target, int num, String suffix) {// target_feature: outerInstance.new InnerClass().methodName()return target.new TargetInner().innerMethod(num) + "_" + suffix;}}
// Target class (public, target_feature: javadoc + anonymous inner class)/**
TargetClass with javadoc (target_feature: javadoc)
Contains anonymous inner class and instance fields*/public class TargetClass {// Target field (per_condition)public String targetField = "targetValue";
// Target inner class (for outerInstance.new InnerClass().methodName())public class TargetInner {public String innerMethod(int num) {return "InnerResult:" + num;}public void useTargetField(String field) {System.out.println("Inner uses: " + field);}}
// target instance method (method_feature: 3 occurrences, instance, super.methodName())public int targetInstanceMethod() {super.toString(); // super.methodName()return parentInstanceMethod() * 2; // super.methodName() (inherited from parent)}
// Target_feature: anonymous inner classpublic Runnable createAnonymousAction() {return new Runnable() {@Overridepublic void run() {System.out.println("Anonymous action: " + targetField);}};}
// Parent method for targetInstanceMethod's super.methodName()protected int parentInstanceMethod() {return 5;}}
// Test classpublic class MoveMethodTest5252 {public static void main(String[] args) {SourceClass source = new SourceSubClass();TargetClass target = new TargetClass();source.processTarget(target);}}