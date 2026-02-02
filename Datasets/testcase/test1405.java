package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)@interface EnumMethodAnno {}
private enum SourceEnum {INSTANCE1, INSTANCE2;
private String sourceField = "sourceData";
// Member inner class 1 (source feature)class SourceInner1 {// Member inner class 2 (source feature)class SourceInnerRec { // source_inner_rec@EnumMethodAnno // has_annotationpublic final int methodToMove() { // base type return// Source contains target's field (per_condition)TargetEnum<String> target = TargetEnum.INSTANCE;String targetFieldVal = target.targetField;
// access_instance_fieldString fieldVal = SourceEnum.this.sourceField;
// expression statementfieldVal += targetFieldVal;
// numbers:1, modifier:default, exp:NullLiteralObject nullObj = null;int count = (nullObj == null) ? 1 : 0;
// variable callSourceInner1 inner1 = new SourceInner1();TargetEnum.MemberInner targetInner = target.new MemberInner();targetInner.doAction();
// ForStatement (private, this.field, 1, pos:same_package_others)for (int i = 0; i < 1; i++) {target.targetField = "updated" + i; // this.field, 1}
// while statementint loopCount = 0;while (loopCount < 1) {loopCount++;}
// requires_try_catch + used_by_reflectiontry {Method method = TargetEnum.MemberInner.class.getMethod("doAction");method.invoke(targetInner);} catch (Exception e) {}
return 0; // base type}}}}
public enum TargetEnum extends SuperClass { // target_feature: type parameter, extends
INSTANCE;
public U targetField = (U) "targetData"; // Target field used by source
// target_feature: anonymous inner classRunnable anonInner = new Runnable() {@Overridepublic void run() {targetField = (U) "anonUpdated";}};
// target_innerclass MemberInner {void doAction() {}}}
abstract class SuperClass<V> {}
