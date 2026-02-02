import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

// Annotation for constructor feature (attribute values of annotations pos)
@Retention(RetentionPolicy.RUNTIME)
@interface OverrideAnno {
    String constructorRef() default "TargetClass(targetParam)"; // constructor feature pos: annotation attribute
}

// Permitted subclass for source_class "permits" feature (Java 17+)
class SourcePermittedSubclass extends SourceClass<String> {}

// Source class: abstract, default modifier, same package, type parameter + permits + local inner + static nested class
abstract class SourceClass<T extends CharSequence> permits SourcePermittedSubclass {
    // Private outer field for access_outer_private feature
    private T outerPrivateField = (T) "sourcePrivate";

    // Static nested class (source_class feature)
    static class SourceStaticNestedClass<U extends Number & Comparable<U>> {
        // With bounds feature helper
        public static U processNumber(U num) {
            return num;
        }
    }

    // Overriding method to refactor: Object return, default access, source position
    @OverrideAnno(constructorRef = "new TargetClass(3)") // constructor feature pos: annotation attribute
    @Override
    public Object toString() {
        // Variable call feature
        T localVar = (T) "baseValue";
        int loopCount = 3; // numbers: 3 feature
        volatile char charLiteral = '3'; // volatile modifier, CharacterLiteral, numbers:3

        // Access outer private field (access_outer_private feature)
        localVar = (T) (localVar + "_" + this.outerPrivateField);

        // Try-catch for IOException feature
        try {
            if (localVar.length() > 0) {
                throw new IOException("Simulated IO exception"); // IOException feature
            }
        } catch (IOException e) {
            // No new exception feature (catch existing, no new Exception instantiation)
            localVar = (T) (localVar + "_ioError");
        }

        // While statement feature
        int count = 0;
        while (count < loopCount) {
            // Switch case feature
            switch (count) {
                case 0:
                    localVar = (T) (localVar + "_case0_" + charLiteral);
                    break;
                case 3: // numbers:3
                    localVar = (T) (localVar + "_case3_" + charLiteral);
                    break;
                default:
                    break;
            }
            count++;
        }

        // Local inner class (source_class feature)
        class LocalInnerClass {
            // Access instance method feature
            public T instanceMethod(T input) {
                return (T) (input + "_localInner");
            }

            // Overload exist feature (overloaded methods)
            public int overloadMethod(int num) {
                return num * 3; // numbers:3
            }

            public int overloadMethod(int num, T str) { // overload variant
                return num + str.length();
            }
        }

        // Constructor invocation feature
        LocalInnerClass localInner = new LocalInnerClass();
        localVar = localInner.instanceMethod(localVar); // Access instance method feature

        // Overload exist feature usage
        int overloadResult = localInner.overloadMethod(loopCount);
        overloadResult += localInner.overloadMethod(loopCount, localVar);
        localVar = (T) (localVar + "_overload_" + overloadResult);

        // With bounds feature (generic with bounds)
        SourceStaticNestedClass<Integer> boundedNested = new SourceStaticNestedClass<>();
        Integer boundedNum = boundedNested.processNumber(loopCount);
        localVar = (T) (localVar + "_bounded_" + boundedNum);

        // Super constructor invocation feature
        class NestedClass extends SourceClass<T> {
            public NestedClass() {
                super(); // super constructor invocation
            }
        }
        new NestedClass();

        // Constructor feature: default modifier, 3, target, constructor, super.methodName(), annotation attr pos, TargetClass Type
        TargetClass<T> targetParam = new TargetClass<>(localVar); // constructor invocation (numbers:3, target)
        Object constructorResult = targetParam.superMethod(loopCount); // super.methodName(arguments)

        // No new exception feature (no 'new Exception()' statements)
        return localVar + "_final_" + constructorResult;
    }

    // Method with local inner class (source_class feature)
    public void methodWithLocalInner(TargetClass<T> targetParam) { // per_condition: contains target parameter
        class LocalInnerClass {
            public void processTarget(TargetClass<T> target) {
                System.out.println(target.getInnerValue());
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        localInner.processTarget(targetParam);
    }
}

// Target class: abstract, public modifier, member inner class target_feature
public abstract class TargetClass<V> {
    private V innerValue;

    public TargetClass(V value) { // Constructor for constructor feature
        this.innerValue = value;
    }

    // Member inner class (target_feature)
    public class TargetMemberInnerClass {
        public V processValue(V input) {
            return (V) (input + "_targetInner");
        }
    }

    // Target inner recursive structure for method relocation
    public static class target_inner_rec {
        // Placeholder for moved overriding method
        @OverrideAnno
        @Override
        public String toString() {
            SourceClass<String> source = new SourcePermittedSubclass();
            return (String) source.toString();
        }
    }

    // super.methodName(arguments) for constructor feature
    public Object superMethod(int num) {
        return num + "_superMethod_" + this.innerValue;
    }

    public V getInnerValue() {
        return innerValue;
    }
}