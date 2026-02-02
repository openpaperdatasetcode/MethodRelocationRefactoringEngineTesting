package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
class ParentClass {}
private class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass();
// Member inner class (source feature)public class SourceMemberInner {}
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public void innerMethod() {}}new SourceLocalInner().innerMethod();}
// Overloading method 1 (no parameters)@MethodAnnotationpublic int overloadedMethod() {return 0;}
// Overloading method 2 (with target parameter, per condition)@MethodAnnotationpublic int overloadedMethod(TargetClass targetParam) {// Type declaration statementTargetClass.TargetStaticNested.NestedRecursive innerRec = new TargetClass.TargetStaticNested.NestedRecursive();
// Assert statementassert TargetClass.staticField > 0 : "Static field must be positive";
// Variable calltargetParam.targetMethod();innerRec.recursiveCall();
// Depends on static fieldint result = TargetClass.staticField + targetParam.instanceField;
return result;}}
public class TargetClass {public static int staticField = 5; // Static field for depends_on_static_fieldpublic int instanceField = 3; // Field for per_condition
// Static nested class (target_feature)public static class TargetStaticNested {// Target inner recursive class (target_inner_rec)public static class NestedRecursive {public void recursiveCall() {}}}
public void targetMethod() {}}