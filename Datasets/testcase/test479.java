// Source record class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import com.refactoring.other.SuperHelper;
import com.refactoring.other.OthersClass;
import java.util.ArrayList;
import java.util.List;

// Source record class (default modifier, member + local inner class)
record SourceClass(String privateField) {
    // Member inner class (source class feature)
    class SourceMemberInner {
        int innerVar;

        // Overload exist feature (overloaded method)
        public int compute(int a) {
            return a * 2;
        }

        public int compute(int a, int b) {
            return a + b;
        }
    }

    // Varargs method to refactor (default access, return List<String>, source position)
    List<String> refactorMethod(TargetClass... targetParams) {
        // Variable call feature
        SourceMemberInner innerObj = new SourceMemberInner();
        int varCall = innerObj.compute(2); // overload_exist (call overloaded method)
        
        // This.var = var feature
        innerObj.innerVar = varCall;
        this.innerObj = innerObj; // Assign to instance var
        
        // Expression statement feature
        varCall += targetParams.length * 2;
        
        // With bounds feature
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) { // bound: i < 5
            if (i > 2) break;
            result.add(String.valueOf(i));
        }

        // SuperConstructorInvocation (public modifier, diff_package_others pos, super.field + 2)
        SuperHelper superHelper = new SuperHelper(2) {
            @Override
            public void init() {
                super.field = 2; // super.field, target_feature 2
            }
        };

        // Abstract private method in ternary operator (pos: ternary operators)
        private abstract int abstractPrivateMethod(TargetClass target);
        int ternaryResult = (targetParams.length > 0) ? 
            abstractPrivateMethod(targetParams[0]) : 
            superHelper.superMethod(2); // super.methodName(arguments)

        // Local inner class (source class feature)
        class SourceLocalInner {
            void processTarget(TargetClass target) {
                // No new exception feature (no throw new Exception)
                if (target == null) return;
                result.add(target.value());
            }
        }

        // Process target parameters (per_condition: method has target parameter)
        SourceLocalInner localInner = new SourceLocalInner();
        for (TargetClass target : targetParams) {
            localInner.processTarget(target);
        }

        // Call method (others_class, strictfp, pos: expression, instanceReference.methodName)
        OthersClass others = new OthersClass();
        Object callResult = others.strictfpMethod(result.size()); // expression pos
        result.add(callResult.toString());

        return result;
    }

    // Instance variable for this.var = var
    private SourceMemberInner innerObj;
}

// Different package for SuperConstructorInvocation (diff_package_others)
package com.refactoring.other;

// SuperHelper for SuperConstructorInvocation
public class SuperHelper {
    public int field;

    // Super constructor invocation
    public SuperHelper(int field) {
        this.field = field;
    }

    public int superMethod(int arg) {
        return field + arg;
    }

    public void init() {}
}

// OthersClass for call_method
package com.refactoring.other;

// Call method class (others_class, strictfp, abstract feature)
public abstract class OthersClass {
    // strictfp modifier, return Object, instanceReference.methodName feature
    public strictfp Object strictfpMethod(int arg) {
        return "processed_" + arg;
    }
}

// Target record class package (different from source)
package com.refactoring.target;

import java.util.function.Consumer;

// Target record class (private modifier, anonymous inner class feature)
private record TargetClass(String id, int value) {
    // Anonymous inner class (target_feature)
    public Consumer<String> getConsumer() {
        return new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s + "_" + id);
            }
        };
    }
}