package com.refactor.movemethod;

import java.lang.reflect.Method;
import java.util.Objects;

// Source record class (default modifier, same package as target)
record SourceRecord(String sourceId, TargetRecord targetField) { // per_condition: contains target class field
    // Static field for method's depends_on_static_field feature
    protected static String STATIC_FIELD = "static_value";

    // Member inner class (source_class feature)
    class MemberInnerClass {
        public String getInnerData() {
            return sourceId + "_inner";
        }

        // Overriding method (method_position: source_inner_rec)
        private TargetRecord methodToMove() {
            // Variable call feature
            String localVar = STATIC_FIELD;
            MemberInnerClass innerInstance = new MemberInnerClass();
            localVar = innerInstance.getInnerData();

            // Access outer protected (access_outer_protected feature)
            String outerProtected = SourceRecord.this.STATIC_FIELD;

            // Access instance method (access_instance_method feature)
            String instanceMethodResult = SourceRecord.this.sourceInstanceMethod();

            // Depends on static field feature (uses STATIC_FIELD)
            String combined = localVar + outerProtected + instanceMethodResult + STATIC_FIELD;

            // No new exception feature (no explicit throw new)
            if (combined.isEmpty()) {
                return null;
            }

            // Used by reflection feature
            try {
                Method reflectMethod = MemberInnerClass.class.getDeclaredMethod("methodToMove");
                reflectMethod.setAccessible(true);
                reflectMethod.invoke(innerInstance);
            } catch (Exception e) {
                // No new exception - catch without throwing new
            }

            // Return TargetRecord (method return_type: TargetClass Type)
            return new TargetRecord(combined, new LocalInnerInTarget() {});
        }
    }

    // Instance method for access_instance_method feature
    private String sourceInstanceMethod() {
        // Local inner class (source_class feature)
        class LocalInnerClass {
            String getLocalData() {
                return targetField.targetId();
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        return localInner.getLocalData();
    }
}

// Target record class (public modifier)
public record TargetRecord(String targetId, Runnable dummy) { // target_feature: local inner class
    public TargetRecord {
        // Local inner class (target_class target_feature)
        class LocalInnerInTarget {
            public String accessTargetField() {
                return targetId;
            }
        }

        // Validate dummy parameter (uses local inner class)
        if (dummy == null) {
            LocalInnerInTarget localInner = new LocalInnerInTarget();
            dummy = () -> System.out.println(localInner.accessTargetField());
        }
    }

    // Override for consistency (method is overriding type)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetRecord that = (TargetRecord) o;
        return Objects.equals(targetId, that.targetId) && Objects.equals(dummy, that.dummy);
    }
}