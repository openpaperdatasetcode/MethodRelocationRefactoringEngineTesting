package test;
strictfp class SourceClass {// Static nested class (source_feature)public static class SourceStaticNested {}
// Member inner class (source_feature)public class SourceInner {// Overloading method 1private int methodToMove(TargetClass target) {return processTarget(target, 1);}
// Overloading method 2private long methodToMove(TargetClass target, long multiplier) {return processTarget(target, multiplier);}
private <T extends Number> T processTarget(TargetClass target, T multiplier) {// Variable call + contains target parameter (per_condition)target.toString();Number result = 0;
// SynchronizedStatement with ClassName.field (count 1, pos: diff_package_others)synchronized (TargetClass.STATIC_FIELD) {try {String targetField = target.targetField;result = targetField.length() * multiplier.intValue();} catch (Exception e) {// No new exceptionresult = 0;}}
return (T) result;}}}
protected class TargetClass {public String targetField = "targetValue"; // Source contains target's field (per_condition)public static final String STATIC_FIELD = "targetStaticField"; // ClassName.field
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {}new TargetLocalInner();}}