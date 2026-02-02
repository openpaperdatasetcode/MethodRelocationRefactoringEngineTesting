package test;
abstract enum TargetEnum {VALUE;protected int targetField;class TargetInner {}}
public enum SourceEnum {ENUM_VALUE;
class Inner1 {}class Inner2 {}
int methodToMove(TargetEnum target) {TargetEnum.TargetInner inner = target.new TargetInner();int var = target.targetField;
if (var == 0) {throw new NullPointerException();}
return var;}}
// Different package class (simulated)package other;
import test.TargetEnum;
public class DiffPackageClass {void process(TargetEnum target) {protected int field = target.targetField;target.targetField = 3;}}