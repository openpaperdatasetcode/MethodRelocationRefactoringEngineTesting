package com.refactoring.movemethod;

// Source enum class: default modifier, same package, member inner + anonymous inner classes
enum SourceEnum {
    INSTANCE;

    // per_condition: source contains field of target
    private TargetEnum<String> targetField = TargetEnum.VALUE;

    // Static field for depends_on_static_field feature
    private static final String STATIC_FIELD = "static_data_6101";

    // Member inner class (source feature)
    class SourceInnerRecursive {
        // Method to be refactored: varargs, TargetClass Type return, default access, source_inner_rec
        TargetEnum<String> methodToMove(String... varargs) {
            // method types parameter is:none (varargs only, no explicit non-varargs params)
            
            // uses_outer_this feature
            SourceEnum outerThis = SourceEnum.this;

            // constructor invocation
            SourceInnerHelper innerHelper = new SourceInnerHelper();

            // variable call
            String varCall = outerThis.targetField.getTypeParamValue();
            innerHelper.setData(varCall);

            // depends_on_static_field
            if (STATIC_FIELD.equals(varCall)) {
                innerHelper.processData();
            }

            // try statement
            try {
                // do-while block (pos: do-while) with instance method feature
                int count = 1; // method_feature: "1"
                do {
                    // instance method (method_feature: instance)
                    // ClassName.methodName(arguments) (method_feature)
                    // inner_class (method_feature)
                    innerHelper.instanceMethod(count, varargs);
                    count++;
                } while (count <= 3);
            } catch (RuntimeException e) {
                // no_new_exception (no explicit new Exception instantiation)
                e.printStackTrace();
            }

            // Return TargetClass Type (TargetEnum)
            return outerThis.targetField;
        }

        // Instance method in inner class (method_feature: inner_class, instance)
        class SourceInnerHelper {
            private String data;

            void setData(String data) {
                this.data = data;
            }

            // instance method (method_feature: instance) with void return
            void instanceMethod(int num, String... args) {
                this.data = STATIC_FIELD + num + args.length;
            }

            void processData() {
                // Empty implementation
            }
        }
    }

    // Anonymous inner class (source feature)
    Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            new SourceInnerRecursive().methodToMove("anon_arg1", "anon_arg2");
        }
    };
}

// Target enum class: no modifier, type parameter, extends, local inner class (target features)
enum TargetEnum<T> extends BaseEnum<T> {
    VALUE("target_6101");

    private final T typeParamValue;

    TargetEnum(T typeParamValue) {
        this.typeParamValue = typeParamValue;
    }

    T getTypeParamValue() {
        return this.typeParamValue;
    }

    // Method containing local inner class (target_feature: local inner class)
    void targetMethod() {
        // Local inner class
        class TargetLocalInner {
            void process(TargetEnum<T> enumInstance) {
                enumInstance.getTypeParamValue();
            }
        }
        new TargetLocalInner().process(this);
    }
}

// Parent class for TargetEnum (target_feature: extends)
abstract class BaseEnum<T> {
    abstract T getTypeParamValue();
}