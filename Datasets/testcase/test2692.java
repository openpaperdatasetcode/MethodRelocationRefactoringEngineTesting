package test;
protected enum SourceEnum {INSTANCE;
// Member inner classes (source_feature)public class Inner1 {}public class Inner2 {}
public class SourceInner {public class SourceInnerRec {// Overloading method 1 (synchronized)public synchronized TargetEnum.TargetInner methodToMove(TargetEnum target) {return processTarget(target, null);}
// Overloading method 2 (synchronized)public synchronized TargetEnum.TargetInner methodToMove(TargetEnum target, String arg1) {return processTarget(target, arg1);}
// Overloading method 3 (synchronized)public synchronized TargetEnum.TargetInner methodToMove(TargetEnum target, String arg1, int arg2) {TargetEnum.TargetInner inner = processTarget(target, arg1);inner.setNum(arg2);return inner;}
private TargetEnum.TargetInner processTarget(TargetEnum target, String arg) {// Type declaration statement (raw_type)TargetEnum rawTarget = target;
// TryStatement with obj.field (count 1, pos: source)private String fieldVal;try {fieldVal = rawTarget.field;} catch (Exception e) {fieldVal = "default";}
// Variable call + contains target parameter (per_condition)rawTarget.toString();TargetEnum.TargetInner inner = rawTarget.new TargetInner();
// Object initialization with target overloading method callObject[] initArgs = new Object[]{methodToMove(rawTarget),methodToMove(rawTarget, arg),methodToMove(rawTarget, arg, 100)};
inner.setValue(fieldVal + arg);return inner;}}}}
private enum TargetEnum {INSTANCE("targetField");
public final String field; // Source contains target's field (per_condition)
TargetEnum(String field) {this.field = field;}
// Member inner class (target_feature)public class TargetInner {private String value;private int num;
public void setValue(String value) {this.value = value;}
public void setNum(int num) {this.num = num;}}}