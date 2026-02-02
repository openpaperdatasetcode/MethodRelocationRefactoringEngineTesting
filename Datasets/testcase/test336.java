package com.refactoring.movemethod;

import java.util.List;

// Private normal source class (same package as target)
private class SourceClass {
    // Member inner class (source feature)
    protected class MemberInner {}

    // Anonymous inner class (source feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in Source");
        }
    };

    // Accessor method (protected access, Object return, target parameter, no method type params)
    @CustomAnnotation // has_annotation feature
    protected Object getTargetValue(TargetClass<String> targetParam) {
        // Variable call
        String localVar = "accessor_var";
        int count = 0;

        // For statement
        for (int i = 0; i < 5; i++) {
            count += i;
        }

        // While statement
        while (count < 10) {
            count++;
        }

        // Uses outer this (outer class this reference)
        MemberInner inner = SourceClass.this.new MemberInner();

        // With bounds (type parameter bounds reference)
        TargetClass<? extends CharSequence> boundedTarget = targetParam;

        // Call sub-class method (expression position, overloading, instance reference)
        TargetSubClass subInstance = new TargetSubClass();
        Object result = subInstance.overloadedMethod(boundedTarget) // overloading 1
                .overloadedMethod(localVar); // overloading 2 (instanceReference.methodName)

        // No new exception (no exception instantiation)
        return result;
    }
}

// Custom annotation for has_annotation feature
@interface CustomAnnotation {}

// Public generic target class (implements interface, type parameter, anonymous inner)
public class TargetClass<T extends CharSequence> implements List<T> { // with_bounds (T extends CharSequence)
    // Anonymous inner class (target feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in Target");
        }
    };

    @Override
    public int size() { return 0; }

    @Override
    public boolean isEmpty() { return false; }

    @Override
    public boolean contains(Object o) { return false; }

    @Override
    public java.util.Iterator<T> iterator() { return null; }

    @Override
    public Object[] toArray() { return new Object[0]; }

    @Override
    public <T1> T1[] toArray(T1[] a) { return null; }

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
    public int indexOf(Object o) { return 0; }

    @Override
    public int lastIndexOf(Object o) { return 0; }

    @Override
    public java.util.ListIterator<T> listIterator() { return null; }

    @Override
    public java.util.ListIterator<T> listIterator(int index) { return null; }

    @Override
    public List<T> subList(int fromIndex, int toIndex) { return null; }
}

// Sub-class of TargetClass (call_method type: sub_class)
public class TargetSubClass extends TargetClass<String> {
    // Overloading method 1
    public TargetSubClass overloadedMethod(TargetClass<? extends CharSequence> target) {
        return this;
    }

    // Overloading method 2 (overloading feature)
    public Object overloadedMethod(String arg) { // instanceReference.methodName(arguments)
        return arg + super.size();
    }
}