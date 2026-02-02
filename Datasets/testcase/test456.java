package refactoring.test;

import java.util.List;
import java.util.ArrayList;

// Source normal class (protected modifier, same package as target, implements interface)
protected class SourceClass implements List<String> {
    // First member inner class
    class FirstMemberInnerClass {
        // Second member inner class (source_inner_rec for method position)
        class SecondMemberInnerClass {
            // Instance field for access_instance_field feature
            String instanceField = "instance field value";

            // Target static method (default access, void return, source_inner_rec position)
            // Precondition: method contains target class parameter
            static void targetMethod(TargetClass<String> targetParam) {
                // Access_instance_field feature
                SecondMemberInnerClass innerObj = new SecondMemberInnerClass();
                String accessedField = innerObj.instanceField;

                // Variable call feature
                List<String> varCallList = new ArrayList<>();
                varCallList.add(accessedField);
                String varCall = varCallList.get(0);

                // ArrayInitializer feature (numbers:2, protected modifier context)
                protected int[] intArray = {2, 2}; // ArrayInitializer with 2, protected modifier

                // Type declaration statement feature
                class TypeDeclarationClass {
                    String typeField = varCall;
                }
                TypeDeclarationClass typeDeclObj = new TypeDeclarationClass();

                // Throw statement (no_new_exception - reuse existing exception)
                if (targetParam == null) {
                    throw new NullPointerException(); // No new exception (standard NPE, no custom new)
                }

                // Use target parameter's type parameter and local inner class
                targetParam.processType();
            }
        }
    }

    // Implements interface methods (minimal implementation)
    @Override
    public int size() { return 0; }
    @Override
    public boolean isEmpty() { return false; }
    @Override
    public boolean contains(Object o) { return false; }
    @Override
    public java.util.Iterator<String> iterator() { return null; }
    @Override
    public Object[] toArray() { return new Object[0]; }
    @Override
    public <T> T[] toArray(T[] a) { return null; }
    @Override
    public boolean add(String s) { return false; }
    @Override
    public boolean remove(Object o) { return false; }
    @Override
    public boolean containsAll(java.util.Collection<?> c) { return false; }
    @Override
    public boolean addAll(java.util.Collection<? extends String> c) { return false; }
    @Override
    public boolean addAll(int index, java.util.Collection<? extends String> c) { return false; }
    @Override
    public boolean removeAll(java.util.Collection<?> c) { return false; }
    @Override
    public boolean retainAll(java.util.Collection<?> c) { return false; }
    @Override
    public void clear() {}
    @Override
    public String get(int index) { return null; }
    @Override
    public String set(int index, String element) { return null; }
    @Override
    public void add(int index, String element) {}
    @Override
    public String remove(int index) { return null; }
    @Override
    public int indexOf(Object o) { return -1; }
    @Override
    public int lastIndexOf(Object o) { return -1; }
    @Override
    public java.util.ListIterator<String> listIterator() { return null; }
    @Override
    public java.util.ListIterator<String> listIterator(int index) { return null; }
    @Override
    public List<String> subList(int fromIndex, int toIndex) { return null; }
}

// Target normal class (public modifier, type parameter + local inner class target_feature)
public class TargetClass<T> {
    // Type parameter feature (T)
    private T typeValue;

    public void processType() {
        // Local inner class target_feature
        class LocalInnerClass {
            void useTypeParameter(T value) {
                typeValue = value;
            }
        }
        new LocalInnerClass().useTypeParameter(null);
    }
}