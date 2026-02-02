import java.util.Arrays;

// Parent class for super.methodName() access and access_outer_protected feature
class ParentClass {
    protected String protectedField = "ProtectedOuterData"; // For access_outer_protected
    protected int superMethod(int val) {
        return val * 2;
    }
}

// Source normal class (public modifier, same package as target, extends ParentClass)
public class SourceClass extends ParentClass {
    // Static nested class (source_class feature)
    public static class SourceStaticNestedClass {
        private static int staticVal = 2; // Matches "2" in static method_feature
    }

    // Member inner class (source_class feature)
    public class SourceMemberInnerClass {
        // Access outer protected field (access_outer_protected)
        public String getOuterProtected() {
            return SourceClass.this.protectedField;
        }
    }

    // Overridden method from ParentClass (overriding feature)
    @Override
    public int superMethod(int val) {
        return val * 3;
    }

    // Method to be moved (default access, returns TargetClass type, source position)
    public TargetClass processTarget(TargetClass targetParam) throws Exception {
        // Per_condition: method contains target parameter
        if (targetParam == null) {
            throw new IllegalArgumentException("Target parameter cannot be null"); // requires_throws
        }

        // method types parameter is:none (only target parameter, no type params)
        int localVar = 2; // Matches "2" in ConstructorInvocation target_feature

        // ConstructorInvocation (private modifier, pos: source, target_feature: this.field, 2)
        class InnerConstructorClass {
            private int field = 2; // target_feature: 2
            
            private InnerConstructorClass() {
                // this.field access (target_feature: this.field)
                this.field = localVar;
                targetParam.setCounter(this.field);
            }
            
            public int getField() {
                return field;
            }
        }
        InnerConstructorClass innerConstructor = new InnerConstructorClass(); // private ConstructorInvocation

        // Static method with specified features (private modifier, pos: array initialization, return Object)
        private static Object staticHelper(TargetClass target) {
            int val = 2; // method_feature: 2
            // method_feature: target + super.methodName() (via ParentClass instance)
            int superResult = new ParentClass().superMethod(val); // super.methodName()
            
            // pos: array initialization
            Object[] dataArray = {
                target.getData(),
                superResult,
                SourceStaticNestedClass.staticVal // method_feature: static
            };
            
            target.setData(Arrays.toString(dataArray));
            return target; // return Object
        }

        // Invoke static method in array initialization context
        Object[] initArray = {(Object) staticHelper(targetParam)};

        // Break statement
        label:
        for (int i = 0; i < 5; i++) {
            if (i == innerConstructor.getField()) {
                break label; // break statement
            }
            // Variable call to target class
            targetParam.setCounter(targetParam.getCounter() + i);
        }

        // Access outer protected field via inner class
        SourceMemberInnerClass inner = new SourceMemberInnerClass();
        targetParam.setData(targetParam.getData() + "_" + inner.getOuterProtected());

        return targetParam; // Return TargetClass type
    }
}

// Target normal class (public modifier, same package as source)
public class TargetClass {
    // Anonymous inner class (target_feature)
    private Runnable anonymousTask = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class: " + data);
        }
    };

    private String data;
    private int counter;

    // Variable call methods
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void executeTask() {
        anonymousTask.run();
    }
}