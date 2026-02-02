package refactoring.test;

import java.io.IOException;

// Source class - public modifier, same package as target, contains anonymous inner & static nested classes
public class SourceClass {
    // Target class field (per_condition: source contains the field of the target)
    private TargetClass<String> targetField = new TargetClass<>();

    // Static nested class
    static class SourceStaticNested {
        int nestedValue;
    }

    // Member inner class (recursive inner structure for method_position: source_inner_rec)
    class SourceInnerClass {
        // Recursive inner class
        class SourceRecursiveInner {
            // Method to be refactored - meets all specified features
            @SuppressWarnings("unchecked")
            TargetClass.TargetRecursiveInner refactorMethod() throws IOException {
                // Type declaration statement
                TargetClass.TargetStaticNested nestedType;
                // Variable call
                targetField.targetValue = "test";
                nestedType = new TargetClass.TargetStaticNested();
                // Depends on inner class
                TargetClass.TargetRecursiveInner innerInstance = targetField.new TargetRecursiveInner();
                innerInstance.innerField = nestedType.nestedField;
                
                // Anonymous inner class usage (source class feature)
                Runnable anonymousRunnable = new Runnable() {
                    @Override
                    public void run() {
                        innerInstance.process();
                    }
                };
                anonymousRunnable.run();

                // Requires throws (IOException) & return Target inner recursive type
                return innerInstance;
            }
        }
    }
}

// Target class - non-sealed modifier, type parameter, static nested class
non-sealed class TargetClass<T> {
    T targetValue;

    // Static nested class (target_feature)
    static class TargetStaticNested {
        int nestedField;
    }

    // Recursive inner class (target_inner_rec - target for method)
    class TargetRecursiveInner {
        int innerField;

        void process() {
            innerField += 1;
        }
    }
}