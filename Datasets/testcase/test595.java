// Source class package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Source class: public, different package from target, anonymous inner class, static nested class
public class SourceClass {
    // Per_condition: source contains target class field
    private TargetClass.TargetInnerClass targetField = new TargetClass().new TargetInnerClass();

    // Static nested class (source class feature)
    static class SourceStaticNestedClass {
        int nestedValue = 6425;
    }

    // Method to refactor: varargs, void return, private access, in source class
    // Method types parameter is: Target class
    private void methodToRefactor(TargetClass.TargetInnerClass... targetParams) {
        // Variable call (target field and varargs parameters)
        String targetValue = targetField.innerData;
        
        // Constructor invocation (target inner class)
        TargetClass.TargetInnerClass newTargetInner = new TargetClass().new TargetInnerClass();
        
        // Labeled statement
        label:
        for (int i = 0; i < 3; i++) {
            if (i == 2) break label;
            targetValue += i;
        }

        // Enhanced for statement (iterate over varargs)
        for (TargetClass.TargetInnerClass param : targetParams) { // enhanced for statement
            targetValue += param.innerData;
        }

        // While statement
        int count = 3; // numbers=3 feature
        while (count > 0) {
            targetValue += count;
            count--;
        }

        // Feature: numbers=3, modifier=default, exp=ParenthesizedExpression
        default String parenthesizedExpression() {
            // ParenthesizedExpression: (expression)
            int num = (3 * (count + 1)); // numbers=3, parenthesized expression
            return targetValue + num;
        }
        targetValue = parenthesizedExpression();

        // IOException feature (requires try-catch)
        try {
            if (targetValue.isEmpty()) {
                throw new IOException("Target value is empty");
            }
        } catch (IOException e) {
            // No new exception feature (no throw new exception)
            targetValue = "io_error: " + e.getMessage();
        }

        // Anonymous inner class (source class feature)
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(targetValue);
            }
        };
        runnable.run();

        // No new exception feature (additional catch block)
        try {
            Integer.parseInt(targetValue);
        } catch (NumberFormatException e) {
            // No throw new exception, only handle
            targetValue = "parse_error";
        }
    }
}

// Target class package (different from source)
package com.refactoring.target;

// Target class: public, anonymous inner class feature
public class TargetClass {
    // Target_inner_rec (target inner recursive)
    public class TargetInnerClass {
        String innerData = "target_inner_data_6425";

        // Anonymous inner class (target class feature)
        public void processData() {
            Runnable anonymousRunnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(innerData);
                }
            };
            anonymousRunnable.run();
        }
    }
}