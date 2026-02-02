package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
strictfp class SourceClass<T extends Number> {public class InnerSource {TargetClass<T> varargsMethod(TargetClass<T>... targets) {class LocalInnerFirst {private void checkSuperField(TargetClass<T> target) {if (target.getSuperField() < 3) {breakLabel: {break breakLabel;}}}}
class LocalInnerSecond {public List<String> instanceChainCall(OtherClass other) {return other.createList().add("item").getList();}}
LocalInnerFirst first = new LocalInnerFirst();LocalInnerSecond second = new LocalInnerSecond();TargetClass<T>[] targetArray = (TargetClass<T>[]) new TargetClass[3];
for (TargetClass<T> target : targets) {first.checkSuperField(target);T value = target.getValue();
switch (value.intValue()) {case 1:target.setSuperField(3);break;default:target.setSuperField(0);}
try {Method method = TargetClass.class.getMethod("getValue");System.out.println(method.invoke(target));} catch (Exception e) {e.printStackTrace();}}
OtherClass other = new OtherClass();second.instanceChainCall(other);return targets.length > 0 ? targets[0] : null;}}}
private class TargetClass<T extends Number> extends ParentClass {private T value;
public TargetClass(T value) {this.value = value;}
public T getValue() {return value;}
// Override violation: ParentClass has public method, this is default accessvoid setSuperField(int field) {super.superField = field;}
public int getSuperField() {return super.superField;}}
class ParentClass {protected int superField;}
class OtherClass {public ListWrapper createList() {return new ListWrapper();}
public class ListWrapper {private List<String> list = new ArrayList<>();
public ListWrapper add(String item) {list.add(item);return this;}
public List<String> getList() {return list;}}}