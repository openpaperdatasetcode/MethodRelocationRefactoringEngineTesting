import java.util.Objects;
// Target interface for implementation (target_class: implements)interface DataValidator {boolean validate(String data);}
// TargetClass: normal, package-private, implements DataValidator, with anonymous inner class (meets target_class specs)class TargetClass implements DataValidator {private String innerData;
// Anonymous inner class (target_feature)private final Runnable dataInitializer = new Runnable() {@Overridepublic void run() {innerData = "default_target_data";}};
public TargetClass() {dataInitializer.run(); // Depend on anonymous inner class}
@Overridepublic boolean validate(String data) {return data != null && data.length() > 3;}
public void setInnerData(String data) {this.innerData = data;}
public String getInnerData() {return innerData;}
// Method for method chaining (call_method: obj.m1().m2().m3())public TargetClass appendSuffix(String suffix) {this.innerData += suffix;return this;}
public TargetClass trimData() {this.innerData = this.innerData.trim();return this;}
public void finalizeData() {this.innerData = this.innerData.toUpperCase();}}
// SourceClass: normal, public, same package as target, with 2 anonymous inner classes (meets source_class specs)public class SourceClass {/**
Instance method to process TargetClass instance
Handles data validation and modification with synchronization
@param target TargetClass instance to process (contains target parameter: per_condition)
@return int Length of processed data (base type return)*/// Method: instance, base type return, public access, source position (meets method specs)public int processTarget(TargetClass target) {// Constructor invocation (method_feature)TargetClass backupTarget = new TargetClass();
// Synchronized statement (method_feature)synchronized (target) {// Expression statement (method_feature)target.setInnerData("source_processed_data");
// Variable call (method_feature)boolean isValid = target.validate(target.getInnerData());if (!isValid) {// Call method with chaining in exception throwing (call_method specs: pos=exception throwing statements)backupTarget.appendSuffix("_backup").trimData().finalizeData();throw new IllegalArgumentException("Invalid data: use backup - " + backupTarget.getInnerData());}
// Depend on target's inner class (depends_on_inner_class: uses dataInitializer's effect)target.appendSuffix("_final").trimData().finalizeData();}
// 1st anonymous inner class (source_feature)Runnable logRunner1 = new Runnable() {@Overridepublic void run() {System.out.println("Process log 1: Data length - " + target.getInnerData().length());}};logRunner1.run();
// 2nd anonymous inner class (source_feature)Runnable logRunner2 = new Runnable() {@Overridepublic void run() {System.out.println("Process log 2: Data - " + target.getInnerData());}};logRunner2.run();
return target.getInnerData().length(); // No exception thrown (no_new_exception)}
// Final method for call_method (call_method: modifier=final, normal type)public final void triggerProcessing(TargetClass target) {try {int dataLength = processTarget(target);System.out.println("Final data length: " + dataLength);} catch (IllegalArgumentException e) {System.out.println("Error: " + e.getMessage());}}
// Test entrypublic static void main(String[] args) {SourceClass source = new SourceClass();TargetClass validTarget = new TargetClass();TargetClass invalidTarget = new TargetClass();
validTarget.setInnerData("valid");invalidTarget.setInnerData("inv");
source.triggerProcessing(validTarget); // Normal flow (no_new_exception)source.triggerProcessing(invalidTarget); // Exception flow (triggers call_method)}}