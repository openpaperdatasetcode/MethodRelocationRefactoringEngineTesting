// Source class package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.io.IOException;

// Diff package others class for ConstructorInvocation pos
package com.refactoring.others;
import com.refactoring.source.SourceClass;
import com.refactoring.target.TargetClass;

public class DiffPackageHelper {
    public static <T> void invokeConstructor(SourceClass.SourceInnerClass innerObj, TargetClass<T> targetParam) {
        innerObj.constructorLogic(targetParam);
    }
}

// Back to source package
package com.refactoring.source;
import com.refactoring.others.DiffPackageHelper;
import com.refactoring.target.TargetClass;
import com.refactoring.target.TargetInterface;

// Super class for super constructor invocation
class SuperSourceClass {
    protected String superField;

    public SuperSourceClass(String value) {
        this.superField = value;
    }
}

// Source class: normal, default modifier, different package from target, static nested + member inner class
class SourceClass extends SuperSourceClass {
    // Per_condition: source contains target class field
    private TargetClass<String> targetField = new TargetClass<>("initialSourceTarget");

    // Static nested class (source feature)
    static class SourceStaticNested<U> {
        public static int staticField = 2; // For ConstructorInvocation target_feature: 2
    }

    // Member inner class (source_inner - method position)
    class SourceInnerClass {
        // ConstructorInvocation feature: private modifier, ClassName.field, 2, pos=diff_package_others
        private void constructorLogic(TargetClass<String> targetParam) {
            // ClassName.field usage (SourceStaticNested.staticField = 2)
            int fieldValue = SourceStaticNested.staticField; // target_feature: ClassName.field, 2
            // Target class constructor invocation
            TargetClass<String> newTarget = new TargetClass<>("constructor_" + fieldValue);
            // pos: diff_package_others (call diff package helper)
            DiffPackageHelper.invokeConstructor(this, newTarget);
        }

        // Method to refactor: instance, void return, protected access, in source_inner
        protected void methodToRefactor(TargetClass<String> targetParam) throws IOException { // requires_throws
            // Super constructor invocation (source class super constructor)
            super SourceClass("superConstructorValue");

            // Variable call (target field and parameter)
            String targetValue = targetField.getValue();
            targetValue += targetParam.getValue();

            // Call ConstructorInvocation feature method
            constructorLogic(targetParam);

            // Used by reflection feature
            try {
                // Access target class field via reflection
                Field valueField = TargetClass.class.getDeclaredField("value");
                valueField.setAccessible(true);
                valueField.set(targetParam, "reflection_" + targetValue);

                // Invoke target class constructor via reflection
                Constructor<TargetClass<String>> constructor = TargetClass.class.getConstructor(String.class);
                TargetClass<String> reflectTarget = constructor.newInstance("reflect_constructor_2"); // 2 alignment
            } catch (Exception e) {
                // Propagate exception (requires_throws)
                throw new IOException("Reflection error", e);
            }

            // Requires_throws validation
            if (targetValue.isEmpty()) {
                throw new IOException("Target value is empty");
            }
        }
    }
}

// Target class package (different from source)
package com.refactoring.target;

// Interface for target class implements feature
interface TargetInterface<T> {
    T getValue();
}

// Target class: normal, no modifier, type parameter, implements, anonymous inner class
class TargetClass<T> implements TargetInterface<T> {
    protected T value;

    public TargetClass(T initialValue) {
        this.value = initialValue;
    }

    @Override
    public T getValue() {
        // Anonymous inner class (target_feature)
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Target anonymous class: " + value);
            }
        };
        anonymousRunnable.run();
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}