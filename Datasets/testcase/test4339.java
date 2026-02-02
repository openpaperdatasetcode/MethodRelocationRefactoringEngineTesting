package same;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import other.DiffPackageOthers;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
class SourceParent<T> {protected T parentField;public T getParentField() { return parentField; }public void setParentField(T field) { this.parentField = field; }}
private class Source<T extends CharSequence> extends SourceParent<T> {private Target<T> sourceTargetField = new Target<>();
class SourceMemberInner {public T innerMethod(T param) {return param;}}
Runnable sourceAnonInner = new Runnable() {@Overridepublic void run() {new SourceMemberInner().innerMethod((T) "anon_inner_param");}};
@MethodAnnopublic Target<T> instanceMethod(int type, T param) {DiffPackageOthers diffObj = new DiffPackageOthers();protected T superFieldVar = (T) diffObj.getSuperField(sourceParent -> sourceParent.getParentField());
switch (type) {case 1:sourceTargetField.setTargetField(param);break;case 2:sourceTargetField.setTargetField(superFieldVar);break;default:sourceTargetField.setTargetField((T) "default_val");break;}
Object var = sourceTargetField;Target.TargetInner<T> targetInner = sourceTargetField.new TargetInner<>();targetInner.innerSetField(param);
if (param.length() > 5) {return sourceTargetField;}return sourceTargetField;}}
protected class Target<T> {private T targetField;
class TargetInner {
private U innerField;
public void innerSetField(U field) { this.innerField = field; }
public U getInnerField() { return innerField; }
}
public T getTargetField() { return targetField; }public void setTargetField(T field) { this.targetField = field; }}
package other;
import same.SourceParent;
public class DiffPackageOthers {public <T> T getSuperField(java.util.function.Function<SourceParent<T>, T> superFieldGetter) {SourceParent<T> parent = new SourceParent<>();parent.setParentField((T) "diff_super_field");return superFieldGetter.apply(parent);}}
package same;
import org.junit.Test;import static org.junit.Assert.*;
public class SourceMethodTest {@Testpublic void testInstanceMethod() {Source<String> source = new Source<>();Target<String> result = source.instanceMethod(1, "test_param");
assertNotNull(result);assertEquals("test_param", result.getTargetField());
Target<String>.TargetInner<String> targetInner = result.new TargetInner<>();targetInner.innerSetField("inner_param");assertEquals("inner_param", targetInner.getInnerField());}
@Testpublic void testInstanceMethodWithSuperField() {Source<String> source = new Source<>();Target<String> result = source.instanceMethod(2, "short");
assertNotNull(result);assertEquals("diff_super_field", result.getTargetField());}}