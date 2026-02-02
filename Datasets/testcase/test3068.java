package test;
import java.lang.reflect.Method;
private class TargetClass<T extends ParentClass> {T targetField;class TargetInner {}}
public class SourceClass<T> {class SourceInner {}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
private void methodToMove(TargetClass<T> target) {class LocalType {}LocalType local = new LocalType();
T var = target.targetField;TargetClass.TargetInner inner = target.new TargetInner();
try {Method method = SourceClass.class.getMethod("methodToMove", TargetClass.class);method.invoke(this, target);} catch (Exception e) {}}
private void methodToMove(TargetClass<T> target, T extraParam) {// Overload exists}}
class ParentClass {}
class SourceSubClass<T> extends SourceClass<T> {public int callMethod(TargetClass<T> target) {if (target != null) {this.methodToMove(target);TargetClass<T> newTarget = new TargetClass<>();return SourceSubClass::methodToMove;} else {return 0;}}
private void methodToMove(TargetClass<T> target) {super.methodToMove(target);}}