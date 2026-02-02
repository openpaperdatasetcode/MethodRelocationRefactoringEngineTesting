package test;
import diffpackage.DiffPackageTarget;
class ParentSource {protected int outerProtectedField = 5;}
protected class SourceClass extends ParentSource {class MemberInner {class InnerRec {public void moveMethod(TargetClass target) {super(); // Super constructor invocationthis.targetVar = target; // this.var = var
// Synchronized statementsynchronized (this) {variableCall(target);}
// Switch caseswitch (TargetClass.staticField) {case 1:DiffPackageTarget.process(target);break;default:break;}
// Raw type usageList rawList = new ArrayList();
// Access outer protected fieldSystem.out.println(SourceClass.this.outerProtectedField);}
private TargetClass targetVar;
private void variableCall(TargetClass target) {target.innerClass.doTask();}}}
public void outerMethod() {class LocalInner {}new LocalInner().toString();}}
private class TargetClass {public static int staticField = 1;
class TargetInner {void doTask() {}}
private TargetInner innerClass = new TargetInner();
{// Instance code block with synchronized method callnew TargetClass().synchronizedMethod();}
public synchronized void synchronizedMethod() {// Local inner class in targetclass TargetLocalInner {}new TargetLocalInner();}
public void moveMethod() {}}
package diffpackage;
import test.TargetClass;
public class DiffPackageTarget {public static void process(TargetClass target) {// SynchronizedStatement in diff packagesynchronized (TargetClass.class) {if (TargetClass.staticField == 1) {System.out.println("Processing target");}}}}