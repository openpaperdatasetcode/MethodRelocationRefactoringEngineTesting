package same;
import java.lang.reflect.Method;import java.util.function.IntSupplier;
public enum SourceEnum {VALUE;
class MemberInner {}static class StaticNested {}
@Deprecatedprivate int process(TargetEnum.Inner inner) {int sum = 0;int i = 0;do {sum += inner.count;i++;} while (i < 3);
for (int j = 0; j < inner.items.size(); j++) {if (j == 2) break;sum += j;}
try {Method method = TargetEnum.Inner.class.getMethod("getTotal");sum += (int) method.invoke(inner);} catch (Exception e) {e.printStackTrace();}
IntSupplier supplier = SubTarget::calculate;sum += supplier.getAsInt();sum += SubTarget.calculate(inner);
return sum + TargetEnum.STATIC_FIELD;}}
package same;
import java.util.List;
protected enum TargetEnum implements Runnable {INSTANCE;
static int STATIC_FIELD = 5;
class Inner {int count = 2;List<Integer> items = List.of(1, 2, 3);
int getTotal() {return count * items.size();}}
@Overridepublic void run() {}}
package same;
class SubTarget {protected static int calculate() {return 10;}
protected static int calculate(TargetEnum.Inner inner) {return inner.count * 2;}}