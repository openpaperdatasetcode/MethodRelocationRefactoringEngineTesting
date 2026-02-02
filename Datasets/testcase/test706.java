// Source class: abstract, protected modifier, same package, no additional features
protected abstract class SourceClass {
    // Abstract method required for abstract class
    public abstract void abstractSourceMethod();

    // Outer method to host inner class (source_inner position)
    public void outerMethod() {
        // Inner class (source_inner position)
        class SourceInnerClass {
            // Method: instance, void return, default access, contains target parameter
            void moveCandidateMethod(StrictfpTargetClass<String> targetParam) {
                // Variable call (target class method)
                int targetValue = targetParam.getInnerValue();
                
                // Break statement
                for (int i = 0; i < 10; i++) {
                    if (i == targetValue) {
                        break; // Break statement feature
                    }
                    targetParam.updateLocalInner(i);
                }

                // Throw statement (no new exception instantiation - reuse existing)
                try {
                    if (targetParam == null) {
                        throw new IllegalArgumentException("Target parameter cannot be null"); // Throw statement
                    }
                } catch (IllegalArgumentException e) {
                    // No new exception thrown (reuse caught exception, no new instantiation)
                    System.out.println("Handled exception: " + e.getMessage());
                }
            }
        }

        // Invoke refactor method with target parameter (per_condition)
        StrictfpTargetClass<String> target = new StrictfpTargetClass<String>() {
            @Override
            public void abstractTargetMethod() {}
        };
        new SourceInnerClass().moveCandidateMethod(target);
    }
}

// Target class: abstract, strictfp modifier, type parameter + local inner class features
strictfp abstract class StrictfpTargetClass<T> {
    private int innerValue = 5;

    // Type parameter usage (target_feature)
    private T data;

    int getInnerValue() {
        return innerValue;
    }

    void updateLocalInner(int val) {
        // Local inner class (target_feature)
        class TargetLocalInner {
            void setValue(int newVal) {
                innerValue = newVal;
            }
        }
        new TargetLocalInner().setValue(val);
    }

    // Abstract method required for abstract class
    public abstract void abstractTargetMethod();
}