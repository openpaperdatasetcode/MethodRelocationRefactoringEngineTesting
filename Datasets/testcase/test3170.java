package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.IntFunction;
@Retention(RetentionPolicy.RUNTIME)@interface FeatureAnn {}
public class TargetClass<T> {T targetField;
public void example() {class LocalInner {} // target_feature: local inner class}
class TargetInner {record TargetInnerRec(T value) {} // target_inner_rec}}
public class SourceClass {class MemberInner {public int process(int num) {return num * 2;}}
public void example() {class LocalInner {} // source feature: local inner class}
@FeatureAnn // has_annotationObject methodToMove(TargetClass<String>.TargetInner.TargetInnerRec rec) {// Variable callString var = rec.value();TargetClass<String> target = new TargetClass<>();MemberInner inner = new MemberInner();
// Try statementtry {var = var.toUpperCase();} catch (NullPointerException e) {var = "default";}
// Array initialization with Lambda (method_feature)IntFunction<Integer>[] funcArray = new IntFunction[] {(param) -> inner.process(param + 2), // 2 as required(param) -> inner.process(param * 2)};
return funcArray[0].apply(1);}}