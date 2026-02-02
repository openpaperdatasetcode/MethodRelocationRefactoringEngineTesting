// Source class package (different from target)
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

// Abstract super class to host abstract method (source class is normal, method is abstract)
abstract class AbstractSuperClass {
    protected abstract List<String> processTarget(TargetClass targetParam);
}

// Public normal source class (different package from target)
public class SourceClass extends AbstractSuperClass {
    // Target field reference (per_condition: source contains target field)
    private TargetClass.TargetMemberInner targetFieldRef;

    // Member inner class (source feature)
    class SourceMemberInner {
        // Abstract method implementation (method_position: source_inner)
        @Override
        protected List<String> processTarget(TargetClass targetParam) {
            // Variable call (target parameter/field access)
            targetFieldRef = targetParam.new TargetMemberInner();
            
            // Used_by_reflection: access target class via reflection
            try {
                Class<?> targetClazz = Class.forName("com.refactoring.target.TargetClass$TargetMemberInner");
                Field field = targetClazz.getDeclaredField("targetField");
                field.setAccessible(true);
                field.set(targetFieldRef, "reflection_value");

                Method method = targetClazz.getDeclaredMethod("getTargetField");
                String reflectedVal = (String) method.invoke(targetFieldRef);
            } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException |
                     NoSuchMethodException | InvocationTargetException e) {
                // No_new_exception: catch without throwing new exception
                return List.of("error");
            }

            // No_new_exception (empty try-catch as fallback)
            try {
                return List.of(targetFieldRef.getTargetField());
            } catch (Exception e) {
                return List.of("fallback");
            }
        }
    }

    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            SourceMemberInner inner = new SourceMemberInner();
            TargetClass target = new TargetClass();
            inner.processTarget(target);
        }
    };
}

// Target class package (different from source)
package com.refactoring.target;

import java.util.List;

// Private normal target class
private class TargetClass {
    // Member inner class (target feature)
    class TargetMemberInner {
        // Target field for per_condition
        private String targetField = "default";

        public String getTargetField() {
            return targetField;
        }

        public void setTargetField(String targetField) {
            this.targetField = targetField;
        }
    }
}