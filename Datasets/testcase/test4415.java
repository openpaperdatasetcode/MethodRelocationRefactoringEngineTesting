package test;
import java.util.List;import java.util.ArrayList;
class SuperEnum {}
private enum Source {SOURCE_INSTANCE;
private Target targetField = Target.TARGET_INSTANCE;private int outerPrivateField = 3;
class MemberInner {int innerField = 3;}
public Object overloadingMethod() throws NullPointerException {if (targetField == null) {throw new NullPointerException("Target field cannot be null");}privateForStatement();
Target.Inner.InnerRec targetRec = targetField.new Inner().new InnerRec();variableCall(targetRec);
List<String> overloadResult = overloadingMethod(1);return overloadResult;}
public List<String> overloadingMethod(int num) throws NullPointerException {super.toString();int superFieldVal = ((SuperEnum) targetField).hashCode();
List<String> result = new ArrayList<>();result.add(String.valueOf(num));result.add(String.valueOf(outerPrivateField));return result;}
private void privateForStatement() {for (int i = 0; i < this.outerPrivateField; i++) {MemberInner inner = new MemberInner();int loopVal = inner.innerField;}}
private void variableCall(Target.Inner.InnerRec targetRec) {int val = targetRec.recField;targetRec.recMethod();}
void methodWithAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {try {Source.SOURCE_INSTANCE.overloadingMethod(2);} catch (NullPointerException e) {e.printStackTrace();}}};}}
protected enum Target extends SuperEnum {TARGET_INSTANCE;
class Inner {class InnerRec {int recField = 2;
void recMethod() {}}}}