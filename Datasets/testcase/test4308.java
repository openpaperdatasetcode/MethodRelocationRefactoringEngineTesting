package same;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
abstract class Source {Target targetField = new Target();static class SourceStaticNested1 {}static class SourceStaticNested2 {}
class SourceInner {class SourceInnerRec {@MethodAnnotationdefault void instanceMethod() {class LocalType {Target.TargetMemberInner innerField = targetField.new TargetMemberInner();}new LocalType();
super.toString();
Object var = targetField;Target.TargetMemberInner innerVar = targetField.new TargetMemberInner();}}}}
class Target {class TargetMemberInner {}}