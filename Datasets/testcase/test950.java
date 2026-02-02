package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Supporting interface for source_class implements feature
interface TestInterface {}

// Sealed target class's permitted subclass (required for sealed class)
final class PermittedSubclass extends TargetClass {}

// Source class (protected, normal class, same package)
protected class SourceClass implements TestInterface {
    // Static nested class (source feature)
    static class SourceStaticNested {
        public static String staticField = "static_data"; // For depends_on_static_field
    }

    // Source inner class (method_position: source_inner)
    class SourceInnerClass {
        // Instance method (final access, return Object, target class: target_inner_rec)
        public final Object processTarget(TargetClass.TargetInnerRec targetParam) {
            // Per_condition: contains target parameter (variable call)
            Object result = targetParam;

            // Try statement
            try {
                // ConstructorInvocation (private modifier, this.field, 1, pos: source)
                PrivateConstructorClass constructorObj = new PrivateConstructorClass(targetParam);
                result = constructorObj;

                // Expression statement
                targetParam.setInnerField("processed");

                // Access instance method
                targetParam.innerInstanceMethod();

                // Depends_on_static_field
                String staticData = SourceStaticNested.staticField;

                // Collection operations (pos for instance method)
                List<TargetClass.TargetInnerRec> collection = new ArrayList<>();
                collection.add(targetParam);
                publicInstanceMethod(collection);

                // For loop to trigger collection operations
                for (TargetClass.TargetInnerRec item : collection) {
                    item.innerInstanceMethod();
                }

                // Override_violation: Implement interface method with incorrect signature
                TestInterface invalidOverride = new TestInterface() {
                    @Override
                    public String toString() { // Correct override
                        return super.toString();
                    }

                    // Violation: Attempt to override non-existent method with same name
                    public void testInterfaceMethod() {}
                };
            } catch (Exception e) {
                // No_new_exception: Catch without throwing new
                result = null;
            }

            return result;
        }

        // Private constructor class for ConstructorInvocation feature
        private class PrivateConstructorClass {
            private TargetClass.TargetInnerRec innerRec;
            private int field = 1; // For this.field = 1

            // Private constructor (target_feature: this.field, 1)
            private PrivateConstructorClass(TargetClass.TargetInnerRec inner) {
                this.innerRec = inner;
                this.field = 1; // this.field assignment with 1
            }
        }

        // Public instance method (required features: 1, source, instance, obj.m1().m2().m3())
        public void publicInstanceMethod(Collection<TargetClass.TargetInnerRec> collection) {
            collection.forEach(item -> {
                // obj.m1().m2().m3() chain
                String chainResult = item.getInnerField() // m1()
                        .concat("_") // m2()
                        .toUpperCase(); // m3()
                // 1 feature (literal value)
                if (chainResult.length() > 1) {
                    item.setInnerField(chainResult);
                }
            });
        }
    }

    // Local inner class (source feature)
    public void sourceLocalInner() {
        class LocalInnerClass {
            public void doSomething() {
                SourceInnerClass inner = new SourceInnerClass();
                inner.processTarget(new TargetClass().new TargetInnerRec());
            }
        }
        new LocalInnerClass().doSomething();
    }
}

// Target class (sealed, normal class, public modifier)
sealed class TargetClass permits PermittedSubclass {
    // Anonymous inner class (target feature)
    Runnable targetAnonymous = new Runnable() {
        @Override
        public void run() {
            TargetInnerRec inner = new TargetInnerRec();
            inner.innerInstanceMethod();
        }
    };

    // Target inner class (target_inner_rec)
    class TargetInnerRec {
        private String innerField;

        public String getInnerField() {
            return innerField;
        }

        public void setInnerField(String innerField) {
            this.innerField = innerField;
        }

        public void innerInstanceMethod() {
            // Instance method for access
        }
    }
}