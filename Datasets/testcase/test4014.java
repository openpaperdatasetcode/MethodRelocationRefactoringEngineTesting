package test;
@interface ParentAnnotation {}
/**
Javadoc for TargetAnnotation: Protected annotation extending ParentAnnotation*/protected @interface TargetAnnotation extends ParentAnnotation {String value() default "targetVal";
class TargetInnerRec {String innerField = "innerRecField";
int instanceMethod() {return innerField.length();}}}
@interface SourceAnnotation {static class SourceStaticNested {}
class SourceMemberInner {/**
Javadoc for constructor: Initializes and processes TargetAnnotation's inner class*/private Object constructor(TargetAnnotation.TargetInnerRec inner) {int i = 0;do {int baseVal = SourceMemberInner::process;i++;} while (i < 1);
String varCall = inner.innerField;int methodCall = inner.instanceMethod();return varCall + methodCall;}
private static int process() {return 1;}}}