// Source class package
package com.refactoring.source;

import com.refactoring.target.TargetClass;
import java.util.ArrayList;
import java.util.List;

// Source class: generic, final, implements, two member inner classes, different package from target
final class SourceClass<T extends CharSequence> implements Runnable {
    // First member inner class
    class FirstInnerClass {}

    // Second member inner class
    class SecondInnerClass {}

    // Super interface/method for overriding
    interface OverrideInterface {
        List<String> methodToRefactor(TargetClass<String> targetParam);
    }

    // Implement overriding feature
    @Override
    public List<String> methodToRefactor(TargetClass<String> targetParam) { // Per_condition: contains target parameter
        // Type declaration statement
        List<String> result = new ArrayList<>();
        
        // Variable call (target parameter's inner class field)
        String targetValue = targetParam.innerClass.getValue();
        
        // Expression statement
        result.add(targetValue);
        
        // Synchronized statement
        synchronized (this) {
            result.add("synchronized_" + targetValue);
        }
        
        // Switch case feature
        int caseValue = 1;
        switch (caseValue) {
            case 1:
                result.add("case1_" + targetValue);
                break;
            case 2:
                result.add("case2_" + targetValue);
                break;
            default:
                result.add("default_" + targetValue);
        }
        
        // Feature: numbers=1, modifier=default, exp=Name
        default String nameFeature() {
            String name = "Feature1"; // numbers=1, exp=Name
            result.add(name);
            return name;
        }
        nameFeature();
        
        // No new exception feature
        try {
            Integer.parseInt(targetValue);
        } catch (NumberFormatException e) {
            // No throw new exception
            result.add("parse_error");
        }
        
        return result;
    }

    // Runnable implementation (implements feature)
    @Override
    public void run() {
        new FirstInnerClass();
        new SecondInnerClass();
    }
}

// Target class package (different from source)
package com.refactoring.target;

import com.refactoring.source.SourceClass;
import java.util.List;

// Target class: generic, abstract, member inner class feature
abstract class TargetClass<U> {
    // Member inner class (target_feature)
    public class InnerClass {
        private U value;

        public U getValue() {
            return value;
        }

        public void setValue(U value) {
            this.value = value;
        }
    }

    // Instance of inner class
    InnerClass innerClass = new InnerClass();

    // call_method: target type, private modifier, static, (parameters) -> methodBody, pos=property assignment, return int
    private static int callMethod; // Property assignment position
    
    // Initialize call_method via lambda (property assignment)
    static {
        // (parameters) -> methodBody feature (lambda expression)
        callMethod = ((SourceClass<String> source, TargetClass<String> target) -> {
            List<String> result = source.methodToRefactor(target);
            return result.size();
        }).apply(new SourceClass<>(), new TargetClass<String>() {});
    }
}