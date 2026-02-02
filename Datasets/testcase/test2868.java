package test;
import java.sql.SQLException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
sealed protected class SourceClass permits SubClass {static class StaticNested {}
@MethodAnno // has_annotationprotected void methodToMove(TargetClass<String> target) throws SQLException {class LocalInner {}LocalInner inner = new LocalInner();
// Uses outer thisSourceClass.this.toString();// Variable call (source contains target's field)int val = target.staticField;
// ExpressionMethodReference with numbers=1Runnable ref = inner::toString;
switch (val) {case 3:// Varargs method in switchTargetClass<String> result = StaticNested.varargsMethod(target, 3, "param");break;default:break;}
// SQLException declaration (no new exception thrown)if (val < 0) throw new SQLException();}
public static class StaticNested {// Varargs method with specified featurespublic static <T> TargetClass<T> varargsMethod(TargetClass<T> target, int num, String... args) {return target;}}}
class SubClass extends SourceClass {}
public class TargetClass<T> {static int staticField = 1; // Target field used in sourcestatic class TargetStaticNested {}}