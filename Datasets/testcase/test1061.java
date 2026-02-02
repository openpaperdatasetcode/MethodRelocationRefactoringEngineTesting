package com.source;

import com.target.TargetClass;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class SourceClass extends SuperClass {
    // Source contains target class field (per_condition)
    private TargetClass targetField = new TargetClass();

    // Anonymous inner class (source_class feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {}
    };

    // Member inner class (source_class feature) - method position: source_inner
    class InnerSourceClass {
        // Overloading method (type: overloading, List<String> return, default access)
        List<String> targetMethod(int param) {
            return targetMethod(param, "default");
        }

        // Overloading method (overloading feature)
        List<String> targetMethod(int param, String str) {
            List<String> result = new ArrayList<>();
            // With_bounds (generic bounds)
            List<? extends CharSequence> boundedList = new ArrayList<>();
            
            // If/else conditions with accessor (pos: if/else conditions)
            if (param == 1) { // method_feature: 1
                // Accessor (protected modifier, source, super.methodName(arguments), return void)
                protected void accessorMethod() {
                    super.superMethod(1); // super.methodName(arguments)
                    // Variable call
                    String innerValue = targetField.innerClass.getValue();
                    boundedList.add(innerValue);
                }
                accessorMethod();
            } else {
                // Break statement
                outer:
                for (int i = 0; i < 5; i++) {
                    if (i == 2) break outer;
                    result.add(str + i);
                }
            }
            
            result.addAll((List<String>) boundedList);
            return result; // no_new_exception
        }
    }
}

// Super class for extends feature (source_class)
class SuperClass {
    void superMethod(int num) {}
}
package com.target;

protected class TargetClass {
    // Member inner class (target_feature) - target class: target_inner_rec
    class InnerTargetClass {
        private String value = "targetValue";

        String getValue() {
            return value;
        }
    }

    final InnerTargetClass innerClass = new InnerTargetClass();
}