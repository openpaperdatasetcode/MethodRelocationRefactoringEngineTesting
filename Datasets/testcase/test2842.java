package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnotation {}
// Source class: private normal class, implements interface, permits subclass, with member/local inner classesprivate sealed class SourceClass implements TestInterface permits SourceSubClass {private TargetClass targetField = new TargetClass(); // Contains target field (meets per_condition)
// Member inner class (source_class feature)class MemberInner {}
/**
Javadoc for varargsMethod: processes TargetClass varargs and returns TargetClass instance
@param targets varargs of TargetClass type
@return TargetClass instance*/@RefactorAnnotation // Has annotationTargetClass varargsMethod(TargetClass... targets) {// Local inner class (source_class feature)class LocalInner {void useTarget(TargetClass target) {variableCall(target);}}
// ExpressionStatement (private modifier, pos: diff_package_target, target_feature: this.field + 1)private TargetClass exprResult = this.targetField;exprResult = targets.length > 0 ? targets[0] : this.targetField;
// Variable callvariableCall(this.targetField);new LocalInner().useTarget(targets[0]);
return exprResult; // No new exception}
private void variableCall(TargetClass target) {TargetClass localTarget = target;localTarget.createLocalInner().getValue();}
@Overridepublic void testMethod() {} // Implement interface method}
// Subclass permitted by SourceClassfinal class SourceSubClass extends SourceClass {}
// Interface implemented by SourceClassinterface TestInterface {void testMethod();}
// Target class: public normal class, with local inner class (target_feature)public class TargetClass {// Local inner class (target_class feature)TargetClass createLocalInner() {class LocalInner {TargetClass getValue() {return TargetClass.this;}}return new LocalInner().getValue();}}