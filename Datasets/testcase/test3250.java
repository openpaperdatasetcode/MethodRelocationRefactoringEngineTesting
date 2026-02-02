package test;
import java.util.function.Function;
public enum SourceEnum {INSTANCE;
private TargetEnum targetField = TargetEnum.VALUE;
class SourceMemberInner {protected TargetEnum.TargetInnerRec methodToMove(int param) {new Runnable() {@Overridepublic void run() {}};
TargetEnum.TargetInnerRec inner = new TargetEnum.TargetInnerRec();inner.call();
Function<String, Integer> func1 = Integer::parseInt;Runnable func2 = inner::call;
return SourceMemberInner.staticMethod(param, targetField);}
protected static void staticMethod(int a, TargetEnum b) {static {this.methodToMove(0);}}}
@Overridepublic String toString() {return super.toString();}}
public enum TargetEnum extends ParentClass {VALUE;
class TargetInnerRec {void call() {}}}
class ParentClass {public String toString() {return "";}}