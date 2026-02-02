package test;
import java.lang.reflect.Method;import java.lang.reflect.Field;
private @interface TargetAnnotation {String value() default "";
class TargetInnerClass {protected String targetInnerField = "target_inner_field";
public void targetInnerMethod() {}}}
@interface SourceAnnotation {String name() default "";
class SourceStaticNested {public static String staticField = "source_static_field";
public static void staticMethod(TargetAnnotation.TargetInnerClass inner) {inner.targetInnerMethod();}}
class SourceInnerClass {protected TargetAnnotation.TargetInnerClass getTargetInner() {return new TargetAnnotation.TargetInnerClass();}}
default TargetAnnotation instanceMethod() {SourceInnerClass sourceInner = new SourceInnerClass();TargetAnnotation.TargetInnerClass targetInner = sourceInner.getTargetInner();TargetAnnotation targetAnno = TargetAnnotation.class.getAnnotation(TargetAnnotation.class);
int count = 0;do {SourceStaticNested.staticMethod(targetInner);count++;} while (count < 1);
try {Field superField = TargetAnnotation.TargetInnerClass.class.getDeclaredField("targetInnerField");superField.setAccessible(true);String fieldVal = (String) superField.get(targetInner);variableCall(targetInner, fieldVal);} catch (NoSuchFieldException | IllegalAccessException e) {e.printStackTrace();}
SubClass sub = new SubClass();Runnable runnable = (sub.shouldExecute()) ? SubClass::staticCall : () -> {};runnable.run();
return targetAnno;}
private void variableCall(TargetAnnotation.TargetInnerClass inner, String val) {inner.targetInnerField = val + "_updated";}}
final class SubClass {public boolean shouldExecute() {return true;}
public static void staticCall() {TargetAnnotation.TargetInnerClass inner = new TargetAnnotation.TargetInnerClass();inner.targetInnerMethod();}}