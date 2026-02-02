package refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Others class for call_method (others_class type)
class OthersClass {
    // Protected generic method with this.methodName(arguments) feature (call_method)
    protected <T> Object genericMethod(T param) {
        return this.processParam(param);
    }

    private <T> Object processParam(T param) {
        return param.toString();
    }
}

// Target interface for implements feature
interface TargetInterface {
    void process();
}

// Source class: abstract, protected modifier, same package, local inner + static nested classes
protected abstract class SourceClass extends OthersClass {
    // Static field for depends_on_static_field feature
    protected static int staticField = 0;

    // Static nested class (source feature)
    protected static class SourceStaticNested {
        int nestedValue;
    }

    // Member inner class (method_position: source_inner)
    protected class SourceInnerClass {
        // Method to be refactored: protected normal, List<String> return, source_inner position
        protected List<String> refactorMethod(TargetClass.TargetInnerClass targetParam) { // per_condition: contains target parameter
            List<String> result = new ArrayList<>();
            
            // Variable call (target inner class field)
            targetParam.innerValue = 10;
            // Depends on static field
            staticField += targetParam.innerValue;

            // Labeled statement
            loopLabel:
            while (staticField < 50) { // While statement
                // Expression statement
                targetParam.innerValue++;
                staticField++;

                // For statement with call_method (others_class, protected, generic, this.methodName(arguments))
                for (int i = 0; i < 3; i++) {
                    Object callResult = OthersClass.this.genericMethod(targetParam.innerValue);
                    result.add(callResult.toString());
                }

                if (staticField >= 50) {
                    break loopLabel;
                }
            }

            // Local inner class (source feature)
            class LocalInnerClass {
                void updateResult() {
                    result.add("local:" + targetParam.innerValue);
                }
            }
            new LocalInnerClass().updateResult();

            // Override violation (invalid override attempt)
            class InvalidOverride extends TargetClass {
                @Override
                public final void process() { // Compile error: final method override
                    // No new exception
                }
            }

            // No new exception, return List<String>
            return result;
        }
    }
}

// Target class: abstract, protected modifier, implements + member inner class features
protected abstract class TargetClass implements TargetInterface {
    // Member inner class (target_inner - target for method)
    protected class TargetInnerClass {
        int innerValue;
    }

    @Override
    public final void process() { // Final method for override_violation
        TargetInnerClass inner = new TargetInnerClass();
        inner.innerValue = 0;
    }
}