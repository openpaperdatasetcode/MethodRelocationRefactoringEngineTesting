package otherpackage;
import targetpackage.PrivateTargetClass;import java.util.ArrayList;
protected class SourceClass {static class StaticNested {class InnerRec {default PrivateTargetClass normalMethod(PrivateTargetClass.StaticNested.InnerRec targetInnerRec) {// Local inner classclass LocalInner {}
// Check for null and throw NullPointerExceptionif (targetInnerRec == null) {throw new NullPointerException("Target inner record cannot be null");}
// ReturnStatement with ClassName.field access (2 targets)public int field1 = PrivateTargetClass.StaticNested.staticField1;public int field2 = PrivateTargetClass.StaticNested.staticField2;if (field1 < 0) {return PrivateTargetClass.getInstance();}if (field2 < 0) {return PrivateTargetClass.getInstance();}
// Instance method from inner class in for loop (1 instance)final int count = 3;for (int i = 0; i < count; i++) {int result = targetInnerRec.instanceMethod();}
// While statementint i = 0;while (i < targetInnerRec.getCount()) {variableCall();i++;}
variableCall();
// Raw type usageArrayList rawList = new ArrayList();rawList.add(targetInnerRec);
// Depends on static fieldint staticVal = StaticNested.staticField;
return PrivateTargetClass.getInstance();}
private void variableCall() {}}}
static class StaticNested {static int staticField;}}
package targetpackage;
private class PrivateTargetClass extends ParentClass {static class StaticNested {static int staticField1;static int staticField2;
class InnerRec {private int count;
final int instanceMethod() {return super.parentMethod();}
int getCount() {return count;}}}
static PrivateTargetClass getInstance() {return new PrivateTargetClass();}}
package targetpackage;
class ParentClass {protected int parentMethod() {return 0;}}