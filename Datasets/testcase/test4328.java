package same.pkg;
import com.other.DiffPackageHandler;import java.util.function.Function;
// Source record: default modifier, with 2 local inner classesrecord SourceRecord(String sourceField) {// Inner class (first level of inner)class SourceInner {// Inner-rec class (source_inner_rec position)class SourceInnerRec {// Target field contained in source (per_condition)private TargetRecord.TargetInnerRec targetInnerRecField;
// Instance method: final modifier, returns TargetClass Typefinal TargetRecord.TargetInnerRec method() {variableCall();
// 1. TryStatement (public modifier) in diff_package_others, uses this.fieldpublic boolean trySuccess = false;DiffPackageHandler handler = new DiffPackageHandler();try {trySuccess = handler.process(this.targetInnerRecField);} catch (Exception e) {// no_new_exception: no additional checked exceptions thrown}
// 2. Abstract method references (3 occurrences) in array initialization// Target's abstract method reference (ClassName::methodName)Function<TargetRecord.TargetInnerRec, Object>[] abstractRefs = new Function[] {TargetRecord.TargetInnerRec::abstractMethod1,TargetRecord.TargetInnerRec::abstractMethod2,TargetRecord.TargetInnerRec::abstractMethod3};
// 3. Local inner class 1class LocalInner1 {TargetRecord.TargetInnerRec getInnerRec() {return targetInnerRecField;}}
// 4. Local inner class 2class LocalInner2 {void updateInnerRec(TargetRecord.TargetInnerRec rec) {targetInnerRecField = rec;}}
// 5. Call source's final normal method in while loop (call_method)LocalInner1 local1 = new LocalInner1();LocalInner2 local2 = new LocalInner2();int count = 0;while (count < 2) {TargetRecord.TargetInnerRec updatedRec = SourceRecord.this.callSourceFinalMethod(local1.getInnerRec());local2.updateInnerRec(updatedRec);count++;}
return targetInnerRecField;}
private void variableCall() {String localVar = targetInnerRecField.innerRecField();}}}
// Source's final normal method (call_method: superTypeReference uses Object::toString)final TargetRecord.TargetInnerRec callSourceFinalMethod(TargetRecord.TargetInnerRec rec) {// superTypeReference.methodName(arguments) (Object is super type of TargetInnerRec)String superTypeVal = Object::toString.apply(rec);return new TargetRecord.TargetInnerRec(rec.innerRecField() + "_updated");}}
// Target record: non-sealed modifier, with static nested class (TargetInnerRec)non-sealed record TargetRecord(String targetField) {// Target's static nested class (target_inner_rec)public static abstract class TargetInnerRec {private final String innerRecField;
public TargetInnerRec(String innerRecField) {this.innerRecField = innerRecField;}
public String innerRecField() {return innerRecField;}
// 3 abstract methods for method referencespublic abstract Object abstractMethod1();public abstract Object abstractMethod2();public abstract Object abstractMethod3();}}
// Diff-package class for TryStatement positionpackage com.other;
import same.pkg.TargetRecord;
public class DiffPackageHandler {public boolean process(TargetRecord.TargetInnerRec innerRec) {return innerRec != null && innerRec.innerRecField() != null;}}