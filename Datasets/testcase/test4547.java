package same.pkg;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.CONSTRUCTOR)@interface MyConstructorAnnotation {}
public record SourceClass(String sourceField) {static class SourceStaticNested {void nestedMethod(TargetClass targetParam) {}}
@MyConstructorAnnotationpublic SourceClass(String sourceField, TargetClass targetParam) {this(sourceField);variableCall(targetParam);
List<String> resultList = new ArrayList<>();for (int i = 0; i < 1; i++) {resultList.add(targetParam.targetField());}
class LocalInner {void innerTryMethod() {try {ParentClass.staticMethod(targetParam.superField);} catch (Exception e) {}}}new LocalInner().innerTryMethod();
if (targetParam == null) {ParentClass.staticMethod(null);throw new IllegalArgumentException("Target parameter is null");}}
private void variableCall(TargetClass targetParam) {String localVar = targetParam.targetField() + "_variable";}}
protected record TargetClass(String targetField) implements MyInterface {String superField = "targetSuperField";}
interface MyInterface {}
class ParentClass {private static void staticMethod(String field) {}}