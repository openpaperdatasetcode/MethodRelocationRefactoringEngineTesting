package test;
import other.DiffPackageClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RecordAnn {}
abstract class ParentRecord {}
abstract record TargetRecord(String data) extends ParentRecord {class TargetInner {} // member inner class
transient String targetField;}
package other;
import test.TargetRecord;
public class DiffPackageClass {public void process(TargetRecord target) {transientLabel: {target.targetField = "3"; // this.field = 3break transientLabel;}}}
package test;
protected record SourceRecord(String value) {public void example1() {class LocalInner1 {} // local inner class}
public void example2() {class LocalInner2 {} // local inner class}
record SourceInnerRec() {} // source_inner_rec
@RecordAnn // has_annotationprivate Object methodToMove(TargetRecord target, SourceInnerRec rec) { // method types parameter is:none// Variable callString var = target.data();String fieldVal = target.targetField;
// Empty statement;
// Call different package classnew other.DiffPackageClass().process(target);
// Call_method in exception handling statementsString result = "";try {result = new OtherClass().recursiveMethod(3);} catch (IllegalArgumentException e) {result = new OtherClass().recursiveMethod(1);}
return fieldVal;}}
class OtherClass {public String recursiveMethod(int num) {if (num <= 0) return "base";return recursiveMethod(num - 1) + num; // recursion}}