package sourcepkg;
import targetpkg.TargetClass;import java.util.function.Supplier;
interface ActionInterface {}
private abstract class SourceClass implements ActionInterface {class MemberInner {}
static class StaticNested {}
/**
Abstract method for Move Method refactoring test
@param target target class instance
@return base type result
*/
private abstract int moveMethod(TargetClass target);
public TargetClass instanceMethod(TargetClass target) {target.obj.field = 1;this.helperMethod1(target);this.helperMethod2(target);
int count = 0;while (count < 2) {try {variableCall(target);target.staticNested.innerMethod();} catch (Exception e) {}count++;}
Supplier<TargetClass> supplier = () -> {while (target.obj.field == 1) {return target;}return null;};return supplier.get();}
private void helperMethod1(TargetClass target) {}private void helperMethod2(TargetClass target) {}
private void variableCall(TargetClass target) {target.staticNested.doTask();}}
package targetpkg;
/**
Javadoc for TargetClass: Final abstract target class with static nested class*/final abstract class TargetClass {public TargetObj obj = new TargetObj(1);
static class TargetObj {int field;TargetObj(int field) {this.field = field;}}
public static class StaticNested {public void doTask() {}public void innerMethod() {}}
public StaticNested staticNested = new StaticNested();
private abstract int moveMethod(TargetClass target);}