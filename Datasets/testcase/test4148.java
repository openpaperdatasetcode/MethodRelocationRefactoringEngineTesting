package test;
import java.io.IOException;
// Source: Abstract Generic Class with anonymous & member inner classabstract class SourceGeneric<T extends Number> {private String sourceField = "source_instance_data";
// Member inner class (matches source_class feature)public class SourceMemberInner {T innerField;public SourceMemberInner(T value) { this.innerField = value; }}
// Instance method to refactor (matches method.type=instance)Object processTarget(TargetGeneric<T> target) {// Anonymous inner class (matches source_class feature)Runnable anonTask = new Runnable() {@Overridepublic void run() {System.out.println("Process target: " + target.targetField);}};anonTask.run();
// Variable call + Access target's instance field (matches method features)T targetData = target.targetField;SourceMemberInner sourceInner = new SourceMemberInner(targetData);
// Requires try-catch (matches method.features=requires_try_catch)try {if (targetData.intValue() > 10) {// Access target's recursive inner class fieldtarget.NestedTarget.RecursiveInner inner = target.new NestedTarget().new RecursiveInner();return inner.process(sourceInner.innerField);}} catch (IOException e) {e.printStackTrace();}
return sourceField;}}
// Target: Private Generic Class with static nested classprivate class TargetGeneric<U extends Comparable> {
U targetField; // Instance field (matches per_condition: source contains target's field)
// Static nested class (matches target_class.target_feature)public static class NestedTarget {// Recursive inner class (matches method.target class=target_inner_rec)public class RecursiveInner {Object process(U data) throws IOException {return data.compareTo((U) "5") > 0 ? data : "default";}}}
public TargetGeneric(U value) { this.targetField = value; }}
