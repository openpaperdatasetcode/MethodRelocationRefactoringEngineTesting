import java.util.Objects;
@interface SourceAnnotation {class MemberInnerClass {@RefactorMethodprivate int normalMethod(TargetAnnotation targetParam) {if (targetParam == null) {throw new NullPointerException("TargetAnnotation cannot be null");}
int sum = 0;for (int i = 0; i < 3; i++) {variableCall(targetParam.memberInner);sum += i;}
int count = 0;while (count < sum) {try {targetParam.memberInner.validate(count);count++;} catch (IllegalArgumentException e) {break;}}
return count;}
private void variableCall(TargetAnnotation.MemberInner inner) {inner.updateValue();}}
{new Runnable() {@Overridepublic void run() {MemberInnerClass inner = new MemberInnerClass();inner.normalMethod(new TargetAnnotation() {});}}.run();}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface RefactorMethod {}
protected @interface TargetAnnotation {class MemberInnerClass {private int value;
public void updateValue() {this.value++;}
public void validate(int num) throws IllegalArgumentException {if (num < 0) {throw new IllegalArgumentException("Invalid number");}}
public int getValue() {return value;}}
MemberInnerClass memberInner() default @MemberInnerClass;}
class CallerClass {private SourceAnnotation.MemberInnerClass sourceInner = new SourceAnnotation.MemberInnerClass();
protected TargetAnnotation callInTernary(boolean flag) {TargetAnnotation target = new TargetAnnotation() {};return flag ? sourceInner::normalMethod : new TargetAnnotation() {};}}