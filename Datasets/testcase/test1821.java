package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass<T> {public static class StaticNestedOne {}public static class StaticNestedTwo {}
/**
Overloaded method to process target inner records
@param innerRec target inner record instance
@return list of processed strings
*/
List<String> overloadedMethod(TargetClass.NestedRec innerRec) {
return processInnerRec(innerRec);
}
/**
Overloaded method to process multiple target inner records
@param innerRecs array of target inner records
@return list of processed strings
*/
List<String> overloadedMethod(TargetClass.NestedRec... innerRecs) {
List<String> result = new ArrayList<>();
for (TargetClass.NestedRec rec : innerRecs) {
result.addAll(processInnerRec(rec));
}
return result;
}
private List<String> processInnerRec(TargetClass.NestedRec innerRec) {List<String> result = new ArrayList<>();
// Type declaration statementTargetClass.NestedStatic nestedStatic = new TargetClass.NestedStatic();
// Expression statementString data = innerRec.value();result.add(data);
// Do statementint i = 0;do {result.add("Iteration: " + i + " - " + data);i++;} while (i < 2);
// Switch statement with instance method callswitch (innerRec.id()) {case 1:Object val1 = this.handleCase(innerRec);result.add(val1.toString());break;default:Object val2 = this.handleCase(innerRec);result.add(val2.toString());}
// Variable call and depends on inner classint sum = innerRec.id() + nestedStatic.calculate(innerRec);result.add("Sum: " + sum);
// Assert statementassert sum > 0 : "Sum must be positive";
return result;}
// Instance method for switch caseprivate Object handleCase(TargetClass.NestedRec rec) {return "Handled: " + rec.value();}
static {// Static code block with call method from others classint code = OtherClass.processCode(100);System.out.println("Static block code: " + code);}}
abstract class TargetClass<T> {public static class NestedStatic {int calculate(NestedRec rec) {return rec.id() * 2;}}
public record NestedRec(int id, String value) {}}
class OtherClass {private static int processCode(int input) {return input / 2;}}
