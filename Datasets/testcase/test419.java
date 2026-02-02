import java.util.function.Function;

// Source class: default modifier, same package, two local inner classes
class SourceClass extends ParentClass {
    // Target class field (fulfills per_condition: source contains target's field)
    TargetClass<String> targetField = new TargetClass<>("source_target_field");

    // Base method for overriding
    @Override
    protected int processData(TargetClass<String>.TargetInnerClass.TargetInnerRecClass param) {
        // Super constructor invocation
        super();

        // Variable call to target parameter
        String targetVar = param.getRecValue();
        
        // Try statement (requires_try_catch feature)
        int result = 0;
        try {
            // SwitchExpression (numbers:1, modifier:public)
            result = switch (targetVar.length()) {
                case 1 -> 1; // numbers:1
                default -> targetVar.length();
            };
            
            // First local inner class (fulfills source_class feature)
            class LocalInnerOne {
                int multiply(int val) {
                    return val * 2;
                }
            }
            
            // Second local inner class (fulfills source_class feature twice)
            class LocalInnerTwo {
                int add(int val) {
                    return val + 3;
                }
            }
            
            LocalInnerOne innerOne = new LocalInnerOne();
            LocalInnerTwo innerTwo = new LocalInnerTwo();
            result = innerTwo.add(innerOne.multiply(result));
        } catch (NullPointerException e) {
            // Requires_try_catch (handle potential NPE)
            result = -1;
        }
        
        return result; // Base type return (int)
    }
}

// Parent class for super constructor invocation and call_method (parent_class type)
class ParentClass {
    // Call method: public modifier, normal, obj.m1().m2().m3() in functional interface
    public String invokeSourceMethod() {
        SourceClass source = new SourceClass();
        TargetClass<String> target = source.targetField;
        TargetClass<String>.TargetInnerClass inner = target.new TargetInnerClass();
        TargetClass<String>.TargetInnerClass.TargetInnerRecClass rec = inner.new TargetInnerRecClass();
        
        // Functional interface position (pos: functional interfaces)
        Function<TargetClass<String>.TargetInnerClass.TargetInnerRecClass, Integer> func = (param) -> {
            // obj.m1().m2().m3() (target_feature)
            String chainVal = param.m1().m2().m3();
            return chainVal.length();
        };
        
        int processedVal = source.processData(rec);
        func.apply(rec);
        return "Processed value: " + processedVal; // String return type
    }
}

/**
 * TargetClass - Javadoc (fulfills target_feature)
 * @param <T> Type parameter (fulfills target_feature)
 */
public class TargetClass<T> {
    private T value;

    public TargetClass(T value) {
        this.value = value;
    }

    // Member inner class (first level)
    public class TargetInnerClass {
        // Target inner recursive class (target_inner_rec)
        public class TargetInnerRecClass {
            private T recValue = (T) "rec_value";

            // Methods for obj.m1().m2().m3() chain
            public TargetInnerRecClass m1() { return this; }
            public TargetInnerRecClass m2() { return this; }
            public String m3() { return recValue.toString(); }

            public T getRecValue() {
                return recValue;
            }
        }
    }

    // Anonymous inner class (fulfills target_feature)
    Runnable targetAnonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(value);
        }
    };
}