import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Super class for super.field feature
class SuperTargetClass<U> {
    protected String superField = "super_target_field_5811";
}

// Source generic class (default modifier, same package, member inner class, anonymous inner class)
class SourceClass<T extends CharSequence> {
    // Private outer field for access_outer_private
    private String outerPrivateField = "source_private_5811";

    // Member inner class (source_class feature)
    class MemberInnerClass<U> {
        void helper(U val) {
            System.out.println(val);
        }
    }

    // Anonymous inner class (source_class feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println(SourceClass.this.outerPrivateField); // uses_outer_this
        }
    };

    // Inner record class (method_position: source_inner_rec)
    record InnerSourceRecord(String data) {
        // Method to be refactored (varargs, TargetClass return, protected access)
        // method types parameter is:base type (int)
        protected TargetClass<String> targetMethod(int baseParam, TargetClass<String>.InnerClass... innerParams) { // per_condition: target parameter (target_inner)
            // TryStatement (private modifier, super.field, 1, pos: same_package_target)
            private void tryBlock(TargetClass<String>.InnerClass param) {
                TargetClass<String> outerTarget = param.getOuterTarget();
                String superField = outerTarget.superField; // super.field
                int num = 1; // target_feature: 1
                try {
                    param.setValue(superField + "_" + num);
                } catch (Exception e) {
                    param.setValue("error_" + e.getMessage());
                }
            }

            // Static method call in exception throwing statements (pos: exception throwing statements)
            try {
                for (TargetClass<String>.InnerClass innerParam : innerParams) {
                    // access_outer_private & uses_outer_this
                    String privateVal = SourceClass.this.outerPrivateField; // access_outer_private + uses_outer_this
                    innerParam.setValue(innerParam.getValue() + "_" + privateVal);
                    
                    tryBlock(innerParam);
                    // 3, inner_class, static, ClassName.methodName(arguments)
                    MemberInnerClass<String> innerObj = new MemberInnerClass<>();
                    StaticHelper.staticMethod(3, innerObj); // ClassName.methodName(arguments)
                    
                    // numbers:1, modifier:public, exp:SuperMethodInvocation
                    public String superCall() {
                        return super.toString(); // SuperMethodInvocation (numbers:1)
                    }
                    innerParam.setValue(innerParam.getValue() + "_" + superCall() + "_1");
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid inner param", e); // exception throwing statements
            }

            // Used by reflection
            TargetClass<String> resultTarget = null;
            try {
                Method method = InnerSourceRecord.class.getDeclaredMethod("targetMethod", int.class, TargetClass.InnerClass[].class);
                method.setAccessible(true);
                resultTarget = (TargetClass<String>) method.invoke(this, baseParam, (Object) innerParams);
            } catch (Exception e) {
                resultTarget = new TargetClass<>("reflection_error");
            }

            // Call method in array initialization (pos: array initialization)
            Function<TargetClass<String>.InnerClass, List<String>> func = TargetClass.InnerClass::callMethod; // instanceReference::methodName
            List<String>[] arr = new List[] {func.apply(innerParams[0])}; // array initialization (constructor feature)

            // Variable call
            resultTarget.getInnerClass().setValue(arr[0].get(0));

            // Return statement
            return resultTarget;
        }

        // Static helper class for static method feature
        static class StaticHelper {
            protected static void staticMethod(int num, MemberInnerClass<String> inner) { // 3, inner_class, static
                if (num != 3) throw new IllegalArgumentException("Num must be 3");
                inner.helper("static_method_" + num);
            }
        }
    }
}

// Target generic class (public modifier, anonymous inner class target_feature)
public class TargetClass<T> extends SuperTargetClass<T> {
    // Anonymous inner class (target_feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in TargetClass: " + superField);
        }
    };

    // Inner class (target_inner)
    public class InnerClass {
        private T value;
        private final TargetClass<T> outerTarget;

        public InnerClass(TargetClass<T> outerTarget) {
            this.outerTarget = outerTarget;
            anonymousInner.run(); // trigger anonymous inner class
        }

        // Call method (target type, private modifier, constructor, instanceReference::methodName, return List<String>)
        private List<String> callMethod() {
            List<String> list = new ArrayList<>();
            list.add(this.value.toString());
            list.add(outerTarget.superField);
            return list;
        }

        // Variable call: getter/setter
        public T getValue() { return value; }
        public void setValue(T value) { this.value = value; }
        public TargetClass<T> getOuterTarget() { return outerTarget; }
    }

    private T value;
    private final InnerClass innerClass;

    // Constructor
    public TargetClass(T value) {
        this.value = value;
        this.innerClass = new InnerClass(this); // constructor feature
    }

    // Variable call: getter for inner class
    public InnerClass getInnerClass() { return innerClass; }
    public T getValue() { return value; }
}