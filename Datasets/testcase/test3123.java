package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodFeatureAnn {}
// Target class (public modifier + static nested class + inner record)public class TargetClass {int thisField; // target_feature: this.field
public static class TargetStaticNested { // target_feature: static nested classclass TargetInner {record TargetInnerRec() {} // target_inner_rec
// LabeledStatement (private modifier, this.field = 1, pos: same_package_target)private void setField(TargetClass target) {fieldLabel: {target.thisField = 1;break fieldLabel;}}}}}
// Source interface for implements featureinterface SourceInterface {}
// Source class (default modifier + implements + two member inner classes)class SourceClass implements SourceInterface {// Source feature: first member inner classclass SourceInner1 {}// Source feature: second member inner classclass SourceInner2 {@MethodFeatureAnn // has_annotationprotected TargetClass methodToMove(TargetClass.TargetStaticNested.TargetInner.TargetInnerRec rec) {// Super constructor invocationsuper();
// Variable callTargetClass target = new TargetClass();TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();TargetClass.TargetStaticNested.TargetInner targetInner = staticNested.new TargetInner();
// Depends_on_inner_classtargetInner.setField(target);
// Do statementint count = 0;do {target.thisField += count;count++;} while (count < 2);
// Synchronized statementsynchronized (target) {target.thisField *= 2;}
// No new exception thrownreturn target;}}}