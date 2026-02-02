package source;
import target.TargetEnum;import java.lang.reflect.Method;
private enum SourceEnum {INSTANCE;
TargetEnum targetField;
private Object moveMethod() {class LocalInner {}new Runnable() {public void run() {}};
try {Method method = TargetEnum.class.getMethod("instanceMethod");targetField.instanceMethod();Object val = targetField.protectedField;return targetField.innerClass;} catch (NoSuchMethodException e) {return null;}}}
package target;
import java.util.List;
abstract enum TargetEnum<T> {VALUE;
protected Object protectedField;MemberInner innerClass = new MemberInner();
abstract void instanceMethod();
class MemberInner {}}