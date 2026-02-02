// Source class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import com.refactoring.others.StaticHelper;

import java.io.IOException;

// Source abstract normal class: abstract modifier, different package, member inner + anonymous inner classes
public abstract class SourceClass<T extends CharSequence> { // with_bounds feature (T extends CharSequence)
    // Private outer field for access_outer_private feature
    private final String outerPrivateField = "outer_private_6154";

    // per_condition: source contains field of target
    protected TargetClass targetField = new TargetClass();

    // Member inner class (source feature)
    protected class SourceMemberInner {
        private String innerField = "source_member_inner_6154";
        
        public String processData(T data) { // with_bounds feature
            return data.toString() + "_processed";
        }
    }

    // ThrowStatement feature (type: ThrowStatement, modifier: private, pos: source)
    private void throwStatementLogic() throws IOException { // requires_throws feature
        int errorCode = 1; // target_feature: "1"
        // target_feature: super.field (access TargetParent's field via target)
        if (targetField.getSuperField() == errorCode) {
            // ThrowStatement feature
            throw new IOException("Super field error (code: " + errorCode + ")");
        }
    }

    // Static method (type: static, modifier: protected, pos: array initialization, return base type)
    protected static int staticMethod() {
        // pos: array initialization
        String[] arr = new String[]{"val1", "val2", "val3"};
        int count = 1; // method_feature: "1"
        
        // method_feature: others_class + static + obj.m1().m2().m3()
        int result = StaticHelper.getInstance() // m1()
                                 .processArr(arr) // m2()
                                 .getCount(count); // m3() (method_feature: "1")
        
        return result; // return_type: base type (int)
    }

    // Method to be refactored: normal, Object return, protected access, source position
    protected Object methodToMove() throws IOException { // requires_throws feature
        // access_outer_private feature
        String privateVal = this.outerPrivateField;

        // variable call feature
        SourceMemberInner memberInner = new SourceMemberInner();
        String varCall = memberInner.innerField + privateVal;

        // with_bounds feature (type bound constraint)
        T data = (T) "test_data_6154";
        String boundedData = memberInner.processData(data);

        // Call ThrowStatement logic (requires_throws)
        throwStatementLogic();

        // Call static method
        int staticResult = staticMethod();

        // Anonymous inner class (source feature)
        Runnable anonymousProcessor = new Runnable() {
            @Override
            public void run() {
                TargetClass.TargetInnerRecursive targetInner = targetField.new TargetInnerRecursive();
                targetInner.setData(varCall + "_" + boundedData);
            }
        };
        anonymousProcessor.run();

        // return statement feature
        return new Object() {
            @Override
            public String toString() {
                return varCall + "_static_" + staticResult;
            }
        };
    }
}
// Target class package (different from source)
package com.refactoring.target;

// Parent class for target extends feature
class TargetParent {
    protected int superField = 1; // target_feature: "1" (super.field)
}

// Target class: normal, default modifier, extends + local inner class (target_features)
class TargetClass extends TargetParent {
    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRecursive {
        private String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            // local inner class (target_feature)
            class TargetLocalInner {
                private String validate(String val) {
                    return val == null ? "default_6154" : val;
                }
            }
            this.data = new TargetLocalInner().validate(data);
        }

        // Recursive method (recursive class context)
        public int recursiveLength(int val) {
            if (val <= 0) return superField; // super.field
            return val + recursiveLength(val - 1);
        }
    }

    public int getSuperField() {
        return super.superField;
    }

    public void setSuperField(int val) {
        super.superField = val;
    }
}
// Others class package (for static method feature)
package com.refactoring.others;

// Others class for method_feature: others_class
public class StaticHelper {
    private static StaticHelper instance;

    private StaticHelper() {}

    // m1() for obj.m1().m2().m3()
    public static StaticHelper getInstance() {
        if (instance == null) {
            instance = new StaticHelper();
        }
        return instance;
    }

    // m2() for obj.m1().m2().m3()
    public StaticHelper processArr(String[] arr) {
        return this;
    }

    // m3() for obj.m1().m2().m3()
    public int getCount(int count) {
        return count * 6154;
    }
}