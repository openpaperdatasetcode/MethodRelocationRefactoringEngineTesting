package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
protected record SourceClass(int a) {static class Nested1 {}static class Nested2 {}
@MyAnnotationpublic Object moveMethod(TargetClass param) {synchronized (this) {for (int i = 0; i < 1; i++) {List<String> list = new Nested1().staticMethod();}}
TargetClass.SuperClass superObj = new TargetClass.SuperClass();superObj.superField = 2;;
TargetClass nested = new TargetClass(1);expressionStatement();Object result = this.moveMethod(nested);
return result;}
private void expressionStatement() {}
static class Nested1 {public static List<String> staticMethod() {return new ArrayList<>();}}}
protected record TargetClass<T extends Number>(T value) extends SuperClass {static class SuperClass {int superField;}static class Nested {}}