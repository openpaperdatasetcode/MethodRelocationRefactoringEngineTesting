package com.refactor.movemethod;

import java.util.ArrayList;
import java.util.List;

// Other package for diff_package_target ConstructorInvocation
package com.refactor.other;
import java.util.List;

class DiffPackageClass {
    int field = 3; // obj.field with value 3 for ConstructorInvocation target_feature
    
    private DiffPackageClass() {
        this.field = 3;
    }
    
    public static DiffPackageClass createInstance() {
        return new DiffPackageClass();
    }
}

// Back to original package
package com.refactor.movemethod;
import com.refactor.other.DiffPackageClass;
import java.util.List;

// Source normal class (default modifier, same package as target, local inner + static nested class features)
class SourceClass {
    // Static field for depends_on_static_field feature
    static String STATIC_FIELD = "static_value";
    
    // Static nested class (source_class feature)
    static class SourceStaticNested {
        String nestedValue;
    }

    // Member inner class (method_position: source_inner)
    class SourceMemberInner {
        // Annotation for has_annotation feature
        @SuppressWarnings("unused")
        // Method Javadoc feature
        /**
         * Instance method to be moved (source_inner position)
         * @param targetParam TargetClass inner class parameter (per_condition)
         * @return List of strings
         * @throws IllegalArgumentException required_throws feature
         */
        List<String> methodToRefactor(TargetClass.TargetMemberInner targetParam) throws IllegalArgumentException {
            // Variable call feature
            String var = STATIC_FIELD;
            List<String> result = new ArrayList<>();
            result.add(var);
            
            // Super constructor invocation feature (within local inner class)
            class LocalInnerForSuperCtor extends SourceStaticNested {
                LocalInnerForSuperCtor() {
                    super(); // super constructor invocation
                }
            }
            new LocalInnerForSuperCtor();
            
            // ConstructorInvocation (private, diff_package_target, obj.field=3)
            DiffPackageClass diffObj = DiffPackageClass.createInstance();
            diffObj.field = 3; // obj.field target_feature with value 3
            
            // Normal method in exception throwing statements (nested feature)
            public List<String> nestedPublicMethod() {
                if (targetParam == null) {
                    throw new IllegalArgumentException("Null target param"); // exception throwing statements pos
                }
                // 1, inner_class, normal, instanceReference.methodName(arguments) features
                TargetClass.TargetMemberInner innerInstance = new TargetClass().new TargetMemberInner();
                innerInstance.innerMethod(1); // instanceReference.methodName(arguments)
                return new ArrayList<>();
            }
            
            // Call nested method (exception throwing statements position)
            result.addAll(nestedPublicMethod());
            
            // Overload exist feature (overloaded method)
            methodToRefactor(targetParam, "overload");
            
            // Depends on static field feature
            result.add(STATIC_FIELD);
            
            // Requires throws (declared in method signature)
            if (result.isEmpty()) {
                throw new IllegalArgumentException("Empty result");
            }
            
            return result;
        }
        
        // Overload method (overload_exist feature)
        List<String> methodToRefactor(TargetClass.TargetMemberInner targetParam, String overloadParam) {
            return new ArrayList<>();
        }
    }

    // Static code block for call_method pos
    static {
        // Call method (protected, source type, new ClassName().method() target_feature)
        protected List<String> callMethod() {
            return new SourceClass().new SourceMemberInner().methodToRefactor(new TargetClass().new TargetMemberInner());
        }
        callMethod(); // Execute in static code block
    }
}

// Target normal class (default modifier, member inner class target_feature)
class TargetClass {
    // Member inner class (target_feature, target_inner for target class)
    class TargetMemberInner {
        void innerMethod(int param) {
            // inner_class feature support
        }
    }
}