package test;
import java.util.List;import java.util.ArrayList;import diffpackage.OthersClass;
class TargetParent {protected int superField1 = 3;protected int superField2 = 3;}
private enum SourceEnum {INSTANCE;
private int outerPrivateField = 10;
class MemberInner {}
public Object moveMethod(TargetEnum target) throws NullPointerException {new MemberInner() {};
OthersClass.process(target);
if (target.this.field != 3) {throw new NullPointerException();}
int sf1 = target.super.superField1;int sf2 = target.super.superField2;
variableCall(target);System.out.println(SourceEnum.this.outerPrivateField);
return new ArrayList<>();}
public List<String> moveMethod(TargetEnum target, String param) throws NullPointerException {super.toString();
if (target == null) {throw new NullPointerException();}
variableCall(target);return new ArrayList<>();}
private void variableCall(TargetEnum target) {target.innerClass.doTask();}}
protected enum TargetEnum extends TargetParent {INSTANCE;
public int field = 3;
class TargetInner {void doTask() {}}
private TargetInner innerClass = new TargetInner();
public Object moveMethod() {return this;}
public List<String> moveMethod(String param) {return new ArrayList<>();}}
package diffpackage;
import test.TargetEnum;
public class OthersClass {public static void process(TargetEnum target) {for (int i = 0; i < 1; i++) {if (target.this.field != 3) {break;}}}}