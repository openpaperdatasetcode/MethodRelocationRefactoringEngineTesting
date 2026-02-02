package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
// Diff package for TypeDeclarationStatementpackage diff;
import test.TargetRecord;
public class DiffPackageTarget {// TypeDeclarationStatement (public modifier, this.field = 3, pos: diff_package_target)public static <T extends TargetRecord<T>> void processTarget(T target) {target.setValue(3);}}
package test;
import diff.DiffPackageTarget;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface OverrideAnnotation {int value() default 0;}
// Target record class (private modifier + type parameter + member inner class)private record TargetRecord<T>(int value) {// Target feature: type parameterpublic int process(U num) {
return value + num.intValue();
}
// Target feature: member inner classpublic class TargetInner {public int getTargetValue() {return TargetRecord.this.value;}}
public void setValue(int newValue) {this.value = newValue; // this.field for target_feature}}
// Source record class (public modifier + anonymous inner class + member inner class)public record SourceRecord(String name) {// Source feature: member inner classpublic class SourceInner {protected int outerProtected = 5; // access_outer_protected
// Overriding method (private modifier, pos: the attribute values of annotations)@OverrideAnnotation(value = 3)private int overrideMethod(TargetRecord<?> target, int count) {if (count <= 0) return target.value();return target.process(count) + overrideMethod(target, count - 1);}}
// Source feature: anonymous inner classprivate final Runnable anonymous = new Runnable() {@Overridepublic void run() {new SourceInner().overrideMethod(new TargetRecord<>(0), 3);}};
// Instance method (strictfp access + List<String> return)public strictfp List<String> methodToMove(TargetRecord<Integer> target) {List<String> result = new ArrayList<>();
// Constructor invocationSourceInner inner = new SourceInner();TargetRecord.TargetInner targetInner = target.new TargetInner();
// Variable callint var = target.value();result.add("initial: " + var);
// With_boundsclass BoundedType<U extends TargetRecord<Integer>> {}BoundedType<TargetRecord<Integer>> bounded = new BoundedType<>();
// While statementint count = 0;while (count < 3) {result.add("loop-" + count);count++;}
// TypeDeclarationStatement (pos: diff_package_target)DiffPackageTarget.processTarget(target);result.add("after diff: " + target.value());
// Access_outer_protectedresult.add("protected: " + inner.outerProtected);
// Overriding method call (instanceReference.methodName(arguments))int overrideResult = inner.overrideMethod(target, 3);result.add("override: " + overrideResult);
// Return statementreturn result;}}