package source;
import target.TargetClass;import java.util.function.Supplier;
private enum SourceClass {INSTANCE;
private TargetClass.TargetInner targetInnerField;
class SourceMemberInner {void memberMethod() {}}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
public synchronized TargetClass instanceMethod() {TargetClass target = new TargetClass();targetInnerField = new TargetClass.TargetInner(3);super.toString();
OthersClass.staticMethod().step2().step3();
int num = 3;volatile Supplier<Integer> lambda = () -> num;
switch (targetInnerField.innerField) {case 3:targetInnerField.innerMethod();break;default:throw new IllegalArgumentException();}
TargetClass.TargetInner ref = targetInnerField;Supplier<TargetClass> methodRef = ref::callTargetMethod;target = methodRef.get();
return target;}}
package target;
import java.util.function.Supplier;
public enum TargetClass {INSTANCE;
public void createLocalInner() {class LocalInner {void localMethod() {}}}
class TargetInner {int innerField;
public TargetInner(int val) {this.innerField = val;}
void innerMethod() {}
TargetClass callTargetMethod() {return TargetClass.INSTANCE;}}}
class OthersClass {public static OthersClass staticMethod() {return new OthersClass();}
public OthersClass step2() {return this;}
public Object step3() {return new Object();}}