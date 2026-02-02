package test;
import java.lang.reflect.Method;import java.util.function.Function;
private class SourceClass<T> {private static class SourceStaticNested {
U nestedField;
public SourceStaticNested(U nestedField) {
this.nestedField = nestedField;
}
}
public class SourceMemberInner {T innerField;public SourceMemberInner(T innerField) {this.innerField = innerField;}}
static {TargetSubClass<String> subInstance = new TargetSubClass<>("initValue");subInstance.callSuperMethod();}
protected int processTarget(TargetClass<T>.TargetInner targetInner) {Function<TargetClass<T>.TargetInner, Object> subMethodFunc = (inner) -> {TargetSubClass<T> sub = new TargetSubClass<>(inner.innerField);return sub.getSubField();};
Function<TargetClass<T>.TargetInner, Object>[] funcArray = new Function[1];funcArray[0] = subMethodFunc;
T targetField = targetInner.innerField;int fieldLength = targetField.toString().length();
try {Method innerMethod = TargetClass.TargetInner.class.getMethod("getInnerField");T reflectedField = (T) innerMethod.invoke(targetInner);fieldLength += reflectedField.toString().length();} catch (Exception e) {fieldLength = -1;}
return fieldLength;}}
public class TargetClass<T> {public class TargetInner {T innerField;public TargetInner(T innerField) {this.innerField = innerField;}public T getInnerField() {return innerField;}}
public void targetMethod() {class TargetLocalInner {void printField(T field) {System.out.println(field);}}new TargetLocalInner().printField(new TargetInner(null).innerField);}}
class TargetSubClass<T> extends TargetClass<T> {private T subField;public TargetSubClass(T subField) {this.subField = subField;}
public void callSuperMethod() {super.targetMethod();}
public Object getSubField() {return subField;}}