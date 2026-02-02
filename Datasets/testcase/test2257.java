package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class SourceClass {class FirstMemberInner {}class SecondMemberInner {@MyAnnotationint methodToMove(AbstractTarget target) {int field = target.targetField;assert TargetClassStaticField.STATIC_FIELD > 0;
if (field == TargetClassStaticField.STATIC_FIELD) {super.toString();field++;}
return field;}}}
abstract class AbstractTarget {int targetField;
void createLocalInner() {class TargetLocalInner {int innerField = targetField;}}}
class TargetClassStaticField {static int STATIC_FIELD = 5;}