package test;
import java.util.List;
/**
Target Enum with Javadoc
This enum demonstrates the target_feature: javadoc*/strictfp enum TargetEnum {VALUE1(10), VALUE2(20);
public int targetField; // Field for per_condition
TargetEnum(int field) {this.targetField = field;}
public void targetMethod() {}
// Target inner class (target_inner)public class TargetInner {public void innerMethod() {}}}
// Private source enum (local inner class + member inner class)private enum SourceEnum {INSTANCE;
private TargetEnum targetField = TargetEnum.VALUE1; // Per condition: source contains target field
// Member inner class (source feature)public class SourceMemberInner {// Source inner recursive class (method_position: source_inner_rec)public class SourceInnerRec {// Normal method (default access modifier, returns base type)int normalMethod() {// Raw typeList rawList;
// Variable callTargetEnum.TargetInner inner = targetField.new TargetInner();inner.innerMethod();targetField.targetMethod();
// ForStatement (private, target_feature: obj.field x1, pos: same_package_target)private int sum = 0;for (int i = 0; i < targetField.targetField; i++) {sum += i;}
return sum;}}}
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public void localMethod() {new SourceMemberInner().new SourceInnerRec().normalMethod();}}new SourceLocalInner().localMethod();}}