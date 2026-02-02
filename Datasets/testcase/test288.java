package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

// Abstract normal source class (abstract modifier, same package as target)
public abstract class SourceClass {
    // Instance field for access_instance_field feature
    protected String instanceField = "source_instance_field";

    // Static nested class (source class feature)
    public static class SourceStaticNested {
        private int nestedField;

        public SourceStaticNested(int nestedField) {
            this.nestedField = nestedField;
        }

        public int getNestedField() {
            return nestedField;
        }
    }

    // Parent method for overriding
    public abstract List<String> overrideMethod(TargetClass targetParam);

    // ConstructorInvocation (private modifier, pos: source, target_feature: obj.field, 1)
    private static class ConstructorInvocation {
        // obj.field feature
        public String field;

        // Constructor with "1" (numeric literal) feature
        public ConstructorInvocation() {
            this.field = "constructor_field_" + 1;
        }
    }

    // Instance method (protected modifier, pos: array initialization, return TargetClass type)
    protected TargetClass instanceMethod(SourceClass outerInstance) {
        // Method feature: 1 (numeric literal)
        int count = 1;
        // Method feature: parent_class (reference to parent/super)
        SourceClass parentRef = outerInstance;
        // Method feature: instance (instance reference)
        // Method feature: outerInstance.new InnerClass().methodName()
        TargetClass.TargetStaticNested innerObj = outerInstance.new TargetClass.TargetStaticNested();
        innerObj.setNestedData(parentRef.instanceField + count);

        // Array initialization position
        TargetClass[] targetArray = new TargetClass[]{
            new TargetClass(innerObj.getNestedData())
        };

        return targetArray[0];
    }

    // Overriding method to refactor (public access, returns List<String>, has target parameter)
    @Override
    public List<String> overrideMethod(TargetClass targetParam) {
        // Per_condition: method contains target parameter
        if (targetParam == null) {
            return new ArrayList<>();
        }

        // ConstructorInvocation usage (source position)
        ConstructorInvocation constructorInv = new ConstructorInvocation();
        // obj.field feature usage
        String constructorFieldVal = constructorInv.field;

        // Access_instance_field feature
        String sourceInstanceField = this.instanceField;

        // Variable call feature
        TargetClass.TargetStaticNested targetNested = targetParam.new TargetStaticNested();
        targetNested.setNestedData(constructorFieldVal);
        String targetFieldVal = targetNested.getNestedData();

        // Array initialization for instance method call
        SourceClass[] outerInstances = new SourceClass[]{this};
        TargetClass instanceMethodResult = instanceMethod(outerInstances[0]);

        // Build result list
        List<String> result = new ArrayList<>();
        result.add(sourceInstanceField);
        result.add(targetFieldVal);
        result.add(instanceMethodResult.getTargetField());

        // Local inner class (source class feature)
        class LocalInnerClass {
            private String localField = result.get(0);

            public String getLocalField() {
                return localField;
            }
        }
        LocalInnerClass localInner = new LocalInnerClass();
        result.add(localInner.getLocalField());

        return result;
    }
}

// Public normal target class (same package as source, static nested class feature)
public class TargetClass {
    // Instance field
    private String targetField;

    public TargetClass(String targetField) {
        this.targetField = targetField;
    }

    // Static nested class (target_feature)
    public static class TargetStaticNested {
        private String nestedData;

        public void setNestedData(String nestedData) {
            this.nestedData = nestedData;
        }

        public String getNestedData() {
            return nestedData;
        }
    }

    public String getTargetField() {
        return targetField;
    }
}