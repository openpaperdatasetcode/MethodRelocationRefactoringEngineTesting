package test;
@interface SourceAnnotation extends ParentAnnotation {class MemberInner {private TargetAnnotation target;
private TargetAnnotation recursiveMethod(TargetAnnotation target, int depth) {this.target = target;if (depth <= 0) {if (target == null) {throw new NullPointerException();}return target;}
String field = target.MemberInner.targetField;variableCall(field);target.MemberInner.staticMethod();return recursiveMethod(target, depth - 1);}
private void variableCall(String value) {System.out.println(value);}}
{new Runnable() {@Overridepublic void run() {new MemberInner();}};}}
public @interface TargetAnnotation {class MemberInner {public static String targetField = "targetField";public static void staticMethod() {}}}
@interface ParentAnnotation {}