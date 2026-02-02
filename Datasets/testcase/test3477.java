package test;
import java.util.List;
abstract class SourceClass {protected TargetClass targetField;
public SourceClass() {targetField = new TargetClass();
new Runnable() {public void run() {class InnerRec {protected void moveMethod() {if (targetField == null) {throw new NullPointerException();}List rawList = targetField.new MemberInner();targetField.someMethod();new TargetClass(1);super.run();}}}};
new Thread() {public void run() {System.out.println("Another anonymous class");}}.start();}}
protected class TargetClass extends ParentClass {class MemberInner {protected void moveMethod() {}}
public TargetClass() {}
public TargetClass(int i) {}
void someMethod() {}}
class ParentClass {public void run() {}}