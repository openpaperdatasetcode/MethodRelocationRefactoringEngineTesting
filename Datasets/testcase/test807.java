package com.refactoring.movemethod;

import java.util.List;
import java.util.ArrayList;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Custom annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface Refactor6147Annotation {}

// Functional interface for source implements feature
interface DataProcessor<T> {
    List<String> process(T data);
}

// Others class for method_feature: others_class
class OthersClass {
    public <T extends CharSequence> TargetClass processTarget(T data) { // with_bounds feature
        TargetClass target = new TargetClassImpl();
        target.new TargetInnerRecursive().setData(data.toString());
        return target;
    }
}

// Sealed abstract source class: sealed modifier, same package, implements + member inner + anonymous inner classes
sealed abstract class SourceClass<T extends CharSequence> implements DataProcessor<T> permits SourceClassImpl {
    // per_condition: source contains field of target
    protected TargetClass targetField = new TargetClassImpl();

    // Member inner class (source feature)
    protected class SourceMemberInner {
        private String innerField = "source_member_inner_6147";
        
        // Instance method for call_method target_feature: OuterClass.InnerClass.methodName()
        public static int staticInnerMethod(TargetClass.TargetInnerRecursive inner) { // target_feature: static
            return inner.getData().length();
        }
    }

    /**
     * Method Javadoc (method javadoc feature)
     * Overloaded method to process target inner recursive class
     * @param targetParam Target inner recursive instance
     * @return List<String> processed result
     */
    // Overloaded method 1 (method type: overloading)
    @Refactor6147Annotation // has_annotation feature
    protected List<String> methodToMove(TargetClass.TargetInnerRecursive targetParam) {
        return methodToMove(targetParam, "default_arg");
    }

    /**
     * Method Javadoc (method javadoc feature)
     * Overloaded method with extra argument
     * @param targetParam Target inner recursive instance
     * @param extra Extra string for processing
     * @return List<String> processed result
     */
    // Overloaded method 2 (method to be refactored: overloading, List<String> return, protected access)
    @Refactor6147Annotation // has_annotation feature
    protected List<String> methodToMove(TargetClass.TargetInnerRecursive targetParam, String extra) {
        // NullPointerException feature (validation)
        if (targetParam == null) {
            throw new NullPointerException("TargetInnerRecursive cannot be null (ID:6147)");
        }

        // variable call feature
        SourceMemberInner memberInner = new SourceMemberInner();
        String varCall = memberInner.innerField + extra;

        // expression statement feature
        targetParam.setData(varCall); // expression statement

        // Instance method (type: instance, modifier: public, pos: ternary operators, return TargetClass Type)
        public TargetClass nestedInstanceMethod() {
            int count = 3; // method_feature: "3"
            // pos: ternary operators
            TargetClass target = (count == 3) 
                ? // method_feature: outerInstance.new InnerClass().methodName()
                  new OthersClass().processTarget(varCall) // method_feature: others_class + instance
                : targetField;
            return target; // return_type: TargetClass Type
        }
        TargetClass processedTarget = nestedInstanceMethod();

        // with_bounds feature (type bound check)
        if (varCall instanceof T boundedData && boundedData.length() > 3) {
            varCall += "_bounded_" + boundedData.length();
        }

        // Anonymous inner class (source feature)
        DataProcessor<String> anonymousProcessor = new DataProcessor<String>() {
            @Override
            public List<String> process(String data) {
                List<String> list = new ArrayList<>();
                list.add(data);
                list.add(processedTarget.new TargetInnerRecursive().getData());
                return list;
            }
        };

        // no_new_exception (no explicit new Exception instantiation beyond NPE requirement)
        return anonymousProcessor.process(varCall);
    }

    // Call method: source type, synchronized modifier, static, pos=switch, returns int
    public synchronized int callMethod(int switchCase) { // target_feature: synchronized
        switch (switchCase) { // pos: switch
            case 1:
                // target_feature: OuterClass.InnerClass.methodName()
                return SourceMemberInner.staticInnerMethod(targetField.new TargetInnerRecursive()); // target_feature: static
            case 2:
                List<String> result = methodToMove(targetField.new TargetInnerRecursive());
                return result.size();
            default:
                return 0;
        }
    }

    // implements feature method implementation
    @Override
    public abstract List<String> process(T data);
}

// Concrete implementation of sealed source class
final class SourceClassImpl<T extends CharSequence> extends SourceClass<T> {
    @Override
    public List<String> process(T data) {
        return methodToMove(targetField.new TargetInnerRecursive(), data.toString());
    }
}

// Final abstract target class: final modifier, member inner class (target_feature)
public final abstract class TargetClass {
    // Target inner recursive class (target_inner_rec)
    public class TargetInnerRecursive {
        private String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        // Recursive method (recursive class context)
        public int recursiveLength(int val) {
            if (val <= 0) return data.length();
            return val + recursiveLength(val - 1);
        }
    }

    public abstract TargetInnerRecursive new TargetInnerRecursive();
}

// Concrete implementation of abstract target class
class TargetClassImpl extends TargetClass {
    @Override
    public TargetInnerRecursive new TargetInnerRecursive() {
        return new TargetInnerRecursive();
    }
}