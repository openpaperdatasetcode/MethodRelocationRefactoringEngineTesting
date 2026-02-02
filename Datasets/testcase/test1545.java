package test;
import java.lang.reflect.Method;
class SourceClass<T> {protected T outerProtected;protected TargetClass<String> targetField;
public class InnerFirst {public class InnerRec {private void process(int... args) {class LocalType {}LocalType local = new LocalType();
TargetClass.InnerFirst targetInnerFirst = new TargetClass.InnerFirst();targetInnerFirst.field = outerProtected;
TargetClass.InnerFirst.InnerRec targetInnerRec = targetField.new InnerFirst().new InnerRec();targetInnerRec.setValue(null, null, null);
TargetClass rawTarget = new TargetClass();
try {Class<?> cls = Class.forName("test.SourceClass
InnerFirst
InnerRec");Method method = cls.getMethod("process", int[].class);method.invoke(this, (Object) new int[]{1, 2});} catch (Exception e) {// no new exception}}}}}
/**
Generic target class with member inner classes
@param type parameter
*/
public class TargetClass {
public class InnerFirst {
public U field;
public class InnerRec {private U value;
public void setValue(U v1, U v2, U v3) {this.value = v1;}}}}