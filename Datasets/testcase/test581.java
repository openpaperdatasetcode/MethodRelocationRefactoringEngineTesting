package refactoring.test;

// Source class: abstract normal class, same package, static nested + local inner classes
abstract class SourceClass {
    // Private outer field for access_outer_private feature
    private int outerPrivateField = 3;

    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Static nested class (source feature)
    static class SourceStaticNested {
        int nestedValue = 3;
    }

    // Overridden method in parent for overriding type
    public TargetClass baseMethod() {
        return new TargetClass();
    }

    // Method to be refactored: overriding, TargetClass return, private access, source position
    private TargetClass baseMethod() { // overriding type (method override)
        // Raw type usage
        TargetClass.TargetInnerRecursive rawInner;
        rawInner = new TargetClass().new TargetInnerRecursive();

        // Variable call (target inner recursive class field)
        targetField.innerRecursive.counter = 3;
        // Access outer private field
        int accessOuterPrivate = SourceClass.this.outerPrivateField;

        // Empty statement
        ;
        // Expression statement
        targetField.innerRecursive.counter++;

        // Static ContinueStatement: source pos, this.field + 3 (target_feature)
        static void continueLogic() {
            SourceStaticNested staticNested = new SourceStaticNested();
            for (int i = 0; i < 5; i++) {
                if (i == 3) {
                    this.field = 3; // this.field + 3 (target_feature)
                    continue; // ContinueStatement
                }
                staticNested.nestedValue++;
            }
        }
        continueLogic();

        // Local inner class (source feature)
        class LocalInnerClass {
            void processTarget() {
                // Access instance method
                targetField.innerRecursive.process();

                // Override violation (invalid override attempt)
                class InvalidOverride extends TargetClass {
                    @Override
                    public final void targetMethod() { // Compile error: final method override
                        // No new exception
                    }
                }
            }
        }
        new LocalInnerClass().processTarget();

        // No new exception, return TargetClass type
        return targetField;
    }

    // Dummy field for ContinueStatement this.field
    private static int field;
}

// Target class: normal, public modifier, empty target_feature
public class TargetClass {
    TargetInnerRecursive innerRecursive = new TargetInnerRecursive();

    public final void targetMethod() {}

    // Target inner recursive class (target_inner_rec - target for method)
    class TargetInnerRecursive {
        int counter = 3;

        void process() {
            counter += 3;
        }

        // Recursive inner structure
        class TargetRecursiveInner {
            int value = 3;
        }
    }
}