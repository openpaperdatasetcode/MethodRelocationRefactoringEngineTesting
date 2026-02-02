package test;
import diffpackage.OthersClass;import java.util.List;
@MyAnnotationenum SourceEnum extends SuperEnum {INSTANCE;
private int outerPrivate;class MemberInner1 {}class MemberInner2 {}
/**
Overloading method to handle TargetEnum*/private int process(TargetEnum target) {OthersClass others = new OthersClass();others.process(target.super.field + 1);
TargetEnum.Inner[] inners = {target.new Inner()};int value = inners[0].compute();
outerPrivate = target.field;return value;}
/**
Overloaded method with String parameter
*/
private int process(String param) {
return param.length();
}
}
protected enum TargetEnum implements Runnable {VALUE;
int field;class Inner implements Computable {@Overridepublic int compute() {return 1;}}
@Overridepublic void run() {}}
class SuperEnum {int field;}
interface Computable {int compute();}
package diffpackage;
public class OthersClass {public void process(int value) {}}
@interface MyAnnotation {}