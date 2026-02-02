// Source class package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Parent class for accessor feature (parent_class)
class ParentAccessorClass {
    protected List<String> getAccessorValues(String... args) {
        List<String> values = new ArrayList<>();
        for (String arg : args) {
            values.add(arg + "_parent");
        }
        return values;
    }
}

// Source class: normal class, default modifier, different package with target, empty features
class SourceClass extends ParentAccessorClass {
    // per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();
    // Static field for depends_on_static_field feature
    private static String staticSourceField = "staticValue";

    /**
     * Method javadoc feature
     * Varargs method that processes target class and returns Object
     * @param args variable arguments list
     * @return processed Object result
     * @throws SQLException if database access error occurs
     */
    private Object moveableVarargsMethod(String... args) throws SQLException {
        // Variable call feature
        String localVar = staticSourceField;
        localVar = targetField.targetField;
        // Uses_outer_this feature
        String outerThisVal = SourceClass.this.toString();

        // Accessor feature: protected modifier, method_feature [1, parent_class, accessor, this.methodName()], pos=exception handling, return List<String>
        protected List<String> accessorMethod() {
            try {
                if (args == null) throw new SQLException("Null arguments"); // SQLException feature + exception handling pos
                return this.getAccessorValues("1", localVar); // this.methodName(arguments) + method_feature "1", "parent_class", "accessor"
            } catch (SQLException e) {
                // no_new_exception feature (no custom exceptions instantiated)
                return new ArrayList<>();
            }
        }
        List<String> accessorResult = accessorMethod();

        // While statement feature
        int counter = 0;
        while (counter < 1) { // method_feature "1"
            localVar += accessorResult.get(counter);
            counter++;
        }

        // Depends_on_static_field feature
        staticSourceField = localVar + "_updated";

        // no_new_exception feature (no custom exceptions thrown)
        return targetField;
    }
}
// Target class package
package com.refactoring.target;

// Target class: normal class, non-sealed modifier, different package with source, target_feature: anonymous inner class
non-sealed class TargetClass {
    String targetField = "targetValue";

    // Anonymous inner class (target_feature)
    void useAnonymousClass() {
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                targetField = "anonymousUpdated";
            }
        };
        anonymousRunnable.run();
    }
}