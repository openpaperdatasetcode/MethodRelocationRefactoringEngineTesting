package test;
import otherpkg.OtherPackageClass;
private record SourceRecord(String content) {// Two anonymous inner classes (source feature){Runnable anon1 = () -> System.out.println("Anonymous 1");Runnable anon2 = () -> System.out.println("Anonymous 2");}
// Source inner class (method_position: source_inner)public class SourceInner {// Overloading method 1 (no parameters)public TargetRecord overloadedMethod() {return new TargetRecord("default");}
// Overloading method 2 (with target parameter, per condition)public TargetRecord overloadedMethod(TargetRecord targetParam) {// Super keywords (record implicitly extends Record)super.toString();
// Access outer protected (record component has implicit access)String outerVal = SourceRecord.this.content;
// Super constructor invocation (target's parent class)TargetSubRecord sub = new TargetSubRecord(targetParam.value(), 100);
// DoStatement (private, target_feature: obj.field x1, pos: diff_package_others)OtherPackageClass other = new OtherPackageClass();private int count = 0;do {count += other.field + targetParam.value().length();} while (count < 5);
// Do statementint loop = 0;do {// Variable calltargetParam.process(outerVal);loop++;} while (loop < 2);
return targetParam;}}}
// Target parent class for super constructor invocationabstract class TargetParentRecord {protected int parentField;
public TargetParentRecord(int field) {this.parentField = field;}}
// Target record class (default modifier, no target_features)record TargetRecord(String value) extends TargetParentRecord {public TargetRecord(String value) {super(0); // Super constructor invocation}
public void process(String data) {}}
// Target sub record for super constructor invocationrecord TargetSubRecord(String value, int num) extends TargetParentRecord {public TargetSubRecord(String value, int num) {super(num); // Super constructor invocation}}
// Diff package class (separate package)package otherpkg;
public class OtherPackageClass {public int field = 3;}