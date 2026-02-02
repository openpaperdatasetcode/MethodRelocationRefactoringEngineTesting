package refactoring.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

// Functional interface for permits feature (Java 17+ sealed class feature adaptation)
sealed interface SealedInterface permits SourceClass {}

// Source normal class (public, same package, implements + permits + member + local inner class)
public class SourceClass implements SealedInterface {
    // Precondition: source contains target class field
    private TargetClass targetField = new TargetClass();
    // Protected outer field for access_outer_protected feature
    protected String outerProtectedField = "outerProtectedValue";

    // Member inner class (source_inner for method position)
    public class MemberInnerClass {
        // Instance field for access_instance_field feature
        private int instanceField = 10;

        // Target overloading method 1 (default access, void return, source_inner)
        public void targetMethod(TargetClass.MemberInnerClassTarget.NestedInnerClass targetParam) {
            doStatementFeature();
            forStatementFeature();
            superConstructorInvocationFeature();
            tryStatementFeature();
            variableCallFeature(targetParam);
            accessOuterProtectedFeature();
            accessInstanceFieldFeature();
            requiresTryCatchFeature();
            constructorNestedMethod(1);
        }

        // Target overloading method 2 (overloading feature)
        public void targetMethod(TargetClass.MemberInnerClassTarget.NestedInnerClass targetParam, String arg) {
            targetMethod(targetParam); // Call overloaded method
        }

        // Constructor nested method (public modifier, collection operations pos, base type return)
        private int constructorNestedMethod(int num) {
            // Method features: 1 (literal), parent_class, constructor, instanceReference.methodName(arguments)
            int literalOne = 1;
            ParentClass parentObj = new ParentClass(); // parent_class feature
            
            // Collection operations pos requirement
            Collection<String> collection = new ArrayList<>();
            collection.add("collectionOp");
            
            // Constructor feature
            MemberInnerClass innerInstance = new MemberInnerClass();
            
            // InstanceReference.methodName(arguments)
            innerInstance.accessInstanceFieldFeature();
            
            return literalOne + num; // Base type return (int)
        }

        // Do statement feature
        private void doStatementFeature() {
            int i = 0;
            do {
                i++;
            } while (i < 5);
        }

        // For statement feature
        private void forStatementFeature() {
            for (int j = 0; j < 3; j++) {
                System.out.println(j);
            }
        }

        // Super constructor invocation feature
        private void superConstructorInvocationFeature() {
            Runnable anonymousSub = new Runnable() {
                {
                    super(); // Super constructor invocation
                }
                @Override
                public void run() {}
            };
        }

        // Try statement feature
        private void tryStatementFeature() {
            try {
                System.out.println(instanceField);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("Try finally block");
            }
        }

        // Variable call feature
        private void variableCallFeature(TargetClass.MemberInnerClassTarget.NestedInnerClass targetParam) {
            String varCall = targetParam.innerField;
            System.out.println(varCall);
        }

        // Access_outer_protected feature
        private void accessOuterProtectedFeature() {
            String accessed = SourceClass.this.outerProtectedField;
            System.out.println(accessed);
        }

        // Access_instance_field feature
        private void accessInstanceFieldFeature() {
            int accessed = this.instanceField;
            System.out.println(accessed);
        }

        // Requires try-catch feature
        private void requiresTryCatchFeature() {
            try {
                Class.forName("refactoring.test.TargetClass");
            } catch (ClassNotFoundException e) { // Mandatory try-catch
                e.printStackTrace();
            }
        }
    }

    // Local inner class feature (source class feature)
    public void sourceLocalInnerClass() {
        class LocalInnerClass {
            void innerMethod() {
                MemberInnerClass inner = new MemberInnerClass();
                inner.targetMethod(targetField.new MemberInnerClassTarget().new NestedInnerClass());
            }
        }
        new LocalInnerClass().innerMethod();
    }

    // Parent class for call_method (parent_class type)
    static class ParentClass {
        // Call method (default modifier, Lambda expressions pos, String return)
        String callMethod() {
            // Lambda expressions pos requirement
            Consumer<String> lambda = s -> System.out.println(s);
            lambda.accept("lambdaExpr");

            // Accessor target_feature
            TargetClass target = new TargetClass();
            target.setInnerField("accessorSet");
            String accessorGet = target.getInnerField();

            // superTypeReference.methodName(arguments) feature
            Object superTypeRef = Object.class.getSimpleName();
            String result = String.valueOf(superTypeRef);

            return accessorGet + result;
        }
    }
}

/**
 * Javadoc for target class (target_feature)
 * This is the target class with extends and member inner class features
 */
public class TargetClass extends ParentTargetClass {
    private String innerField = "targetInnerField";

    // Accessor methods (for call_method accessor feature)
    public String getInnerField() { return innerField; }
    public void setInnerField(String value) { this.innerField = value; }

    // Member inner class target_feature (target_inner_rec context)
    public class MemberInnerClassTarget {
        // Nested inner class (target_inner_rec)
        public class NestedInnerClass {
            String innerField = "nestedInnerField";
        }
    }
}

// Parent class for TargetClass's extends feature
class ParentTargetClass {}