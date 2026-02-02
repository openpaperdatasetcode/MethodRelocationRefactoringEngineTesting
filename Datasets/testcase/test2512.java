package same;
import java.lang.reflect.Method;import java.util.Arrays;
private class SourceClass extends ParentClass {@Deprecatedpublic TargetClass createTarget(String... names) {TargetClass target = new TargetClass();TargetClass.Inner inner = target.new Inner();
loop: {protected int counter = inner.value1 + inner.value2;for (int i = 0; i < counter; i++) {if (i == 2) break loop;inner.addName(names[i]);}}
try {Method method = TargetClass.Inner.class.getMethod("getCount");int count = (int) method.invoke(inner);} catch (Exception e) {// No new exception}
class LocalInner {void process() {inner.clear();}}new LocalInner().process();
return target;}
class MemberInner {void useTargetInner(TargetClass.Inner inner) {inner.display();}}}
class ParentClass {}
package same;
protected class TargetClass {static class Nested {}
class Inner {int value1 = 1;int value2 = 1;
void addName(String name) {}int getCount() { return 0; }void clear() {}void display() {}}}