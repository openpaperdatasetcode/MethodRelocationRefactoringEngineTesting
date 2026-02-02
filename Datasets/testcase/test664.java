import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnn {}

// Source class: normal, default modifier, same package, local inner + member inner class
class SourceClass {
    // Private field for access_outer_private feature
    private String outerPrivateField = "privateValue";
    // Source contains target field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Member inner class (source_class feature)
    class MemberInnerSourceClass {
        // Target method: instance, Object return, private access, source_inner position
        @MethodAnn // has_annotation feature
        private Object sourceInnerMethod() {
            // Variable call (access outer private field + target field)
            String varCall = SourceClass.this.outerPrivateField;
            varCall += SourceClass.this.targetField.toString();

            // NullLiteral: numbers=1, private modifier
            private Object nullLiteral = null;
            int num = 1;
            if (nullLiteral == null && num == 1) {
                nullLiteral = varCall;
            }

            try {
                // IOException feature (trigger try-catch)
                if (varCall.isEmpty()) {
                    throw new IOException("Test IO exception");
                }
                // Call_method: others_class, private, constructor, ClassName::methodName, exception throwing pos
                OthersClass.helperMethod(SourceClass.this.targetField);
            } catch (IOException e) {
                // No new exception thrown
                varCall = "fallback: " + e.getMessage();
            }

            // Local inner class (source_class feature)
            class LocalInnerSourceClass {
                String getCombinedValue() {
                    return varCall;
                }
            }

            return new LocalInnerSourceClass().getCombinedValue();
        }
    }

    // Trigger method to invoke inner method
    public Object triggerMethod() {
        return new MemberInnerSourceClass().sourceInnerMethod();
    }
}

// Others class for call_method feature
class OthersClass {
    // Call_method: private modifier, constructor, ClassName::methodName, exception throwing pos, void return
    private static void helperMethod(TargetClass targetParam) {
        try {
            // Constructor reference + method reference (ClassName::methodName)
            TargetClass::new;
            targetParam::implementMethod;
            // Exception throwing statement (pos for call_method)
            if (targetParam == null) {
                throw new IllegalArgumentException("Target is null");
            }
        } catch (IllegalArgumentException e) {
            // No new exception propagated
        }
    }
}

// Target class: normal, protected modifier, implements + anonymous inner class features
protected class TargetClass implements Runnable {
    @Override
    public void run() {
        // Anonymous inner class (target_feature)
        new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous inner class");
            }
        }.run();
    }

    // Method for method reference in call_method
    public void implementMethod() {}
}