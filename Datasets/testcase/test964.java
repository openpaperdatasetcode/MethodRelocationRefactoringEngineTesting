package com.refactoring.movemethod;

// Super class for super keywords feature
class SuperClass {
    protected String superField = "SUPER_VALUE"; // ClassName.field + 1 feature base
    public SuperClass(String val) {} // For this(arguments) feature
}

// Abstract normal source class (same package, two member inner classes)
abstract class SourceClass extends SuperClass {
    // Target field reference (per_condition: source contains target field)
    private TargetClass.TargetInner targetFieldRef;

    // First member inner class (source feature)
    class SourceMemberInner1 {
        public void updateTarget(TargetClass.TargetInner inner) {
            inner.setInnerField("updated_by_inner1");
        }
    }

    // Second member inner class (source feature)
    class SourceMemberInner2 {
        public String getTargetValue(TargetClass.TargetInner inner) {
            return inner.getInnerField();
        }
    }

    // Constructor for this(arguments) feature
    public SourceClass() {
        this("default"); // this(arguments) call
    }

    public SourceClass(String val) {
        super(val); // super keywords for constructor
    }

    // Normal method (public access, TargetClass Type return, source position)
    public TargetClass.TargetInner processTarget(TargetClass.TargetInner targetParam) {
        // Per_condition: contains target parameter (variable call)
        if (targetParam == null) {
            TargetClass target = new TargetClass();
            targetFieldRef = target.new TargetInner();
        } else {
            targetFieldRef = targetParam;
        }

        // Type declaration statement
        String targetVal = targetFieldRef.getInnerField();
        // super keywords (access super class field)
        String combinedVal = super.superField + "_" + targetVal;

        // For statement
        for (int i = 0; i < 3; i++) {
            // Depends_on_inner_class: use source member inner classes
            new SourceMemberInner1().updateTarget(targetFieldRef);
            targetVal = new SourceMemberInner2().getTargetValue(targetFieldRef);
        }

        // DoStatement (private modifier, ClassName.field, 1, pos: source)
        privateDoStatement(targetFieldRef);

        // No_new_exception (empty try-catch)
        try {
            // Additional variable call
            targetFieldRef.setInnerField(combinedVal + "_final");
        } catch (Exception e) {
            // Do not throw new exception
            targetFieldRef.setInnerField("fallback_value");
        }

        return targetFieldRef;
    }

    // Private DoStatement method
    private void privateDoStatement(TargetClass.TargetInner inner) {
        // ClassName.field + 1 feature (SuperClass.superField as ClassName.field)
        int count = 1;
        do {
            String fieldVal = SuperClass.superField + "_" + count;
            inner.setInnerField(fieldVal);
            count++;
        } while (count <= 1); // 1 feature (loop runs once)
    }
}

// Protected target normal class (local inner class feature)
protected class TargetClass {
    // Target inner class (target_inner)
    class TargetInner {
        private String innerField = "default_inner"; // Target field for per_condition

        public String getInnerField() {
            return innerField;
        }

        public void setInnerField(String innerField) {
            this.innerField = innerField;
        }
    }

    // Local inner class (target feature)
    public void targetMethod() {
        class TargetLocalInner {
            void processInner(TargetInner inner) {
                inner.setInnerField(inner.getInnerField() + "_local_processed");
            }
        }
        new TargetLocalInner().processInner(new TargetInner());
    }
}