package com.refactoring.movemethod;

import java.util.function.Function;

// Source record class (public, same package as target)
public record SourceClass(String privateField, int value) {
    // Member inner class (source_inner position for target method)
    public class SourceInnerClass {
        // Method to be refactored: varargs, return TargetClass, final access, source_inner position
        public final TargetClass refactorMethod(TargetClass... targetParams) {
            // Variable call
            int localVar = SourceClass.this.value;
            TargetClass result = null;
            
            // Switch case
            switch (targetParams.length) {
                case 0:
                    result = new TargetClass("default", 0); // Constructor invocation
                    break;
                case 1:
                    // Access outer private field (access_outer_private)
                    String outerPrivate = SourceClass.this.privateField;
                    // Expression statement
                    localVar += outerPrivate.length();
                    // TypeMethodReference (protected modifier, numbers=2)
                    Function<TargetClass, String> func1 = TargetClass::getId; // 1st method reference
                    Function<TargetClass, Integer> func2 = TargetClass::getValue; // 2nd method reference
                    result = new TargetClass(func1.apply(targetParams[0]), func2.apply(targetParams[0]));
                    break;
                default:
                    result = targetParams[0];
                    break;
            }
            
            // Anonymous inner class usage
            Runnable anonRunnable = new Runnable() {
                @Override
                public void run() {
                    // No new exception thrown (no_new_exception)
                    System.out.println(result);
                }
            };
            anonRunnable.run();
            
            return result;
        }
    }

    // Anonymous inner class in source class
    public Runnable getAnonymousRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(privateField);
            }
        };
    }
}

// Target record class (public, with local inner class feature)
public record TargetClass(String id, int value) {
    // Call method: final modifier, return String, pos=do-while, superTypeReference call
    public final String targetCallMethod() {
        // Local inner class (target_feature)
        class LocalInnerClass {
            String process(TargetClass tc) {
                // superTypeReference.methodName(arguments)
                Object superRef = tc.getClass().getSuperclass();
                return superRef.toString() + tc.id();
            }
        }

        LocalInnerClass localInner = new LocalInnerClass();
        String output = "";
        int count = 0;
        
        // do-while position for call method logic
        do {
            // Normal target_feature
            output = localInner.process(this);
            count++;
        } while (count < 3);
        
        return output;
    }
}