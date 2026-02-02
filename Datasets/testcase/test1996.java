package test;
import java.lang.reflect.Method;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
sealed abstract class SourceClass permits SubClass {protected int outerProtected = 5;
class MemberInner {}
{new Runnable() {@Overridepublic void run() {}};}
@Override@MyAnnotationpublic final int hashCode() {TargetClass target = new TargetClass();if (target.field == 0) ;
int result = target.field + outerProtected;
try {Method method = TargetClass.LocalHolder.class.getMethod("getCount");result += (int) method.invoke(null);} catch (Exception e) {result = TargetClass.LocalHolder.getCount();}
return result;}
abstract void abstractMethod();}
final class SubClass extends SourceClass {@Overridevoid abstractMethod() {}}
class TargetClass {int field;
void process() {class LocalHolder {protected static int getCount() {return 3;}}}}