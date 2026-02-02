package samepkg;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnotation {}
class SourceClass<T> {protected String outerProtectedField = "protectedValue"; private TargetClass<T> targetField; // Per condition: source contains target's field
public class MemberInnerClass {
protected static TargetClass staticInnerMethod1(TargetClass target, String arg) {
target.setValue(arg);
return target;
}
protected static TargetClass staticInnerMethod2(TargetClass target, int arg) {
target.setCount(arg);
return target;
}
}
@RefactorAnnotation // has_annotationprotected Object process(TargetClass<T> targetParam) {Runnable sourceAnonymous = new Runnable () {@Overridepublic void run () {targetParam.execute ();}};sourceAnonymous.run ();
// While statementint index = 0;while (index < 3) {targetParam.update(index++);}
// Variable call + access_outer_protectedString protectedVal = this.outerProtectedField;targetParam.setValue((T) protectedVal);
SourceClass<T> outerInstance = this;try {if (targetParam.getCount () < 0) {throw new IllegalArgumentException ("Count invalid:" +outerInstance.new MemberInnerClass ().staticInnerMethod1 (targetParam, protectedVal).getCount ());}if (targetParam.getValue () == null) {throw new NullPointerException ("Value null:" +outerInstance.new MemberInnerClass ().staticInnerMethod2 (targetParam, 10).getValue ());}} catch (IllegalArgumentException | NullPointerException e) {e.printStackTrace ();}
// Used by reflectiontry {Method staticMethod = MemberInnerClass.class.getDeclaredMethod("staticInnerMethod1", TargetClass.class, String.class);staticMethod.setAccessible(true);staticMethod.invoke(null, targetParam, "reflectionArg");} catch (Exception e) {e.printStackTrace();}
return targetParam;}
public void setTargetField(TargetClass<T> targetField) {this.targetField = targetField;}}
class TargetClass {
private U value;
private int count;
public void execute () {Runnable targetAnonymous = new Runnable () {@Overridepublic void run () {count++;}};targetAnonymous.run ();}
public void update(int num) {count += num;}
public U getValue() {return value;}
public void setValue(U value) {this.value = value;}
public int getCount() {return count;}
public void setCount(int count) {this.count = count;}}