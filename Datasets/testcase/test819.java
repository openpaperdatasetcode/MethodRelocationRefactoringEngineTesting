package refactoring.test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

// Super class for super.field and super constructor invocation
class SuperClassForTarget {
    protected int superField = 1;
    public SuperClassForTarget(int value) {
        this.superField = value;
    }
}

// Target class: generic, protected modifier, anonymous inner class feature
protected class TargetClass<T> extends SuperClassForTarget {
    public T targetData;

    // Anonymous inner class (target_feature)
    private List<T> anonymousList = new ArrayList<T>() {
        @Override
        public boolean add(T e) {
            targetData = e;
            return super.add(e);
        }
    };

    // Super constructor invocation in target class constructor
    public TargetClass() {
        super(1);
    }

    public void processData(T... data) {
        for (T item : data) {
            anonymousList.add(item);
        }
    }
}

// Source class: generic, protected modifier, local inner + static nested class features
protected class SourceClass<S> {
    // Source contains target field (per_condition)
    private TargetClass<String> targetField = new TargetClass<>();

    // Static nested class (source_class feature)
    static class StaticNestedSource {
        public static int nestedStaticValue = 1;
    }

    /**
     * Method Javadoc (method feature)
     * @param args varargs parameter (method type: varargs)
     */
    private void refactorMethod(S... args) {
        // VariableDeclarationStatement: static modifier, super.field, 1, pos=source
        int localVar = SuperClassForTarget.superField + 1;
        
        // Labeled statement (method feature)
        label:
        for (S arg : args) {
            if (arg == null) {
                break label;
            }
            // Variable call (method feature)
            targetField.processData((String) arg);
        }

        // Local inner class (source_class feature) - depends_on_inner_class
        class LocalInnerClass {
            void invokeReflection() {
                // Used_by_reflection (method feature) + requires_try_catch
                try {
                    Method method = TargetClass.class.getMethod("processData", Object[].class);
                    method.invoke(targetField, (Object) new String[]{"reflection"});
                } catch (Exception e) {
                    // Requires try-catch (handles reflection exceptions)
                    e.printStackTrace();
                }
            }
        }

        // Depends_on_inner_class (method feature) - use local inner class
        new LocalInnerClass().invokeReflection();
        
        // Super constructor invocation (method feature)
        TargetClass<Integer> newTarget = new TargetClass<Integer>() {};
    }
}