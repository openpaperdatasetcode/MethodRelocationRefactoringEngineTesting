package test;
import java.lang.reflect.Method;
class ParentTarget {}
protected class SourceClass {private AbstractTargetClass targetField = new AbstractTargetClass() {};
public void outerMethod() {// Anonymous inner classnew Runnable() {@Overridepublic void run() {localInnerMethod(targetField);}}.run();}
private void localInnerMethod(AbstractTargetClass target) {// Local inner classclass LocalInner {int innerMethod(AbstractTargetClass obj) {labeledBlock: {if (obj.obj.field != 1) {break labeledBlock;}variableCall(obj);this.helperMethod();}
// Reflection usagetry {Method method = AbstractTargetClass.TargetObj.class.getMethod("getField");method.invoke(obj.obj);} catch (Exception e) {}
return obj.obj.field;}
private void helperMethod() {}}new LocalInner().innerMethod(target);}
private void variableCall(AbstractTargetClass target) {target.innerClass.doTask();}
// Call method (sub_class, constructor parameter list)public void callMethod() {SubTarget subTarget = new SubTarget(new SourceClass().new LocalInnerHelper().callInnerMethod(targetField));}
class LocalInnerHelper {AbstractTargetClass.TargetInnerRec callInnerMethod(AbstractTargetClass target) {return target.new TargetInnerRec();}}}
abstract class AbstractTargetClass extends ParentTarget {public TargetObj obj = new TargetObj(1);
class TargetInnerRec {void doTask() {}}
static class TargetObj {int field;TargetObj(int field) {this.field = field;}public int getField() {return field;}}
public void targetMethod() {// Local inner class in targetclass TargetLocalInner {}new TargetLocalInner();}
int innerMethod() {class InnerRec {private int returnMethod(AbstractTargetClass obj) {if (obj.obj.field == 1) {return obj.obj.field;}return -1;}}return new InnerRec().returnMethod(this);}}
class SubTarget extends AbstractTargetClass {public SubTarget(AbstractTargetClass.TargetInnerRec innerRec) {innerRec.doTask();}}