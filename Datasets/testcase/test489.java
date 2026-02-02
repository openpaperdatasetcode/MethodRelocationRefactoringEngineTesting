package com.refactor.movemethod;

import java.util.Arrays;
import java.util.List;

// Source enum class (protected modifier, same package, anonymous inner + static nested class)
protected enum SourceEnum {
    // Enum constants
    VALUE1, VALUE2;

    // Per_condition: source contains target class field
    private final TargetEnum targetField = TargetEnum.TARGET_VALUE1;

    // Static nested class (source_class feature)
    public static class SourceStaticNested {
        int nestedValue = 10;

        // Instance method for access_instance_method feature
        public int getNestedValue() {
            return nestedValue;
        }
    }

    // Anonymous inner class (source_class feature)
    private final Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class");
        }
    };

    // Inner record for method_position: source_inner_rec
    public record SourceInnerRec() {
        // Abstract method (public access, base type return, source_inner_rec position)
        public abstract int refactorMethod();

        // Concrete implementation (fulfills method features)
        public int refactorMethod() {
            // Expression statement feature
            int localVar = 0;
            SourceStaticNested staticNested = new SourceStaticNested();

            // Variable call feature
            sourceAnonymous.run();
            localVar = staticNested.getNestedValue();

            // Type declaration statement feature
            class LocalTypeDeclaration {
                int processValue(int val) {
                    return val * 2;
                }
            }

            // Access instance method feature
            LocalTypeDeclaration localType = new LocalTypeDeclaration();
            localVar = localType.processValue(localVar);

            // Enhanced for statement feature
            List<Integer> numList = Arrays.asList(1, 2, 3);
            for (int num : numList) {
                localVar += num;
            }

            // No_new_exception feature (no explicit throw new Exception)
            if (targetField == null) {
                localVar = 0;
            }

            // Return base type (int)
            return localVar;
        }
    }
}

// Target enum class (default modifier, static nested class target_feature)
enum TargetEnum {
    TARGET_VALUE1, TARGET_VALUE2;

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        String nestedData = "target_nested";

        public String getNestedData() {
            return nestedData;
        }
    }

    // Call method (public modifier, target type, switch pos, instance + method reference, returns String)
    public String callMethod() {
        SourceEnum.SourceInnerRec innerRec = new SourceEnum.SourceInnerRec();
        String result = "";

        // Switch position for call_method
        switch (this) {
            case TARGET_VALUE1:
                // Instance feature + instanceReference::methodName target_feature
                result = String.valueOf(innerRec::refactorMethod);
                break;
            case TARGET_VALUE2:
                result = new TargetStaticNested().getNestedData();
                break;
            default:
                result = "default";
                break;
        }

        return result;
    }
}