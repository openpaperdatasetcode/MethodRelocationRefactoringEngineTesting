package com.refactoring.movemethod;

// Private enum source class (two static nested classes; same package as target)
private enum SourceEnum {
    INSTANCE;

    // Source contains target enum field (satisfies per_condition)
    private final TargetEnum targetField = TargetEnum.VALUE;

    // First static nested class (source feature)
    public static class SourceStaticNested1 {}
    // Second static nested class (source feature)
    public static class SourceStaticNested2 {}

    // Inner class containing the target method (source_inner position)
    public class SourceInnerClass {
        // Varargs method (public access, void return, target enum parameter)
        public void varargsMethod(TargetEnum targetParam, String... args) {
            // Variable call
            String localVar = "enum_var";
            int argCount = args.length;

            // Inner class for depends_on_inner_class feature
            class DependedInnerClass {
                void processArgs() {
                    // Depends on inner class to process varargs
                    for (String arg : args) {
                        localVar += "_" + arg;
                    }
                }
            }

            // Depends on inner class (instantiate and call method)
            DependedInnerClass dependedInner = new DependedInnerClass();
            dependedInner.processArgs();

            // Access target enum field (variable call extension)
            localVar += targetField.name() + targetParam.ordinal();

            // No new exception (no exception instantiation/throw)
            System.out.println("Processed varargs result: " + localVar);
        }
    }
}

// Private enum target class (anonymous inner class feature)
private enum TargetEnum {
    VALUE;

    // Anonymous inner class (target_feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner class in TargetEnum: " + VALUE.name());
        }
    };

    // Helper method to demonstrate anonymous inner class usage
    public void executeInnerRunnable() {
        anonymousInner.run();
    }
}