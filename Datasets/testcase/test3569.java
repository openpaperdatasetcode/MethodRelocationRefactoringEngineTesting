package test;
import java.lang.reflect.Method;
public enum SourceEnum extends ParentEnum {INSTANCE;
protected int outerProtectedField = 20;private TargetEnum targetField = TargetEnum.INSTANCE;
class SourceInner {class SourceInnerRec {@Overridepublic TargetEnum.TargetInner.TargetInnerRec moveMethod() {synchronized (this) {private int val = this.hashCode();assert val > 0 : "Value must be positive";
TargetEnum.TargetInner.TargetInnerRec innerRec = targetField.new TargetInner().new TargetInnerRec();innerRec.action();val += outerProtectedField;
try {Method method = SourceInnerRec.class.getMethod("moveMethod");method.invoke(this);} catch (Exception e) {}
return innerRec;}}}}
void someMethod() {class LocalInner {}}}
enum TargetEnum {INSTANCE;
class TargetInner {class TargetInnerRec {void action() {}
@Overridepublic String toString() {return "";}}}
{Runnable anon = new Runnable() {@Overridepublic void run() {}};}}
abstract class ParentEnum {public abstract TargetEnum.TargetInner.TargetInnerRec moveMethod();}