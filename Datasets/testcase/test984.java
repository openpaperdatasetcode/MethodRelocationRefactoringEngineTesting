// Target class package (different from source)
package com.refactoring.target;

import java.util.ArrayList;
import java.util.List;

// Parent class for target_class extends feature
class TargetParentClass {
    protected Object parentMethod() {
        return "parent_" + 1; // 1 from method_feature
    }
}

// Target class (normal class, abstract modifier, extends + local inner class)
public abstract class TargetClass extends TargetParentClass {
    String targetField; // For per_condition (source contains this field)
    public static int staticField = 1; // For depends_on_static_field

    // Local inner class (target_feature)
    public void targetMethod() {
        class TargetLocalInner {
            Object getField() { // accessor method for method_feature
                return TargetClass.this.targetField + "_local_inner_" + 1;
            }
        }
        new TargetLocalInner().getField();
    }

    // Abstract method (required for abstract class)
    public abstract int abstractMethod();
}

// Source class package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.util.List;
import java.util.ArrayList;

// Source class (normal class, public modifier, different package, type parameter feature)
public class SourceClass<T extends CharSequence> { // with_bounds (T extends CharSequence)
    // Accessor method (default modifier, method_feature:1/inner_class/accessor/super.methodName(), pos=collection operations, return Object)
    <U> Object accessorMethod(TargetClass target, List<U> collection) { // pos=collection operations
        // super.methodName() (call TargetParentClass method)
        Object superResult = target.parentMethod();
        collection.add((U) (target.targetField + "_accessor_" + 1)); // 1 from method_feature
        return superResult; // inner_class feature (relies on TargetLocalInner)
    }

    // Method to be refactored (instance, base type return, public access, source position)
    public int moveMethod(TargetClass targetParam) {
        // Per_condition: source contains the field of the target (access targetParam.targetField)
        if (targetParam == null) {
            return 0;
        }

        // Constructor invocation (anonymous subclass of abstract TargetClass)
        TargetClass targetInstance = new TargetClass() {
            @Override
            public int abstractMethod() {
                return 1; // 1 from method_feature
            }
        };

        // Empty statement
        ;

        // Type declaration statement
        T typeParam = (T) targetParam.targetField; // type parameter feature
        List<String> collection = new ArrayList<>(); // for collection operations

        // Accessor method call (pos=collection operations)
        Object accessorResult = this.accessorMethod(targetParam, collection);

        // Variable call
        String varCall = targetParam.targetField; // access target field (per_condition)
        targetParam.targetField = varCall + "_var_modified_" + 1;

        // With_bounds usage (T extends CharSequence)
        String boundVal = typeParam.toString().toUpperCase();
        targetParam.targetField += "_bounded_" + boundVal;

        // Depends on static field
        int staticVal = TargetClass.staticField;
        targetParam.targetField += "_static_" + staticVal;

        // No new exception
        return staticVal + collection.size();
    }
}