package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
abstract class SourceClass<T> {class MemberInner {class InnerRecursive {@MyAnnotationTargetClass<T> methodToMove(TargetClass<T>... targets) {class LocalInner {}LocalInner local = new LocalInner();
int i = 0;do {for (TargetClass<T> target : targets) {TargetClass<T>.MemberInner.InnerRecursive innerRec = target.new MemberInner().new InnerRecursive();
assert innerRec.field != null : "Field must not be null";innerRec.variableCall();
Supplier<Object> supplier = () -> SourceClass.this.new MemberInner().new InnerRecursive().genericMethod(innerRec);Object result = supplier.get();}i++;} while (i < 1);
return targets[0];}
public Object generic genericMethod(TargetClass<T>.MemberInner.InnerRecursive inner) {
return inner.field;
}
}
}
}
class TargetClass<T> implements TestInterface {class MemberInner {class InnerRecursive {T field;
void variableCall() {}}}}
interface TestInterface {}