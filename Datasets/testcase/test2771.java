// Target package (different from source package)package com.target;
import java.util.List;import java.util.ArrayList;
// Target: protected normal class with member inner class (target_feature)protected class TargetClass {public String value = "targetValue";
// Member inner class (target_class target_feature)public class TargetInner {public List<String> process() {return new ArrayList<>(List.of(value));}
public TargetInner m1() { return this; }public TargetInner m2() { return this; }public int m3() { return value.length(); }}
public TargetInner getInner() {return new TargetInner();}}
// Others_class for overriding method featureabstract class OtherParentClass {public abstract List<String> overrideMethod(TargetClass target);}
class OtherChildClass extends OtherParentClass {@Overridepublic List<String> overrideMethod(TargetClass target) {return target.new TargetInner().process();}}
// Source package (different from target package)package com.source;
import com.target.TargetClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnno {}
// Source: final normal class (anonymous inner + local inner class)final class SourceClass {private String outerPrivateField = "outerPrivate"; // For access_outer_private feature
// Member inner class (method_position: source_inner)private class SourceInner {// Overloading methods (method type: overloading)@RefactorAnno // Has annotationprivate int overloadedMethod(TargetClass target) { // Contains target parameter (meets per_condition)// Static code blocks (pos for overriding method feature)static {OtherChildClass other = new OtherChildClass();List<String> overrideResult = other.overrideMethod(target); // OuterClass.InnerClass.methodName()}
// Super keywordssuper.toString();
// Type declaration statementsTargetClass.TargetInner targetInner = target.new TargetInner();String privateVal = outerPrivateField; // Access outer private
// Expression statementtargetInner.process();
// Variable callvariableCall(target);
// Access instance methodList<String> instanceResult = target.getInner().process();
// Do statementdo {// Switch caseswitch (target.value.length()) {case 9:privateVal += "case1";break;default:privateVal += "default";}break; // Break statement} while (false);
// While statementint count = 0;while (count < 1) {count++;}
// Call_method: expression pos, method chainint callResult = target.getInner().m1().m2().m3();
return callResult; // Base type return (int), no new exception}
// Overloaded method (overloading feature)private int overloadedMethod(TargetClass target, int extra) {variableCall(target);return target.value.length() + extra; // No new exception}
private void variableCall(TargetClass target) {TargetClass local = target;local.new TargetInner().process();}}
// Anonymous inner class 1 (source_class feature)public Runnable anonymous1 = new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();new SourceInner().overloadedMethod(target);}};
// Local inner class (source_class feature)public void useLocalInner(TargetClass target) {class LocalInner {void process() {new SourceInner().overloadedMethod(target, 5);}}new LocalInner().process();}}