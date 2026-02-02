package com.refactoring.test;

import java.lang.reflect.Method;
import java.util.List;

protected class SourceClass<T> permits TargetClass<T> {
    // Source contains target class field (per_condition)
    private TargetClass<String> targetField = new TargetClass<>();

    // Anonymous inner class 1 (source_class feature)
    Runnable anonymousInner1 = new Runnable() {
        @Override
        public void run() {}
    };

    // Anonymous inner class 2 (source_class feature)
    List<String> anonymousInner2 = new java.util.ArrayList<>() {{
        add("anonymous");
    }};

    // Method to be refactored (instance, TargetClass Type return, private access, position: source)
    @SuppressWarnings("unchecked")
    private TargetClass<String> targetMethod() {
        // While statement
        int count = 1; // target_feature 1
        while (count <= 5) {
            // Variable call
            String targetValue = targetField.localInnerField;
            count++;
            if (count == 3) break; // break statement
        }

        // SwitchStatement (protected modifier, ClassName.field, 1, pos: diff_package_others)
        protected String switchVar = TargetClass.STATIC_FIELD; // ClassName.field
        switch (switchVar) {
            case "1": // target_feature 1
                break;
            default:
                break;
        }

        // Override violation (attempt to override final method)
        @Override // Compile error (override_violation)
        public final String toString() {
            return super.toString();
        }

        // Used by reflection
        try {
            Method method = SourceClass.class.getDeclaredMethod("targetMethod");
            method.setAccessible(true);
            method.invoke(this);
        } catch (Exception e) {
            // No new exception (no_new_exception)
        }

        return targetField;
    }
}

class TargetClass<T> {
    // Static field for SwitchStatement (ClassName.field)
    public static final String STATIC_FIELD = "1"; // target_feature 1
    String localInnerField;

    // Local inner class (target_feature)
    void initLocalInner() {
        class LocalInnerClass {
            String value = "localInnerValue";
        }
        LocalInnerClass local = new LocalInnerClass();
        this.localInnerField = local.value;
    }

    public TargetClass() {
        initLocalInner();
    }
}