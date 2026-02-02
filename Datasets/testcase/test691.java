package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

// Source generic class (private modifier, same package as target, empty features)
private class SourceGenericClass<T> {
    // Per_condition: source contains target class field
    private TargetGenericClass<String> targetField = new TargetGenericClass<>("target-field");
    
    // Protected member for access_outer_protected feature
    protected String outerProtectedField = "protected-value";

    // Instance method (meets all method features)
    public List<String> instanceMethod() {
        List<String> result = new ArrayList<>();
        
        // Labeled statement feature
        label1: {
            if (result.isEmpty()) {
                result.add("labeled-statement");
                break label1;
            }
        }
        
        // Type declaration statement feature
        class LocalTypeDeclaration {
            String getValue() {
                return outerProtectedField;
            }
        }
        
        // Variable call feature
        LocalTypeDeclaration localInstance = new LocalTypeDeclaration();
        String varCallResult = localInstance.getValue();
        result.add(varCallResult);
        
        // Access_outer_protected feature
        result.add(this.outerProtectedField);
        
        // Uses_outer_this feature
        SourceGenericClass<T> outerThis = SourceGenericClass.this;
        result.add(outerThis.outerProtectedField);
        
        // No_new_exception feature (no explicit throw new)
        if (targetField == null) {
            result.add("null-target");
        } else {
            result.add(targetField.getGenericField()); // Accessor call
        }
        
        return result;
    }

    // Call method (source type, default modifier, target features in ternary)
    Object callMethod() {
        // Ternary operator position + super.methodName() + accessor feature
        return (targetField != null) ? 
            super.toString() : 
            targetField.getGenericField(); // Accessor for target_feature
    }
}

// Target generic class (final modifier, implements interface, local inner class)
final class TargetGenericClass<E> implements Collection<E> {
    private E genericField;

    // Constructor
    public TargetGenericClass(E genericField) {
        this.genericField = genericField;
        
        // Local inner class (target_feature)
        class LocalInnerClass {
            E getInnerValue() {
                return genericField;
            }
        }
        
        LocalInnerClass inner = new LocalInnerClass();
        this.genericField = inner.getInnerValue();
    }

    // Accessor (target_feature for call_method)
    public E getGenericField() {
        return this.genericField;
    }

    // Super.methodName() support (inherited from Object)
    @Override
    public String toString() {
        return "TargetGenericClass{" + "genericField=" + genericField + '}';
    }

    // Required Collection interface methods (empty implementations)
    @Override
    public int size() { return 0; }
    @Override
    public boolean isEmpty() { return true; }
    @Override
    public boolean contains(Object o) { return false; }
    @Override
    public java.util.Iterator<E> iterator() { return null; }
    @Override
    public Object[] toArray() { return new Object[0]; }
    @Override
    public <T> T[] toArray(T[] a) { return a; }
    @Override
    public boolean add(E e) { return false; }
    @Override
    public boolean remove(Object o) { return false; }
    @Override
    public boolean containsAll(Collection<?> c) { return false; }
    @Override
    public boolean addAll(Collection<? extends E> c) { return false; }
    @Override
    public boolean removeAll(Collection<?> c) { return false; }
    @Override
    public boolean retainAll(Collection<?> c) { return false; }
    @Override
    public void clear() {}
}