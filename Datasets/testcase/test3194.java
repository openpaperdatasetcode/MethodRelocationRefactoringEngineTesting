package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface EnumMethodAnnotation {}
// Parent class for call_method (parent_class type)class EnumParentClass {private String parentMethod(String data) {return "Parent-" + data;}}
// Source enum class (public modifier, type parameter + permits)public enum SourceEnum<T> implements EnumParentClass {INSTANCE;
private TargetEnum targetField = TargetEnum.VALUE1; // Per condition: source contains target field
// Varargs method (private access modifier, returns base type)@EnumMethodAnnotationprivate int varargsMethod(T... params) {// Raw type usageTargetEnum rawTarget = TargetEnum.VALUE2;
// Variable call + access_instance_methodtargetField.targetMethod();int targetVal = targetField.getFieldValue();
// Target inner recursive class (target_inner_rec)TargetEnum.TargetInnerRec innerRec = targetField.new TargetInnerRec();innerRec.recursiveAction();
return targetVal;}
// Static code blocks (pos for call_method)static {// Call method (parent_class, private, instance, superTypeReference.methodName(arguments))String callResult = SourceEnum.INSTANCE.parentMethod("enum-call");}}
// Target enum class (strictfp modifier, local inner class)strictfp enum TargetEnum {VALUE1, VALUE2;
public int fieldValue = 5; // Field for per_condition
public void targetMethod() {}
public int getFieldValue() {return fieldValue;}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {public void recursiveAction() {}}
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {public void localMethod() {}}new TargetLocalInner().localMethod();}}