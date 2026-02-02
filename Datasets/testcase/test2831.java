package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnotation {}
protected class SourceClass {protected class SourceInner {@RefactorAnnotationprivate Object methodToMove(TargetClass target) {TargetClass.MemberInner inner = target.new MemberInner();int count = 0;
while (count < 5) {if (inner.field == 2) {; // Empty statementcontinue; // ContinueStatement with target_feature: this.field and 2}variableCall(inner);count++;}
// First anonymous inner classnew Runnable() {@Overridepublic void run() {super.toString(); // Super constructor invocationinner.field = 3;}}.run();
// Second anonymous inner classnew Object() {{System.out.println(inner.field);}};
return inner.field;}
private void variableCall(TargetClass.MemberInner inner) {inner.field += 1;}}}
abstract class TargetClass {public class MemberInner {protected int field;}}