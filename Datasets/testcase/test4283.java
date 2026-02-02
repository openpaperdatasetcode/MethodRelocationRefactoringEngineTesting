package same;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
final class Source<T> {protected String outerProtectedField = "protectedValue";Target<String> targetField = new Target<>();
class SourceInner {Object varargsMethod(Target.TargetInner... innerParams) {Target.TargetInner firstInner = innerParams[0];
firstInner.instanceMethod();String expStmt = targetField.staticField;expStmt;
Object var = firstInner;String accessOuter = Source.this.outerProtectedField;String staticField = Target.staticField;
return var;}}
@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)private @interface MethodRefAnnotation {int value() default Source::callPrivateMethod;}
@MethodRefAnnotationprivate int callPrivateMethod() {return new SourceInner().varargsMethod(targetField.new TargetInner());}}
class Target {
public static String staticField = "targetStaticField";
class TargetInner {public void instanceMethod() {}}
Runnable targetAnonInner = new Runnable() {@Overridepublic void run() {new TargetInner().instanceMethod();}};}
