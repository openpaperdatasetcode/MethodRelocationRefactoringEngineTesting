package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnn {}
interface MyInterface {}
sealed class SourceClass permits SubSource {class MemberInner {protected TargetClass instanceMethod(int num, String str, Object obj, TargetClass superRef) {return superRef;}}
void localInnerMethod() {class LocalInner {}}
@MyAnnfinal TargetClass instanceMethod(TargetClass target) {try {Method method = SourceClass.class.getMethod("instanceMethod", TargetClass.class);method.invoke(this, target);} catch (Exception e) {}
variableCall = target.field;TargetClass[] arr = { target };
TargetClass result = (arr.length > 0)? new MemberInner().instanceMethod(2, "inner_class", new Object(), target): target;
Supplier<Object> supplier = () -> this.abstractMethod();return result;}
abstract Object abstractMethod();
String variableCall;}
final class SubSource extends SourceClass {@OverrideObject abstractMethod() {return null;}}
abstract class TargetClass implements MyInterface {String field;static class TargetStaticNested {}}