package com.refactoring.movemethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

// Annotation for instance method feature pos: attribute values of annotations
@interface MethodAnnotation {
    String value() default "(targetParam) -> sourceInstance.process(targetParam)";
}

// Super class for source class extends feature
class SuperSourceClass {
    protected String superField = "superValue";
}

// Interface for source class implements feature
interface SourceInterface {
    List<String> process(TargetClass target);
}

// Source class: private, extends, implements, member inner class, local inner class, same package as target
private class SourceClass extends SuperSourceClass implements SourceInterface {
    // Per_condition: source contains target class field
    private TargetClass targetField = new TargetClass();

    // Member inner class (source class feature)
    class SourceInnerClass {
        String innerField = "innerValue";
    }

    /**
     * Method Javadoc (method feature)
     * Instance method, List<String> return, default access, in source class
     * @return List<String> result
     * @throws IOException required throws feature
     */
    List<String> methodToRefactor() throws IOException { // requires_throws feature
        // Variable call (target field and its local inner class)
        String targetValue = targetField.getLocalInnerValue();
        
        // Local inner class (source class feature)
        class LocalInnerClass {
            String processTarget(String input) {
                return input + new SourceInnerClass().innerField;
            }
        }
        String processedValue = new LocalInnerClass().processTarget(targetValue);
        
        // Instance method feature: default modifier, 1, source, instance, (parameters) -> methodBody, pos=annotation attribute, return List<String>
        @MethodAnnotation // pos: the attribute values of annotations
        default List<String> instanceHelperMethod() {
            List<String> result = new ArrayList<>();
            int count = 1; // method_feature: 1
            // (parameters) -> methodBody (lambda expression, source class instance)
            SourceClass sourceInstance = new SourceClass();
            result.add(((TargetClass param) -> sourceInstance.methodToRefactor().get(0)).apply(targetField));
            for (int i = 0; i < count; i++) {
                result.add(processedValue);
            }
            return result;
        }
        List<String> helperResult = instanceHelperMethod();
        
        // Used by reflection feature
        try {
            Method method = TargetClass.class.getDeclaredMethod("getLocalInnerValue");
            method.setAccessible(true);
            String reflectionValue = (String) method.invoke(targetField);
            helperResult.add(reflectionValue);
        } catch (Exception e) {
            // Wrap in required throws exception
            throw new IOException("Reflection error", e);
        }
        
        helperResult.add(targetValue);
        return helperResult;
    }

    // Implement SourceInterface (implements feature)
    @Override
    public List<String> process(TargetClass target) {
        try {
            return methodToRefactor();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}

// Target class: public, local inner class feature, same package as source
public class TargetClass {
    private String targetValue = "targetValue";

    public String getLocalInnerValue() {
        // Local inner class (target class feature)
        class TargetLocalInnerClass {
            String getValue() {
                return targetValue;
            }
        }
        return new TargetLocalInnerClass().getValue();
    }
}