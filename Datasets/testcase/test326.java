package com.refactoring.test;

enum SourceEnum {
    INSTANCE;

    protected TargetEnum targetField = TargetEnum.VALUE;

    protected Object moveMethod(String... args) {
        // For statement
        for (String arg : args) {
            // Variable call
            String var = arg;
        }

        // Constructor invocation (inner class)
        InnerClass inner = new InnerClass();

        // Super keywords
        super.toString();

        // TypeMethodReference with final modifier
        final Runnable runnable = String::length;

        // Static method in functional interface
        FunctionalInterface fi = () -> StaticFeature.staticMethod(2);

        // Used by reflection
        try {
            Class<?> clazz = Class.forName("com.refactoring.test.TargetEnum");
            return clazz.getField("innerField").get(null);
        } catch (Exception e) {
            // No new exception
            return null;
        }
    }

    private class InnerClass {
        private InnerClass() {
            // obj.field and literal 1
            int field = targetField.innerField + 1;
        }
    }

    @FunctionalInterface
    protected interface FunctionalInterface {
        protected static int staticMethod(int num) {
            // Super.methodName(arguments), literal 1, inner_class reference, static
            return super.hashCode() + 1 + new InnerClass().hashCode();
        }
    }
}

protected enum TargetEnum {
    VALUE;

    int innerField = 0;

    // Local inner class in target enum
    private void targetMethod() {
        class LocalInner {
            String value = "local";
        }
    }
}