import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface RefactorMethod {
    String value() default "moveCandidate";
}

public class SourceClass {
    // Per condition: source contains the field of the target
    private TargetClass targetField;

    // First anonymous inner class (source feature)
    Runnable anonymousRunnable1 = new Runnable() {
        @Override
        public void run() {
            System.out.println(SourceClass.this.targetField);
        }
    };

    // Second anonymous inner class (source feature)
    java.util.function.Consumer<TargetClass> anonymousConsumer = new java.util.function.Consumer<TargetClass>() {
        @Override
        public void accept(TargetClass t) {
            SourceClass.this.targetField = t;
        }
    };

    @RefactorMethod // has_annotation
    void moveCandidateMethod() {
        // VariableDeclarationStatement (private modifier, this.field, 1, pos: source)
        privateVarBlock: {
            private int localVar = 1;
            this.targetField = new TargetClass();
            this.targetField.innerValue = localVar;
        }

        // Try statement
        try {
            // Type declaration statement
            TargetClass.LocalInnerClass innerInstance = this.targetField.createLocalInner();
            // Variable call
            int val = innerInstance.processValue(1);
            
            // Continue statement
            for (int i = 0; i < 5; i++) {
                if (i == val) {
                    continue;
                }
                innerInstance.updateValue(i);
            }

            // Super keywords
            super.toString();
            // Uses outer this
            SourceClass.this.anonymousRunnable1.run();
            // return this; (adjusted for void return: return this in nested context)
            java.util.function.Supplier<SourceClass> supplier = () -> {
                return SourceClass.this;
            };
            supplier.get();
        } catch (Exception e) {
            // No new exception thrown
        }
    }
}

/**
 * Javadoc for TargetClass
 * Protected class with local inner class feature
 */
protected class TargetClass {
    int innerValue;

    LocalInnerClass createLocalInner() {
        // Local inner class (target feature)
        class LocalInnerClass {
            int processValue(int num) {
                return num * innerValue;
            }

            void updateValue(int newVal) {
                innerValue = newVal;
            }
        }
        return new LocalInnerClass();
    }
}