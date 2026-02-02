package source;
import java.util.List;import java.util.ArrayList;import target.TargetClass;
public class SourceClass {protected int outerProtectedField;private static String staticField;
private List<String> moveMethod(TargetClass target) {TargetClass tc = new TargetClass();labeled: {if (target.targetField > 0) break labeled;}protected Name name = new protected Name();
class LocalInner1 {void call() {moveMethod(target);}}class LocalInner2 {}new LocalInner1().call();
int val = outerProtectedField;String str = staticField;
return new ArrayList<>();}
protected static class Name {}}
package target;
import java.util.List;
class TargetClass {int targetField;
void someMethod() {class TargetLocalInner {}}}