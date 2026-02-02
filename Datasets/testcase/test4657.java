package test;
import java.lang.annotation.Annotation;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface ParentAnnotation {}
@ParentAnnotation@interface SourceAnnotation {class MemberInner {}static class StaticNested {}
public void method(TargetAnnotation target);
public default TargetAnnotation overloadedMethod(int val) {return null;}
public default TargetAnnotation overloadedMethod(String str) {class LocalType {}LocalType local = new LocalType();
int count = 3;if (count > 0) {return super.overloadedMethod(count);} else {return null;}}}
public @interface TargetAnnotation {default void sampleMethod() {class TargetLocalInner {}TargetLocalInner local = new TargetLocalInner();}}
class SourceAnnotationImpl implements SourceAnnotation {@Overridepublic void method(TargetAnnotation target) {TargetAnnotation result = overloadedMethod(3);}
@Overridepublic TargetAnnotation overloadedMethod(int val) {return null;}
@Overridepublic TargetAnnotation overloadedMethod(String str) {return SourceAnnotation.super.overloadedMethod(str);}
@Overridepublic Class<? extends Annotation> annotationType() {return SourceAnnotation.class;}}