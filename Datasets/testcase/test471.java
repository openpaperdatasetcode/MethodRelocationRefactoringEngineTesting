package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface InstanceMethodAnnotation {}

/**
 * Source normal class (public modifier, same package as target, two member inner classes)
 * Features: member inner class (2 instances), instance method with required features
 */
public class SourceClass {
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "outerPrivateValue";

    // First member inner class (source feature)
    public class FirstMemberInnerClass {
        // Field for VariableDeclarationStatement (this.field + 2)
        private int innerField = 2;

        // Second member inner class (source_inner for method position)
        public class SecondMemberInnerClass {
            /**
             * Target instance method (final access, returns TargetClass type, source_inner position)
             * Precondition: contains target class parameter (TargetClass.InnerTargetClass)
             * @param targetParam Inner target class parameter
             * @return TargetClass instance
             */
            @InstanceMethodAnnotation // has_annotation feature
            public final TargetClass<String> targetMethod(TargetClass.InnerTargetClass targetParam) {
                // VariableDeclarationStatement (private modifier, this.field + 2, source pos)
                private int varDeclVar = FirstMemberInnerClass.this.innerField + 2; // this.field + 2

                // Variable call feature
                String varCall = targetParam.typeField;
                System.out.println(varCall + varDeclVar);

                // Access_outer_private feature (access outer class's private field)
                String accessedPrivate = SourceClass.this.outerPrivateField;
                targetParam.typeField = accessedPrivate;

                // Expression statement feature
                int exprVar = varDeclVar * 3;
                exprVar += 5; // Expression statement (modification)

                // Break statement feature
                breakLabel:
                for (int i = 0; i < 10; i++) {
                    if (i == exprVar) {
                        break breakLabel; // Labeled break statement
                    }
                }

                // Generic nested method (private modifier, exception throwing pos, Object return)
                private <T extends String> Object genericNestedMethod(T param) {
                    // Method features: 2 (literal), source, generic, instanceReference.methodName(arguments)
                    int literalTwo = 2;
                    SourceClass sourceInstance = new SourceClass();
                    
                    // InstanceReference.methodName(arguments)
                    FirstMemberInnerClass innerInstance = sourceInstance.new FirstMemberInnerClass();
                    int refResult = innerInstance.innerField + literalTwo;

                    // Exception throwing statements (pos requirement)
                    try {
                        if (param == null) {
                            throw new NullPointerException(); // no_new_exception (standard exception)
                        }
                    } catch (NullPointerException e) {
                        return "exception: " + e.getMessage();
                    }

                    return param + refResult; // Generic return type (Object)
                }

                // Invoke generic nested method
                genericNestedMethod(varCall);

                // Return TargetClass type (target class type)
                return targetParam.getOuterTargetInstance();
            }
        }
    }

    // Helper method to instantiate inner classes
    public void invokeTargetMethod() {
        FirstMemberInnerClass firstInner = new FirstMemberInnerClass();
        SecondMemberInnerClass secondInner = firstInner.new SecondMemberInnerClass();
        
        TargetClass<String> targetInstance = new TargetClass<>("typeParamValue");
        secondInner.targetMethod(targetInstance.new InnerTargetClass());
    }
}

/**
 * Target normal class (public modifier, javadoc + type parameter target_features)
 * Javadoc: target_feature
 * Type parameter: T (generic type)
 */
public class TargetClass<T> {
    private T typeParam;

    // Constructor for type parameter initialization
    public TargetClass(T typeParam) {
        this.typeParam = typeParam;
    }

    // Inner target class (target_inner context)
    public class InnerTargetClass {
        String typeField = "innerTargetField";

        // Getter for outer target instance
        public TargetClass<T> getOuterTargetInstance() {
            return TargetClass.this;
        }
    }

    // Getter for type parameter (access type parameter feature)
    public T getTypeParam() {
        return typeParam;
    }
}