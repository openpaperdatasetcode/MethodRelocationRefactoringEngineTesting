package test;
import otherpackage.OuterObj;import java.lang.reflect.Method;import java.util.function.Supplier;
interface SourceInterface {class MemberInner {class InnerRecursive {private Object methodToMove(TargetInterface targetParam, Object... args) {OuterObj outerObj = new OuterObj();outerObj.field = 10;
TargetInterface targetInstance = new TargetInterface() {};TargetInterface result = (args.length > 0) ?targetInstance.instanceMethod(1, 2, 3) :targetInstance.instanceMethod(0, 0, 0);
for (Object arg : args) {System.out.println(arg);}
switch (args.length) {case 1:targetParam.variableCall();break;default:break;}
class LocalType {}LocalType local = new LocalType();
Supplier<Object> lambda = () -> SourceInterface.this.toString();
try {Method refMethod = InnerRecursive.class.getMethod("methodToMove", TargetInterface.class, Object[].class);refMethod.invoke(this, targetParam, args);} catch (Exception e) {}
targetParam.accessInstanceMethod();return null;}}}
static class StaticNested {}}
public interface TargetInterface {default TargetInterface instanceMethod(int a, int b, int c) {return this;}
void variableCall();
void accessInstanceMethod();}
package otherpackage;
public class OuterObj {public int field;}