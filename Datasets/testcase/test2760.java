package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnno {}
// Target: default normal class with member inner class (target_feature)class TargetClass {public String value = "targetValue";
// Member inner class (target_class target_feature)public class TargetInner {public TargetClass getTarget() {return TargetClass.this;}}}
// Source: abstract normal class (member inner + local inner class)abstract class SourceClass {// Member inner class (source_class feature)protected class SourceInner {// Protected varargs method (returns TargetClass type)@RefactorAnno // Has annotationprotected TargetClass varargsMethod(TargetClass... targets) { // Contains target parameter (meets per_condition)for (TargetClass target : targets) {// Assert statementassert target != null : "Target cannot be null";
// Expression statementTargetClass.TargetInner targetInner = target.new TargetInner();
// Variable callvariableCall(target);
// Depends on inner class (source's local inner class)class LocalInner {void process() {targetInner.getTarget();}}new LocalInner().process();
// NullPointerException riskif (target.value == null) {target.value.toString();}
// Break statementbreak;}
// Override violation: conflicting method signature in subclassOverrideViolationSub sub = new OverrideViolationSub();return sub.varargsMethod(targets[0]); // No new exception}
private void variableCall(TargetClass target) {TargetClass local = target;local.new TargetInner().getTarget();}}
// Local inner class (source_class feature)protected class SourceLocalInner {public void useTarget(TargetClass target) {}}
// Subclass causing override_violationprivate class OverrideViolationSub extends SourceClass {@Overrideprotected String varargsMethod(TargetClass target) { // Return type conflicts with parent's TargetClassreturn target.value;}}}