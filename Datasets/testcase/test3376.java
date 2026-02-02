package test;
import otherpackage.OthersClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
abstract class ParentSource {public abstract List<String> process(TargetClass target) throws Exception;}
public class SourceClass extends ParentSource {
// Instance code block (method_feature position){TargetClass target = new TargetClass();TargetClass.InnerClass inner = target.new InnerClass();inner.overrideMethod(target);}
@MethodAnnotation // has_annotation@Override // overriding methodpublic List<String> process(TargetClass target) throws Exception {List<String> result = new ArrayList<>();// Raw type usageList rawList = new ArrayList();
// Type declaration statementclass LocalType {}new LocalType();
// DoStatement (target_feature: super.field=2)OthersClass.processTarget(target);
// Override violation (Object.toString() return type mismatch)@Overridepublic List<String> toString() {return result;}
variableCall(target);rawList.add(target.field);result.addAll((List<String>) rawList);return result;}
private void variableCall(TargetClass target) {target.innerClass.helper();}
// Call_method: sub_class, private modifierprivate TargetClass callMethod(TargetClass target) {// Lambda in constructor parameter listSubTarget subTarget = new SubTarget(() -> target.field *= 2);// OverloadingsubTarget.overloadMethod(target);subTarget.overloadMethod(target, 3);return subTarget;}}
// Strictfp target class with member inner classstrictfp class TargetClass {public int field = 2; // super.field=2 (inherited from ParentTarget)
class MemberInner {public void helper() {}}
public MemberInner innerClass = new MemberInner();
// Inner class for overriding method_featurepublic class InnerClass {public TargetClass overrideMethod(TargetClass target) {return new TargetClass();}}
public void publicMethod() {}
@Overridepublic List<String> process(TargetClass target) {return new ArrayList<>();}}
// Parent class for target's super.fieldclass ParentTarget {protected int field = 2;}
// Sub_class for call_methodclass SubTarget extends TargetClass {public SubTarget(Runnable lambda) {lambda.run();}
// Overloading methodspublic void overloadMethod(TargetClass target) {}public void overloadMethod(TargetClass target, int multiplier) {}}
// Different package class for DoStatement positionpackage otherpackage;
import test.TargetClass;
public class OthersClass {public static void processTarget(TargetClass target) {// Private DoStatement with target_featureprivate int count = 0;do {if (target.field != 2) break;count++;} while (count < 1);}}