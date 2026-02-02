package com.refactoring.test;

import java.lang.reflect.Method;

/**
 * Target record class with javadoc (target_feature) and member inner class
 */
public record TargetRecord(String data) { // target_class: record, public modifier
    // Member inner class (target_feature)
    public class TargetMemberInner {
        public String modifyData(String input) {
            return input + "_modified_by_inner";
        }
    }

    // Default constructor (implicit, but explicit for clarity)
    public TargetRecord {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
    }
}

// Subclass of TargetRecord (for call_method type:sub_class)
class TargetSubRecord extends TargetRecord {
    public TargetSubRecord(String data) {
        super(data);
    }

    // call_method (type:sub_class, modifier:protected, target_feature:normal + new ClassName().method())
    protected TargetRecord callMethod(TargetRecord param) {
        // new ClassName().method() feature
        TargetRecord.TargetMemberInner inner = new TargetRecord(param.data()).new TargetMemberInner();
        String modified = inner.modifyData(param.data());
        return new TargetSubRecord(modified);
    }
}

// Source record class (default modifier, same package, local inner + anonymous inner class)
record SourceRecord(String recordField) { // source_class: record, default modifier
    // Inner class for method_position:source_inner
    class SourceInnerClass {
        // Method to be refactored (instance, Object return, private access, source_inner position)
        private Object moveMethod(TargetRecord targetParam) {
            // Per_condition: contains target parameter
            if (targetParam == null) {
                throw new IllegalArgumentException("Target parameter cannot be null"); // exception throwing statements
            }

            // Constructor (type:constructor, modifier:public, method_feature:1/inner_class/constructor/this.methodName(arguments))
            // pos: exception throwing statements
            public TargetRecord innerClassConstructor(int num) {
                // this.methodName(arguments) feature
                String processed = this.processData(targetParam.data(), num); // 1 from method_feature
                return new TargetRecord(processed);
            }

            // Invoke constructor (pos: exception throwing statements)
            TargetRecord constructedTarget = innerClassConstructor(1);

            // Constructor invocation
            TargetRecord newTarget = new TargetRecord("new_target_data");
            TargetSubRecord subTarget = new TargetSubRecord("sub_target_data");

            // Super constructor invocation (Object superclass)
            Object superObj = new Object();

            // Synchronized statement
            synchronized (this) {
                targetParam = constructedTarget;
            }

            // Used by reflection
            try {
                Method modifyMethod = TargetRecord.TargetMemberInner.class.getMethod("modifyData", String.class);
                TargetRecord.TargetMemberInner inner = targetParam.new TargetMemberInner();
                String reflectedData = (String) modifyMethod.invoke(inner, targetParam.data());
                targetParam = new TargetRecord(reflectedData);
            } catch (Exception e) {
                // No new exception (handle reflection exceptions internally)
            }

            // Variable call
            String varCall = targetParam.data();
            targetParam = new TargetRecord(varCall + "_variable_call");

            // While loop with call_method (pos:while)
            int count = 0;
            while (count < 3) {
                targetParam = subTarget.callMethod(targetParam); // new ClassName().method() feature
                count++;
            }

            // Local inner class (source_feature)
            class LocalInnerProcessor {
                Object processTarget(TargetRecord target) {
                    return target.data() + "_local_processed";
                }
            }
            Object processedObj = new LocalInnerProcessor().processTarget(targetParam);

            // Anonymous inner class (source_feature)
            Runnable anonymousInner = new Runnable() {
                @Override
                public void run() {
                    System.out.println(targetParam.data());
                }
            };
            anonymousInner.run();

            // No new exception
            return processedObj;
        }

        // Helper method for this.methodName(arguments)
        private String processData(String data, int num) {
            return data + "_processed_" + num;
        }
    }
}