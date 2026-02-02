package test;
import java.lang.reflect.Method;
final class SourceClass extends ParentClass {public static class StaticNested {}
{Runnable anon = new Runnable() {@Overridepublic void run() {}};}
@Overridepublic int moveMethod (TargetClass<? extends Number> target) {class LocalType {}LocalType local = new LocalType ();
assert target != null : "Target cannot be null";int fieldVal = target.instanceField;fieldVal += TargetClass.StaticNested.staticField;
target.inner.innerRec.process(fieldVal);TargetClass.StaticNested.process(target);
try {Method method = SourceClass.class.getMethod("moveMethod", TargetClass.class);method.invoke(this, target);} catch (Exception e) {}
return fieldVal;}
public int moveMethod(TargetClass<String> target) {class LocalType {}LocalType local = new LocalType();
assert target.instanceField > 0 : "Instance field must be positive";int fieldVal = target.instanceField;fieldVal += TargetClass.StaticNested.staticField;
target.inner.innerRec.process(fieldVal);return fieldVal;}}
abstract class ParentClass {public abstract int moveMethod(TargetClass<? extends Number> target);}
public class TargetClass<T> {public int instanceField = 10;public static class StaticNested {public static int staticField = 5;public static void process(TargetClass<?> target) {}}
TargetInner inner = new TargetInner();
class TargetInner {TargetInnerRec innerRec = new TargetInnerRec();
class TargetInnerRec {void process(int val) {}}}}