import java.util.List;
import java.util.ArrayList;

// Different package simulation - source interface in package com.source
package com.source;

import com.target.TargetInterface;

// Source interface: no modifier, different package from target, anonymous inner/static nested class
interface SourceInterface {
    // Static nested class (fulfills source_class feature)
    static class SourceStaticNested {
        protected int outerProtectedField = 42; // For access_outer_protected
    }

    // Target method: instance, base type return, public access, in source interface
    default int processData(TargetInterface param) {
        // Source contains target's field (per_condition)
        TargetInterface.TargetMemberInner targetField = param.new TargetMemberInner();
        
        // Variable call to target parameter
        int targetVar = targetField.targetField;
        
        // Access outer protected field (access_outer_protected)
        int protectedVal = new SourceStaticNested().outerProtectedField;
        
        // Anonymous inner class (fulfills source_class feature)
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println(targetVar + protectedVal);
            }
        };
        anonymousInner.run();
        
        // Nested instance method (protected modifier, superTypeReference.methodName(arguments) in do-while)
        do {
            List<String> nestedResult = param.nestedInstanceMethod(1); // superTypeReference.methodName(arguments)
            targetVar += nestedResult.size();
        } while (targetVar < 10);
        
        // ConditionalExpression (numbers:2, modifier:default)
        int conditionalVal = (targetVar > 5) ? 2 : 0;
        
        // No new exception thrown (no_new_exception)
        return targetVar + protectedVal + conditionalVal; // Base type return (int)
    }
}

// Target interface: default modifier, same package (com.target), javadoc/member inner class
package com.target;

/**
 * Javadoc for TargetInterface (fulfills target_feature)
 */
interface TargetInterface {
    // Member inner class (fulfills target_feature)
    class TargetMemberInner {
        int targetField = 10;
    }

    // Nested instance method (protected modifier, returns List<String>)
    default List<String> nestedInstanceMethod(int arg) {
        List<String> result = new ArrayList<>();
        result.add(String.valueOf(arg));
        return result;
    }
}