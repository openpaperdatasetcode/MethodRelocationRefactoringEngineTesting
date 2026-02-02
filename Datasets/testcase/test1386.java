package sourcepkg;
import java.util.ArrayList;import java.util.List;import targetpkg.TargetClass;
interface TestInterface {TargetClass methodToMove();}
class SourceClass implements TestInterface {// Source contains target's field (per_condition)private TargetClass targetField = new TargetClass();
// Anonymous inner class (source feature)Runnable anon = new Runnable() {@Overridepublic void run() {}};
// Local inner class (source feature)class LocalInner {void doSomething() {}}
@Overrideprivate TargetClass methodToMove() { // overriding// numbers:3, modifier:protected, exp:InstanceofExpressionObject obj = 3;boolean isNum = obj instanceof Integer;
// variable callLocalInner local = new LocalInner();local.doSomething();TargetClass target = targetField;target.getInner().innerMethod();
// overload_existtarget.overload(3);target.overload("3");
// raw_typeList rawList = new ArrayList();
// Access target's field (per_condition verification)Object targetData = target.targetField;
return target;}
// overload_exist (source side)private void overload(int num) {}private void overload(String str) {}}
package targetpkg;
strictfp class TargetClass {public String targetField = "target_data"; // Target field used by source
// target_feature: member inner classprotected class MemberInner {void innerMethod() {}}
public MemberInner getInner() {return new MemberInner();}
// overload_exist (target side)public void overload(int num) {}public void overload(String str) {}}