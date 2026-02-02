package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
interface Processable {}
// Source record with implements and permitspublic sealed record SourceRecord(String data) implements Processable permits ExtendedSourceRecord {
@MethodAnnotation // has_annotation@Overridepublic final void process(TargetRecord target) {// VariableDeclarationStatement (target_feature: this.field=1)private int targetFieldValue = target.this.field;
if (target == null) {throw new NullPointerException("Target cannot be null");}
// Instance method feature in if/else conditionsObject result = target.instanceMethod(target);
do {synchronized (this) { // synchronized statementvariableCall(target);target.accessInstanceMethod(); // access_instance_method
// switch statementswitch (targetFieldValue) {case 1:System.out.println("Target field matches required value");break;default:throw new IllegalArgumentException("Invalid field value");}}} while (targetFieldValue == 1);}
private void variableCall(TargetRecord target) {target.staticNested.doTask();}}
// Permitted extension of SourceRecordfinal record ExtendedSourceRecord(String data) extends SourceRecord {public ExtendedSourceRecord(String data) {super(data);}}
// Final target record with static nested classfinal record TargetRecord(String data) {public int field = 1; // this.field=1
public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
// Instance method for method_featurepublic Object instanceMethod(TargetRecord target) {return target.data + target.field;}
public void accessInstanceMethod() {}
public final void process() {staticNested.doTask();}}