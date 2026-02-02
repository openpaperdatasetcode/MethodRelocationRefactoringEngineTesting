package com.source;

import com.target.TargetGenericClass;
import com.other.OthersClass;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Others class for overriding method feature
package com.other;
import com.target.TargetGenericClass;

public class OthersClass {
    public static class BaseClass {
        protected void baseMethod(TargetGenericClass<String>.TargetInnerRec inner) {
            // Super method for super.methodName(arguments)
        }
    }
}

// Back to source package
package com.source;
import com.other.OthersClass;
import com.target.TargetGenericClass;

// Source class: generic class, strictfp modifier, different package from target, local inner + member inner class
strictfp class SourceGenericClass<T extends TargetGenericClass<String>.TargetInnerRec> {
    // per_condition: source contains field of target
    private TargetGenericClass<String>.TargetInnerRec targetField = new TargetGenericClass<String>().new TargetInnerRec();

    // Member inner class feature
    protected class MemberInnerClass extends OthersClass.BaseClass {
        // Overriding feature: protected modifier, 1/others_class/overriding/super.methodName(arguments), pos: Lambda expressions, void return
        @Override
        protected void baseMethod(TargetGenericClass<String>.TargetInnerRec inner) {
            super.baseMethod(inner); // super.methodName(arguments)
            inner.setValue("overridden_1"); // 1 feature
        }
    }

    // Method: varargs, return List<String>, protected access, in source class
    protected List<String> processTarget(T... targetParams) {
        // Type declaration statement
        MemberInnerClass innerClass;
        List<String> result = new ArrayList<>();
        
        // Variable call (target parameter/field)
        T innerRec = targetParams.length > 0 ? targetParams[0] : (T) targetField;
        
        // Super keywords usage (via member inner class)
        innerClass = new MemberInnerClass();
        superMethodCall(innerClass, innerRec);
        
        // Local inner class feature
        class LocalInnerClass {
            public void processInner(T inner) {
                inner.setValue(SourceGenericClass.this.toString() + "_1"); //
                inner.setValue(SourceGenericClass.this.toString() + "_1"); // 1 feature + outer this
                result.add(inner.getValue());
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        localInner.processInner(innerRec);
        
        // Overriding method call in Lambda expressions (pos for overriding feature)
        Consumer<TargetGenericClass<String>.TargetInnerRec> lambda = innerClass::baseMethod; // Lambda expressions pos
        lambda.accept(innerRec);
        
        // No new exception - wrap existing if any
        try {
            result.add(innerRec.getValue());
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e); // No new exception instantiation
        }
    }

    // Helper method for super keywords
    private void superMethodCall(MemberInnerClass innerClass, T innerRec) {
        innerClass.baseMethod(innerRec);
    }
}

package com.target;

// Target class: generic class, abstract modifier, local inner class feature
abstract class TargetGenericClass<T> {
    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRec {
        private T value;
        
        public T getValue() {
            return value;
        }
        
        public void setValue(T value) {
            this.value = value;
        }
    }

    // Local inner class (target_feature)
    public void targetMethod() {
        class LocalInnerClass {
            public void validateInner(TargetInnerRec inner) {
                if (inner.getValue() == null) {
                    inner.setValue((T) "default_1"); // 1 feature
                }
            }
        }
        LocalInnerClass local = new LocalInnerClass();
        local.validateInner(new TargetInnerRec());
    }

    // Abstract method for target generic class
    public abstract void abstractMethod(T value);
}