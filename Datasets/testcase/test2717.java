package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
enum SourceEnum<T extends TargetEnum> {INSTANCE;
// Type parameter (source_feature)private T targetRef;
// Static nested classes (source_feature)public static class StaticNested1 {}public static class StaticNested2 {}
@MethodAnnotprotected void methodToMove(T target) { // Method types parameter: Target class// Variable call + contains target parameter (per_condition)target.toString();this.targetRef = target;
// Raw type usageTargetEnum rawTarget = target;TargetEnum.TargetInner rawInner = rawTarget.new TargetInner();
// Access instance fieldString targetField = rawInner.innerField;
// Depends on static fieldrawInner.innerField = targetField + SourceStaticField.STATIC_VALUE;}
// Static field for depends_on_static_field featureprivate static class SourceStaticField {public static final String STATIC_VALUE = "_source_static";}}
public enum TargetEnum {TARGET_INSTANCE("targetInnerValue");
public final String enumField;
TargetEnum(String enumField) {this.enumField = enumField;}
// Static nested class (target_feature)public static class TargetStaticNested {}
public class TargetInner {public String innerField = enumField; // Source contains target's field (per_condition)}}