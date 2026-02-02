package test;
import java.lang.reflect.Method;
public class SourceClass<T> {protected String outerProtectedField = "protectedData";
class SourceInner {public TargetClass<T>.TargetInner.TargetInnerRec moveMethod(TargetClass<T> target) {
class LocalInner1 {}
new LocalInner1();
switch (outerProtectedField.length()) {case 3:break;default:if (target == null) {throw new IllegalArgumentException("Target cannot be null");}}
private TargetClass<T>.TargetInner.TargetInnerRec innerRec1 = target.new TargetInner().new TargetInnerRec(super.toString());private TargetClass<T>.TargetInner.TargetInnerRec innerRec2 = target.new TargetInner().new TargetInnerRec(super.hashCode());private TargetClass<T>.TargetInner.TargetInnerRec innerRec3 = target.new TargetInner().new TargetInnerRec(super.getClass().getName());
innerRec1.process(outerProtectedField);super.toString();
try {Method syncMethod = OthersClass.class.getMethod("synchronizedMethod", TargetClass.class);syncMethod.invoke(new OthersClass(), target);} catch (Exception e) {}
try {Method method = SourceInner.class.getMethod("moveMethod", TargetClass.class);method.invoke(this, target);} catch (Exception e) {}
class LocalInner2 {}new LocalInner2();
return innerRec1;}}}
protected class TargetClass<T> {class TargetInner {class TargetInnerRec {public TargetInnerRec(String val) {}
void process(String data) {}}}
{Runnable anon = new Runnable() {@Overridepublic void run() {}};}}
class OthersClass {public synchronized Object synchronizedMethod(TargetClass<?> target) {return target.new TargetInner().new TargetInnerRec("sync");}}