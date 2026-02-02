package com.refactoring.movemethod;

import java.util.List;
import java.util.ArrayList;

// Source generic class: final modifier, same package, static nested + local inner classes
final class SourceClass<T> {
    // per_condition: source contains field of target
    private TargetClass<T> targetField = new TargetClass<>();

    // Static field for depends_on_static_field feature
    private static final String STATIC_FIELD = "static_6126";

    // Static nested class (source feature)
    private static class SourceStaticNested {
        private String nestedField = "static_nested_val";
    }

    // Inner class (method_position: source_inner)
    public class SourceInnerClass {
        // Method to be refactored: varargs, TargetClass Type return, final access, source_inner
        public final TargetClass<T> methodToMove(T... varargs) {
            // type declaration statement feature
            SourceStaticNested staticNested = new SourceStaticNested();
            List<String> strList = new ArrayList<>();

            // variable call feature
            String varCall = staticNested.nestedField + STATIC_FIELD; // depends_on_static_field

            // Varargs method (type: varargs, modifier: private, pos: ternary operators, return List<String>)
            private List<String> nestedVarargsMethod(String... args) {
                int count = 1; // method_feature: "1"
                // pos: ternary operators
                String data = (args.length > 0) ? args[0] : STATIC_FIELD;
                // method_feature: inner_class + instanceReference.methodName(arguments)
                strList.add(targetField.new TargetInnerClass().processData(data, count));
                // method_feature: varargs
                for (String arg : args) {
                    strList.add(arg + "_" + count);
                }
                return strList;
            }
            nestedVarargsMethod(varCall, STATIC_FIELD);

            // Local inner class (source feature)
            class SourceLocalInner {
                private void validate(T val) {
                    if (val == null) {
                        // no_new_exception (no explicit new Exception instantiation)
                        return;
                    }
                    targetField.setData(val);
                }
            }
            new SourceLocalInner().validate(varargs.length > 0 ? varargs[0] : null);

            // no_new_exception feature
            targetField.innerClassField = varCall;
            return targetField;
        }

        // Call method: inner_class type, public modifier, instance, super.methodName(), pos=switch, returns void
        public void callMethod(int switchCase) {
            switch (switchCase) { // pos: switch
                case 1:
                    // target_feature: instance + super.methodName()
                    super.toString();
                    this.methodToMove((T) "arg1");
                    break;
                case 2:
                    super.hashCode();
                    this.methodToMove((T) "arg1", (T) "arg2");
                    break;
                default:
                    super.getClass();
                    this.methodToMove();
                    break;
            }
        }
    }
}

/**
 * Javadoc for TargetClass (target_feature: javadoc)
 * Generic target class with member inner class
 * ID: 6126
 */
// Target generic class: public modifier, javadoc + member inner class (target_features)
public class TargetClass<T> {
    private T data;
    public String innerClassField;

    // Member inner class (target_feature)
    public class TargetInnerClass {
        public String processData(String data, int count) {
            return data + "_" + count + "_inner";
        }
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }
}