package same;
import java.sql.SQLException;import java.lang.reflect.Method;import java.util.List;
public class SourceClass {private int outerPrivate = 5;class MemberInner1 {}class MemberInner2 {}
class InnerRec {int process(TargetClass.InnerRec<Integer> inner) throws SQLException {SubClass sub = new SubClass();inner.value = sub.calculate(outerPrivate);
int i = 0;do {inner.count++;i++;} while (i < inner.limit);
try {Method method = TargetClass.InnerRec.class.getMethod("getValue");} catch (Exception e) {}
return inner.count;}
String process(TargetClass.InnerRec<String> inner) throws SQLException {return inner.value;}}}
class SubClass extends TargetClass {private <T> int calculate(T val) {return super.compute(val);}}
package same;
import java.util.function.Function;
class TargetClass {class InnerRec<T> {T value;int count = 0;int limit = 3;
T getValue() {return value;}}
protected <T> int compute(T data) {return data.hashCode();}
void processList(List<?> list) {Function<Object, Object> func = TargetClass::convert;for (Object item : list) {func.apply(item);}}
static <T> Object convert(T item) {return item.toString();}}