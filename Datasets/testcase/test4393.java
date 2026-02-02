package source;
import java.util.List;import target.TargetClass;
public abstract class SourceClass {protected int outerProtectedField = 10;
{Runnable anon1 = new Runnable() {@Overridepublic void run() {}};Runnable anon2 = new Runnable() {@Overridepublic void run() {}};}
public final Object moveMethod(TargetClass target) {TargetClass.TargetInner inner = target.new TargetInner();int targetField = target.targetField;int outerVal = this.outerProtectedField;
int i = 0;while (i < TargetClass.staticField) {inner.setValue(i);i++;}
protected WhileStatement(target);
List<String> result = new SubClass().create().getList();return result;}
protected void WhileStatement(TargetClass target) {while (target.targetField < 1) {target.targetField++;}}}
package target;
import java.util.List;import java.util.ArrayList;
protected class TargetClass implements Cloneable {int targetField;static int staticField = 5;
class TargetInner {private int value;void setValue(int v) { value = v; }}
void methodWithLocal() {class LocalInner {}}
@Overridepublic Object clone() throws CloneNotSupportedException {return super.clone();}}
package source;
import java.util.List;import target.TargetClass;
class SubClass extends SourceClass {public synchronized List<String> getList() {return new ArrayList<>();}
public SubClass create() {return new SubClass();}}
