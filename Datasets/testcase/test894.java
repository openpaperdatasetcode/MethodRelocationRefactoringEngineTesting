package com.refactoring.test;

// Others class for call_method (type:others_class)
class OthersClass {
    // call_method (public modifier, normal + super.methodName(arguments), pos:exception handling statements, return Object)
    public Object callMethod(TargetClass.TargetInner param) {
        super.toString(); // super.methodName(arguments)
        param.innerField += 3; // 3 from instance method_feature
        return param.innerField;
    }
}

// Target class (normal class, default modifier, member inner class feature)
class TargetClass {
    static int staticField = 1; // For depends_on_static_field feature
    // Target inner class (target_inner)
    public class TargetInner {
        int innerField; // For per_condition (source contains this field)

        // Final method for override_violation
        public final void finalMethod() {}
    }
}

// Source class (normal class, public modifier, same package, member inner + local inner class)
public class SourceClass {
    // Member inner class (source_feature)
    class SourceMemberInner {
        // VariableDeclarationStatement inner class (pos:inner class)
        protected class VarDeclInner {
            // VariableDeclarationStatement (protected modifier, obj.field, 1, pos:inner class)
            protected int declaredVar = TargetClass.staticField; // obj.field (TargetClass.staticField) + 1
        }
    }

    // Method to be refactored (instance, TargetClass.TargetInner return, default access, source position)
    TargetClass.TargetInner moveMethod(TargetClass.TargetInner targetParam) {
        // Per_condition: source contains the field of the target (access targetParam.innerField)
        if (targetParam == null) {
            TargetClass outer = new TargetClass();
            return outer.new TargetInner();
        }

        // VariableDeclarationStatement (pos:inner class)
        SourceMemberInner.VarDeclInner varDecl = new SourceMemberInner().new VarDeclInner();
        targetParam.innerField = varDecl.declaredVar; // 1 from target_feature

        // Instance method (synchronized modifier, method_feature:3/target/instance/(parameters)->methodBody, pos:exception throwing statements)
        synchronized TargetClass.TargetInner instanceMethod(TargetClass.TargetInner param) {
            try {
                if (param.innerField < 3) {
                    throw new IllegalArgumentException("Value < 3"); // exception throwing statements pos
                }
            } catch (IllegalArgumentException e) {
                // Lambda expression: (parameters) -> methodBody
                Runnable lambda = () -> param.innerField = 3; // 3 from method_feature
                lambda.run();
            }
            return param; // return TargetClass Type
        }

        // Continue statement
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                continue; // continue statement feature
            }
            targetParam.innerField += i;
        }

        // Type declaration statement
        OthersClass others = new OthersClass();
        TargetClass.TargetInner typeDeclVar = targetParam;

        // Expression statement
        targetParam.innerField *= 2;

        // Variable call
        int varCall = targetParam.innerField; // Access target field (per_condition)
        targetParam.innerField = varCall + 1;

        // Override violation (attempt to override final method)
        class OverrideViolation extends TargetClass.TargetInner {
            @Override
            public void finalMethod() {} // Compile error (override_violation)
        }

        // Depends on static field
        targetParam.innerField += TargetClass.staticField;

        // Exception handling statements with call_method
        try {
            instanceMethod(targetParam);
        } catch (Exception e) {
            others.callMethod(targetParam); // call_method pos:exception handling statements
        }

        // Local inner class (source_feature)
        class LocalInner {
            void updateTarget(TargetClass.TargetInner inner) {
                inner.innerField += 10;
            }
        }
        new LocalInner().updateTarget(targetParam);

        // No new exception
        return targetParam;
    }
}