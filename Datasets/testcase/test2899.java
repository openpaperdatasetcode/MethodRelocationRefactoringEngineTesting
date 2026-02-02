package samepkg;
import otherpkg.DiffPackageHelper;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface DefaultAnnotation {}
protected class SourceClass<T> {static class StaticNested<T> {}
Runnable anonymous = new Runnable() {@Overridepublic void run() {new SourceClass<T>().processTarget(new TargetSubclass<>());}};
public final void processTarget(TargetClass<T> target) {@DefaultAnnotationString exp = "AnnotationExpression";
try {synchronized (target) {DiffPackageHelper.checkSuperField(target);assert ((TargetSubclass<T>) target).superField == 1;}
TargetClass.MemberInner<T> inner = target.new MemberInner<>();inner.doAction();System.out.println(exp);} catch (Exception e) {}}}
abstract class TargetClass<T> implements Runnable {class MemberInner {
void doAction() {}
}
@Overridepublic void run() {}}
class TargetSubclass<T> extends TargetClass<T> {int superField = 1;}
package otherpkg;
import samepkg.TargetClass;
public class DiffPackageHelper {public static <T> void checkSuperField(TargetClass<T> target) {if (target instanceof TargetSubclass) { System.out.println( ((TargetSubclass) target).superField);}}}