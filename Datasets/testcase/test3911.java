package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnnotation {}
// Diff-package class for TryStatement pos: "diff_package_others"package test.other;import test.SourceInterface;import test.TargetInterface;import java.util.List;
public class DiffPackageClass {public List<String> useSourceMethod(SourceInterface<String> source, TargetInterface target) {// TryStatement with target_feature (ClassName.field & 2)try {return source.strictfpMethod(target);} finally {if (SourceInterface.SourceStaticNested.STATIC_FIELD == 2) {}}}}
// Back to test packagepackage test;
import java.util.List;import java.util.ArrayList;
// Source interface: generic (type parameter), with static nested & member inner classesinterface SourceInterface<T> {// Static nested class (source feature)static class SourceStaticNested {public static final int STATIC_FIELD = 2;}
// Member inner class (source feature)class SourceInner {private T innerPrivateField;
public SourceInner(T field) {this.innerPrivateField = field;}
private T getInnerPrivateField() {return innerPrivateField;}}
// Private outer field for "access_outer_private"private static final String OUTER_PRIVATE_FIELD = "outer-private-data";
/**
Instance method: strictfp access, List<String> return
Contains target field (satisfies per_condition)*/@RefactorTestAnnotation // has_annotation featurestrictfp default List<String> strictfpMethod(TargetInterface target) {List<String> result = new ArrayList<>();// Uses outer this (uses_outer_this feature) - interface instance referenceSourceInterface<?> outerInstance = this;
// Constructor invocation (method feature)SourceInner inner = new SourceInner((T) OUTER_PRIVATE_FIELD);// OuterClass.this.x (interface inner class access outer)result.add((String) inner.getInnerPrivateField());
// access_outer_private featureresult.add(OUTER_PRIVATE_FIELD);
// if statement (method feature)if (SourceStaticNested.STATIC_FIELD == 2) {variableCall(); // Variable call feature}
// while statement (method feature)int count = 0;while (count < 2) {// Expression statement (method feature)result.add("Count: " + count);count++;}
// Use target class (target class: target)target.processResult(result);return result;}
// Variable call target methodprivate static void variableCall() {}}
// Target interface: with local inner class (target feature)interface TargetInterface {default void processResult(List<String> result) {// Local inner class (target feature)class TargetLocalInner {void addSuffix(List<String> list) {for (int i = 0; i < list.size(); i++) {list.set(i, list.get(i) + "-processed");}}}new TargetLocalInner().addSuffix(result);}}