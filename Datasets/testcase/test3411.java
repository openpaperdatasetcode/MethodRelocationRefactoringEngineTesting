package source;
import target.TargetRecord;import java.lang.reflect.Method;import java.util.List;
// Non-sealed source record (different package) with two static nested classesnon-sealed record SourceRecord(String data) {// Two static nested classes (source_class feature)public static class StaticNested1 {}public static class StaticNested2 {}
// Public accessor method (position: source)public TargetRecord getProcessedTarget(TargetRecord target) {// Type declaration statementclass LocalType {}new LocalType();
// Raw typeList rawList;
// Protected InfixExpression (numbers=2)protected int sum = target.value() + 5;protected boolean isEven = sum % 2 == 0;
// Switch statementswitch (target.value()) {case 1:expressionStatement(target, "case1");break;case 2:expressionStatement(target, "case2");break;default:expressionStatement(target, "default");}
// While loop (static method_feature position)int count = 0;while (count < 2) {StaticNested1.staticMethod(target);StaticNested2.staticMethod(target);count++;}
// Used by reflectiontry {Method method = TargetRecord.StaticNested.class.getDeclaredMethod("process", TargetRecord.class);method.invoke(null, target);} catch (Exception e) {}
variableCall(target);// Overload existgetProcessedTarget(target, 10);return target;}
// Overload methodpublic TargetRecord getProcessedTarget(TargetRecord target, int multiplier) {target = new TargetRecord(target.value() * multiplier);return target;}
private void expressionStatement(TargetRecord target, String suffix) {target = new TargetRecord(Integer.parseInt(target.value() + suffix.replaceAll("\D+", "")));}
private void variableCall(TargetRecord target) {TargetRecord.StaticNested.process(target);}
// Static nested classes with static methods (for method_feature)public static class StaticNested1 {public static void staticMethod(TargetRecord target) {target = new TargetRecord(target.value() + 1);}}
public static class StaticNested2 {public static void staticMethod(TargetRecord target) {target = new TargetRecord(target.value() + 2);}}}
package target;
// Protected target record with static nested classprotected record TargetRecord(int value) {// Static nested class (target_feature)public static class StaticNested {public static void process(TargetRecord target) {target = new TargetRecord(target.value() * 2);}}}