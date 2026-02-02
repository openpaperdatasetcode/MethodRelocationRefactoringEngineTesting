package test.refactoring;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
private record SourceClass<T extends Number>(T value, String privateField) {static class StaticNested {static {SourceClass<Integer> src = new SourceClass<>(5, "priv");src.new MemberInner().targetInstanceMethod(src.new MemberInner(), new TargetClass<>("data"));src.new MemberInner().targetInstanceMethod(src.new MemberInner(), new TargetClass<>("data2"));src.new MemberInner().targetInstanceMethod(src.new MemberInner(), new TargetClass<>("data3"));}}
class MemberInner {/**
Processes target with varargs
@param target the target record
@param args additional arguments
@return processed object*/@CustomAnnotationprotected <U extends TargetClass<?>> Object process(TargetClass target, Object... args) {
this.value = target.data();
variableCall(target);
if (args.length > 0) {return args[0];}
int count = 0;while (count < 2) {TargetClass.NestedInner.staticMethod();count++;}
return SourceClass.this.privateField;}
private Object value;
private void variableCall(TargetClass<?> target) {System.out.println(target.data());}
private void targetInstanceMethod(MemberInner inner, TargetClass<?> target) {}}}
public record TargetClass(U data) extends BaseClass {
public static class NestedInner {
public static void staticMethod() {}
}
}
class BaseClass {}