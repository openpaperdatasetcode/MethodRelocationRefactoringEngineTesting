package refactoring.test;

// Source class: normal, protected modifier, same package as target, features: two local inner classes
protected class SourceClass {
    // Per condition: source contains target class field
    TargetClass targetField = new TargetClass();

    // Member inner class (host for source_inner_rec method position)
    class SourceInnerRecClass {
        // Method to be refactored: instance, return Object, protected access, position source_inner_rec
        protected Object moveMethod() {
            // Variable call feature
            int localVar = 10;
            // Access outer protected feature
            String outerProtected = SourceClass.this.protectedField;

            // First local inner class (source_class feature)
            class FirstLocalInner {
                // Super constructor invocation feature
                FirstLocalInner() {
                    super();
                }

                String process(String data) {
                    return data + "-processed-1";
                }
            }

            // Second local inner class (source_class feature)
            class SecondLocalInner {
                int calculate(int val) {
                    return val * 2;
                }
            }

            FirstLocalInner firstInner = new FirstLocalInner();
            SecondLocalInner secondInner = new SecondLocalInner();
            
            localVar = secondInner.calculate(localVar);
            String processedData = firstInner.process(outerProtected);

            // No new exception thrown (no_new_exception feature)
            return targetField.innerClass().combine(processedData, localVar);
        }
    }

    // Protected field for access_outer_protected feature
    protected String protectedField = "source-protected-data";
}

// Target class: normal, default modifier, same package, target_feature: member inner class
class TargetClass {
    // Member inner class (target_feature)
    class TargetInnerClass {
        Object combine(String data, int num) {
            return data + "-" + num;
        }
    }

    public TargetInnerClass innerClass() {
        return new TargetInnerClass();
    }
}