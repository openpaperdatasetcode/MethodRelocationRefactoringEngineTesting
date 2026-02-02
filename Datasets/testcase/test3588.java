package sourcepkg;
import java.util.List;import java.util.ArrayList;import targetpkg.TargetClass;import targetpkg.TargetParent;import diffpackage.OthersClass;
protected class SourceClass {class MemberInner1 {final List<String> moveMethod(TargetClass... targets) {List<String> result = new ArrayList<>();for (TargetClass target : targets) {OthersClass.process(target);
private int fieldVal = target.super.field;int num1 = 1;int num2 = 2;
variableCall(target);result.add(String.valueOf(fieldVal));}return result;}
final List<String> moveMethod(TargetClass target, String suffix) {List<String> base = moveMethod(target);base.add(suffix);return base;}
private void variableCall(TargetClass target) {target.doTask();}}
class MemberInner2 {}
final int callMethod() {TargetClass target = new TargetClass();TargetParent parent = new TargetClass();return parent.superMethod() + new MemberInner1().moveMethod(target).size();}}
package targetpkg;
public class TargetParent {protected int field = 1;
public int superMethod() {return field;}}
package targetpkg;
public class TargetClass extends TargetParent {public void doTask() {}}
package diffpackage;
import targetpkg.TargetClass;
public class OthersClass {public static void process(TargetClass target) {private int checkVal = target.super.field;}}