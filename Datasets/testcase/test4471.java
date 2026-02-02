package source;
import target.TargetClass;
public class SourceClass<T extends Number> {private int outerPrivate = 5;TargetClass<String, Integer> targetField = new TargetClass<>();
{new Runnable() {};}
class MemberInner {TargetClass<String, Integer> innerField;
public MemberInner() {if (targetField == null) {throw new NullPointerException();}int val = outerPrivate;switch (targetField.field) {case 1:val += targetField.field;break;default:val = 0;}innerField = new TargetClass.MemberInner<>(val);innerField.superMethod();}}}
package target;
public class TargetClass<K, V> {int field = 1;
class MemberInner<T> {T value;
protected MemberInner(T val) {this.value = val;}
protected void superMethod() {System.out.println(TargetClass.this.field);}}}