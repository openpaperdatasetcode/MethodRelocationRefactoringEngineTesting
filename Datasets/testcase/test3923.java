import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
public class SourceClass {public TargetClass targetField;
public SourceClass() {targetField = new TargetClass();}
default List<String> method() {List<String> result = new ArrayList<>();
TargetClass.NestedStaticClass inner = new TargetClass.NestedStaticClass();result.add(recursiveMethod(1, inner));
class LocalType {String value = "local";}LocalType lt = new LocalType();result.add(lt.value);
protected String strLit = "protectedString";result.add(strLit);
try {Method refMethod = TargetClass.NestedStaticClass.class.getMethod("recursiveInner");result.add(refMethod.invoke(inner).toString());} catch (Exception e) {}
variableCall(inner);return result;}
default Object recursiveMethod(int n, TargetClass.NestedStaticClass inner) {return n <= 0 ? "base" : super.recursiveMethod(n - 1, inner);}
private void variableCall(TargetClass.NestedStaticClass inner) {inner.recursiveInner();}
public class MemberInnerClass {void useTarget() {targetField.nestedStaticField = "used";}}
public static class StaticNestedClass {TargetClass.NestedStaticClass getTargetInner() {return new TargetClass.NestedStaticClass();}}}
class TargetClass {public String nestedStaticField;
public static class NestedStaticClass<T extends CharSequence> {private int count = 0;
public Object recursiveInner() {count++;return count <= 1 ? "innerBase" : recursiveInner();}}}
class SuperClass {protected Object recursiveMethod(int n, TargetClass.NestedStaticClass inner) {return "superResult";}}