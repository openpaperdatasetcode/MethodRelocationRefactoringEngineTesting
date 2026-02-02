package refactoring.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Different package other class (simulated in same package for compilation, marked as diff_package_others)
package refactoring.other;
public class DiffPackageOtherClass {
    public int superField = 1; // target_feature: super.field, 1

    public DiffPackageOtherClass() {
        super(); // ConstructorInvocation feature
    }
}

package refactoring.test;
import refactoring.other.DiffPackageOtherClass;

// Parent class for source_class extends feature
class SourceParentClass {
    protected int parentField = 0;
}

// Target class: normal, protected modifier, same package, target_feature: type parameter, implements, member inner class
protected class TargetClass<T extends CharSequence> implements List<T> {
    // Member inner class (target_feature)
    public class TargetMemberInner {
        List<String> processData(int value) {
            List<String> list = new ArrayList<>();
            list.add("Target processed: " + value);
            return list;
        }
    }

    // Implement List interface methods (minimal implementation)
    @Override
    public int size() { return 0; }
    @Override
    public boolean isEmpty() { return true; }
    @Override
    public boolean contains(Object o) { return false; }
    @Override
    public java.util.Iterator<T> iterator() { return null; }
    @Override
    public Object[] toArray() { return new Object[0]; }
    @Override
    public <E> E[] toArray(E[] a) { return null; }
    @Override
    public boolean add(T t) { return false; }
    @Override
    public boolean remove(Object o) { return false; }
    @Override
    public boolean containsAll(java.util.Collection<?> c) { return false; }
    @Override
    public boolean addAll(java.util.Collection<? extends T> c) { return false; }
    @Override
    public boolean addAll(int index, java.util.Collection<? extends T> c) { return false; }
    @Override
    public boolean removeAll(java.util.Collection<?> c) { return false; }
    @Override
    public boolean retainAll(java.util.Collection<?> c) { return false; }
    @Override
    public void clear() {}
    @Override
    public T get(int index) { return null; }
    @Override
    public T set(int index, T element) { return null; }
    @Override
    public void add(int index, T element) {}
    @Override
    public T remove(int index) { return null; }
    @Override
    public int indexOf(Object o) { return -1; }
    @Override
    public int lastIndexOf(Object o) { return -1; }
    @Override
    public java.util.ListIterator<T> listIterator() { return null; }
    @Override
    public java.util.ListIterator<T> listIterator(int index) { return null; }
    @Override
    public List<T> subList(int fromIndex, int toIndex) { return null; }
}

// Source class: normal, public modifier, same package as target, features: extends, static nested class, member inner class
public class SourceClass extends SourceParentClass {
    // Static nested class (source_class feature)
    public static class SourceStaticNested {
        int nestedValue = 1;
    }

    // Member inner class (source_inner_rec - method position host)
    public class SourceInnerRecClass {
        // Method to be refactored: instance, return List<String>, final access, position source_inner_rec
        // Per condition: contains target class parameter
        public final List<String> moveMethod(TargetClass<String> targetParam) throws SQLException {
            // Variable call feature
            int localVar = 0;
            List<String> resultList = new ArrayList<>();

            // ConstructorInvocation feature (type: ConstructorInvocation, modifier: public, target_feature: super.field, 1, pos: diff_package_others)
            DiffPackageOtherClass diffPackageObj = new DiffPackageOtherClass();
            localVar += diffPackageObj.superField; // super.field, 1

            // ArrayCreation feature (numbers: 1, modifier: public, exp: ArrayCreation)
            String[] strArray = new String[1]; // Array creation with size 1
            strArray[0] = "array-value-" + localVar;
            resultList.add(strArray[0]);

            // SQLException feature (declares throws, no new exception thrown)
            if (targetParam == null) {
                throw new SQLException("Target parameter cannot be null");
            }

            // Use target parameter's member inner class
            resultList.addAll(targetParam.new TargetMemberInner().processData(localVar));

            // No new exception thrown (no_new_exception feature)
            return resultList;
        }
    }
}