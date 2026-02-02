package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public enum SourceEnum implements Runnable {INSTANCE;
class MemberInner {}
{new Runnable() {public void run() {}};}
@MyAnnotationprotected Object methodToMove(TargetEnum target) {TargetEnum.MemberInner inner = target.new MemberInner();int[] arr = {1};Object raw = new ArrayList(); // Raw type
do {if (inner.obj.field == 1) {SourceEnum.this.toString();}int val = arr[0];} while (inner.field > 0);
// Override violation: implementing class reduces accessTargetEnum.SubEnum sub = new TargetEnum.SubEnum() {@Overridepublic void publicMethod() {}};
return inner;}
public void run() {}}
protected enum TargetEnum {VALUE;
class MemberInner {SourceEnum obj = SourceEnum.INSTANCE;int field = 1;}
protected static class SubEnum {public void publicMethod() {}}}