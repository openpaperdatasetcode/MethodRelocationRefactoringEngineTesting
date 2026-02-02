package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface ParentAnnotation {}
@interface SourceAnnotation {class MemberInner {protected int outerProtected;}
{new Runnable() {};}
@MyAnnotationstrictfp default int getValue() {int var = TargetAnnotation.staticField;variableCall = TargetAnnotation.field;accessInstance = new MemberInner().outerProtected;
int i = 0;do {int result = this.parentMethod();switch (i) {case 2:continue;default:i++;}} while (i < 3);
return variableCall;}
int variableCall = 0;int accessInstance;
@ParentAnnotationdefault int parentMethod() {return 0;}}
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public @interface TargetAnnotation {int field = 1;static int staticField = 2;
static class StaticNested {}}