package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
abstract class ParentEnum {public abstract TargetEnum process(TargetEnum target);}
public enum SourceEnum {INSTANCE;
class InnerClass {@MethodAnnotation@Overridepublic int process(TargetEnum target) {super(); // super constructor invocationint result = 0;List rawList; // raw_type
// MethodInvocation (numbers=3, public modifier)public int sum = target.add(1) + target.add(2) + target.add(3);
if (sum > 5) { // if statement// Overriding method_feature in if/else conditionsTargetEnum processed = target.parentProcess(target);result = processed.value;} else {result = target.value;}
// for statementfor (int i = 0; i < 3; i++) {super.toString(); // super keywordsvariableCall(target);result += i;}
// expression statementtarget.value = result;return result;}
private void variableCall(TargetEnum target) {target.innerClass.helper();}}}
protected enum TargetEnum extends ParentEnum {VALUE1(1), VALUE2(2), VALUE3(3);
public int value;
TargetEnum(int value) {this.value = value;}
class MemberInner {public void helper() {}}
public MemberInner innerClass = new MemberInner();
public int add(int num) {return value + num;}
@Overrideprotected TargetEnum parentProcess(TargetEnum target) { // overriding method_featuretarget.value *= 2;return target;}}