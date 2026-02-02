package refactoring.test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

// Target class: default modifier, member inner class (target_feature), same package as source
class TargetClass {
    private String targetField = "targetValue";
    
    // Member inner class (target_feature)
    class TargetInnerClass {
        String innerField = "innerTargetValue";
        
        // Instance method for access_instance_method feature
        String getInnerData() {
            return innerField;
        }
    }
    
    public String getTargetField() {
        return targetField;
    }
}

// Source class: public modifier, two anonymous inner classes (source_feature), same package as target
public class SourceClass {
    // First anonymous inner class (source_feature)
    private Runnable firstAnonymous = new Runnable() {
        @Override
        public void run() {
            System.out.println("First anonymous inner class");
        }
    };

    // Second anonymous inner class (source_feature)
    private List<String> secondAnonymous = new ArrayList<String>() {
        @Override
        public boolean add(String e) {
            return super.add(e.toUpperCase());
        }
    };

    private String helperMethod(String input) {
        return input + "_processed";
    }

    // Instance method: default access, List<String> return type, target parameter (per_condition)
    List<String> refactorMethod(TargetClass targetParam) {
        List<String> result = new ArrayList<>();
        int count = 0;

        // Do statement (method feature)
        do {
            // Variable call (method feature)
            String varCall = targetParam.getTargetField();
            
            // Access instance method (access_instance_method feature)
            TargetClass.TargetInnerClass innerObj = targetParam.new TargetInnerClass();
            String innerData = innerObj.getInnerData();
            
            // this.methodName(arguments) (method feature)
            String processed = this.helperMethod(varCall + innerData);
            result.add(processed);
            
            count++;
        } while (count < 3);

        // Used_by_reflection (method feature)
        try {
            Method targetMethod = TargetClass.class.getMethod("getTargetField");
            String reflectedValue = (String) targetMethod.invoke(targetParam);
            result.add("reflected: " + reflectedValue);
        } catch (Exception e) {
            // No new exception thrown (method feature)
            result.add("reflection error: " + e.getMessage());
        }

        // No new exception thrown (method feature) - no 'new Exception()' statements
        return result;
    }
}