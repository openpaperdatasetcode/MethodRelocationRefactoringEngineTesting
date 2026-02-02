package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
abstract class SourceClass {static int staticField;
public int overloadedMethod(int num) {return num;}
@TestAnnotationpublic int overloadedMethod(PrivateTargetClass target) {class LocalInner1 {}class LocalInner2 {}
LocalInner1 local1 = new LocalInner1();int var = staticField;
if (target == null) {throw new NullPointerException();}
return target.instanceMethod() + var;}}
private abstract class PrivateTargetClass {int instanceMethod() {class TargetLocalInner {}return 0;}}