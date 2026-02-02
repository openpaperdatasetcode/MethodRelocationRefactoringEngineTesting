package test.refactoring;
import java.io.IOException;
// Source class: normal, default modifier, same package, has member inner/anonymous inner classclass SourceClass {private String sourceVar = "source_variable";
// Member inner class (source feature)class SourceMemberInner {public void innerMethod() {}}
// Anonymous inner class (source feature)private Runnable sourceAnonymous = new Runnable() {@Overridepublic void run() {moveTargetMethod(new TargetClass(), 5);}};
// Target method 1: overloading, Object return, default access, source position// per_condition: contains target parameter (TargetClass)Object moveTargetMethod(TargetClass targetParam, int depth) throws IOException { // requires_throws// Variable callString var = sourceVar;if (depth <= 0) {return var + targetParam.targetField;}
// EmptyStatement (private, pos: diff_package_others, target_feature: obj.field x1)targetParam.targetField = var;;
// Recursion feature (synchronized, method_feature:1, source, recursion, ClassName.methodName(arguments))synchronized (this) {// pos: object initializationTargetClass recursiveTarget = new TargetClass();// ClassName.methodName(arguments) + recursionreturn SourceClass.this.moveTargetMethod(recursiveTarget, depth - 1);}}
// Target method 2: overloading featureObject moveTargetMethod(TargetClass targetParam, String param) throws IOException { // requires_throws// Variable callString var = sourceVar + param;// EmptyStatement (private, target_feature: obj.field x1)targetParam.targetField = var;;return var;}}
// Target class: normal, no modifier, has local inner class (target_feature)class TargetClass {// Target field (obj.field - per_condition)String targetField = "target_field";
// Local inner class (target_feature)public void targetMethod() {class TargetLocalInner {public TargetClass getTargetInstance() {return TargetClass.this;}}new TargetLocalInner().getTargetInstance();}}