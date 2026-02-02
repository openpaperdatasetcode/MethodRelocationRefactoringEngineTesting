package com.refactor.movemethod;

import java.util.Objects;

// Source normal class (default modifier, same package as target, type parameter + anonymous + local inner class)
class SourceClass<T extends CharSequence> {
    // Member inner class (method_position: source_inner_rec)
    class SourceInnerClass {
        // Instance method to refactor (protected access, returns TargetClass type, has target parameter - per_condition)
        protected TargetClass methodToRefactor(TargetClass.TargetInner targetParam) {
            // Variable call feature
            String localVar = sourceInstanceMethod();
            int switchVar = localVar.length();

            // Switch case feature
            switch (switchVar) {
                case 1:
                    localVar = "case1";
                    break;
                default:
                    localVar = "default";
                    break;
            }

            // Throw statement feature (no_new_exception: no custom new exception, only standard)
            if (targetParam == null) {
                throw new IllegalArgumentException("Target parameter cannot be null");
            }

            // Access instance method feature
            String instanceMethodResult = SourceClass.this.sourceInstanceMethod();
            localVar += instanceMethodResult;

            // Constructor in exception handling statements (specified method_feature)
            Object constructorResult = null;
            try {
                // obj.m1().m2().m3() + 1 + others_class + constructor features
                OthersClass obj = new OthersClass();
                constructorResult = obj.m1().m2().m3(1);
            } catch (Exception e) {
                // Exception handling statements position for constructor
                constructorResult = new OthersClass(); // Default constructor call
            }

            // No_new_exception feature (no explicit 'new Exception()' thrown)
            if (constructorResult == null) {
                return new TargetClass().new TargetInner("fallback");
            }

            // Return TargetClass Type (target_inner)
            return new TargetClass(targetParam.getValue());
        }
    }

    // Instance method for access_instance_method feature
    private String sourceInstanceMethod() {
        // Local inner class (source_class feature)
        class LocalInnerClass {
            String getValue() {
                return "local_inner_value";
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        
        // Anonymous inner class (source_class feature)
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println(localInner.getValue());
            }
        };
        anonymous.run();
        
        return localInner.getValue();
    }
}

// Target normal class (protected modifier, local inner class target_feature)
protected class TargetClass {
    private final String value;

    // Default constructor (supports constructor feature)
    TargetClass() {
        this.value = "default";
        // Local inner class (target_feature)
        class TargetLocalInner {
            String processValue() {
                return value + "_processed";
            }
        }
        TargetLocalInner localInner = new TargetLocalInner();
        this.value = localInner.processValue();
    }

    // Constructor with parameter
    TargetClass(String value) {
        this.value = value;
    }

    // Target_inner (target class inner component)
    class TargetInner {
        private final String innerValue;

        TargetInner(String innerValue) {
            this.innerValue = innerValue;
        }

        String getValue() {
            return innerValue;
        }
    }

    // Instance method (supports access_instance_method)
    public String getTargetValue() {
        return this.value;
    }
}

// Others_class (supports "others_class" method_feature)
class OthersClass {
    OthersClass m1() {
        return this;
    }

    OthersClass m2() {
        return this;
    }

    Object m3(int num) {
        return new Object();
    }
}