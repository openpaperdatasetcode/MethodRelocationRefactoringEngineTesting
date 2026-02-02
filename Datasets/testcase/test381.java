package refactoring.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Interface for source enum's implements feature
interface SourceOperation {
    TargetEnum.TargetInnerRecursive getTargetInner(TargetEnum.TargetInnerRecursive inner);
}

// Subclass for method_feature: sub_class
class TargetSubClass extends TargetEnum {
    TargetSubClass(String value) {
        super(value);
    }

    @Override
    public TargetInnerRecursive createInner() {
        return new TargetInnerRecursive();
    }
}

// Source enum: public modifier, same package, implements + double static nested classes
public enum SourceEnum implements SourceOperation {
    INSTANCE;

    // First static nested class (source feature)
    public static class SourceStaticNestedOne {
        int value = 1;
    }

    // Second static nested class (source feature - duplicate)
    public static class SourceStaticNestedTwo {
        List<String> list = new ArrayList<>();
    }

    // Accessor method to be refactored (accessor type, TargetEnum type return, public access)
    @Override
    public TargetEnum.TargetInnerRecursive getTargetInner(TargetEnum.TargetInnerRecursive targetParam) { // per_condition
        // Variable call (target inner recursive class field)
        targetParam.counter = 1;
        targetParam.recursiveInner.value = "accessor";

        // Labeled statement
        processLabel: {
            // Private varargs method in Lambda expressions pos (method_feature)
            Consumer<TargetEnum.TargetInnerRecursive> lambda = inner -> {
                List<String> result = this.varargsMethod(inner, "arg1", "arg2"); // this.methodName(arguments)
                inner.recursiveInner.value = result.get(0);
            };
            lambda.accept(targetParam);
            break processLabel;
        }

        // No new exception, return TargetEnum inner recursive type
        return targetParam;
    }

    // Private varargs method (method_feature: varargs, 1, sub_class, this.methodName)
    private List<String> varargsMethod(TargetEnum.TargetInnerRecursive inner, String... args) {
        List<String> list = new ArrayList<>();
        int count = 1; // 1 (method_feature)
        // Sub_class usage (method_feature: sub_class)
        TargetSubClass subClass = new TargetSubClass("sub");
        inner.counter = subClass.createInner().counter + count;
        
        // Varargs processing
        for (String arg : args) {
            list.add(arg + "_" + count);
        }
        return list;
    }
}

// Target enum: default modifier, local inner class feature
enum TargetEnum {
    VALUE1("value1"), VALUE2("value2");

    private final String value;

    TargetEnum(String value) {
        this.value = value;
    }

    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRecursive {
        int counter = 1;
        TargetRecursiveInner recursiveInner = new TargetRecursiveInner();

        // Recursive inner structure
        public class TargetRecursiveInner {
            String value;
        }
    }

    public TargetInnerRecursive createInner() {
        // Local inner class (target_feature)
        class TargetLocalInner {
            void init(TargetInnerRecursive inner) {
                inner.counter = 1;
                // No new exception
            }
        }

        TargetInnerRecursive inner = new TargetInnerRecursive();
        new TargetLocalInner().init(inner);
        return inner;
    }
}