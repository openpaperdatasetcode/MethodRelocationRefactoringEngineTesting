package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodFeatureAnn {}
protected class TargetClass {String targetField;class TargetInner {} // target_feature: member inner class}
private class SourceClass {class SourceInner {// has_annotation (duplicate as required)@MethodFeatureAnn@MethodFeatureAnnprivate void methodToMove(TargetClass target, String... args) {// Variable callString var = target.targetField;TargetClass.TargetInner inner = target.new TargetInner();
// Expression statementtarget.targetField = "processed";
// Enhanced for statementfor (String arg : args) {var += arg;}
// with_bounds: generic type with boundsclass BoundedGeneric<T extends TargetClass> {}BoundedGeneric<TargetClass> boundedObj = new BoundedGeneric<>();}}}