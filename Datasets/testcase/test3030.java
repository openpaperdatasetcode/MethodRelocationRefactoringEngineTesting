package test;
import otherpackage.OtherClass;import java.util.function.Supplier;
strictfp class SourceClass {static class NestedClass {@MyAnnotationObject overloadedMethod(TargetClass targetParam) {new Runnable() {public void run() {}};
otherpackage.OtherClass.PublicField superFieldRef = otherpackage.OtherClass.PublicField.INSTANCE;assert superFieldRef != null : "Super field must not be null";
GenericInner<String> inner1 = new GenericInner<>();GenericInner<Integer> inner2 = new GenericInner<>();GenericInner<Boolean> inner3 = new GenericInner<>();inner1.m1().m2().m3();inner2.m1().m2().m3();inner3.m1().m2().m3();
targetParam.doAction();Object result;try {result = OtherClass.staticMethod(this.overloadedMethod(targetParam, "extra"));} catch (Exception e) {result = null;}return result;}
Object overloadedMethod(TargetClass targetParam, String extra) {return extra;}
class GenericInner<T> {GenericInner<T> m1() { return this; }GenericInner<T> m2() { return this; }void m3() {}}}
{Supplier<Void> supplier = () -> null;}}
class TargetClass implements MyInterface {void doAction() {class LocalInner {}}}
interface MyInterface {}
@interface MyAnnotation {}
// Different package: otherpackagepackage otherpackage;
public class OtherClass {public static final PublicField PublicField = new PublicField();
public static class PublicField {public static final PublicField INSTANCE = new PublicField();}
public static Object staticMethod(Object input) {return input;}}