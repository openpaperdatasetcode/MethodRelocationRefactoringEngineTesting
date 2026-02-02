package test;
import java.lang.reflect.Method;import java.util.List;
public class SourceClass<T extends List<String>> {private int outerPrivateField = 10;
private void moveMethod(TargetClass<T> target) throws Exception {class LocalType {}LocalType local = new LocalType();
TargetClass<T>.Inner inner = target.new Inner(1);inner.obj.field = 1;
assert target != null;
for (int i = 0; i < 1; i++) {target.field = outerPrivateField;variableCall(target);}
Method method = TargetClass.class.getMethod("getField");method.invoke(target);}
private void variableCall(TargetClass<T> t) {}}
protected class TargetClass<T extends CharSequence> {int field;
class Inner {class Obj {int field;}Obj obj;
private Inner(int val) {this.obj = new Obj();}}
public int getField() {return field;}}
