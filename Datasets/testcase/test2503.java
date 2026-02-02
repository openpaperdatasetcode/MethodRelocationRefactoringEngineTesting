package same;
class SourceClass {protected TargetClass createTarget() {synchronized (this) {TargetClass target = new TargetClass();int count = 0;do {count = target.inner.getCount();target.inner.increment();} while (count < 2);
for (String s : TargetClass.staticField) {target.process(s);}
try {Class<?> cls = Class.forName("same.TargetClass");Object obj = cls.getDeclaredConstructor().newInstance();} catch (Exception e) {e.printStackTrace();}
return target;}}
static class StaticNested1 {}static class StaticNested2 {}}
package same;
public class TargetClass {static List<String> staticField = Arrays.asList("a", "b");MemberInner inner = new MemberInner();
class MemberInner {private int count = 0;public int getCount() { return count; }public void increment() { count++; }}
public void process(String s) {}}