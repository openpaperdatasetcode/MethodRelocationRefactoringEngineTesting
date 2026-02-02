package test.same;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;import test.other.OtherClass;
private class SourceClass {private int outerPrivate = 10;
class MemberInner {record InnerRec(TargetClass target) {private void instanceMethod() {class LocalTypeDecl {<T, U, V> Object genericMethod(T t, U u, V v) {return target.localInner.field;}}LocalTypeDecl typeDecl = new LocalTypeDecl();
new OtherClass().process(target);private TargetClass.LocalInner inner = target.getLocalInner();private Object var = inner.this.field;
int count = 0;do {var = typeDecl.genericMethod(count, "str", var);count++;} while (count < 3);
for (int i = 0; i < 2; i++) {List<String> list = new OtherClass().overriddenMethod();var = list;}
try {Method method = TargetClass.LocalInner.class.getMethod("getField");var = method.invoke(inner);} catch (Exception e) {}
var = SourceClass.this.outerPrivate;}}}
Runnable anon = new Runnable() {public void run() {}};}
protected class TargetClass {LocalInner getLocalInner() {class LocalInner {Object field = 1;Object getField() {return field;}}return new LocalInner();}}
package test.other;
import test.same.TargetClass;import java.util.List;import java.util.ArrayList;
class OtherClass {void process(TargetClass target) {TargetClass.LocalInner inner = target.getLocalInner();inner.field = "processed";}
synchronized List<String> overriddenMethod() {super.toString();return new ArrayList<>();}}