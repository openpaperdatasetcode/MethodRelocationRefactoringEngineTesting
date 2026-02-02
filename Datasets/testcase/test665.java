import java.util.ArrayList;
import java.util.List;

// Source class: abstract, default modifier, same package, anonymous inner + local inner class
abstract class SourceClass {
    // Protected field for access_outer_protected feature
    protected int outerProtectedField = 1;
    // Source contains target field (per_condition)
    protected TargetClass<String> targetField = new TargetClass<>() {};

    // Member inner class for source_inner_rec (recursive inner method)
    class InnerSourceClass {
        // Target method: normal, base type return, default access, source_inner_rec position
        int sourceInnerRecMethod(int count) {
            // Variable call (access outer protected field + target field)
            String varCall = String.valueOf(SourceClass.this.outerProtectedField);
            varCall += SourceClass.this.targetField.toString();

            // CreationReference: numbers=1, default modifier
            default Runnable creationRef = TargetClass::new;
            int num = 1;

            // Enhanced for statement (enhganced for statement)
            List<Integer> numList = new ArrayList<>();
            numList.add(1);
            for (int n : numList) {
                varCall += n;
            }

            // Switch case feature
            switch (num) {
                case 1:
                    varCall = "case_1: " + varCall;
                    break;
                default:
                    varCall = "default: " + varCall;
                    break;
            }

            // Requires try-catch block
            try {
                // Throw statement feature
                if (count > 3) {
                    throw new IllegalArgumentException("Count exceeds limit: " + varCall);
                }
                // Call_method: others_class, default modifier, normal, instanceReference::methodName, if/else pos
                int callResult;
                if (num == 1) {
                    callResult = OthersClass.helperMethod(SourceClass.this.targetField);
                } else {
                    callResult = OthersClass.helperMethod(SourceClass.this.targetField);
                }
                varCall += callResult;
            } catch (IllegalArgumentException e) {
                // Handle exception (requires_try_catch)
                varCall = "error: " + e.getMessage();
                return 0;
            }

            // Local inner class (source_class feature)
            class LocalInnerSourceClass {
                int processTarget() {
                    return SourceClass.this.outerProtectedField + num;
                }
            }

            // Anonymous inner class (source_class feature)
            new Runnable() {
                @Override
                public void run() {
                    System.out.println(varCall);
                }
            }.run();

            // Recursive call (source_inner_rec)
            if (count < 3) {
                return sourceInnerRecMethod(count + 1);
            }

            // Return base type
            return new LocalInnerSourceClass().processTarget();
        }
    }

    // Trigger method to invoke inner recursive method
    public int triggerMethod() {
        return new InnerSourceClass().sourceInnerRecMethod(1);
    }
}

// Others class for call_method feature
class OthersClass {
    // Call_method: default modifier, normal, instanceReference::methodName, if/else pos, int return
    static int helperMethod(TargetClass<String> targetParam) {
        TargetClass<String> instanceRef = targetParam;
        return instanceRef::getTypeParam; // Instance reference method reference
    }
}

// Target class: abstract, no modifier, type parameter + anonymous inner class features
abstract class TargetClass<T> {
    private T typeParam;

    public TargetClass() {
        // Anonymous inner class (target_feature)
        new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous inner class");
            }
        }.run();
    }

    // Method for method reference in call_method
    public int getTypeParam() {
        return 1;
    }
}