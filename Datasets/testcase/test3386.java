package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
// Source enum (default modifier) with anonymous and member inner classesenum SourceEnum {INSTANCE;
class MemberInner {class InnerRec {}}
// Anonymous inner classprivate final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println(SourceEnum.this.name());}};
@MethodAnnotation // has_annotationpublic final int process(TargetEnum target) {// Constructor invocationTargetEnum.InnerClass inner = target.new InnerClass();int result = 0;
labeledBlock: {// ParenthesizedExpression (numbers=3, public modifier)public int val = ((3 + (target.value + 2)) * 1);result += val;
// Uses outer thisresult += SourceEnum.this.ordinal();
// Access instance fieldresult += target.value;
variableCall(target);if (result > 10) break labeledBlock;}
anonymousTask.run();new MemberInner();return result;}
private void variableCall(TargetEnum target) {target.doTask();}}
// Abstract target enum with local inner classabstract enum TargetEnum {VALUE1(1), VALUE2(2);
public int value;
TargetEnum(int value) {this.value = value;}
class InnerClass {}
public void doTask() {// Local inner class (target_feature)class LocalInner {void calculate() {value *= 2;}}new LocalInner().calculate();}
// Call_method: others_class, private modifierprivate List<String> callMethod() {switch (value) {case 1:return OthersClass.getChain().m1().m2().m3();default:return new ArrayList<>();}}}
class OthersClass {// Static method for call_method target_featurepublic static Chain getChain() {return new Chain();}
public static class Chain {public Chain m1() { return this; }public Chain m2() { return this; }public List<String> m3() { return new ArrayList<>(); }}}