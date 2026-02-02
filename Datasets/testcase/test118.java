package com.refactoring.movemethod;

import java.io.IOException;

// Source class: generic class, public modifier, same package, type parameter, permits, member inner, static nested
public sealed class SourceClass<T extends CharSequence> permits SourceClass.StaticNestedSource, OtherPermittedClass {
    // per_condition: source contains target class field
    private TargetClass<String> targetField = new TargetClass<>();
    private String sourceVar = "sourceVariable";

    // Static nested class (source feature)
    static final class StaticNestedSource extends SourceClass<String> {}

    // Member inner class (source feature)
    class MemberInnerSource {
        void innerMethod() {}
    }

    // Method: instance, void return, protected access, source position
    protected void moveableInstanceMethod() throws IOException { // requires_throws feature
        // Variable call feature
        String localVar = sourceVar;
        localVar = targetField.targetInstanceMethod();

        // Numbers: 1, default modifier, CharacterLiteral
        default char charLiteral = '1';

        // Instance code blocks with recursion (final modifier, method_feature: 2, others_class, recursion, lambda)
        {
            final Runnable recursiveRunnable = new Runnable() {
                int count = 2; // method_feature "2"

                @Override
                public void run() { // return_type void
                    // method_feature: others_class, recursion, (parameters) -> methodBody
                    OtherClass otherInstance = new OtherClass();
                    if (count-- > 0) {
                        this.run(); // recursion
                        otherInstance.process(() -> this.run()); // lambda methodBody
                    }
                }
            };
            recursiveRunnable.run();
        }
    }
}

// Permits class for source sealed class (source feature: permits)
final class OtherPermittedClass extends SourceClass<Character> {}

// Target class: generic class, private modifier, target_feature: local inner class
private class TargetClass<U extends Number> {
    // Target inner class (method's target class: target_inner)
    class TargetInnerClass {
        void targetInnerMethod() {}
    }

    // Instance method for variable call
    String targetInstanceMethod() {
        // Local inner class (target_feature)
        class LocalInnerTarget {
            int localField = 1;
        }
        LocalInnerTarget localInner = new LocalInnerTarget();
        return String.valueOf(localInner.localField);
    }
}

// Others_class for recursion method_feature
class OtherClass {
    void process(Runnable runnable) {
        runnable.run();
    }
}