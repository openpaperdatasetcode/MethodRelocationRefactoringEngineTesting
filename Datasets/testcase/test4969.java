package test;

public enum SourceEnum {
    VALUE1,
    VALUE2;

    // Field of target class (per_condition)
    private TargetEnum targetParam;

    SourceEnum() {
        this.targetParam = TargetEnum.INSTANCE1;
    }

    // Local inner class (source_feature)
    public void methodWithLocalClass() {
        class LocalInner {
            // Overriding method in inner class (method_position: source_inner_rec)
            private void overrideMethod(TargetEnum param) {
                // Constructor invocation
                TargetEnum newInstance = TargetEnum.INSTANCE1;
                
                // Private constructor invocation with this.field
                if (param != null) {
                    param.setField("modified");
                }
                
                // Type declaration statement
                TargetEnum localTarget;
                
                // Variable call
                localTarget = targetParam;
                
                // Call to super.methodName()
                super.toString();
                
                // Instance method calls (2 required)
                TargetEnum result1 = localTarget.protectedMethod1();
                TargetEnum result2 = localTarget.protectedMethod2(TargetEnum.INSTANCE2);
                
                // Expression statement
                result1.getField();
            }
        }
    }

    // Anonymous inner class (source_feature)
    public Runnable createAnonymousClass() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class in SourceEnum");
            }
        };
    }

    // Final inner class method call in for loop (call_method)
    public void processTargets() {
        final class InnerProcessor {
            @Override
            public String toString() { // Overriding
                return "InnerProcessor";
            }

            TargetEnum process(TargetEnum target) {
                return TargetEnum.INSTANCE1; // ClassName.methodName(arguments)
            }
        }

        InnerProcessor processor = new InnerProcessor();
        for (TargetEnum target : TargetEnum.values()) {
            processor.process(target);
        }
    }
}
    
package test;

public enum TargetEnum {
    INSTANCE1,
    INSTANCE2;

    private String field;

    TargetEnum() {
        this.field = "default";
    }

    // Protected instance methods returning TargetEnum type (2 required)
    protected TargetEnum protectedMethod1() {
        return INSTANCE1;
    }

    protected TargetEnum protectedMethod2(TargetEnum param) {
        super.toString(); // super.methodName()
        return param;
    }

    // Method with local inner class (target_feature)
    public void methodWithLocalClass() {
        class LocalInner {
            void doSomething() {
                System.out.println("Local inner class in TargetEnum");
            }
        }
        new LocalInner().doSomething();
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
    