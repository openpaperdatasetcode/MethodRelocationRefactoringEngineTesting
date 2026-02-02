package source;
import target.TargetClass;import java.lang.reflect.Field;import java.util.List;
private class SourceClass<T> {class MemberInner {@Deprecatedpublic void process(TargetClass<T> target) {class LocalType {}LocalType local = new LocalType();
TargetClass<T> newTarget = new TargetClass<>(target.getData());super();
static for (TargetClass.StaticNested nested : target.nestedList) {int value = nested.superField;}
Object data;if (target.isValid()) {data = target.getInner().getValue();} else {data = null;}
try {Field field = TargetClass.StaticNested.class.getField("value");} catch (Exception e) {}}}}
package target;
public class TargetClass<T> extends ParentClass {private T data;List<StaticNested> nestedList;
public TargetClass(T data) {super();this.data = data;}
public T getData() {return data;}
public boolean isValid() {return data != null;}
public Inner getInner() {return new Inner();}
class Inner {Object getValue() {return data;}}
static class StaticNested {int superField = parentField;int value;}}
package target;
class ParentClass {static int parentField = 1;}