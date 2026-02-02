package com.source;

import com.target.TargetClass;

// Source class: public normal class, different package from target, static nested + member inner class
public class SourceClass {
    // Static nested class feature
    public static class StaticNestedClass {
        public static void doStatementLogic(TargetClass target) {
            int count = 1;
            // DoStatement: static modifier, obj.field + 1, pos: source
            do {
                target.field = "value_" + count;
                count++;
            } while (count <= 1);
        }
    }

    // Member inner class (source_inner_rec hierarchy)
    public class MemberInnerClass {
        // Inner recursive class for source_inner_rec method position
        public class InnerRecursiveClass {
            public InnerRecursiveClass(TargetClass target) {
                this(target.field); // this(arguments) feature
            }
            
            public InnerRecursiveClass(String fieldValue) {
                // Empty constructor for this(arguments)
            }

            // Method: instance, void return, default access, source_inner_rec position
            void processTarget(TargetClass targetParam) {
                // Type declaration statement
                TargetClass target;
                
                // Variable call (target parameter)
                target = targetParam;
                
                // Constructor invocation
                TargetClass newTarget = new TargetClass();
                
                // this(arguments) usage
                InnerRecursiveClass innerRec = new InnerRecursiveClass(target);
                
                // Access instance method of target class
                target.localInnerMethod();
                
                // DoStatement feature call
                StaticNestedClass.doStatementLogic(target);
                
                // No new exception - wrap existing if any
                try {
                    target.field = newTarget.field;
                } catch (Exception e) {
                    throw new RuntimeException(e); // No new exception instantiation
                }
            }
        }
        
        void callInnerRecursive(TargetClass target) {
            InnerRecursiveClass innerRec = new InnerRecursiveClass(target);
            innerRec.processTarget(target);
        }
    }

    // Instance method to trigger inner class method
    public void triggerProcessing() {
        TargetClass target = new TargetClass();
        MemberInnerClass memberInner = new MemberInnerClass();
        memberInner.callInnerRecursive(target);
    }
}

package com.target;

// Target class: default modifier, local inner class feature
class TargetClass {
    String field; // obj.field feature for DoStatement

    // Local inner class (target_feature)
    public void localInnerMethod() {
        class LocalInnerClass {
            public void processField(String field) {
                TargetClass.this.field = field;
            }
        }
        LocalInnerClass local = new LocalInnerClass();
        local.processField("local_value_1"); // 1 feature
    }
}