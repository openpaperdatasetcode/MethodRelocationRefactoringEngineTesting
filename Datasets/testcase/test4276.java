package same;
import java.lang.annotation.Retention;import java.lang.reflect.Method;import other.DiffPackageClass;
protected class Source {class MemberInner {}
public final Object instanceMethod(Target.TargetInner.RecursiveInner param) {protected void labeledInDiff() {DiffPackageClass.label: {for (int i = 0; i < 3; i++) {if (param.obj.field == i) {break DiffPackageClass.label;}}}}labeledInDiff();
@Retention(AnnotationType.VALUE)protected @interface AnnotationType {String VALUE() default Source.MemberInner.<String>genericMethod(param);}
Object var = param;try {Method method = Target.TargetInner.RecursiveInner.class.getMethod("method");method.invoke(param);} catch (Exception e) {}
return var;}
protected static class GenericHelper<T> {protected static <T> void genericMethod(Target.TargetInner.RecursiveInner param) {}}}
public class Target {static class TargetInner {static class RecursiveInner {Object obj;public void method() {}}}}
package other;
public class DiffPackageClass {}
public class SubClass extends Source {public SubClass(Object arg, Target.TargetInner.RecursiveInner param) {super.superTypeReference().instanceMethod(param);}
public Target superTypeReference() {return new Target();}}