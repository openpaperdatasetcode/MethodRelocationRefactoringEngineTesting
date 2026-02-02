package com.refactor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Sealed super class for record extension (Java 16+)
sealed class RecordSuperClass permits SourceRecord {
    protected void superMethod(String arg) {
        // Super method for super.methodName(arguments) feature
    }
}

// Source class: record class, private modifier, same package as target, no extra features
private record SourceRecord(TargetRecord<String> targetField) extends RecordSuperClass {
    // per_condition: source contains field of target (targetField in record components)

    // Method: instance, base type (int) return, strictfp access, in source class
    strictfp int processTarget(TargetRecord<String>.TargetInnerRec targetParam) {
        // Variable call (target parameter)
        TargetRecord<String>.TargetInnerRec innerRec = targetParam;
        
        // Inner class for WhileStatement pos (inner class)
        class InnerForWhile {
            // WhileStatement: static modifier, this.field + 1, pos: inner class
            static void whileLogic(TargetRecord<String>.TargetInnerRec inner) {
                int count = 1;
                while (count <= 1) {
                    inner.field = "value_" + count; // this.field + 1 feature
                    count++;
                }
            }
        }
        InnerForWhile.whileLogic(innerRec);
        
        // Instance method: strictfp modifier, 1/target/instance/super.methodName(arguments), pos: array initialization, void return
        strictfp void[] instanceMethods = new void[] {
            ((Runnable) () -> {
                super.superMethod(innerRec.field + "_1"); // super.methodName(arguments) + 1 feature
            }).run()
        };
        
        // Super keywords usage
        super.superMethod(innerRec.field);
        
        // Assert statement
        assert innerRec.field != null : "Field is null";
        
        // NullPointerException feature
        try {
            if (innerRec == null) {
                throw new NullPointerException("Inner rec is null (1)");
            }
            
            // Used by reflection
            Method setFieldMethod = TargetRecord.TargetInnerRec.class.getMethod("setField", String.class);
            setFieldMethod.invoke(innerRec, "reflection_1");
            
            // call_method: source type, default modifier, static + ClassName.methodName(arguments), pos: while, returns List<String>
            int i = 0;
            List<String> result = new ArrayList<>();
            while (i < 1) {
                result.addAll(SourceRecord.staticCallMethod(innerRec)); // ClassName.methodName(arguments)
                i++;
            }
            
            return result.size();
        } catch (Exception e) {
            // No new exception - wrap existing
            throw new RuntimeException(e);
        }
    }

    // call_method: static feature, default modifier, pos: while, returns List<String>
    static List<String> staticCallMethod(TargetRecord<String>.TargetInnerRec inner) {
        List<String> list = new ArrayList<>();
        list.add(inner.field);
        list.add("1"); // 1 feature
        return list;
    }

    // Compact constructor to initialize target field if null
    public SourceRecord {
        if (targetField == null) {
            targetField = new TargetRecord<>("default");
        }
    }
}

// Target class: record class, strictfp modifier, type parameter + static nested class features
strictfp record TargetRecord<T>(T data) {
    // Static nested class (target_feature)
    public static class StaticNestedClass<T> {
        public static void modifyInner(TargetInnerRec<T> inner) {
            inner.setField((T) ("static_" + inner.field));
        }
    }

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRec<T> {
        T field;

        public void setField(T field) {
            this.field = field;
        }

        public T getField() {
            return field;
        }
    }

    // Compact constructor to initialize inner rec
    public TargetRecord {
        if (data == null) {
            data = (T) "default_data";
        }
    }

    // Convenience method to create inner rec
    public TargetInnerRec<T> createInnerRec() {
        TargetInnerRec<T> inner = new TargetInnerRec<>();
        inner.setField(data);
        return inner;
    }
}