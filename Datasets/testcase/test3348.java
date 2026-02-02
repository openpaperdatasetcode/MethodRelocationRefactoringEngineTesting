package test;
import java.util.ArrayList;import java.util.List;
interface TargetInterface {}
public class SourceClass {static class StaticNested {}
class MemberInner {class InnerRec {private int process(TargetClass... targets) {// Type declaration statementclass LocalType {}new LocalType();
int total = 0;// Enhanced for statementfor (TargetClass target : targets) {OthersClass.validate(target); // SuperConstructorInvocation positionvariableCall(target);total += target.instanceField;}
new StaticNested();return total;}
private void variableCall(TargetClass target) {target.localInnerTask();}}}
protected List<String> callMethod(TargetClass target) {List<String> result = new ArrayList<>();try {if (target == null) {throw new IllegalArgumentException("Target cannot be null"); // Exception throwing position}// Overloading + outerInstance.new InnerClass().methodName()result.add(String.valueOf(new MemberInner().new InnerRec().process(target)));result.add(new MemberInner().new InnerRec().process(target, 2));} catch (Exception e) {result.add(e.getMessage());}return result;}}
public class TargetClass implements TargetInterface {public int instanceField = 3; // obj.field=3
public void localInnerTask() {class LocalInner {}new LocalInner();}
private int process(TargetClass... targets) {int sum = 0;for (TargetClass t : targets) sum += t.instanceField;return sum;}
private int process(TargetClass target, int multiplier) {return target.instanceField * multiplier;}}
class OthersClass {public static void validate(TargetClass target) {// Static SuperConstructorInvocation (target_feature: obj.field=3)new ParentOthersClass(target.instanceField);}}
class ParentOthersClass {public ParentOthersClass(int value) {}}