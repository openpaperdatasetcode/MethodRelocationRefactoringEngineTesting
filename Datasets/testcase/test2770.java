package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnot {}
private class SourceClass {private String outerField = "outerValue";
// Member inner classpublic class SourceInner {// Source inner record (source_inner_rec)public record SourceInnerRec<T extends Number & Comparable<T>>() {@CustomAnnotprivate TargetClass methodToMove(TargetClass target, T value) {// Uses outer this referenceSourceClass outerThis = SourceClass.this;String combined = outerThis.outerField + target.targetField;
// Constructor invocationTargetClass.MemberInner inner = target.new MemberInner(combined);
// this(arguments) in inner record constructornew SourceInnerRec<>();
// Variable call + contains target parameter (per_condition)target.toString();
// Depends on static fieldinner.setData(inner.getData() + TargetClass.STATIC_FIELD);
// Local inner class (source_feature)class LocalProcessor {public void process() {try {inner.validate(value);} catch (Exception e) {// No new exception}}}new LocalProcessor().process();
return target;}}}}
public class TargetClass {public String targetField = "targetValue"; // Source contains target's field (per_condition)public static final String STATIC_FIELD = "_static"; // Depends on static field
// Member inner class (target_feature)public class MemberInner {private String data;
public MemberInner(String initData) {this.data = initData;}
public String getData() {return data;}
public void setData(String data) {this.data = data;}
public <T extends Number & Comparable<T>> void validate(T value) {if (value.compareTo((T) Integer.valueOf(0)) < 0) {throw new IllegalArgumentException("Negative value");}}}
// Static code blocks with target constructor and this.methodName()static {TargetClass instance = new TargetClass();int result = instance.processInner(5);}
// Target instance method for call_methoddefault int processInner(int num) {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.SourceInnerRec<Integer> rec = inner.new SourceInnerRec<>();TargetClass processed = rec.methodToMove(this, num);return processed.targetField.length();}}