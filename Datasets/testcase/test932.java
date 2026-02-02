package com.refactoring.test;

// Functional interface for target_class implements feature
interface TargetInterface {
    void processField(String value);
}

// Target class (normal class, public modifier, implements + member inner class)
public class TargetClass implements TargetInterface {
    String targetField; // For per_condition (source contains this field)

    // Member inner class (target_feature)
    public class TargetInnerRec {
        String innerField;
        TargetInnerRec nestedInner; // Recursive feature (target_inner_rec)

        // Accessor method for method_feature
        public void setInnerField(String value) { // accessor type
            this.innerField = value + "_" + 1; // 1 from method_feature
        }

        // Accessor method (getter)
        public String getInnerField() {
            return this.innerField;
        }
    }

    // Implements interface method (target_feature: implements)
    @Override
    public void processField(String value) {
        this.targetField = value + "_impl_" + 1;
    }
}

// Source class (normal class, protected modifier, same package, anonymous inner + member inner class)
protected class SourceClass {
    // Member inner class (source_feature)
    class SourceMemberInner {
        // Inner recursive class (source_inner_rec for method_position)
        class SourceInnerRecursive {
            // TypeDeclarationStatement (static modifier, obj.field, 1, pos=source)
            private static void typeDeclStatement(TargetClass.TargetInnerRec target) {
                // Type declaration statement + obj.field + 1
                String fieldVal = target.innerField + "_type_decl_" + 1;
                TargetClass.TargetInnerRec newInner = new TargetClass().new TargetInnerRec();
                newInner.innerField = fieldVal;
            }

            // Accessor method (public modifier, method_feature:1/inner_class/accessor/instanceReference.methodName(arguments), pos=exception throwing, return void)
            public void accessorMethod(TargetClass.TargetInnerRec target) {
                if (target == null) {
                    throw new NullPointerException("Target null: " + 1); // pos=exception throwing statements
                }
                // instanceReference.methodName(arguments) + inner_class + 1
                target.setInnerField(target.getInnerField() + "_accessor_" + 1);
            }

            // Method to be refactored (varargs, TargetClass return, private access, source_inner_rec)
            private TargetClass moveMethod(TargetClass.TargetInnerRec... targetParams) {
                // Per_condition: contains target parameter (target_inner_rec)
                if (targetParams == null || targetParams.length == 0) {
                    throw new NullPointerException("No target params: " + 1); // NullPointerException + 1
                }

                // Super constructor invocation (Object superclass)
                Object superObj = new Object();

                // TypeDeclarationStatement invocation (pos=source)
                typeDeclStatement(targetParams[0]);

                // Additional type declaration statement
                TargetClass outerTarget = new TargetClass();
                TargetClass.TargetInnerRec firstInner = targetParams[0];

                // Accessor method call (pos=exception throwing statements)
                try {
                    accessorMethod(firstInner);
                } catch (NullPointerException e) {
                    firstInner = outerTarget.new TargetInnerRec();
                    firstInner.innerField = "default_" + 1;
                }

                // Variable call (access target inner field - per_condition)
                String varCall = firstInner.getInnerField();
                firstInner.setInnerField(varCall + "_var_modified_" + 1);

                // Process recursive structure
                if (firstInner.nestedInner == null) {
                    firstInner.nestedInner = outerTarget.new TargetInnerRec();
                    firstInner.nestedInner.innerField = "nested_" + 1;
                }

                // Anonymous inner class (source_feature)
                Runnable anonymous = new Runnable() {
                    @Override
                    public void run() {
                        outerTarget.processField(firstInner.innerField);
                    }
                };
                anonymous.run();

                // No new exception
                return outerTarget;
            }
        }
    }
}