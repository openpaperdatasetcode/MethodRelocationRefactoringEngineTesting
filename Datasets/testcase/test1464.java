package test.refactoring;
// Source class: normal, protected, same package, has static nested/anonymous inner classprotected class SourceClass {private String sourceVar = "source_variable";
// Static nested class (source feature)public static class SourceStaticNested {}
// Anonymous inner class (source feature)private Runnable sourceAnonymous = new Runnable() {@Overridepublic void run() {new SourceInnerClass().new SourceInnerRecClass().moveTargetMethod(new TargetClass(), "arg1", "arg2");}};
// Member inner class (parent of source_inner_rec)class SourceInnerClass {// Member inner class (source_inner_rec: method's original position)class SourceInnerRecClass {// Target method: varargs, Object, protected, source_inner_rec position// per_condition: contains target parameter (TargetClass)protected Object moveTargetMethod(TargetClass targetParam, Object... varargs) {// Variable callString var = sourceVar;Object result = var + targetParam.targetField;
// Expression statementSystem.out.println("Varargs length: " + varargs.length);result = targetParam.targetInnerClass.new TargetInnerClass().innerMethod(varargs);
// No new checked exceptionreturn result;}}}}
// Target class: normal, public, has member inner class (target_feature)public class TargetClass {// Target field referenced by sourcepublic String targetField = "target_field";
// Member inner class (target_feature)public class TargetInnerClass {public Object innerMethod(Object... params) {return params.length > 0 ? params[0] : "default";}}
// Target inner class instancepublic TargetInnerClass targetInnerClass = new TargetInnerClass();}