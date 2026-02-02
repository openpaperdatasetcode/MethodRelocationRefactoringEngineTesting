package test;

import java.lang.annotation.*;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CustomAnnotation {
    String value() default "processed";
}

// Interface for source class to implement
interface DataProcessor {
    String process(String data);
}

// Source: public normal class with implements, static nested class, and member inner class
public class SourceClass implements DataProcessor {
    // Static nested class (source_feature)
    public static class SourceStaticNested {
        public static String staticProcess(String input) {
            return "static_processed:" + input;
        }
    }

    // Member inner class (source_feature)
    public class SourceInner {
        private String innerData;

        public SourceInner(String data) {
            this.innerData = data;
        }

        public String getInnerData() {
            return innerData;
        }
    }

    // Protected normal method to be refactored
    @CustomAnnotation // has_annotation
    protected TargetClass normalMethod(TargetClass target, String input) {
        // Variable call: use target parameter and its methods
        String targetData = target.getTargetData();
        target.setTargetData(SourceStaticNested.staticProcess(input));
        
        // Create and use member inner class
        SourceInner inner = new SourceInner(targetData);
        target.addToHistory(inner.getInnerData());

        // Requires_try_catch: handle potential exceptions
        try {
            // Process data using interface method implementation
            String processed = process(input);
            target.setTargetData(processed);
            
            // Use target's static nested class
            TargetClass.TargetStatic.staticLog("Processed: " + processed);
        } catch (IllegalArgumentException e) {
            // Handle specific exception without throwing new one
            target.setTargetData("error: " + e.getMessage());
        } catch (Exception e) {
            // Handle general exceptions
            target.setTargetData("general_error");
        }

        // Return TargetClass Type
        return target;
    }

    // Implement DataProcessor interface method
    @Override
    public String process(String data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Input data cannot be empty");
        }
        return "processed:" + data;
    }
}
    package test;

import java.util.ArrayList;
import java.util.List;

// Target: public normal class with static nested class (target_feature)
public class TargetClass {
    private String targetData;
    private List<String> history = new ArrayList<>();

    // Static nested class (target_feature)
    public static class TargetStatic {
        public static void staticLog(String message) {
            System.out.println("TargetLog: " + message);
        }

        public static String format(String input) {
            return "formatted:" + input;
        }
    }

    public TargetClass() {
        this.targetData = "default_data";
    }

    public TargetClass(String initialData) {
        this.targetData = TargetStatic.format(initialData);
    }

    // Getter for variable call
    public String getTargetData() {
        return targetData;
    }

    // Setter for variable call
    public void setTargetData(String targetData) {
        this.targetData = targetData;
    }

    public void addToHistory(String entry) {
        history.add(entry);
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }
}
    