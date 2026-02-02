package com.refactoring.movemethod;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Protected normal source class (same package as target)
protected class SourceClass {
    // Static field for depends_on_static_field feature
    public static String staticField = "static_value";

    // Static nested class (source feature)
    public static class SourceStaticNested {}

    // Varargs method (final access, List<String> return, target class parameter)
    public final List<String> targetMethod(TargetClass targetParam, String... args) {
        // Local inner class (source feature)
        class LocalInnerClass {
            String innerVar = "local";
        }
        LocalInnerClass localInner = new LocalInnerClass();

        // Variable call
        String localVar = localInner.innerVar;
        List<String> result = new ArrayList<>();

        // Raw type usage
        List rawList = result;

        // Depends on static field
        rawList.add(staticField);

        // Assert statement
        assert targetParam != null : "Target parameter cannot be null";

        // Used by reflection (access target class static nested field)
        try {
            Field field = TargetClass.TargetStaticNested.class.getDeclaredField("nestedField");
            field.setAccessible(true);
            String reflectedValue = (String) field.get(null);
            result.add(reflectedValue);
        } catch (Exception e) {
            // No new exception (no instantiation of new Exception)
            result.add("reflection_error");
        }

        // Process varargs
        for (String arg : args) {
            result.add(localVar + arg);
        }

        return result;
    }
}

// Protected normal target class (same package as source)
protected class TargetClass {
    // Static nested class (target feature)
    public static class TargetStaticNested {
        public static String nestedField = "target_nested_value";
    }
}