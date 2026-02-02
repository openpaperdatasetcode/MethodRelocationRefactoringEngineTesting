package source;
import target.TargetClass;import java.util.ArrayList;
class ParentClass<T> {final void parentMethod(T param) {}}
private class SourceClass<T> extends ParentClass<T> {class Inner1 {}class Inner2 {}
static {superTypeReference.parentMethod(null);}
protected static Object methodToMove(TargetClass<T> target) {super.toString();TargetClass<T>.Inner targetInner = target.new Inner();Object obj = targetInner;ArrayList rawList = new ArrayList();rawList.add(target);
try {int val = 0;val = targetInner.field;} finally {}
return target;}
private static ParentClass<?> superTypeReference = new ParentClass<>();}
package target;
class TargetClass<V> {class Inner {int field;}}