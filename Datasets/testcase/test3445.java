package test;
// Target parent annotation (for target's extends feature)public @interface ParentAnnotation {}
// Target interface (for target's implements feature)public interface AnnotationInterface {}
// Target annotation class (type: @interface, default modifier, extends + implements + member inner class)default @interface TargetAnnotation extends ParentAnnotation, AnnotationInterface {// Member inner class (target_feature)public @interface TargetMemberInner {}
int targetField() default 5; // Field for per_condition}
// Source annotation class (type: @interface, default modifier, local inner class + member inner class)@interface SourceAnnotation {// Member inner class (source feature)public @interface SourceMemberInner {// Instance method (protected access modifier, returns base type)protected int instanceMethod(TargetAnnotation targetParam);}
// Local inner class (source feature)class SourceLocalInner {public int process(TargetAnnotation target) {// Uses outer this (reference to annotation instance)SourceAnnotation.SourceMemberInner inner = SourceAnnotation.SourceMemberInner.class.getAnnotation(SourceAnnotation.SourceMemberInner.class);
// NullPointerExceptionif (target == null) {throw new NullPointerException("Target annotation cannot be null");}
// CaseDefaultExpression (numbers=1, modifier=protected)protected int result = switch (target.targetField()) {case 3 -> 3;case 4 -> 4;default -> 5; // Default case expression};
// Variable callint targetVal = target.targetField();
return result + targetVal;}}
// Delegate to local inner class method (per_condition: source contains target field)int value() default new SourceLocalInner().process(TargetAnnotation.class.getAnnotation(TargetAnnotation.class));}