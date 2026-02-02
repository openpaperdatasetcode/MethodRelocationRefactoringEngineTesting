package com.source;

import com.target.TargetClass;
import com.refactor.SamePackageOthers;
import java.util.ArrayList;
import java.util.List;

// Same package others class for VariableDeclarationStatement pos (same_package_others)
package com.refactor;

import com.target.TargetClass;

public class SamePackageOthers {
    // VariableDeclarationStatement: private modifier, ClassName.field + 3, pos: same_package_others
    public static void varDeclLogic() {
        private String varField = TargetClass.staticField + "_3"; // ClassName.field + 3 feature
        TargetClass target = new TargetClass();
        target.field = varField;
    }
}

// Target class package
package com.target;

// Target class: abstract modifier, no extra features
public abstract class TargetClass {
    public static String staticField = "static_value"; // ClassName.field feature
    public String field; // obj.field for variable access
}

// Back to source package
package com.source;

import com.target.TargetClass;
import com.refactor.SamePackageOthers;
import java.util.List;

// Source class: normal class, default modifier, different package from target, local inner + anonymous inner class
class SourceClass {
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "private_3"; // 3 feature
    // per_condition: source contains field of target
    private TargetClass targetField = new TargetClass() {}; // Raw type + target field

    // Method: varargs, return List<String>, public access, in source class
    public List<String> processTarget(TargetClass... targetParams) {
        // Type declaration statement
        TargetClass target;
        List<String> result = new ArrayList<>();
        
        // Variable call (target parameter/field)
        target = targetParams.length > 0 ? targetParams[0] : targetField;
        
        // VariableDeclarationStatement from same_package_others (ClassName.field + 3)
        SamePackageOthers.varDeclLogic();
        
        // Super constructor invocation (via anonymous inner class of target)
        TargetClass anonymousTarget = new TargetClass() {
            {
                super(); // Super constructor invocation
                this.field = outerPrivateField + "_3"; // 3 feature
            }
        };
        
        // Access outer private field
        target.field = SourceClass.this.outerPrivateField; // access_outer_private
        
        // Raw type usage (raw_type feature)
        TargetClass rawTarget = new TargetClass() {}; // Raw type of abstract target class
        rawTarget.field = "raw_3"; // 3 feature
        
        // Local inner class feature
        class LocalInnerClass {
            public void processTarget(TargetClass t) {
                t.field = t.field + "_local_3"; // 3 feature
                result.add(t.field);
            }
        }
        new LocalInnerClass().processTarget(target);
        
        // Anonymous inner class feature
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                target.field = anonymousTarget.field + "_anonymous_3"; // 3 feature
                result.add(target.field);
            }
        };
        anonymousInner.run();
        
        // No new exception - wrap existing if any
        try {
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e); // No new exception instantiation
        }
    }
}