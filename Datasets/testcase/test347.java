package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

// Abstract normal source class (extends + implements, same package as target)
abstract class SourceClass extends BaseClass implements Runnable {
    // Source contains target class field (satisfies per_condition)
    private TargetClass targetField = new TargetClass();

    // Private outer field for access_outer_private feature
    private String outerPrivateField = "outer_private_value";

    // Inner class containing the target method (source_inner position)
    public class SourceInnerClass {
        /**
         * Javadoc for the instance method to be refactored
         * @param targetParam parameter of target class type
         * @return List<String> result collection
         */
        @CustomAnnotation // has_annotation feature
        List<String> instanceMethod(TargetClass targetParam) {
            // Variable call
            String localVar = outerPrivateField;
            List<String> result = new ArrayList<>();

            // Raw type usage
            List rawList = result;

            // VariableDeclarationStatement (transient modifier, super.field + 3, same_package_target pos)
            transient int transientVar = SourceClass.super.superField + 3;

            // CreationReference with number 3, default modifier
            Supplier<TargetClass> creationRef = TargetClass::new; // CreationReference (3)
            TargetClass createdTarget = creationRef.get();

            // While statement with abstract method feature (default modifier, 2, sub_class, abstract)
            int count = 2;
            while (count > 0) {
                // superTypeReference.methodName(arguments)
                TargetSubClass subInstance = new TargetSubClass();
                TargetClass abstractResult = subInstance.abstractMethod(createdTarget);
                rawList.add(abstractResult.toString());
                count--;
            }

            // Access outer private field
            String privateVal = SourceClass.this.outerPrivateField;
            rawList.add(privateVal);

            // Requires try-catch block
            try {
                if (targetParam == null) {
                    throw new NullPointerException();
                }
                rawList.add(targetParam.toString());
            } catch (NullPointerException e) {
                rawList.add("null_target_param");
            }

            return result;
        }
    }

    @Override
    public void run() {
        // Implements Runnable interface method
    }
}

// Annotation for has_annotation feature
@interface CustomAnnotation {}

// Base class for SourceClass extension (super.field source)
class BaseClass {
    protected int superField = 0;
}

// Public normal target class (anonymous inner class feature)
public class TargetClass {
    // Anonymous inner class (target_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous inner in TargetClass");
        }
    };
}

// Sub-class of TargetClass (abstract method feature - sub_class)
class TargetSubClass extends TargetClass {
    // Abstract method (default modifier, returns TargetClass, superTypeReference.methodName)
    abstract TargetClass abstractMethod(TargetClass param);
}