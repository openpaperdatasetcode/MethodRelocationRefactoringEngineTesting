package com.source;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import com.target.TargetClass;

class SourceClass {
    // Source contains target class field (per_condition)
    private TargetClass targetField;

    // Static nested classes (source_class feature)
    static class InnerSourceClass1 {}
    static class InnerSourceClass2 {}

    // Method to be refactored (instance, void return, private access, position: source)
    @SuppressWarnings("unused")
    private void targetMethod() throws SQLException { // requires_throws, SQLException feature
        // Type declaration statement
        List<String> dataList = Arrays.asList("a", "b", "c");
        
        // Labeled statement
        outerLoop:
        // EnhancedForStatement (transient modifier, super.field, pos: source, target_feature 2)
        for (transient String item : dataList) {
            // Variable call
            String targetValue = targetField.getValue();
            // Super.field reference
            String superField = super.toString();
            
            // Switch case
            switch (item) {
                case "a":
                    System.out.println("Case A: " + targetValue);
                    break outerLoop; // Use labeled break
                case "b":
                    System.out.println("Case B: " + superField);
                    break;
                default:
                    break outerLoop;
            }
        }
    }
}
package com.target;

protected class TargetClass {
    private String value = "targetFieldValue";

    public String getValue() {
        return this.value;
    }
}