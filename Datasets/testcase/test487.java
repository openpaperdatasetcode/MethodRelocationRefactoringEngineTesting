package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;

// Sealed interface for permits feature in source class (valid permits syntax)
sealed interface SealedInterface<T> permits SourceClass {}

// Source normal class (protected modifier, same package, type parameter + permits + local inner + member inner)
protected class SourceClass<T extends CharSequence> implements SealedInterface<T> {
    // Static field for depends_on_static_field feature
    public static final String STATIC_FIELD = "static_value";

    // Member inner class (source_class feature)
    class SourceMemberInner {
        String innerData = "member_inner";
    }

    // Fix: Abstract method cannot be final (override_violation feature implemented via invalid modifier combination)
    public abstract void refactorMethod(TargetClass.TargetInnerRec targetParam);

    // Concrete implementation (override_violation: attempting to override final abstract - intentional invalidity)
    @Override
    public final void refactorMethod(TargetClass.TargetInnerRec targetParam) {
        // Variable call feature
        SourceMemberInner memberInner = new SourceMemberInner();
        String varCall = memberInner.innerData;

        // Local inner class (source_class feature)
        class SourceLocalInner {
            String processData(String data) {
                return data + "_processed";
            }
        }
        SourceLocalInner localInner = new SourceLocalInner();
        varCall = localInner.processData(varCall);

        // Depends on static field feature
        varCall += STATIC_FIELD;

        // Raw type feature (unparameterized generic type)
        List rawList = new ArrayList();
        rawList.add(varCall);

        // Switch case feature with super keywords
        switch (targetParam.getInnerValue()) {
            case "case1":
                rawList.add(super.toString()); // Super keywords feature
                break;
            case "case2":
                rawList.add(STATIC_FIELD);
                break;
            default:
                rawList.add("default_case");
                break;
        }

        // No_new_exception feature (no explicit 'throw new Exception()' - only runtime checks)
        if (targetParam == null) {
            rawList.add("null_param");
        }
    }
}

// Target normal class (default modifier, javadoc + implements + local inner class target_feature)
/**
 * TargetClass - Complete Javadoc for target_feature
 * Implements Collection<String> to fulfill 'implements' target_feature
 * This class contains inner components for move method refactoring target
 */
class TargetClass implements Collection<String> {
    // Target_inner_rec (inner component for target class)
    public class TargetInnerRec {
        private String innerValue;

        public TargetInnerRec(String innerValue) {
            this.innerValue = innerValue;
        }

        public String getInnerValue() {
            return innerValue;
        }
    }

    // Local inner class (target_feature)
    class TargetLocalInner {
        String validate(TargetInnerRec innerRec) {
            return (innerRec.getInnerValue() != null) ? innerRec.getInnerValue() : "default";
        }
    }

    // Valid Collection interface implementations (no null returns)
    @Override
    public int size() { return 0; }

    @Override
    public boolean isEmpty() { return true; }

    @Override
    public boolean contains(Object o) { return false; }

    @Override
    public Iterator<String> iterator() { return new ArrayList<String>().iterator(); }

    @Override
    public Object[] toArray() { return new Object[0]; }

    @Override
    public <E> E[] toArray(E[] a) { return a; }

    @Override
    public boolean add(String e) { return false; }

    @Override
    public boolean remove(Object o) { return false; }

    @Override
    public boolean containsAll(Collection<?> c) { return false; }

    @Override
    public boolean addAll(Collection<? extends String> c) { return false; }

    @Override
    public boolean removeAll(Collection<?> c) { return false; }

    @Override
    public boolean retainAll(Collection<?> c) { return false; }

    @Override
    public void clear() {}
}

// Others_class for call_method (public modifier, instance + super.methodName() target_feature, exception throwing pos)
class OthersClass extends SourceClass<String> {
    // Call method (public modifier, others_class type, exception throwing pos, returns List<String>)
    public List<String> callMethod() {
        List<String> result = new ArrayList<>();
        TargetClass target = new TargetClass();
        TargetClass.TargetInnerRec targetParam = target.new TargetInnerRec("test");

        try {
            // Instance feature (call instance method)
            this.refactorMethod(targetParam);
            // Super.methodName() target_feature (in exception throwing context)
            result.add(super.toString());
        } catch (UnsupportedOperationException e) {
            // Exception throwing statements position
            throw new RuntimeException("Failed to call refactorMethod", e);
        }

        return result;
    }
}