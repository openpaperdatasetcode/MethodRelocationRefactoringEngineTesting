package refactoring.test;

import java.util.List;

// Source class: enum, default modifier, same package as target, features: static nested class, member inner class
enum SourceEnum {
    INSTANCE;

    // Per condition: source contains target class field
    private TargetEnum targetField = TargetEnum.VALUE_1;

    // Protected field for access_outer_protected feature
    protected String protectedOuterField = "source-protected-data";

    // Static nested class (source_class feature)
    static class SourceStaticNested {
        int nestedValue = 0;
    }

    // Member inner class (source_inner_rec - method position host)
    class SourceInnerRecClass {
        /**
         * Javadoc for varargs method (method javadoc feature)
         * @return Object result
         */
        // Method to be refactored: varargs, return Object, protected access, position source_inner_rec
        // method types parameter is:none (no typed parameters beyond varargs)
        protected Object moveMethod(Object... args) {
            // Variable call feature
            int localVar = 0;
            // Access outer protected feature
            String outerProtected = SourceEnum.this.protectedOuterField;

            // Raw type feature (List without generic type)
            List rawList = new java.util.ArrayList();
            rawList.add(localVar);

            // Expression statement feature
            String exprStmt = outerProtected + " - " + localVar;
            rawList.add(exprStmt);

            // Throw statement feature (standard exception, no new custom exception)
            if (args == null) {
                throw new IllegalArgumentException("Arguments cannot be null");
            }

            // Enhanced for loop with varargs
            for (Object arg : args) {
                localVar++;
                rawList.add(arg);
            }

            // No new exception thrown (no_new_exception feature)
            return targetField.getInnerRecClass().process(rawList);
        }
    }
}

/**
 * Javadoc for TargetEnum (target_feature: javadoc)
 * Enum class for move method refactoring target
 */
// Target class: enum, private modifier, same package, target_feature: javadoc, local inner class
private enum TargetEnum {
    VALUE_1, VALUE_2;

    public Object getInnerRecClass() {
        // Local inner class (target_feature)
        class TargetInnerRecClass {
            Object process(List list) {
                StringBuilder sb = new StringBuilder();
                for (Object obj : list) {
                    sb.append(obj).append(",");
                }
                return sb.toString();
            }
        }
        TargetInnerRecClass innerRec = new TargetInnerRecClass();
        return innerRec;
    }
}