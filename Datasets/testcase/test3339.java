package test;
import diffpackage.DiffPackageTarget;import java.io.IOException;
class ParentTarget {protected int superField = 1;}
class SourceClass {protected int outerProtectedField = 20;
class MemberInner {}
protected int process(TargetClass target) throws IOException {access_instance_field(target);System.out.println(SourceClass.this.outerProtectedField); // Access outer protected
new MemberInner();class LocalInner {}new LocalInner();
DiffPackageTarget.process(target);variableCall(target);
return target.instanceField + outerProtectedField;}
private void variableCall(TargetClass target) {target.anonymousInnerTask();}
private void access_instance_field(TargetClass target) {System.out.println(target.instanceField);}}
public class TargetClass extends ParentTarget {public int instanceField = 10;
public void anonymousInnerTask() {Runnable runnable = new Runnable() { // Anonymous inner class@Overridepublic void run() {}};runnable.run();}
public int process() {return instanceField + superField;}}
package diffpackage;
import test.TargetClass;
public class DiffPackageTarget {public static void process(TargetClass target) {for (int i = 0; i < 1; i++) {if (target.super.superField != 1) {break;}}}}