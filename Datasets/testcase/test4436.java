package test;
import java.util.List;import java.util.ArrayList;
@interface SourceAnnotation<T> {class InnerA {}class InnerB {protected String outerProtectedField = "protected-value";}
@Deprecatedpublic default List<String> methodToMove(TargetAnnotation param) {InnerB innerB = new InnerB();TargetAnnotation.Nested targetNested = new TargetAnnotation.Nested();
labeledBlock: {String thisField = innerB.outerProtectedField;if (thisField == null) break labeledBlock;}
List<String> result = new ArrayList<>();for (int i = 0; i < 1; i++) {TargetAnnotation.Nested accessed = getTargetNested(targetNested);result.add(accessed.nestedField);}
String var = param.targetField;result.add(var);return result;}
private TargetAnnotation.Nested getTargetNested(TargetAnnotation.Nested nested) {return nested;}}
private @interface TargetAnnotation {String targetField() default "target-default";
static class Nested {String nestedField = "nested-value";}}
