import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
sealed class BaseClass permits FinalSourceClass {}
final class FinalSourceClass extends BaseClass {private TargetClass targetField;
public FinalSourceClass() {this.targetField = new TargetClass();}
private List<String> instanceMethod() {List<String> result = new ArrayList<>();
if (targetField == null) {throw new NullPointerException("Target field cannot be null");}
TargetClass.InnerClass inner = targetField.new InnerClass();result.add(String.valueOf(recursiveMethod(3, inner)));
this.instanceMethodHelper(result);
try {Method refMethod = TargetClass.InnerClass.class.getMethod("recursiveInner", int.class);result.add(refMethod.invoke(inner, 2).toString());} catch (Exception e) {}
variableCall(inner);return result;}
private void instanceMethodHelper(List<String> list) {list.add("helper");}
public int recursiveMethod(int n, TargetClass.InnerClass inner) {if (n <= 0) return 0;return n + new TargetClass().new InnerClass().recursiveInner(n - 1);}
private void variableCall(TargetClass.InnerClass inner) {inner.doAction();}}
protected class TargetClass {public class InnerClass {public int recursiveInner(int n) {if (n <= 0) return 1;return n * recursiveInner(n - 1);}
public void doAction() {}}}
class ConstructorCaller {ConstructorCaller() {this(new FinalSourceClass().recursiveMethod(3, new TargetClass().new InnerClass()));}
ConstructorCaller(int value) {}}