package refactoring.test;

import java.lang.reflect.Method;

// Source class: normal, default modifier, same package as target, features: anonymous inner class, static nested class
class SourceClass {
    // Static nested class (source_class feature)
    static class SourceStaticNestedClass<T extends Number> {
        T nestedValue;
    }

    // Method to be refactored: varargs, return TargetClass type, final access, position source
    // Per condition: method contains target class parameter
    final TargetClass<String> moveMethod(TargetClass<String> targetParam, String... args) {
        // Variable call feature
        int localVar = args.length;
        SourceStaticNestedClass<Integer> staticNested = new SourceStaticNestedClass<>();
        staticNested.nestedValue = localVar;

        // With_bounds feature (type parameter with bounds)
        class BoundedLocalClass<T extends CharSequence & Comparable<T>> {
            T process(T value) {
                // Super keywords feature (anonymous inner class with super call)
                Runnable anonymousRunnable = new Runnable() {
                    @Override
                    public void run() {
                        super.toString();
                        System.out.println(value);
                    }
                };
                anonymousRunnable.run();
                return value;
            }
        }

        BoundedLocalClass<String> boundedClass = new BoundedLocalClass<>();
        // Expression statement with bounded class usage
        String processed = boundedClass.process(targetParam.getValue());

        // Used_by_reflection feature
        try {
            Method method = TargetClass.class.getMethod("getValue");
            method.invoke(targetParam);
        } catch (Exception e) {
            // No_new_exception feature (no new custom exception thrown)
            e.printStackTrace();
        }

        // Return TargetClass type
        return new TargetClass<>(processed);
    }
}

// Target class: normal, public modifier, same package, target_feature: type parameter, extends, local inner class
public class TargetClass<T extends CharSequence> extends BaseClass {
    private T value;

    public TargetClass(T value) {
        this.value = value;
    }

    public T getValue() {
        // Local inner class feature
        class TargetLocalInnerClass {
            T retrieveValue() {
                return value;
            }
        }
        TargetLocalInnerClass localInner = new TargetLocalInnerClass();
        return localInner.retrieveValue();
    }
}

// Base class for target class extends feature
class BaseClass {}