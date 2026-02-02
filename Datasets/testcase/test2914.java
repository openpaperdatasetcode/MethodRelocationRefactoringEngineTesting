package sourcepkg;
import targetpkg.TargetClass;
class SourceClass {class MemberInner {}
Runnable anonymous = new Runnable() {@Overridepublic void run() {new SourceClass().methodToMove(new TargetClass());}};
TargetClass methodToMove(TargetClass target) {TargetClass.InnerRec innerRec = target.new InnerRec();innerRec.doAction();
int value = switch (3) {case 1 -> 10;case 2 -> 20;case 3 -> 30;default -> 0;};
TargetClass newTarget = new TargetClass();newTarget.instanceMethod();return newTarget;}}
package targetpkg;
strictfp class TargetClass {class InnerRec {void doAction () {}}
public void instanceMethod() {}
public void methodToMove() {}}
package sourcepkg;
import targetpkg.TargetClass;
class SourceSubclass extends SourceClass {protected String callMethod(TargetClass target, int num) {switch (num) {case 1:return target.new InnerRec().m1().m2().m3();default:return callMethod(target, num - 1);}}}
package targetpkg;
class TargetClass.InnerRec {TargetClass.InnerRec m1() { return this; }TargetClass.InnerRec m2() { return this; }String m3() { return "chainedResult"; }}