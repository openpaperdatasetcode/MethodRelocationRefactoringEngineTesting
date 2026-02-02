import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

// Wrapper class to enable private modifier for SourceClass (top-level classes can't be private)
class PackageLevelWrapper {
    // Source class: normal, private modifier, same package, anonymous inner + static nested class
    private class SourceClass {
        // Static nested class (source_class feature)
        static class SourceStaticNestedClass {
            public TargetClass helperMethod(TargetClass target) {
                return target;
            }
        }

        // Anonymous inner class (source_class feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class execution");
            }
        };

        // Source inner class (method_position: source_inner)
        class SourceInnerClass {
            /**
             * Method to refactor - returns TargetClass, default access, source_inner position
             * @param targetParam TargetClass parameter (satisfies per_condition)
             * @return TargetClass instance
             * @throws SQLException if targetParam is null
             */
            TargetClass methodToRefactor(TargetClass targetParam) throws SQLException {
                // Variable call feature
                String localVar = "localVar";
                int switchVar = 3;

                // Type declaration statement feature
                List<String> stringList;

                // Enhanced for statement (enhganced for statement)
                for (String str : Arrays.asList("a", "b", "c")) {
                    localVar += str;
                }

                // Depends on inner class feature
                SourceInnerHelper innerHelper = new SourceInnerHelper();
                targetParam = innerHelper.processTarget(targetParam);

                // Switch statement (pos for overloading feature)
                switch (switchVar) {
                    case 3:
                        // Overloading feature: protected modifier, 3, others_class, overloading, this.methodName(arguments)
                        targetParam = this.overloadingMethod(3, new OthersClass(), targetParam);
                        break;
                    default:
                        break;
                }

                // SQLException feature (no new exception - uses standard SQL exception)
                if (targetParam == null) {
                    throw new SQLException("Target parameter cannot be null");
                }

                // No new exception feature (no custom exception instantiation)
                return targetParam;
            }

            // Overloading method (matches type/modifier/method_feature/pos/return_type)
            protected TargetClass overloadingMethod(int num, OthersClass others, TargetClass target) { // 3, others_class, overloading
                // this.methodName(arguments)
                return this.overloadingMethod(num, target); // Overloading call
            }

            // Overloading method (overload variant)
            protected TargetClass overloadingMethod(int num, TargetClass target) {
                return target;
            }

            // Inner helper class for depends_on_inner_class feature
            private class SourceInnerHelper {
                public TargetClass processTarget(TargetClass target) {
                    return target;
                }
            }
        }
    }

    // Others class for overloading method feature
    class OthersClass {
        public void othersMethod() {}
    }
}

/**
 * TargetClass - abstract modifier, javadoc + member inner class target_feature
 * This class serves as the target for Move Method refactoring
 */
abstract class TargetClass {
    // Member inner class (target_feature)
    public class TargetInnerClass {
        public void innerMethod() {}
    }

    // Target class for method relocation
    public static class target {
        // Placeholder for moved method
        TargetClass methodToRefactor(TargetClass targetParam) throws SQLException {
            PackageLevelWrapper wrapper = new PackageLevelWrapper();
            PackageLevelWrapper.SourceClass source = wrapper.new SourceClass();
            PackageLevelWrapper.SourceClass.SourceInnerClass inner = source.new SourceInnerClass();
            return inner.methodToRefactor(targetParam);
        }
    }
}