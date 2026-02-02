package source.pkg;
import java.util.ArrayList;import java.util.List;import target.pkg.TargetClass;
public final class SourceClass<T> {private TargetClass targetInstance = new TargetClass();
// Member inner classpublic class MemberInnerClass {public void innerMethod(String data) {}}
// Overload 1: method to be movedprotected List<String> processData(String input) {List<String> result = new ArrayList<>();MemberInnerClass memberInner = new MemberInnerClass();
try {// Anonymous inner classRunnable runnable = new Runnable() {@Overridepublic void run() {// VariableDeclarationStatement (pos: inner class) with target_feature (obj.field, 1)String targetField = targetInstance.memberInner.targetField; // obj.fieldint count = 1; // target_feature "1"memberInner.innerMethod(targetField + count);}};runnable.run();
// Enhanced for statementList<String> dataList = List.of(input, "second", "third");for (String data : dataList) {// Call_method (source type, instance, new ClassName().method()) in for loopint callResult = new SourceClass.MemberInnerClass().callableMethod(data);result.add(data + ":" + callResult);
// Variable call: target's member inner class methodtargetInstance.memberInner.process(data);}} catch (IllegalArgumentException e) {// requires_try_catchresult.add("Error: " + e.getMessage());}
return result;}
// Overload 2: overload_exist featureprotected List<String> processData(String input, int limit) {List<String> result = processData(input);return result.subList(0, Math.min(limit, result.size()));}
// Call_method implementation (source type, protected modifier)protected class MemberInnerClass {public int callableMethod(String arg) {return arg.length();}}}
package target.pkg;
import java.util.List;
private class TargetClass {// Target's member inner class (target_feature)public MemberInnerClass memberInner = new MemberInnerClass();
public class MemberInnerClass {public String targetField = "tf"; // obj.field for source accesspublic void process(String data) {}}
// Method will be moved here:// protected List<String> processData(String input) { ... }//// protected List<String> processData(String input, int limit) { ... }}
