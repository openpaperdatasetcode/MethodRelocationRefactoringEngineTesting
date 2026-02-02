import java.io.IOException;

protected class SourceClass<T> implements Runnable {
    private int sourceField = 10;

    // Member inner class
    class MemberInnerClass {
        // Local inner class within member inner class (source_inner_rec)
        void outerMethod() {
            class LocalInnerClass {
                // Overriding private method with required features
                private int processData(TargetClass<String>.TargetInnerClass param) throws IOException {
                    super.run();
                    int result = 0;
                    for (int i = 0; i < 5; i++) {
                        if (i == 3) {
                            break;
                        }
                        result += param.targetField; // Variable call to target class parameter
                    }
                    return result; // Base type return
                }
            }
        }
    }

    @Override
    public void run() {
        // Required interface implementation
    }
}

class TargetClass<U> {
    // Target inner class for method move
    class TargetInnerClass {
        int targetField = 5;
    }
}