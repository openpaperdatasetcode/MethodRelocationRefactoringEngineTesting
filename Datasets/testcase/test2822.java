package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
protected class SourceClass extends ParentSource {public class MemberInner {public class SourceInnerRec {@Overrideprivate TargetClass methodToMove(TargetClass targetParam, String... varargs) {// Constructor invocationOthersClass others = new OthersClass();TargetClass.MemberInnerTarget inner = targetParam.new MemberInnerTarget();
// Ternary operator with varargs featureObject varargsResult = (varargs.length >= 1) ?others.processVarargs(1, varargs) : this.defaultVarargs();
// Variable callinner.field = (String) varargsResult;variableCall(targetParam, inner);
// Anonymous inner classnew Runnable() {@Overridepublic void run() {// Call_method in Lambda expressionsSupplier<List<String>> lambda = () -> targetParam.callOverloaded(inner.field);lambda.get().forEach(System.out::println);}}.run();
return targetParam;}
protected Object defaultVarargs() {return "default";}
private void variableCall(TargetClass target, TargetClass.MemberInnerTarget inner) {target.updateInner(inner);}}}}
abstract class ParentSource {protected abstract TargetClass methodToMove(TargetClass target, String... varargs);}
private class TargetClass {public class MemberInnerTarget {String field;}
// Call_method: target type, final modifier, overloadingpublic final List<String> callOverloaded(String val) {List<String> list = new ArrayList<>();list.add(val);return list;}
public final List<String> callOverloaded(MemberInnerTarget inner) {List<String> list = new ArrayList<>();list.add(inner.field);return list;}
public void updateInner(MemberInnerTarget inner) {this.new MemberInnerTarget().field = inner.field;}}
class OthersClass {// Varargs method for method_featurepublic Object processVarargs(int num, String... varargs) {return varargs[0] + num;}}