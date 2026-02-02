package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public class SourceClass<T> {public static class StaticNested {}
public class SourceInner {@MyAnnotationprotected TargetClass<T> methodToMove(TargetClass<T>... params) {class LocalInner {}LocalInner local = new LocalInner();
TargetClass<T>.MemberInner inner = params[0].new MemberInner();Object result = inner.privateMethod(params);
return params[0];}}}
public class TargetClass<V> {public class MemberInner {private Object privateMethod(TargetClass<V>... args) {return new Object();}}}