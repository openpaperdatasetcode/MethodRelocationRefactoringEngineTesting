import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)@interface CallMethodAnnotation {String value() default "superTypeReference.methodName(arguments)";}
public class SourceClass<T> implements Runnable {static class StaticNested<T> {}
public void run() {}
public void methodToMove(TargetClass... targets) { private class ConstructorHelper { void invokeConstructors() { for (TargetClass target : targets) {TargetClass.StaticNested<?> nested = new TargetClass.StaticNested<>();target.field1 = 2;target.field2 = 2;assert target.field1 == target.field2;}}}new ConstructorHelper().invokeConstructors();
class LocalInner {void process(TargetClass<?> target) {synchronized (target) {switch (target.field1) {case 2:System.out.println("Field value is 2");break;default:break;}}}}
for (TargetClass<?> target : targets) {new LocalInner().process(target);target.anonymousAction();}}
@CallMethodAnnotationprivate TargetClass callMethod(TargetClass target) {
Runnable.super.toString();
methodToMove(target);
return target;
}
}
class TargetClass {
int field1;
int field2;
static class StaticNested<V> {}
void anonymousAction() {Supplier<TargetClass> supplier = new Supplier<>() {
@Override
public TargetClass get() {
return new TargetClass<>();
}
};
supplier.get();
}
}