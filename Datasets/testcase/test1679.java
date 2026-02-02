package test;
import java.lang.annotation.*;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {String value() default TargetClass.MemberInner.strictfpMethod();}
private class SourceClass<T> {private T outerPrivateField;
static class StaticNested {}
static {new SourceClass<>().overloadedMethod();}
@MyAnnotationprivate TargetClass<T>.MemberInner.InnerRec instanceMethod(TargetClass<T>.MemberInner.InnerRec param) {class LocalInner {}
assert param != null;variableCall();T val = SourceClass.this.outerPrivateField;
return new TargetClass<T>().new MemberInner().new InnerRec();}
public List<String> overloadedMethod() {super.toString();return new ArrayList<>();}
private void variableCall() {}}
sealed class TargetClass extends ParentClass permits SubTargetClass {
class MemberInner {
class InnerRec {}
strictfp static void strictfpMethod() {}}}
abstract class ParentClass<V> {}
final class SubTargetClass extends TargetClass<String> {}
