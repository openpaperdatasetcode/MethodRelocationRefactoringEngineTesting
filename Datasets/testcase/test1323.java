package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
// Parent class for source_class extends featureabstract class ParentClass {public abstract void processTarget(TargetClass target);}
// Source class (abstract, same package, extends + anonymous inner + local inner class)abstract class SourceClass extends ParentClass {// Method to be refactored: overriding, final, void return@Overridepublic final void processTarget(TargetClass targetParam) { // per_condition// Per_condition: contains target parameterif (targetParam == null) {throw new IllegalArgumentException("Target cannot be null");}
// Public ArrayAccess (numbers:1, modifier:public, exp:ArrayAccess)String[] targetArray = targetParam.targetArray;String arrayValue = targetArray[0]; // ArrayAccess
// With_bounds (generic with bounds)Function<? extends CharSequence, String> boundedFunc = CharSequence::toString;String boundedResult = boundedFunc.apply(arrayValue);
// Local inner class (source feature)class LocalInner {// call_method: inner_class, default modifier, constructor + this.methodName(arguments)List<String> callInPropertyAssignment() {// Target_feature: constructorLocalHelper helper = new LocalHelper(targetParam);// Target_feature: this.methodName(arguments)return this.processHelper(helper);}
private List<String> processHelper(LocalHelper helper) {List<String> result = new ArrayList<>();result.add(helper.getTargetData());return result;}}
try {// Variable call: target's anonymous inner classRunnable targetAnon = targetParam.createAnonymousAction();targetAnon.run();
// Source feature: anonymous inner classRunnable sourceAnon = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous: " + boundedResult);}};sourceAnon.run();
// Property assignment with call_methodLocalInner inner = new LocalInner();List<String> callResult = inner.callInPropertyAssignment();targetParam.setProcessedData(callResult); // Property assignment pos
// Variable call: target's instance methodtargetParam.updateArray(boundedResult);} catch (Exception e) {// no_new_exception: rethrow without wrappingthrow e;}}
// Inner helper class for call_method constructor featureclass LocalHelper {private TargetClass target;
// Constructor (call_method target_feature)public LocalHelper(TargetClass target) {this.target = target;}
public String getTargetData() {return target.targetArray[0];}}}
// Concrete subclass of SourceClass (to instantiate abstract class)class ConcreteSourceClass extends SourceClass {}
// Target class (protected, target_feature: anonymous inner class)protected class TargetClass {public String[] targetArray = {"targetValue"};private List<String> processedData;
// Target_feature: anonymous inner classpublic Runnable createAnonymousAction() {return new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous: " + targetArray[0]);}};}
public void updateArray(String value) {this.targetArray[0] = value;}
public void setProcessedData(List<String> processedData) {this.processedData = processedData;}
public List<String> getProcessedData() {return processedData;}}
// Test classpublic class MoveMethodTest5265 {public static void main(String[] args) {SourceClass source = new ConcreteSourceClass();TargetClass target = new TargetClass();source.processTarget(target);System.out.println("Updated Target Array: " + target.targetArray[0]);System.out.println("Processed Data: " + target.getProcessedData());}}