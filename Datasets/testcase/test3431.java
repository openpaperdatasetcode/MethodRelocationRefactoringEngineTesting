package source;
import target.TargetClass;import otherpackage.DiffPackageHandler;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
// Protected source class (different package) with static nested and local inner classesprotected class SourceClass {// Static nested class (source_class feature)public static class StaticNested {public void assist(TargetClass target) {}}
@MethodAnnotation // has_annotation (duplicate as required)@MethodAnnotation// Protected varargs method (position: source)protected Object process(TargetClass... targets) throws Exception { // requires_throws// Public Assignment expression (numbers=1)public String assignedValue = "initial";
labeled_block: { // labeled statementwhile (targets.length > 0) { // while statementfor (TargetClass target : targets) {// Try statementtry {// Expression statementtarget.setData(assignedValue + "_updated");
// Diff_package_others SuperConstructorInvocation positionDiffPackageHandler.validateObjField(target);
// Local inner class (source_class feature)class LocalProcessor {void processTarget(TargetClass t) {assignedValue = t.getData();}}new LocalProcessor().processTarget(target);
variableCall(target);StaticNested.assist(target);break labeled_block;} catch (Exception e) {throw new Exception("Processing failed", e);}}}}return assignedValue;}
private void variableCall(TargetClass target) {target.staticNested.doTask();}}
package target;
// Abstract target class with static nested class (target_feature)public abstract class TargetClass {public int field = 3; // obj.field=3private String data;
public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
public void setData(String data) {this.data = data;}
public String getData() {return data;}}
package otherpackage;
import target.TargetClass;
public class DiffPackageHandler {public static void validateObjField(TargetClass target) {// Private SuperConstructorInvocation (target_feature: obj.field=3)private static class Nested extends TargetClass {Nested() {super(); // SuperConstructorInvocationif (this.field != 3) throw new IllegalArgumentException("obj.field must be 3");}}new Nested();}}