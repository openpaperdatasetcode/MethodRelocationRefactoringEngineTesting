package test;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
@interface SourceAnnotation {class Inner {protected static int staticMethod(TargetAnnotation target) {// Constructor invocationTargetAnnotation.StaticNested nested = new TargetAnnotation.StaticNested();
// Super constructor invocation in anonymous subclassTargetAnnotation subTarget = new TargetAnnotation() {};
// Variable callint base = target.value();
// Depends on static fieldbase += TargetAnnotation.StaticNested.STATIC_FIELD;
// Ternary operator with constructor method (inner_class)TargetAnnotation.StaticNested.InnerInner inner = base > 5? new TargetAnnotation.StaticNested().new InnerInner(): nested.new InnerInner();int result = inner.calculate();
return result;}}}
@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.TYPE)default @interface TargetAnnotation {int value() default 0;
// Static nested classclass StaticNested {public static int STATIC_FIELD = 10;
class InnerInner {public int calculate() {return 5;}}}}