package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.io.IOException;

// Source class: abstract normal class, abstract modifier, same package, permits, static nested, local inner class
public abstract sealed class SourceClass permits SourceClass.StaticNestedSource, ConcreteSubClass {
    // per_condition: source contains target class field
    private TargetClass<String> targetField = new TargetClass<>();

    // Static nested class (source feature)
    static final class StaticNestedSource extends SourceClass {
        @Override
        TargetClass<String> overloadingMethod(String param) {
            return new TargetClass<>();
        }

        @Override
        TargetClass<String> overloadingMethod(Integer param) {
            return new TargetClass<>();
        }
    }

    // Method: overloading, return TargetClass Type, private access, source position
    private TargetClass<String> overloadingMethod(String param) throws IOException { // requires_throws feature
        // Local inner class (source feature)
        class LocalInnerSource {
            String localField = "localValue";
        }
        LocalInnerSource localInner = new LocalInnerSource();

        // Variable call feature
        String localVar = localInner.localField;
        localVar = targetField.targetField;

        // Numbers: 1, default modifier, CharacterLiteral
        default char charLiteral = '1';

        // IfStatement: private modifier, obj.field, 1, pos=source
        private void ifStatementLogic(TargetClass<String> obj) {
            int num = 1; // target_feature "1"
            if (obj.targetField != null) { // target_feature "obj.field"
                localVar = obj.targetField;
            }
        }
        ifStatementLogic(targetField);

        // Continue statement feature
        for (int i = 0; i < 5; i++) {
            if (i == 1) continue;
            localVar += i;
        }

        // Assert statement feature
        assert param != null : "Parameter cannot be null";

        // Super constructor invocation (via reflection for abstract class context)
        try {
            Method superMethod = SourceClass.class.getSuperclass().getConstructor();
            superMethod.invoke(this);
        } catch (Exception e) {
            throw new IOException("Reflection error", e);
        }

        // Used_by_reflection feature
        try {
            Method method = this.getClass().getDeclaredMethod("overloadingMethod", String.class);
            method.setAccessible(true);
            method.invoke(this, param);
        } catch (Exception e) {
            throw new IOException("Reflection access failed", e);
        }

        return targetField;
    }

    // Overloading method (overloading type feature)
    private TargetClass<String> overloadingMethod(Integer param) throws IOException {
        return targetField;
    }
}

// Permits implementation class for source sealed abstract class
final class ConcreteSubClass extends SourceClass {
    public ConcreteSubClass() {
        // Super constructor invocation
        super();
    }

    @Override
    TargetClass<String> overloadingMethod(String param) {
        return new TargetClass<>();
    }

    @Override
    TargetClass<String> overloadingMethod(Integer param) {
        return new TargetClass<>();
    }
}

// Target class: normal class, private modifier, target_feature: type parameter, member inner class
private class TargetClass<T> {
    String targetField = "targetValue";

    // Member inner class (target_feature)
    class MemberInnerTarget {
        T innerField;
    }
}