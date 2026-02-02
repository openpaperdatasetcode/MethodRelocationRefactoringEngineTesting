// Diff package others class for BreakStatement pos and call_method
package com.refactoring.others;
import com.refactoring.movemethod.SourceEnum;
import com.refactoring.movemethod.TargetEnum;
import java.io.IOException;

public class OthersClass {
    // Static code blocks for call_method pos
    static {
        // pos: Static code blocks
        TargetEnum.TargetInner inner = TargetEnum.VALUE1.new TargetInner(); // target_feature: constructor
        // superTypeReference.methodName(arguments)
        String result = SourceEnum.SourceInterface.superMethod(inner.getInnerValue());
        System.out.println("Static block result: " + result);
    }

    // BreakStatement feature: private modifier, this.field, 1, pos=diff_package_others
    private static int breakStatementLogic(TargetEnum.TargetInner inner) throws IOException {
        // pos: diff_package_others
        int result = 0;
        for (int i = 0; i < 5; i++) {
            inner.setInnerField(1); // this.field feature, target_feature: 1
            if (i == 1) { // target_feature: 1
                break; // BreakStatement
            }
            result += i;
        }
        if (result == 0) {
            throw new IOException("Break statement triggered (1)"); // IOException feature
        }
        return result;
    }

    // call_method: others_class type, public modifier, constructor, superTypeReference.methodName(), pos=Static code blocks, return String
    public static String callMethod(TargetEnum.TargetInner inner) {
        // target_feature: constructor (new TargetInner instance)
        TargetEnum.TargetInner newInner = TargetEnum.VALUE1.new TargetInner();
        newInner.setInnerValue(inner.getInnerValue() + "_ctor_3"); // numbers=3
        // superTypeReference.methodName(arguments)
        return SourceEnum.SourceInterface.superMethod(newInner.getInnerValue());
    }
}

// Back to source package
package com.refactoring.movemethod;

import com.refactoring.others.OthersClass;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessAnnotation {
    int value() default 3; // numbers=3
}

// Interface for source enum implements feature (with superMethod for SuperMethodReference)
interface SourceInterface {
    String process(TargetEnum.TargetInner inner);

    // Super method for SuperMethodReference feature
    default String superMethod(String value) {
        return value + "_super_3"; // numbers=3
    }
}

// Source enum class: public, same package as target, implements, local inner, static nested class
public enum SourceEnum implements SourceInterface {
    INSTANCE;

    // Static field for depends_on_static_field feature
    private static final int STATIC_FIELD = 3; // numbers=3

    // Static nested class (source feature)
    static class SourceStaticNested {
        public static String processValue(String s) {
            return s + "_static_" + STATIC_FIELD; // depends_on_static_field, numbers=3
        }
    }

    // Source_inner_rec (inner recursive class for method_position)
    public class SourceInnerRec {
        // Method to refactor: instance, base type (int) return, default access, in source_inner_rec
        @ProcessAnnotation(3) // has_annotation feature, numbers=3, public modifier
        int methodToRefactor(TargetEnum.TargetInner targetParam) throws IOException {
            // Per_condition: method contains target parameter
            if (targetParam == null) {
                throw new IOException("Target parameter is null"); // IOException feature
            }

            int result = 0;
            // For statement
            for (int i = 0; i < STATIC_FIELD; i++) { // depends_on_static_field, numbers=3
                // Variable call (target inner class)
                String innerValue = targetParam.getInnerValue();
                result += innerValue.length();

                // BreakStatement feature call (diff_package_others)
                result += OthersClass.breakStatementLogic(targetParam);

                // SuperMethodReference feature: numbers=3, public modifier, exp=SuperMethodReference
                public String superRefProcess(String val) { // numbers=3, public modifier
                    // SuperMethodReference (SourceInterface::superMethod)
                    return SourceInterface.superMethod(val + "_super_ref_3"); // numbers=3
                }
                result += superRefProcess(innerValue).length();

                // Depends_on_static_field feature
                innerValue = SourceStaticNested.processValue(innerValue);
                targetParam.setInnerValue(innerValue);

                // No_new_exception feature
                try {
                    Integer.parseInt(innerValue);
                } catch (NumberFormatException e) {
                    // No throw new exception, only handle
                    targetParam.setInnerValue("formatted_" + innerValue + "_3"); // numbers=3
                }
            }

            // Local inner class (source feature)
            class LocalInnerProcessor {
                int process(int input) {
                    return input * STATIC_FIELD; // depends_on_static_field, numbers=3
                }
            }
            result = new LocalInnerProcessor().process(result);

            // Call call_method (others_class type)
            String callResult = OthersClass.callMethod(targetParam);
            result += callResult.length();

            return result; // Base type (int) return
        }
    }

    // Implements interface method
    @Override
    public String process(TargetEnum.TargetInner inner) {
        return inner.getInnerValue() + "_processed_3"; // numbers=3
    }
}

// Target enum class: private, member inner class feature
private enum TargetEnum {
    VALUE1, VALUE2;

    // Member inner class (target_feature)
    public class TargetInner {
        private String innerValue = "target_inner_1"; // target_feature: 1
        private int innerField; // this.field feature

        public String getInnerValue() {
            return innerValue;
        }

        public void setInnerValue(String innerValue) {
            this.innerValue = innerValue;
        }

        public int getInnerField() {
            return innerField;
        }

        public void setInnerField(int innerField) {
            this.innerField = innerField;
        }
    }
}