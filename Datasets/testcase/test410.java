// Source class: private normal class, same package, type parameter/anonymous inner/member inner class
private class SourceClass<T extends Number> {
    // Private field for access_outer_private feature
    private int outerPrivateField = 99;
    
    // Member inner class (fulfills source_class feature)
    class SourceMemberInner {
        Object m1() { return new SourceMemberInner(); }
        SourceMemberInner m2() { return this; }
        Object m3() { return outerPrivateField; }
    }

    // Base method for overriding
    TargetClass baseProcess(TargetClass param) {
        return param;
    }

    // Target method: overriding, TargetClass return, default access, in source class
    @Override
    TargetClass baseProcess(TargetClass param) {
        // Type declaration statement
        SourceClass<Integer> typeDecl;
        
        // Variable call to target parameter (per_condition: source contains target's field)
        String targetVar = param.targetField;
        
        // Access outer private field (access_outer_private)
        int privateVal = this.outerPrivateField;
        
        // Lambda expression containing private instance method with obj.m1().m2().m3()
        Runnable lambda = () -> {
            // Private instance method feature (obj.m1().m2().m3())
            Object result = new SourceMemberInner().m1().m2().m3();
        };
        lambda.run();
        
        // No new exception thrown (no_new_exception)
        // Anonymous inner class (fulfills source_class feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println(targetVar + privateVal);
            }
        };
        anonymousInner.run();
        
        return param; // TargetClass return type
    }

    // Private instance method (matches nested method type/modifier)
    private Object nestedInstanceMethod() {
        return new SourceMemberInner().m1().m2().m3();
    }

    // Field of target class (per_condition: source contains target's field)
    TargetClass targetFieldInstance = new TargetClass() {};
}

// Target class: abstract normal class, anonymous inner class feature
abstract class TargetClass {
    // Field used in source (per_condition)
    String targetField = "target_value";

    // Anonymous inner class (fulfills target_feature)
    Runnable targetAnonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(targetField);
        }
    };
}