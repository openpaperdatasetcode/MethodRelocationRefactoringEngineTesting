package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import diffpackage.OthersClass;
@Retention(RetentionPolicy.RUNTIME)@interface MethodTestAnnot {}
class ParentTarget {protected int superMethod() {return 1;}}
public class SourceClass {private TargetClass targetField = new TargetClass();
@MethodTestAnnotprotected void moveMethod() {int numLiteral = 1;
try {OthersClass.process(targetField);if (targetField.obj.field == 1) {}} catch (Exception e) {}
for (int i = 0; i < numLiteral; i++) {int result = targetField.normalMethod();variableCall();}}
private void variableCall() {targetField.innerClass.doTask();}}
protected class TargetClass extends ParentTarget {public TargetObj obj = new TargetObj(1);
class TargetMemberInner {void doTask() {}}
private TargetMemberInner innerClass = new TargetMemberInner();
int normalMethod() {return super.superMethod();}
static class TargetObj {int field;TargetObj(int field) {this.field = field;}}
protected void moveMethod() {}}
package diffpackage;
import test.TargetClass;
public class OthersClass {public static void process(TargetClass target) {target.obj.field = 1;}}