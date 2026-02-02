package source;
import target.Target;import java.util.function.Function;
abstract class Source {private Target targetField;
static class StaticNested {int nestedField;}
class Inner {public void instanceMethod() {if (targetField == null) {return;}
super.getClass();
Function<Integer, String> methodRef = Target::publicMethod;String refResult = methodRef.apply(1);
variableCall(targetField);
int i = 0;while (i < 3) {int callResult = targetField.finalInstanceMethod();i++;}}
private void variableCall(Target target) {int val = target.targetField;StaticNested staticNested = new StaticNested();int nestedVal = staticNested.nestedField;}}
void methodWithLocalInner() {class LocalInner {void localMethod(Target target) {target.finalInstanceMethod();}}LocalInner local = new LocalInner();local.localMethod(targetField);}}
package target;
protected class Target {int targetField;
public static String publicMethod(int num) {return String.valueOf(num);}
final int finalInstanceMethod() {return targetField++;}
void methodWithLocalInner() {class LocalInner {void localMethod() {System.out.println(targetField);}}LocalInner local = new LocalInner();local.localMethod();}}