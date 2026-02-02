package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Supporting annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Supporting interface for source enum implements feature
interface SourceEnumInterface {
    List<String> processTarget(PrivateTargetEnum target);
}

// Source enum class (protected modifier, same package, implements + two anonymous inner classes)
protected enum SourceEnum implements SourceEnumInterface {
    INSTANCE1, INSTANCE2;

    // Target field reference (per_condition: source contains target field)
    private PrivateTargetEnum targetFieldRef = PrivateTargetEnum.TARGET_INSTANCE;

    // Source inner record (method_position: source_inner_rec)
    @RefactorAnnotation // has_annotation feature
    record SourceInnerRec(String data) {
        // Instance method (protected access, List<String> return)
        protected List<String> processTarget(PrivateTargetEnum targetParam) {
            List<String> result = new ArrayList<>();
            Object lock = new Object();

            // Synchronized statement
            synchronized (lock) {
                // Type declaration statement
                String localVar = targetParam.targetField;
                // Variable call (target parameter/field access)
                result.add(localVar);
                // Super keywords (access enum's super method)
                String superStr = SourceEnum.this.toString();
                result.add(superStr);

                // ConstructorInvocation (protected modifier, obj.field, 3, pos: inner class)
                protectedConstructorInvocation(targetParam);

                // Uses_outer_this (access outer enum instance)
                SourceEnum.this.targetFieldRef = targetParam;

                // Requires_try_catch (handle potential exceptions)
                try {
                    // Call inner class method (exception throwing statements pos)
                    int callResult = new InnerClass().innerMethod(targetParam);
                    result.add(String.valueOf(callResult));
                } catch (Exception e) {
                    result.add("error: " + e.getMessage());
                }
            }

            return result;
        }

        // Protected ConstructorInvocation method (inner class pos)
        protected void protectedConstructorInvocation(PrivateTargetEnum target) {
            // Inner class for pos
            class ConstructorInner {
                void invoke() {
                    // obj.field with value 3
                    target.targetField = "value_3";
                    // Constructor invocation of target's inner class
                    PrivateTargetEnum.TargetInner inner = target.new TargetInner();
                    inner.setField(3);
                }
            }
            new ConstructorInner().invoke();
        }

        // Inner class for call_method feature
        class InnerClass {
            // call_method: type=inner_class, modifier=private, overriding, ClassName::methodName, exception throwing pos
            private int innerMethod(PrivateTargetEnum target) throws Exception {
                // Overriding feature (override Object's toString)
                @Override
                public String toString() {
                    return super.toString();
                }

                // Exception throwing statements (pos)
                if (target == null) {
                    throw new Exception("Target is null");
                }

                // ClassName::methodName (method reference)
                target.getTargetValues().forEach(PrivateTargetEnum::processValue);
                return target.targetField.length() + 3;
            }
        }
    }

    // First anonymous inner class (source feature)
    Runnable anonymousInner1 = new Runnable() {
        @Override
        public void run() {
            SourceInnerRec rec = new SourceInnerRec("test");
            rec.processTarget(PrivateTargetEnum.TARGET_INSTANCE);
        }
    };

    // Second anonymous inner class (source feature)
    Runnable anonymousInner2 = new Runnable() {
        @Override
        public void run() {
            SourceInnerRec rec = new SourceInnerRec("data");
            rec.processTarget(PrivateTargetEnum.TARGET_INSTANCE);
        }
    };

    @Override
    public List<String> processTarget(PrivateTargetEnum target) {
        return new SourceInnerRec("default").processTarget(target);
    }
}

// Target enum class (private modifier, local inner class feature)
private enum PrivateTargetEnum {
    TARGET_INSTANCE;

    // Target field for per_condition
    String targetField = "default_target";

    // Member inner class for ConstructorInvocation
    class TargetInner {
        private int field;

        public void setField(int field) {
            this.field = field;
        }
    }

    // Local inner class (target feature)
    public List<String> getTargetValues() {
        class TargetLocalInner {
            String process(String val) {
                return val.toUpperCase();
            }
        }
        TargetLocalInner local = new TargetLocalInner();
        List<String> values = new ArrayList<>();
        values.add(local.process(targetField));
        return values;
    }

    // Static method for method reference (ClassName::methodName)
    public static void processValue(String val) {
        // Do nothing - for method reference feature
    }
}