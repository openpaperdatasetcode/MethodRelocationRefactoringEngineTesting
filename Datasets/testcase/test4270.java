package test;
import java.util.List;
@interface SourceAnnotation {// Field of target class (matches "source contains the field of the target")TargetAnnotation.TargetParam targetField() default @TargetAnnotation.TargetParam;
// Instance method to be refactoredprotected Object processTarget(TargetAnnotation targetParam);
// Local inner class (source_class feature)class SourceLocalInner {void useAnnotation() {SourceAnnotation annotation = null;// Variable call + method invocation with target class parameterObject result = annotation.processTarget(annotation.targetField());// Raw type (matches "raw_type" feature)List rawList = new java.util.ArrayList();}}
// Anonymous inner class (source_class feature)Runnable sourceAnonymous = new Runnable() {@Overridepublic void run() {SourceLocalInner local = new SourceLocalInner();local.useAnnotation();}};}
// Target annotation with type parameter (target_class feature)@interface TargetAnnotation {// Type parameter (matches "target_feature: type parameter")<T> T getParamValue();
// Nested annotation as target class component@interface TargetParam {String value() default "default";}}