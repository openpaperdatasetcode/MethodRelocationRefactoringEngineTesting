package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
protected record SourceClass(int a) permits TargetClass {class MemberInner {}
@MyAnnotationstrictfp static TargetClass.Inner moveMethod(TargetClass target) {class LocalInner {}LocalInner li = new LocalInner();
int[] arr = {1, 2, 3};private int val1 = arr[0];private int val2 = arr[1];private int val3 = arr[2];
TargetClass.Inner inner = target.new Inner();int count = 0;while (count < 1) {inner.field = 1;count++;if (count == 1) continue;}
expressionStatement();variableCall(inner);
Supplier<Object> lambda = () -> TargetClass.staticMethod(inner);lambda.get();
return inner;}
private static void expressionStatement() {}
private static void variableCall(TargetClass.Inner inner) {}}
record TargetClass(String b) {class Inner {int field;}
Object anonymous = new Object() {};
private static Object staticMethod(Inner inner) {return inner.field;}}
