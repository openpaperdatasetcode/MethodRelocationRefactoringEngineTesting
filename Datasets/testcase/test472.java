package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation with attribute for "the attribute values of annotations" pos
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {
    int value() default 3; // Attribute value 3 matches method_feature "3"
}

// Parent class for overriding feature
class ParentClass {
    public int overrideMethod(TargetClass targetParam) {
        return 0;
    }
}

// Source normal class (default modifier, same package as target, member inner + static nested class)
class SourceClass extends ParentClass {
    // Static nested class feature (source class feature)
    public static class StaticNestedClass {
        public String nestedMethod() {
            return "nestedValue";
        }
    }

    // Member inner class (source_inner for method position)
    public class MemberInnerClass {
        // Instance method for access_instance_method feature
        public int instanceMethod() {
            return 10;
        }

        /**
         * Target overriding method (default access, base type return, source_inner position)
         * Precondition: contains target class parameter
         */
        @RefactorAnnotation(value = 3) // has_annotation + annotation attribute pos
        @Override
        public int overrideMethod(TargetClass targetParam) {
            // Variable call feature
            StaticNestedClass staticNested = new StaticNestedClass();
            String varCall = staticNested.nestedMethod();
            int varLength = varCall.length();

            // Expression statement feature
            varLength += 5;
            varLength *= 2; // Expression statement (modification)

            // Super keywords feature
            int superResult = super.overrideMethod(targetParam);
            varLength += superResult;

            // Access_instance_method feature
            int instanceMethodResult = this.instanceMethod();
            varLength += instanceMethodResult;

            // Instance nested method (private modifier, annotation attribute pos, void return)
            @RefactorAnnotation(3) // Annotation attribute pos requirement
            private void instanceNestedMethod() {
                // Method features: 3 (literal), inner_class, instance, obj.m1().m2().m3()
                int literalThree = 3;
                InnerHelperClass inner = new InnerHelperClass();
                
                // obj.m1().m2().m3() feature (method chaining)
                String chainResult = inner.m1().m2().m3(literalThree);
                System.out.println(chainResult);
            }

            // Inner class for instanceNestedMethod (inner_class feature)
            private class InnerHelperClass {
                public InnerHelperClass m1() { return this; }
                public InnerHelperClass m2() { return this; }
                public String m3(int num) { return "chain_" + num; }
            }

            // Invoke nested instance method
            instanceNestedMethod();

            // No_new_exception (implicit NPE if targetParam is null, no explicit new exception)
            if (targetParam == null) {
                throw new NullPointerException(); // Reuse standard exception
            }

            return varLength; // Base type return (int)
        }
    }

    // Helper method to instantiate inner class and invoke target method
    public void invokeTargetMethod() {
        MemberInnerClass inner = new MemberInnerClass();
        inner.overrideMethod(new TargetClass());
    }
}

// Target normal class (strictfp modifier, static nested class target_feature)
strictfp class TargetClass {
    // Static nested class target_feature
    public static class TargetStaticNestedClass {
        public int nestedField = 5;
    }

    // Instance of static nested class for access
    public TargetStaticNestedClass staticNested = new TargetStaticNestedClass();
}