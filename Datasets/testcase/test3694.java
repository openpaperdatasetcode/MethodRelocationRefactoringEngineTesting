package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {String value() default "";}
private enum SourceEnum<T> {INSTANCE;
public class MemberInner {public class DeepInner {private TargetEnum<T> instanceMethod(TargetEnum<T> target) {@CustomAnnotation(OtherClass.Nested.getInfo(target))class TypeDecl {TargetEnum<T> process(TargetEnum<T> t) {return t;}}
TypeDecl typeDecl = new TypeDecl();TargetEnum<T> processed = typeDecl.process(target);
for (TargetEnum<T> t : TargetEnum.values()) {if (t.field == 1) {continue;}processed = this.instanceMethod(t);}
new Runnable() {@Overridepublic void run() {TargetEnum<T> created = new TargetEnum<>(MemberInner.this.new InnerHelper().createTarget(target.getField()));System.out.println(created.getField());}}.run();
return processed;}}}
public class InnerHelper {public T createTarget(T value) {return value;}}}
public enum TargetEnum<T> {VALUE1(null), VALUE2(null);
public static final String STATIC_FIELD = "static_data";public T field;
TargetEnum(T field) {this.field = field;}
public T getField() {class LocalInner {T getValue() {return field;}}return new LocalInner().getValue();}
public void setField(T field) {this.field = field;}}
class OtherClass {public static class Nested {public static <T> String getInfo(TargetEnum<T> target) {return target.field.toString() + TargetEnum.STATIC_FIELD;}}}
