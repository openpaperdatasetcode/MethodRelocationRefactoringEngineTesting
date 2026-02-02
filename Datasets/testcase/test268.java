package com.refactoring.movemethod;

// Sealed generic source class (same package as target, local/anonymous inner class features)
public sealed class SourceClass<T> permits SourceSubClass<T> {
    // Public ForStatement class (same_package_target position, target_feature: ClassName.field, 1)
    public class ForStatement {
        // ClassName.field feature
        public static final int STATIC_FIELD = 1; // Feature "1" (numeric literal)

        public void execute(T[] elements) {
            // ForStatement implementation
            for (int i = 0; i < elements.length; i++) {
                System.out.println(ForStatement.STATIC_FIELD + ": " + elements[i]);
            }
        }
    }

    // Instance method to refactor (protected access, void return, target parameter)
    protected void moveTargetMethod(TargetClass<T> targetParam) {
        // Per_condition: method contains target parameter
        if (targetParam == null) {
            return;
        }

        // StringLiteral with number 3 (private modifier, exp: StringLiteral)
        private final String stringLiteral = "literal_" + 3;

        // Type declaration & variable call feature (ForStatement)
        ForStatement forStmt = new ForStatement();
        T[] targetArray = targetParam.getArrayData();
        forStmt.execute(targetArray);

        // Instance method (default modifier, pos: property assignment, return TargetClass type)
        TargetClass<T> instanceMethod(TargetClass<T> target) {
            // Method feature: 1 (numeric literal)
            int count = 1;
            // Method feature: target (reference target class)
            target.setProperty(stringLiteral + "_" + count);
            // Method feature: instance (instance reference)
            // Method feature: superTypeReference.methodName(arguments)
            SourceClass<T> superRef = SourceClass.this;
            superRef.toString(); // Super type method call

            // Property assignment position
            target.property = target.getData();
            return target;
        }

        // Call instance method (property assignment)
        TargetClass<T> processedTarget = instanceMethod(targetParam);

        // Array initialization position for call_method
        TargetClass<T>[] targetArrayInit = new TargetClass[]{processedTarget};
        // Call target's private method (ClassName.methodName(arguments))
        TargetClass.callPrivateMethod(targetArrayInit[0]);

        // Local inner class (source class feature)
        class LocalInnerClass {
            private T localData = processedTarget.getData();
            
            public T getData() {
                return localData;
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        System.out.println(localInner.getData());

        // Anonymous inner class (source class feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner: " + localInner.getData());
            }
        };
        anonymousRunnable.run();
    }
}

// Permitted subclass for sealed source class
non-sealed class SourceSubClass<T> extends SourceClass<T> {}

// Non-sealed generic target class (same package as source, local inner class feature)
non-sealed class TargetClass<T> {
    // Property for property assignment position
    public T property;

    private T[] arrayData;
    private T data;

    public TargetClass(T[] arrayData, T data) {
        this.arrayData = arrayData;
        this.data = data;
    }

    // Private call_method (type: target, modifier: private, target_feature: instance, ClassName.methodName)
    private static <T> void callPrivateMethod(TargetClass<T> target) {
        // Target_feature: instance (instance reference)
        target.setData((T) target.property);
        // Target_feature: ClassName.methodName(arguments)
        TargetClass.validate(target);
    }

    // Static helper method for ClassName.methodName feature
    private static <T> void validate(TargetClass<T> target) {
        if (target.property == null) {
            throw new IllegalArgumentException("Property is null");
        }
    }

    // Local inner class (target_feature)
    public void methodWithLocalInner() {
        class LocalInnerClass {
            private T innerData = TargetClass.this.data;
            
            public T getInnerData() {
                return innerData;
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        this.property = localInner.getInnerData();
    }

    // Accessor methods
    public T[] getArrayData() {
        return arrayData;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setProperty(T property) {
        this.property = property;
    }
}