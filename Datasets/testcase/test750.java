// Source class: abstract, public modifier, same package, two member inner classes (source_class feature)
public abstract class SourceClass {
    // First member inner class (source_class feature)
    public class FirstMemberInnerClass {
        public int helperMethod(int num) {
            return num * 2;
        }
    }

    // Second member inner class (source_class feature)
    public class SecondMemberInnerClass {
        public String processString(String input) {
            return input + "_processed";
        }
    }

    // Instance method to refactor: void return, private access, source position
    private void instanceMethod(TargetClass targetParam) { // per_condition: contains target parameter
        // Variable call feature
        int localVar = 1; // matches "1" in accessor method_feature
        String varStr = "sourceVar";

        // Access instance method feature
        FirstMemberInnerClass firstInner = new FirstMemberInnerClass();
        localVar = firstInner.helperMethod(localVar);
        SecondMemberInnerClass secondInner = new SecondMemberInnerClass();
        varStr = secondInner.processString(varStr);

        // Accessor feature: private modifier, 1, target, accessor, instanceReference.methodName(), expression pos, base type return
        int accessorResult = targetParam.getInnerValue(); // instanceReference.methodName(arguments) (expression pos)
        localVar += accessorResult; // "1" + target accessor value

        // No new exception feature (no 'new Exception()' statements)
        System.out.println("Result: " + localVar + ", " + varStr);
    }

    // Accessor method implementation (matches accessor feature specs)
    private int accessorMethod(TargetClass target) { // private modifier, target, accessor
        return target.getInnerValue() + 1; // "1" in method_feature
    }
}

// Target class: abstract, abstract modifier, anonymous inner class target_feature
abstract class TargetClass {
    private int innerValue = 5;

    // Anonymous inner class (target_feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class: " + innerValue);
        }
    };

    // Target class for method relocation
    public static class target {
        // Placeholder for moved instance method
        private void instanceMethod(TargetClass targetParam) {
            SourceClass source = new SourceClass() {}; // Instantiate abstract source class
            source.instanceMethod(targetParam);
        }
    }

    // Accessor method (instanceReference.methodName(arguments) for accessor feature)
    public int getInnerValue() {
        return innerValue;
    }

    // Mutator (accessor variant)
    public void setInnerValue(int value) {
        this.innerValue = value;
    }
}