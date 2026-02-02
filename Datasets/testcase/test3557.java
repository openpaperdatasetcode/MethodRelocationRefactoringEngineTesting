package sourcepkg;
import targetpkg.TargetClass;
protected class SourceClass {private int outerPrivateField = 10;private TargetClass targetField = new TargetClass(2);
class MemberInner1 {}class MemberInner2 {}
private int moveMethod(TargetClass... targets) {for (TargetClass target : targets) {new TargetClass(2); // ConstructorInvocation with this.field=2assert target.this.field == 2 : "Target field validation";
variableCall(target);System.out.println(SourceClass.this.outerPrivateField);}return outerPrivateField;}
private void variableCall(TargetClass target) {target.staticNested.doTask();}
private void callMethod() {TargetClass target = new TargetClass(2) {@Overridepublic void doTask() {super.doTask();}};SourceClass.moveMethod(target);}
private static void moveMethod(TargetClass target) {new SourceClass().moveMethod(target);}}
package targetpkg;
private class TargetClass {int field;
public TargetClass(int field) {this.field = field;}
public static class StaticNested {void doTask() {}}
StaticNested staticNested = new StaticNested();
public void doTask() {}
private int moveMethod(TargetClass... targets) {return targets.length;}}
