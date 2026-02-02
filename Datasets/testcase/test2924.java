import java.util.ArrayList;import java.util.List;
public class SourceClass<T> {private String outerPrivateField = "privateValue";static class StaticNested<T> {}
public List<String> methodToMove(TargetClass<?> target, List<T> paramList, String... varargs) {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println(SourceClass.this.outerPrivateField);}};anonymous.run();
for (String arg : varargs) {target.anonymousAction(arg);}
protectedVarargsMethod(target, paramList);TargetClass.InnerInner inner = target.new InnerInner();inner.useTargetField();
List<String> result = new ArrayList<>();result.add(SourceClass.this.outerPrivateField);result.add(target.targetField);return result;}
protected void protectedVarargsMethod(TargetClass target, List list) {
for (U item : list) {
target.instanceMethod(item.toString());
}
}
}
sealed class TargetClass extends ParentClass permits TargetSubclass {
String targetField;
class InnerInner {void useTargetField() {System.out.println(targetField);}}
public void instanceMethod(String arg) {}
void anonymousAction(String param) {Runnable anonymous = new Runnable() {@Overridepublic void run() {targetField = param;}};anonymous.run();}}
abstract class ParentClass<V> {}
final class TargetSubclass extends TargetClass<String> {}