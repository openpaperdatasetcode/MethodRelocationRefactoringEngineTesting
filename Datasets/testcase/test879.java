// Source package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import com.refactoring.target.TargetSubClass;
import java.util.List;
import java.util.ArrayList;

// Parent class for source extends feature
class SourceParentClass {}

// Interface for source implements feature
interface SourceInterface {
    TargetClass processTarget(TargetClass target);
}

// Source class (public modifier, different package, extends + implements + static nested + local inner class)
public class SourceClass extends SourceParentClass implements SourceInterface {
    private String outerField = "sourceField";

    // Static nested class (source feature)
    public static class SourceStaticNested {
        static List<String> staticMethod(TargetSubClass subClass) {
            // obj.m1().m2().m3() feature (method_feature)
            String chainResult = subClass.m1().m2().m3();
            List<String> list = new ArrayList<>();
            list.add(chainResult);
            list.add(String.valueOf(1)); // 1 from method_feature
            return list;
        }
    }

    @Override
    public TargetClass processTarget(TargetClass target) {
        return target;
    }

    // Method to be refactored (instance, TargetClass return, default access, source position)
    TargetClass moveMethod(TargetClass targetParam) { // method types parameter is:none (only target param)
        // Per_condition: contains target parameter (targetParam)
        if (targetParam == null) {
            return new TargetClass<>("default");
        }

        // Static method in constructor parameter list (pos:the parameter list of constructors)
        TargetSubClass subClass = new TargetSubClass(SourceStaticNested.staticMethod(new TargetSubClass())); // 1/sub_class/static/obj.m1().m2().m3()

        // Uses outer this
        String outerVal = SourceClass.this.outerField;

        // Variable call
        String targetField = targetParam.getField();
        targetParam.setField(targetField + "_" + outerVal);

        // Local inner class (source feature)
        class LocalInnerClass {
            void updateTarget(TargetClass<String> target) {
                target.setField(target.getField() + "_local");
            }
        }
        new LocalInnerClass().updateTarget(targetParam);

        // No new exception
        return targetParam;
    }
}

// Target package
package com.refactoring.target;

import java.util.List;

// Target class (default modifier, type parameter + member inner class)
class TargetClass<T> {
    private T field;

    public TargetClass(T field) {
        this.field = field;
    }

    // Member inner class (target_feature)
    public class TargetMemberInner {
        void validateField() {
            if (field == null) {
                field = (T) "default";
            }
        }
    }

    public T getField() {
        return field;
    }

    public void setField(T field) {
        this.field = field;
        new TargetMemberInner().validateField();
    }
}

// Subclass for method_feature (sub_class)
class TargetSubClass {
    public TargetSubClass() {}

    public TargetSubClass(List<String> dummy) {}

    // Method chain for obj.m1().m2().m3()
    public TargetSubClass m1() { return this; }
    public TargetSubClass m2() { return this; }
    public String m3() { return "chainResult"; }
}