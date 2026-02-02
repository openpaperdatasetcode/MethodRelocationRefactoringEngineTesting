package refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

// Annotation for has_annotation feature
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

// Parent class for source class extends feature
class ParentGenericClass<K> {
    protected K parentField;
}

// Source generic class (public modifier, same package as target, extends + static nested + anonymous inner class)
public class SourceClass<T extends Number> extends ParentGenericClass<T> {
    // Private outer field for access_outer_private feature
    private String outerPrivateField = "outerPrivate";

    // Static nested class (source feature)
    public static class StaticNestedClass<U> {
        // Nested static class (source_inner_rec for method position)
        public class NestedInnerClass<V> {
            // Instance field for access_instance_field feature
            private int instanceField = 5;

            // Target instance method (final access, Object return, source_inner_rec)
            // Precondition: method contains target class parameter
            @RefactorAnnotation // has_annotation feature
            public final Object targetMethod(TargetClass<String> targetParam) {
                // Access_outer_private feature (access outer generic class's private field)
                String outerPrivate = SourceClass.this.outerPrivateField;

                // Access_instance_field feature
                int accessedInstanceField = this.instanceField;

                // Variable call feature
                List<String> varCallList = new ArrayList<>();
                varCallList.add(outerPrivate);
                String varCall = varCallList.get(0);

                // Numbers:2, default modifier, Assignment expression
                default int assignVar = 2;
                assignVar = assignVar * 2; // Assignment feature with 2

                // Super constructor invocation (in anonymous inner class context)
                Runnable anonymousInner = new Runnable() {
                    {
                        super(); // Super constructor invocation
                    }
                    @Override
                    public void run() {}
                };

                // BreakStatement (private modifier, obj.field + 3, source pos)
                private int breakField = targetParam.staticNested.targetField + 3; // obj.field + 3
                for (int i = 0; i < 10; i++) {
                    if (i == breakField) {
                        break; // BreakStatement feature
                    }
                }

                // Accessor nested method (public modifier, object initialization pos, base type return)
                public int accessorMethod(int num) {
                    // Method features: 1 (literal), inner_class, accessor, instanceReference.methodName(arguments)
                    int literalOne = 1;
                    LocalInnerClass inner = new LocalInnerClass();
                    
                    // InstanceReference.methodName(arguments)
                    int result = inner.getField(literalOne); // accessor feature (getter)
                    
                    // Object initialization pos requirement
                    LocalInnerClass initInner = new LocalInnerClass();
                    initInner.setField(result); // accessor feature (setter)
                    
                    return initInner.getField(0); // Base type return (int)
                }

                // Local inner class for accessor feature
                class LocalInnerClass {
                    private int innerField;
                    
                    // Accessor (getter)
                    public int getField(int dummy) {
                        return innerField + dummy;
                    }
                    
                    // Accessor (setter)
                    public void setField(int value) {
                        innerField = value;
                    }
                }

                // No_new_exception (reuse existing exception, no explicit new)
                try {
                    if (targetParam == null) {
                        throw new NullPointerException();
                    }
                } catch (NullPointerException e) {
                    return "exception: " + e.getMessage();
                }

                accessorMethod(accessedInstanceField);
                return varCall + breakField;
            }
        }
    }

    // Anonymous inner class feature (source class feature)
    public void anonymousInnerFeature() {
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                StaticNestedClass<Integer> staticNested = new StaticNestedClass<>();
                NestedInnerClass<String> nested = staticNested.new NestedInnerClass<>();
                nested.targetMethod(new TargetClass<>());
            }
        };
        anonymousRunnable.run();
    }

    // Call method (source type, default modifier, exception handling pos, Object return)
    Object callMethod() {
        try {
            // Static target_feature
            int staticVal = StaticNestedClass.NestedInnerClass.class.hashCode();
            
            // ClassName::methodName feature
            SourceClass<String> sourceObj = new SourceClass<>();
            StaticNestedClass<String> staticNested = new StaticNestedClass<>();
            NestedInnerClass<String> nested = staticNested.new NestedInnerClass<>();
            Runnable r = () -> nested.targetMethod(new TargetClass<>()); // ClassName::methodName equivalent
            
            // Exception handling statements (pos requirement)
            return nested.targetMethod(new TargetClass<>());
        } catch (Exception e) {
            return e;
        }
    }
}

// Target generic class (public modifier, static nested class target_feature)
public class TargetClass<U> {
    // Static nested class target_feature
    public static class StaticNestedTargetClass {
        int targetField = 10; // obj.field for BreakStatement feature
    }

    // Instance of static nested class for field access
    public StaticNestedTargetClass staticNested = new StaticNestedTargetClass();
}