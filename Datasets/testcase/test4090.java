package test;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
@Target(ElementType.METHOD)@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnnotation {}
abstract class ParentSource {protected ParentSource() {}}
abstract class SourceClass extends ParentSource {@RefactorTestAnnotationpublic final TargetClass varargsMethod(TargetClass targetParam, String... extraArgs) {super();
if (targetParam == null) {throw new IllegalArgumentException("TargetClass parameter cannot be null");}
String arg = extraArgs.length > 0 ? extraArgs[0] : "default";switch (arg) {case "inner":TargetClass.MemberInner inner = targetParam.new MemberInner();int var = inner.getInnerValue();break;default:var = targetParam.getTargetField();break;}
return targetParam;}}
/**
TargetClass contains member inner class to test refactoring dependencies,
and provides methods for variable access in the source method.*/class TargetClass {private int targetField = 10;
/**
Member inner class of TargetClass, used to test inner class dependencies in refactoring
*/
class MemberInner {
public int getInnerValue() {
return 5;
}
}
public int getTargetField() {return targetField;}}