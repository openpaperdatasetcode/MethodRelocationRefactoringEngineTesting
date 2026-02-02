package test;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
@Target(ElementType.METHOD)@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
public enum SourceEnum<T extends Number> {INSTANCE(10);
private final int sourceField;private TargetEnum targetField;
SourceEnum(int sourceField) {this.sourceField = sourceField;this.targetField = new TargetEnum.MemberInner(this).createTargetEnum();}
class InnerClass {@strictfpTargetEnum constructorMethod(T param) {class LocalType {}LocalType local = new LocalType();
int var = SourceEnum.this.sourceField + TargetEnum.staticField;;
TargetEnum target = new TargetEnum(var) {@Overridevoid targetMethod() {}};return target;}
@MethodAnnotationTargetEnum getTargetFromInner() {return constructorMethod((T) Integer.valueOf(5));}}}
protected enum TargetEnum {VALUE1(5),VALUE2(15);
int targetField;static int staticField = 3;
TargetEnum(int targetField) {super();this.targetField = targetField;}
void targetMethod() {}
class MemberInner {private final SourceEnum<?> outerSource;
MemberInner(SourceEnum<?> outerSource) {this.outerSource = outerSource;}
TargetEnum createTargetEnum() {return new TargetEnum(outerSource.sourceField + TargetEnum.staticField);}}}