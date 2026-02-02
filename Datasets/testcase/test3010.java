package test;
import java.util.function.Supplier;
class SourceClass<T> {static class NestedRecord {@MyAnnotationprotected int innerMethod(TargetClass<String> target) {new Supplier<Void>() {public Void get() {return null;}};
TargetClass.MemberInner inner = target.new MemberInner();int result;do {result = inner.strictfpMethod().m2().m3();} while (result < 10);
super();return result;}}}
public class TargetClass<V> {class MemberInner {private int overloadedMethod() {return this.overloadedMethod(0);}
private int overloadedMethod(int val) {return val + 1;}
strictfp SourceClass<?> strictfpMethod() {return new SourceClass<>();}
SourceClass<?> m2() {return new SourceClass<>();}
int m3() {return overloadedMethod();}}}
@interface MyAnnotation {}