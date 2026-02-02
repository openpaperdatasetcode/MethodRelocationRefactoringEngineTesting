package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
class SourceClass {private String outerPrivateField = "private_data";
public class FirstMemberInner {}public class SecondMemberInner {}
protected void instanceMethod(TargetClass targetParam) {// LabeledStatement (private, target_feature: ClassName.field x1, pos: inner class)privateLabel: {if (TargetClass.STATIC_FIELD > 0) {break privateLabel;}}
// Expression statement + variable call to target's inner recursive classTargetClass.TargetInnerRec rawInner = new TargetClass.TargetInnerRec();rawInner.variableCall();
// Lambda expression: () -> System.out.println(this.value)Runnable lambda = () -> System.out.println(this.outerPrivateField);lambda.run();
// Instance method feature (1, target, instance, this.methodName(arguments))int baseTypeResult = targetParam.thisInstanceMethod(10);
// Access outer private fieldString privateVal = outerPrivateField;rawInner.process(privateVal);
// Override violation (target inner class method overrides without @Override)TargetClass.TargetInnerRec overrideInner = new TargetClass.TargetInnerRec() {public void variableCall() {} // No @Override annotation};}}
public class TargetClass {public static int STATIC_FIELD = 5;public String value = "target_value";
public class TargetInnerRec {public void variableCall() {}public void process(String data) {}}
public int thisInstanceMethod(int param) {return param * 2;}
@Strictfp@CallMethodAnnotation(value = TargetClass.TargetInnerRec.staticMethod())public static class TargetInnerRec {public static int staticMethod() {return 100;}}}
@Retention(RetentionPolicy.RUNTIME)@interface CallMethodAnnotation {int value();}