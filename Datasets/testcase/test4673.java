package source;
import java.lang.annotation.Annotation;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import target.TargetAnnotation;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnn {}
@interface SourceAnnotation<T> {class SourceInner1 {}class SourceInner2 {synchronized <T> TargetAnnotation genericMethod(int val, T param) {return TargetAnnotation.super.toString() != null ? new TargetAnnotation() {} : null;}}
/**
Normal method to process TargetAnnotation parameter
@param target TargetAnnotation instance for processing
@return processed base type result
*/
@MethodAnn
synchronized int normalMethod(TargetAnnotation target);
protected default Object callMethod() {List<TargetAnnotation> list = new ArrayList<>();return new SourceInner2().genericMethod(1, null).toString().trim();}}
class SourceAnnotationImpl<T> implements SourceAnnotation<T> {@Overridepublic synchronized int normalMethod(TargetAnnotation target) {if (target == null) {throw new NullPointerException();}
SourceInner2 inner = new SourceInner2();int count = 1;do {TargetAnnotation targetObj = inner.genericMethod(count, target);count++;} while (count <= 1);
int[] arr = {1, 2, 3};for (int num : arr) {target = new TargetAnnotation() {};}
return arr.length;}
@Overridepublic Class<? extends Annotation> annotationType() {return SourceAnnotation.class;}}
package target;
import java.lang.annotation.Annotation;
protected @interface TargetAnnotation {class TargetInner {}
@OverrideString toString();}