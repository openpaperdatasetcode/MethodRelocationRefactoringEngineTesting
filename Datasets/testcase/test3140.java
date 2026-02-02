package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnn {}
protected class TargetClass {int objField; // target_feature: obj.fieldstatic class TargetStaticNested { // target_feature: static nested classrecord TargetInnerRec() {} // target_inner_rec}}
abstract class SourceClass {class MemberInner { // source_feature: member inner classclass InnerClass {// VariableDeclarationStatement (private modifier, obj.field = 1, pos: inner class)private void declareVariable(TargetClass target) {target.objField = 1;}}
@MethodAnn // has_annotationprivate abstract int methodToMove(TargetClass.TargetStaticNested.TargetInnerRec rec);}
// Concrete subclass with override_violation (weaker access than parent)class SourceConcrete extends MemberInner {// Override violation: parent is private, but violates logical override contract@Overridepublic int methodToMove(TargetClass.TargetStaticNested.TargetInnerRec rec) {// Super constructor invocationsuper();
// Variable callTargetClass target = new TargetClass();InnerClass inner = new InnerClass();inner.declareVariable(target);int var = target.objField;
// Source_feature: local inner classclass LocalInner {int getValue() {return var;}}
return new LocalInner().getValue();}}}