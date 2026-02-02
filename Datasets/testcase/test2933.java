import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)@interface TestAnnotation {}
public class SourceClass<T> {class MemberInner {public Object methodToMove(TargetClass<?> target) {class LocalInner {@TestAnnotationprivate void varargsMethod(String... args) {super.toString();System.out.println(args[0] + args[1]);}
private void varargsMethod(Integer... args) {}}
LocalInner local = new LocalInner();switch (target.field) {case 1:local.varargsMethod("a", "b");break;default:break;}
target.anonymousAction();return target.field;}}}
protected class TargetClass {
int field;
void anonymousAction() {Runnable anonymous = new Runnable() {@Overridepublic void run() {field = 1;}};anonymous.run();}}