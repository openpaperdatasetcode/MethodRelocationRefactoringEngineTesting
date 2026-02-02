package test.refactoring;

import other.package.DiffPackageClass;
import java.util.function.Function;

// Diff package class for "pos: diff_package_others"
package other.package;
public class DiffPackageClass {
    public String processData(String input) {
        return "diff_package_processed:" + input;
    }
}
package test.refactoring;

// Interface for target_class "implements" feature
interface Processable {
    String process(String data);
}

// Parent class for super constructor invocation
class ParentSource {
    protected String parentData;

    public ParentSource(String parentData) {
        this.parentData = parentData;
    }
}

// SourceClass: protected, same package, has member inner & static nested class
protected class SourceClass extends ParentSource {
    // Static nested class (source_feature)
    static class SourceStaticNested {
        public static String staticProcess(String input) {
            return "static_nested:" + input;
        }
    }

    // Member inner class (source_feature)
    public class SourceMemberInner {
        private int innerCount;

        public SourceMemberInner(int count) {
            this.innerCount = count;
        }
    }

    // Inner recursive class (method_position: source_inner_rec)
    public class SourceInnerRec {
        // Method: instance type, return base type (String), private access
        private String instanceMethod(TargetClass targetParam) {
            // Super constructor invocation through parent class
            super.parentData = "updated_parent_data";
            
            // ConstructorInvocation: private modifier, pos: diff_package_others, target_feature: obj.field (1)
            DiffPackageClass diffObj = new DiffPackageClass();
            private String processedDiff = diffObj.processData(targetParam.targetField); // obj.field access
            
            // Overriding method feature: public modifier, pos: property assignment
            Function<TargetClass, TargetClass> overrideFunc = (t) -> {
                t.setTargetField(t.getTargetField() + "_overridden");
                return t;
            };
            TargetClass modifiedTarget = overrideFunc.apply(targetParam);
            
            // For statement
            SourceMemberInner memberInner = new SourceMemberInner(3);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < memberInner.innerCount; i++) {
                result.append(SourceStaticNested.staticProcess(modifiedTarget.getTargetField() + "_" + i)).append("|");
            }
            
            // Access instance method (target's instance methods)
            result.append(modifiedTarget.targetInner.innerMethod(processedDiff));
            
            // Variable call
            variableCall(modifiedTarget, "Loop iterations: " + memberInner.innerCount);
            
            return result.toString();
        }

        // Variable call feature
        private void variableCall(TargetClass target, String message) {
            System.out.printf("[SourceInnerRec] %s | Target data: %s%n",
                message, target.getTargetField());
        }
    }

    public SourceClass() {
        super("initial_parent_data");
    }

    // Method to trigger inner recursive class method
    public String startProcessing(TargetClass target) {
        SourceInnerRec innerRec = new SourceInnerRec();
        return innerRec.instanceMethod(target); // Method contains target parameter (per_condition)
    }
}

// TargetClass: strictfp modifier, implements interface, has member inner class
strictfp class TargetClass implements Processable {
    // Field for "obj.field" access
    String targetField;

    // Member inner class (target_feature)
    public class TargetMemberInner {
        public String innerMethod(String input) {
            return "target_inner_processed:" + input;
        }
    }

    public TargetClass(String targetField) {
        this.targetField = targetField;
    }

    // Implement interface method (target_feature: implements)
    @Override
    public String process(String data) {
        return "target_processed:" + data;
    }

    // Instance methods for access_instance_method feature
    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }

    // Instance of member inner class
    public final TargetMemberInner targetInner = new TargetMemberInner();
}

// Test entry
class TestEntry {
    public static void main(String[] args) {
        TargetClass target = new TargetClass("test_data");
        SourceClass source = new SourceClass();
        String result = source.startProcessing(target);
        System.out.println("Processing result: " + result);
    }
}
    