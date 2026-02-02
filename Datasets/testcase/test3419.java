package test;
private class SourceClass {// Two static nested classes (source feature)public static class FirstStaticNested {}public static class SecondStaticNested {}
// Instance method (default access modifier, returns Object)Object instanceMethod(TargetClass<String> targetParam) {// NullPointerExceptionif (targetParam == null) {throw new NullPointerException("Target parameter cannot be null");}
try {// Target's inner recursive class (target_inner_rec)TargetClass<String>.TargetInnerRec innerRec = targetParam.new TargetInnerRec();
// Expression statement + variable callinnerRec.variableCall();TargetClass<String>.TargetStaticNested staticNested = new TargetClass.TargetStaticNested<>();staticNested.process(targetParam);
// For loop + break statementfor (int i = 0; i < 3; i++) {if (i == 1) {break;}innerRec.recursiveAction();}} catch (Exception e) {// No new exception}
return targetParam;}}
private class TargetClass<T> {// Type parameter (target_feature)private T data;
// Static nested class (target_feature)public static class TargetStaticNested {
public void process(TargetClass target) {}
}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {public void variableCall() {}public void recursiveAction() {}}}