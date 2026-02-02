import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Super interface for source_class implements feature
interface SourceInterface {
    default void defaultMethod() {}
}

// Parent class for generic method_feature: parent_class
class ParentClass<T> {
    protected T process(T param, int num) {
        return param;
    }
}

// Source abstract class (strictfp modifier, same package, implements, member inner class, anonymous inner class)
strictfp abstract class SourceClass extends ParentClass<StrictfpTargetClass> implements SourceInterface {
    // per_condition: source contains the field of the target
    private final StrictfpTargetClass targetField = new ConcreteTargetClass();

    // Member inner class (source_class feature)
    class MemberInnerClass {
        public MemberInnerClass(String data) {
            this(); // this(arguments) feature
        }

        public MemberInnerClass() {}

        <T> StrictfpTargetClass genericProcess(T param, int num) {
            // (parameters) -> methodBody (lambda expression)
            Function<T, StrictfpTargetClass> lambda = (p) -> {
                ConcreteTargetClass target = new ConcreteTargetClass();
                target.setValue(p.toString() + "_lambda_" + num);
                return target;
            };
            return lambda.apply(param);
        }
    }

    // Anonymous inner class (source_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in SourceClass");
        }
    };

    // Method to be refactored (varargs, Object return, private access, position: source)
    // method types parameter is:Target class
    private Object targetMethod(StrictfpTargetClass... params) { // per_condition: target parameter
        List<String> result = new ArrayList<>();
        anonymousInner.run();

        // Generic method call in for loop (pos: for, 1, parent_class, generic, (parameters) -> methodBody)
        for (int i = 0; i < 1; i++) { // method_feature: 1
            MemberInnerClass innerObj = new MemberInnerClass("data_" + i); // this(arguments)
            for (StrictfpTargetClass param : params) {
                // parent_class + generic + (parameters) -> methodBody
                StrictfpTargetClass genericResult = innerObj.<String>genericProcess(param.getValue(), i);
                // Expression statement
                String processedVal = genericResult.getValue() + "_processed"; // expression statement
                // Variable call
                param.setValue(processedVal);
                result.add(processedVal);
            }
        }

        // If statement
        if (result.isEmpty()) {
            result.add(targetField.getValue() + "_default");
        } else {
            result.add("count: " + result.size());
        }

        // No new exception
        return result;
    }

    // Abstract method (required for abstract source class)
    public abstract void abstractSourceMethod();
}

/**
 * Target abstract class (strictfp modifier, javadoc, static nested class target_feature)
 * Javadoc feature compliance for 5820
 */
strictfp abstract class StrictfpTargetClass {
    // Static nested class (target_feature)
    public static class StaticNestedTargetClass {
        static {
            // Call method in Static code blocks (pos: Static code blocks)
            StrictfpTargetClass target = new ConcreteTargetClass();
            target.callMethod(); // target, final, abstract, superTypeReference.methodName(arguments)
        }

        public static String format(String s) {
            return s.toUpperCase();
        }
    }

    private String value = "target_value_5820";

    // Call method (target type, final modifier, abstract, superTypeReference.methodName(arguments), return void)
    public final void callMethod() {
        // superTypeReference.methodName(arguments)
        String formatted = StaticNestedTargetClass.format(this.getValue());
        this.setValue(formatted);
    }

    // Variable call: getter/setter
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // Abstract method (target_feature: abstract)
    public abstract void abstractTargetMethod();
}

// Concrete implementation of target abstract class
class ConcreteTargetClass extends StrictfpTargetClass {
    @Override
    public void abstractTargetMethod() {}
}