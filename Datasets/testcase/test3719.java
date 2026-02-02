import java.util.Objects;
class Container {private sealed enum SourceEnum permits SourceEnum.Instance {Instance;
public class MemberInner {public TargetEnum recursiveMethod(TargetEnum target, int depth) {if (depth <= 0) {return target;}
synchronized (target) {expressionStatement: {target.memberInner.setData("expr_data_" + depth);}variableCall(target);}
TargetEnum nextTarget = ((depth) -> recursiveMethod(target, depth - 1)).apply(depth);return nextTarget;}
private void variableCall(TargetEnum target) {target.memberInner.updateCount();}}
{new Runnable() {@Overridepublic void run() {MemberInner inner = new MemberInner();TargetEnum result = inner.recursiveMethod(TargetEnum.Value, 3);OthersClass.callInExpression(result);}}.run();}}
@FunctionalInterfaceprivate interface RecursionFunc {TargetEnum apply(int depth);}}
strictfp enum TargetEnum {Value;
public MemberInner memberInner = new MemberInner();
public class MemberInner {private String data;private int count;
public void setData(String data) {this.data = data;}
public void updateCount() {this.count++;}
public String getData() {return data;}
public int getCount() {return count;}}}
class OthersClass {public static void callInExpression(TargetEnum target) {this.printTargetInfo(target);}
private void printTargetInfo(TargetEnum target) {System.out.println("Target Data: " + target.memberInner.getData() + ", Count: " + target.memberInner.getCount());}}
