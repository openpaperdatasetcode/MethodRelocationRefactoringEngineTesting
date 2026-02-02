package source;
import target.TargetClass;import java.util.List;
sealed protected class SourceClass implements Runnable permits SourceSubClass {class MemberInner {}
class SourceInner {// Overload exists<T> void methodToMove(T target) {}
<T extends Number> void methodToMove(TargetClass<T> target) {class LocalInner {}LocalInner local = new LocalInner();
// ConstructorInvocation (static) with target_featureTargetClass.StaticNested staticNested = new TargetClass.StaticNested();staticNested.superField = 1;
// Variable call (contains target parameter)T val = target.memberInner.value;// Expression statementval = (T) Integer.valueOf(1);
// Do statementdo {// Switch caseswitch (val.intValue()) {case 1:// Lambda: () -> System.out.println(this.value)Runnable lambda = () -> System.out.println(target.memberInner.value);lambda.run();break;default:break;}} while (val.intValue() < 2);
// Synchronized constructor in property assignmentParentClass parent = new ParentClass() {synchronized ParentClass() {super.toString(); // super.methodName()}};
// Call method: synchronized static + instanceReference.methodNameOthersClass.synchronizedStaticMethod(target.memberInner);}}
@Overridepublic void run() {}}
final class SourceSubClass extends SourceClass {}
package target;
protected class TargetClass<T> extends SuperClass implements List<T> {MemberInner memberInner = new MemberInner();
class MemberInner {T value;}
static class StaticNested {int superField;}
@Overridepublic int size() { return 0; }@Overridepublic boolean isEmpty() { return false; }@Overridepublic boolean contains(Object o) { return false; }@Overridepublic java.util.Iterator<T> iterator() { return null; }@Overridepublic Object[] toArray() { return new Object[0]; }@Overridepublic <T1> T1[] toArray(T1[] a) { return null; }@Overridepublic boolean add(T t) { return false; }@Overridepublic boolean remove(Object o) { return false; }@Overridepublic boolean containsAll(java.util.Collection c) { return false; } @Override public boolean addAll(java.util.Collection c) { return false; } @Override public boolean addAll(int index, java.util.Collection c) { return false; } @Override public boolean removeAll(java.util.Collection c) { return false; }@Overridepublic boolean retainAll(java.util.Collection<?> c) { return false; }@Overridepublic void clear() {}@Overridepublic T get(int index) { return null; }@Overridepublic T set(int index, T element) { return null; }@Overridepublic void add(int index, T element) {}@Overridepublic T remove(int index) { return null; }@Overridepublic int indexOf(Object o) { return 0; }@Overridepublic int lastIndexOf(Object o) { return 0; }@Overridepublic java.util.ListIterator<T> listIterator() { return null; }@Overridepublic java.util.ListIterator<T> listIterator(int index) { return null; }@Overridepublic java.util.List<T> subList(int fromIndex, int toIndex) { return null; }}
class SuperClass {}
package other;
import target.TargetClass;
public class OthersClass {public static synchronized void synchronizedStaticMethod(TargetClass.MemberInner inner) {inner.value = null; // instanceReference.methodName(arguments)}}