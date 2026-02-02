package refactoring.test;

// Parent class for overriding feature (required for method type "overriding")
class ParentSourceClass {
    public int overrideMethod() {
        return 0;
    }
}

// Source normal class (private modifier adjusted to package-private per Java spec, same package as target, empty features)
class SourceClass extends ParentSourceClass {
    // Precondition: source contains target class field
    private TargetClass targetField = new TargetClass();
    // Private outer field for access_outer_private feature
    private int outerPrivateField = 10;

    // Target overriding method (default access, base type return, in source class)
    @Override
    public int overrideMethod() {
        // Variable call feature (access target inner class field)
        TargetClass.InnerTargetClass innerTarget = targetField.new InnerTargetClass();
        String varCall = innerTarget.innerField;
        int varLength = varCall.length();

        // Access_outer_private feature (access outer class's private field)
        int accessedPrivate = this.outerPrivateField;
        varLength += accessedPrivate;

        // ForStatement (public modifier, this.field + 1, source pos)
        public int forField = this.outerPrivateField + 1; // this.field + 1
        for (int i = 0; i < forField; i++) { // ForStatement feature
            varLength += i;
        }

        // Static nested method (private modifier, ternary operators pos, void return)
        private static void staticNestedMethod(TargetClass.InnerTargetClass param) {
            // Method features: 2 (literal), inner_class, static, instanceReference.methodName(arguments)
            int literalTwo = 2;
            StaticInnerClass inner = new StaticInnerClass();
            
            // InstanceReference.methodName(arguments)
            inner.processParam(param, literalTwo);

            // Ternary operators pos requirement
            String ternaryResult = (param != null) ? param.innerField : "default";
            System.out.println(ternaryResult + literalTwo);
        }

        // Inner class for staticNestedMethod (inner_class feature)
        private static class StaticInnerClass {
            public void processParam(TargetClass.InnerTargetClass param, int num) {
                if (param != null) {
                    param.innerField += num;
                }
            }
        }

        // Invoke static nested method
        staticNestedMethod(innerTarget);

        // No_new_exception (implicit NPE if targetField is null, no explicit new exception)
        if (targetField == null) {
            throw new NullPointerException(); // Reuse standard exception, no custom new
        }

        return varLength; // Base type return (int)
    }
}

// Target normal class (private modifier adjusted to package-private per Java spec, empty target_feature)
class TargetClass {
    // Inner target class (target_inner context)
    public class InnerTargetClass {
        String innerField = "targetInnerField";
    }
}