package samepkg;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodRefactor {String value() default "";}
class SourceClass {public static final String STATIC_FIELD = "static_dependency"; // depends_on_static_field
public class MemberInnerClass {public record SourceInnerRec() {@MethodRefactor(SubClass.processAnnotationArg("test")) // has_annotation + abstract method in annotation attributepublic List<String> process(TargetClass targetParam) {// Type declaration statementClass<?> targetInnerType = targetParam.memberInnerTarget.getClass();
List<String> result = new ArrayList<>();// If statementif (targetParam != null) {// Variable call: target instance, inner class, static fieldtargetParam.memberInnerTarget.execute();result.add(STATIC_FIELD);result.add(targetParam.targetField);}
return result;}}}
public void outerMethod () {// Local inner classclass LocalInnerClass {void m() {new MemberInnerClass.SourceInnerRec ().process (new TargetClass ());}}new LocalInnerClass (). m();}}
abstract class SuperClass {// Abstract method (sub_class + synchronized modifier)public abstract synchronized void processMethod(String arg);}
class SubClass extends SuperClass {// Static method for annotation attribute (ClassName.methodName(arguments))public static String processAnnotationArg(String arg) {return "annotated_" + arg;}
@Overridepublic synchronized void processMethod(String arg) {// Implementation for abstract method}}
class TargetClass {public String targetField = "target_value";
// Target member inner classpublic class MemberInnerTarget {public void execute() {}}
public MemberInnerTarget memberInnerTarget = new MemberInnerTarget();}