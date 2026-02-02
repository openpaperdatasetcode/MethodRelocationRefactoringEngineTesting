package test;
import other.OthersClass;
class SourceClass {static class StaticNested {}
/**
Method Javadoc
@return TargetClass instance*/strictfp TargetClass methodToMove(TargetClass.TargetInnerRec... targets) {class LocalInner {}
super.toString(); // super keywords
label: {// LabeledStatement with target_featureOthersClass.field = 1;break label;}
// Expression statementint val = 5;// Variable callTargetClass target = new TargetClass();target.memberInner.count = val;
return target;}
int callMethod() {int i = 0;while (i < 3) {// Accessor and lambda in whileTargetClass target = new TargetClass();target.getMemberInner().setCount(10);Runnable lambda = () -> methodToMove(target.new TargetInnerRec());lambda.run();i++;}return i;}}
class TargetClass {MemberInner memberInner = new MemberInner();
class MemberInner {int count;
int getCount() { return count; }void setCount(int c) { count = c; }}
record TargetInnerRec() {}}
package other;
public class OthersClass {public static int field;}