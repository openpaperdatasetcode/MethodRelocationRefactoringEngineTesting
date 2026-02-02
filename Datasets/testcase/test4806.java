package test.refactoring;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for call_method position: "the attribute values of annotations"
@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation {
    String value();
}

// Parent class for TargetClass to enable SuperConstructorInvocation
class TargetParent {
    protected String parentField1;
    protected String parentField2;

    public TargetParent(String field1, String field2) {
        this.parentField1 = field1;
        this.parentField2 = field2;
    }

    public String getParentInfo() {
        return "Parent[field1=" + parentField1 + ", field2=" + parentField2 + "]";
    }
}

// TargetClass: normal class, public modifier, has static nested class (target_feature)
public class TargetClass extends TargetParent {
    // Fields for "this.field" access (target_feature: 2)
    public String targetField1;
    public String targetField2;

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        public static String staticProcess(String input) {
            return "target_static_processed: " + input;
        }
    }

    public TargetClass(String field1, String field2) {
        super(field1, field2);
        this.targetField1 = field1;
        this.targetField2 = field2;
    }

    public String getCombinedData() {
        return targetField1 + "|" + targetField2;
    }
}

// SourceClass: normal class, public, same package, has two static nested classes (source_feature)
public class SourceClass {
    // Source contains target field (per_condition)
    private TargetClass targetField;

    // 1st static nested class (source_feature)
    public static class SourceStaticNested1 {
        public static String helperMethod1(String input) {
            return "source_static1: " + input;
        }
    }

    // 2nd static nested class (source_feature)
    public static class SourceStaticNested2 {
        public static String helperMethod2(String input) {
            return "source_static2: " + input;
        }
    }

    // Inner class for call_method (type: inner_class)
    public class SourceInner {
        // call_method: default modifier, static target_feature, super.methodName, pos: annotation attribute
        @TestAnnotation(value = "call_method_in_annotation")
        public Object callMethod(TargetClass target) {
            // Static method call (target_feature: static)
            String staticResult = TargetClass.TargetStaticNested.staticProcess(target.getCombinedData());
            // Super method call (target_feature: super.methodName(arguments))
            String superResult = target.getParentInfo();
            return staticResult + "|" + superResult;
        }
    }

    public SourceClass(TargetClass target) {
        this.targetField = target;
    }

    // Overloaded method 1 (method type: overloading), return base type (String)
    public synchronized String process() throws IOException {
        return process(targetField); // Delegate to main overloaded method
    }

    // Overloaded method 2 (method type: overloading), return base type (String)
    public synchronized String process(TargetClass target) throws IOException {
        // Per_condition: method contains target parameter
        if (target == null) {
            throw new IOException("Target parameter cannot be null");
        }

        // Try statement (method feature)
        try {
            // SuperConstructorInvocation (modifier private, pos: same_package_target, target_feature: this.field 2)
            class NestedProcessor extends TargetParent {
                private NestedProcessor(TargetClass target) {
                    super(target.targetField1, target.targetField2); // Super constructor invocation
                    // Access this.field (2 fields)
                    this.parentField1 = SourceStaticNested1.helperMethod1(this.parentField1);
                    this.parentField2 = SourceStaticNested2.helperMethod2(this.parentField2);
                }

                public String getProcessedData() {
                    return parentField1 + "|" + parentField2;
                }
            }
            NestedProcessor processor = new NestedProcessor(target);

            // Variable call (method feature)
            variableCall(target, "Processing with NestedProcessor");

            // Call inner class method (call_method)
            SourceInner inner = new SourceInner();
            Object callMethodResult = inner.callMethod(target);

            // Return base type (String)
            return processor.getProcessedData() + "|callMethodResult=" + callMethodResult;
        } catch (Exception e) {
            // no_new_exception: wrap in existing IOException
            throw new IOException("Processing failed: " + e.getMessage());
        }
    }

    // Variable call (method feature)
    private void variableCall(TargetClass target, String message) {
        System.out.printf("[SourceClass] %s | Target fields: %s, %s%n",
                message, target.targetField1, target.targetField2);
    }
}

// Test entry
public class TestEntry {
    public static void main(String[] args) {
        try {
            // Initialize target
            TargetClass target = new TargetClass("initial_field1", "initial_field2");
            
            // Initialize source (contains target field: per_condition)
            SourceClass source = new SourceClass(target);
            
            // Test overloaded methods
            String result1 = source.process(target);
            String result2 = source.process();
            
            System.out.println("Overloaded method 1 result: " + result1);
            System.out.println("Overloaded method 2 result: " + result2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    