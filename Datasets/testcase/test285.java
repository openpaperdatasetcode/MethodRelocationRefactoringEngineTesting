package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Functional interface for source enum implementation
interface EnumProcessor {
    int process(String input);
}

// Others class for overriding feature
class OthersClass {
    int baseMethod(String s) {
        return s.length();
    }
}

// Source enum class (strictfp modifier, same package, implements + anonymous inner + static nested class)
strictfp enum SourceEnum implements EnumProcessor {
    SOURCE_INSTANCE;

    // Target enum field to satisfy pre-condition
    private final TargetEnum targetField = TargetEnum.TARGET_INSTANCE;

    // Private field for access_outer_private feature
    private int outerPrivateField = 3;

    // Static nested class (source feature)
    private static class SourceStaticNested {
        String nestedField = "static_nested";
    }

    // Anonymous inner class (source feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(SourceEnum.this.outerPrivateField); // uses_outer_this feature
        }
    };

    // Inner class containing the abstract method (source_inner position)
    public class SourceInnerClass {
        // Abstract method to be moved (default access, returns List<String>, source_inner position)
        public abstract List<String> moveableMethod();

        // Concrete implementation to demonstrate feature usage
        public class SourceConcrete extends SourceInnerClass {
            @Override
            public List<String> moveableMethod() {
                List<String> result = new ArrayList<>();

                // Overriding feature (private modifier, if/else pos, 3 + others_class + overriding + (parameters) -> methodBody)
                private OthersClass othersObj = new OthersClass();
                Function<String, Integer> overrideFunc = (s) -> { // (parameters) -> methodBody
                    if (s.length() == 3) { // Number 3 feature
                        return othersObj.baseMethod(s); // others_class feature
                    } else {
                        return 0;
                    }
                };

                // Synchronized statement feature
                Object lock = new Object();
                synchronized (lock) {
                    // Access_outer_private feature
                    int outerAccess = SourceEnum.this.outerPrivateField;
                    // Expression statement feature
                    String exprStmt = outerAccess + targetField.staticNested.targetField;
                    result.add(exprStmt);
                }

                // Switch statement feature
                String switchVar = targetField.name();
                switch (switchVar) {
                    case "TARGET_INSTANCE":
                        // Call_method feature (target, default modifier, normal + superTypeReference.methodName(arguments) in switch pos)
                        Object callResult = Enum.class.cast(targetField).name(); // superTypeReference.methodName(arguments)
                        result.add(callResult.toString());
                        break;
                    default:
                        result.add("default");
                }

                // Variable call feature
                String varCall = new SourceStaticNested().nestedField + overrideFunc.apply("test") + SourceEnum.this.outerPrivateField;
                result.add(varCall);

                // Uses_outer_this feature (call anonymous inner class)
                SourceEnum.this.anonymousInner.run();

                // No new exception instantiation (no_new_exception feature)
                return result;
            }
        }

        // Implementation of EnumProcessor interface (overriding base method)
        @Override
        public int process(String input) {
            return input.length();
        }
    }
}

// Target enum class (public modifier, static nested class target feature)
public enum TargetEnum {
    TARGET_INSTANCE;

    // Static nested class (target feature)
    public static class TargetStaticNested {
        String targetField = "target_static_field";
    }

    // Instance of static nested class
    public final TargetStaticNested staticNested = new TargetStaticNested();

    // Normal method for call_method feature
    public Object normalMethod(String param) {
        return param + staticNested.targetField;
    }
}