import java.util.function.Function;
protected class SourceClass<T> {public static int methodToMove(TargetClass target) {
private class VarDeclHelper {
int fieldA;
int fieldB;
void init() {
this.fieldA = 2;
this.fieldB = 2;
assert target.targetField == this.fieldA;
}
}
VarDeclHelper helper = new VarDeclHelper();
helper.init();
class LocalInner {void useTarget() {System.out.println(target.targetField);}}new LocalInner().useTarget();
Runnable anonymous = new Runnable() {@Overridepublic void run() {target.anonymousAction();}};anonymous.run();
try {Function<TargetClass, TargetClass> func = ParentClass::staticMethod;
TargetClass result = func.apply(target);
} catch (Exception e) {}
return target.targetField;}}
public class TargetClass {
int targetField;
void anonymousAction() {Runnable anonymous = new Runnable() {@Overridepublic void run() {targetField = 2;}};anonymous.run();}}
class ParentClass {public static <V> TargetClass<V> staticMethod(TargetClass<V> target) {target.targetField = 2;return target;}}