package refactoring.test;

// Parent class for super.methodName()
class TargetParent {
    protected void parentMethod() {
        System.out.println("Parent method executed");
    }
}

// Target class: final modifier, static nested class (target_feature), same package as source
final class TargetClass extends TargetParent {
    // Static nested class (target_inner - target class of method)
    public static class TargetInner {
        public int innerField = 2;

        public void innerMethod() {
            System.out.println("Target inner method");
        }
    }
}

// Subclass for method_feature: sub_class
class TargetSubClass extends TargetClass {
    @Override
    protected void parentMethod() {
        super.parentMethod(); // Super method invocation
    }
}

// Source class: protected modifier, two member inner classes (source_feature), same package as target
protected class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass.TargetInner targetInnerField = new TargetClass.TargetInner();

    // First member inner class (source_feature)
    class FirstMemberInner {
        public String innerData = "firstInnerData";
    }

    // Second member inner class (source_feature)
    class SecondMemberInner {
        public int calculate(int value) {
            return value * 2;
        }
    }

    // Normal method feature: private modifier, 2, sub_class, normal, super.methodName(), pos=ternary operators, return_type=void
    private void helperMethod(TargetSubClass subClass) {
        // Ternary operator (pos)
        boolean flag = subClass != null ? true : false;
        // 2: use value 2 in logic
        int count = flag ? 2 : 1;
        for (int i = 0; i < count; i++) {
            subClass.parentMethod(); // super.methodName() via sub_class
        }
    }

    // Varargs method: protected access, base return type (int), method_position=source
    protected int refactorMethod(int... args) {
        int result = 0;

        // Variable call (method feature)
        result += targetInnerField.innerField;
        targetInnerField.innerMethod();

        // Expression statement (method feature)
        result += args.length > 0 ? args[0] : 0;

        // Synchronized statement (method feature)
        synchronized (this) {
            FirstMemberInner firstInner = new FirstMemberInner();
            System.out.println("Synchronized: " + firstInner.innerData);
        }

        // Switch statement (method feature)
        switch (result) {
            case 2:
                result += 2;
                break;
            case 3:
                result *= 2;
                break;
            default:
                result = 0;
        }

        // Execute helper method (sub_class instance)
        TargetSubClass subClass = new TargetSubClass();
        helperMethod(subClass);

        // Use second member inner class
        SecondMemberInner secondInner = new SecondMemberInner();
        result = secondInner.calculate(result);

        // No new exception thrown (method feature)
        return result;
    }
}