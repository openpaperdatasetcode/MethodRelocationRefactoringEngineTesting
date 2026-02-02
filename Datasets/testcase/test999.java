import java.io.IOException;

abstract class SourceClass {
    // Local inner class (source feature)
    public void outerMethod() {
        class SourceLocalInner {
            // Member inner class with recursive structure (source_inner_rec)
            class SourceMemberInnerRec {
                // Varargs, private, return Object, no type parameters, contains target parameter
                private Object moveCandidateMethod(TargetClass... targetParams) throws IOException {
                    // Requires throws declaration
                    // Try statement
                    try {
                        if (targetParams == null) throw new IOException("Target params null");
                        // Variable call
                        for (TargetClass target : targetParams) {
                            // Override violation: attempt to override final method
                            TargetClass.ImplementedInterface inner = target.new MemberInnerClass() {
                                @Override
                                public final void finalMethod() {} // Compile error (override violation)
                            };
                            inner.process();
                        }
                    } catch (IOException e) {
                        // Handle exception, no new exception thrown
                        return null;
                    }
                    return targetParams.length > 0 ? targetParams[0] : new Object();
                }
            }

            void invokeRefactorMethod(TargetClass... targets) throws IOException {
                new SourceMemberInnerRec().moveCandidateMethod(targets);
            }
        }

        new SourceLocalInner().invokeRefactorMethod(new TargetClass());
    }

    // Member inner class (source feature)
    class SourceMemberInner {
        void helper() {}
    }

    // Abstract method required for abstract class
    public abstract void abstractMethod();
}

public class TargetClass implements TargetClass.ImplementedInterface {
    // Implements (target_feature)
    interface ImplementedInterface {
        void process();
        final void finalMethod(); // Final method for override violation
    }

    // Member inner class (target_feature)
    class MemberInnerClass implements ImplementedInterface {
        @Override
        public void process() {}

        @Override
        public final void finalMethod() {} // Final method
    }

    @Override
    public void process() {}

    @Override
    public final void finalMethod() {}
}