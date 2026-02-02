package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
// Super type for call method's superTypeReferenceclass SuperType {public List<String> superMethod(String arg) {return List.of(arg);}}
protected class SourceClass extends SuperType {private int outerPrivateField = 10; // For access_outer_private feature
// Overload exists (feature: overload_exist)public strictfp int instanceMethod(TargetClass targetParam) {return instanceMethod(targetParam, 0);}
/**
Instance method to be refactored (type: instance)
Return type: base type (int)
Access modifier: strictfp
Per_condition: contains TargetClass parameter*/public strictfp int instanceMethod(TargetClass targetParam, int num) {// EmptyStatement with private modifier + target_feature: obj.field, 2 (pos: diff_package_others)private; targetParam.targetField += 2;
// Variable call (feature: variable call)String var = "test";var.length();
// Access outer private field (feature: access_outer_private)int sum = outerPrivateField + targetParam.targetField;
// Requires try-catch (feature: requires_try_catch)int result = 0;try {result = Integer.parseInt(targetParam.toString()) + sum;} catch (NumberFormatException e) {result = sum;}
return result;}
// Feature: local inner classpublic void outerMethod() {class LocalInnerClass {public void innerInvoke(TargetClass target) {SourceClass.this.instanceMethod(target);}}
// Feature: anonymous inner classRunnable anonymous = new Runnable() {@Overridepublic void run() {LocalInnerClass inner = new LocalInnerClass();inner.innerInvoke(new TargetClass());}};anonymous.run();
// Call method: type=source, modifier=public, pos=do-whileTargetClass target = new TargetClass();List<String> callResults = new ArrayList<>();do {// Target_feature: superTypeReference.methodName(arguments)List<String> res = SuperType.this.superMethod("callArg");callResults.addAll(res);// Call refactored method (source type call)instanceMethod(target);} while (callResults.size() < 2);}}
final class TargetClass {int targetField = 5; // For obj.field feature
// Target_feature: anonymous inner classpublic Runnable createAnonymousRunnable() {return new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};}
@Overridepublic String toString() {return String.valueOf(targetField);}
// Refactored method moved here (target class: target)public strictfp int instanceMethod(int num) {// EmptyStatement with private modifier + target_feature: obj.field, 2 (pos: diff_package_others)private; this.targetField += 2;
// Variable call (feature: variable call)String var = "test";var.length();
// Access outer private field (adapted for target class context)int sum = 10 + this.targetField; // Simulate original outer private field value
// Requires try-catch (feature: requires_try_catch)int result = 0;try {result = Integer.parseInt(this.toString()) + sum;} catch (NumberFormatException e) {result = sum;}
return result;}
// Overload exists (consistent with source class)public strictfp int instanceMethod() {return instanceMethod(0);}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoringTest5043 {@Testpublic void testMoveInstanceMethodFromSourceToTarget() {// Test original source method invocationSourceClass source = new SourceClass();TargetClass targetParam = new TargetClass();int sourceResult = source.instanceMethod(targetParam, 3);assertTrue(sourceResult > 0);
// Test refactored method in target classTargetClass target = new TargetClass();int targetResult = target.instanceMethod(3);assertTrue(targetResult > 0);
// Test call method in do-whilesource.outerMethod();
// Verify target parameter usage (per_condition compliance)assertEquals(5 + 2, target.targetField); // Verify EmptyStatement effect}}