package refactoring.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Parent interface for target's extends feature
interface ParentTargetInterface {
    void parentMethod();
}

// Helper interface for source's implements feature
interface HelperInterface {
    void helper();
}

// Source interface: empty modifier, same package, implements + static nested + local inner classes
interface SourceInterface extends HelperInterface {
    // Source contains target field (per_condition)
    TargetInterface.TargetInnerInterface targetField = new TargetInterface.TargetInnerInterface() {};

    // Static nested class (source feature)
    static class SourceStaticNested {
        int value = 2;
    }

    // Member inner interface (method_position: source_inner)
    interface SourceInnerInterface {
        // Overloading method 1
        @Deprecated // has_annotation
        protected void refactorMethod();

        // Overloading method 2 (overloading type)
        @SuppressWarnings("unchecked") // has_annotation
        protected void refactorMethod(TargetInterface.TargetInnerInterface param);

        // Default instance method (functional interfaces pos, 2/method_feature, target/feature, this.methodName)
        default List<String> instanceMethod(TargetInterface.TargetInnerInterface targetParam) {
            List<String> list = new ArrayList<>();
            int i = 0;
            // While statement
            while (i < 2) { // 2 (method_feature)
                // Variable call
                targetParam.innerData = "test" + i;
                // Access instance method
                targetParam.processData();
                list.add(targetParam.innerData);
                i++;
            }
            // This.methodName(arguments) (method_feature)
            this.refactorMethod(targetParam);
            return list;
        }

        // Call method: source, default modifier, overriding, super.methodName(), exception throwing pos
        default void callMethod() {
            try {
                // Exception throwing statements
                if (targetField.innerData == null) {
                    throw new IllegalArgumentException();
                }
                // Overriding + super.methodName() (target_feature)
                super.toString();
            } catch (IllegalArgumentException e) {
                // No new exception
            }
        }
    }

    // Local inner class (source feature) - within default method
    @Override
    default void helper() {
        class LocalInnerClass {
            void init() {
                // Super constructor invocation
                new Object() {
                    {
                        super(); // super constructor invocation
                    }
                };

                // Functional interfaces pos for instanceMethod call
                Function<TargetInterface.TargetInnerInterface, List<String>> func = this::instanceMethod;
                func.apply(targetField);

                // Call method invocation (exception throwing statements pos)
                SourceInnerInterface.super.callMethod();
            }

            // Instance method call helper
            List<String> instanceMethod(TargetInterface.TargetInnerInterface param) {
                return new SourceInnerInterface() {}.instanceMethod(param);
            }
        }
        new LocalInnerClass().init();
    }
}

/**
 * Javadoc for TargetInterface (target_feature: javadoc)
 * This is the target interface with extends, implements and anonymous inner class features
 */
abstract interface TargetInterface extends ParentTargetInterface, HelperInterface {
    // Inner interface (target_inner - target for method)
    interface TargetInnerInterface {
        String innerData = "";

        default void processData() {
            // Anonymous inner class (target_feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    // No new exception
                }
            };
            anonymous.run();
        }
    }

    @Override
    default void parentMethod() {
        // Implements feature (implements HelperInterface)
        HelperInterface.super.helper();
    }

    @Override
    default void helper() {
        // Anonymous inner class usage (target_feature)
        TargetInnerInterface inner = new TargetInnerInterface() {};
        inner.processData();
    }
}